package geometries;

import static primitives.Util.*;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;

import java.util.List;
public class Tube extends Geometry {

	/**
	 * variable for Ray
	 */
	private Ray axisRay;
	private double radius;

	/**
	 * getter
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	public double getRadius() {
		return radius;
	}


	/**
	 * constructor
	 * @param ray variable for axisRay
	 * @param rad variable for radius
	 */
	public Tube(Ray ray, double rad) {
		axisRay = new Ray(ray.getP0(), ray.getDir());
		radius = rad;
	}

	@Override
	public Vector getNormal(Point3D point) {
		
		Point3D P0 = axisRay.getP0();
        Vector v = axisRay.getDir();

        Vector P0_P = point.subtract(P0); //create vector

        double t = v.dotProduct(P0_P);

        if (isZero(t)) { //ortogonal
            return P0_P.normalize();
        }

        Point3D o = P0.add(v.scale(t)); //to find the place of the point we want

        if (point.equals(o)) {
            throw new IllegalArgumentException("point cannot be on the tube axis");
        }

        Vector n = point.subtract(o).normalize();

        return n;

		
	}

	@Override
	public String toString() {
		return "axisRay: " + axisRay + "radius " + radius;
	}

	public List<Point3D> findIntsersections(Ray ray) {
		 Vector vAxis = axisRay.getDir();
	        Vector v = ray.getDir();
	        Point3D p0 = ray.getP0();

	        // At^2+Bt+C=0
	        double a = 0;
	        double b = 0;
	        double c = 0;

	        double vVa = alignZero(v.dotProduct(vAxis));
	        Vector vVaVa;
	        Vector vMinusVVaVa;
	        if (vVa == 0) // the ray is orthogonal to the axis
	            vMinusVVaVa = v;
	        else {
	            vVaVa = vAxis.scale(vVa);
	            try {
	                vMinusVVaVa = v.subtract(vVaVa);
	            } catch (IllegalArgumentException e1) { // the rays is parallel to axis
	                return null;
	            }
	        }
	        // A = (v-(v*va)*va)^2
	        a = vMinusVVaVa.lengthSquared();

	        Vector deltaP = null;
	        try {
	            deltaP = p0.subtract(axisRay.getP0());
	        } catch (IllegalArgumentException e1) { // the ray begins at axis P0
	            if (vVa == 0) // the ray is orthogonal to Axis
	                return List.of(ray.getPoint(radius));

	            double t = alignZero(Math.sqrt(radius * radius / vMinusVVaVa.lengthSquared()));
	            return t == 0 ? null : List.of(ray.getPoint(t));
	        }

	        double dPVAxis = alignZero(deltaP.dotProduct(vAxis));
	        Vector dPVaVa;
	        Vector dPMinusdPVaVa;
	        if (dPVAxis == 0)
	            dPMinusdPVaVa = deltaP;
	        else {
	            dPVaVa = vAxis.scale(dPVAxis);
	            try {
	                dPMinusdPVaVa = deltaP.subtract(dPVaVa);
	            } catch (IllegalArgumentException e1) {
	                double t = alignZero(Math.sqrt(radius * radius / a));
	                return t == 0 ? null : List.of(ray.getPoint(t));
	            }
	        }

	        // B = 2(v - (v*va)*va) * (dp - (dp*va)*va))
	        b = 2 * alignZero(vMinusVVaVa.dotProduct(dPMinusdPVaVa));
	        c = dPMinusdPVaVa.lengthSquared() - radius * radius;

	        // A*t^2 + B*t + C = 0 - lets resolve it
	        double discr = alignZero(b * b - 4 * a * c);
	        if (discr <= 0) return null; // the ray is outside or tangent to the tube

	        double doubleA = 2 * a;
	        double tm = alignZero(-b / doubleA);
	        double th = Math.sqrt(discr) / doubleA;
	        if (isZero(th)) return null; // the ray is tangent to the tube

	        double t1 = alignZero(tm + th);
	        if (t1 <= 0) // t1 is behind the head
	            return null; // since th must be positive (sqrt), t2 must be non-positive as t1

	        double t2 = alignZero(tm - th);

	        // if both t1 and t2 are positive
	        if (t2 > 0)
	            return List.of(ray.getPoint(t1), ray.getPoint(t2));
	        else // t2 is behind the head
	            return List.of(ray.getPoint(t1));


	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
