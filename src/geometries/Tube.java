package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;
public class Tube implements Geometry {

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

}
