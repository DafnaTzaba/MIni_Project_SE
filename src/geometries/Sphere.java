package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
public class Sphere extends Geometry {
	/**
	 * point to center of the sphere and radius
	 */
	private Point3D center;
	private double radius;

	/**
	 * getter
	 */
	public Point3D getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	/**
	 * constructor
	 * 
	 * @param point variable for the center point
	 * @param rad   variable for the radius
	 */
	public Sphere(Point3D point, double rad) {
		center = new Point3D(point.getX(), point.getY(), point.getZ());
		radius = rad;
	}

	@Override
	public String toString() {
		return "center Sphere: " + center + "radius: " + radius;
	}

	@Override
	public Vector getNormal(Point3D point) {

		Vector radNormal= point.subtract(center);
		return radNormal.normalize();
	}
	
	
	
	public List<Point3D> findIntsersections(Ray ray) {
		Point3D P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(center)) {
            return List.of(center.add(v.scale(radius)));
        }

        Vector U = center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        // no intersections : the ray direction is above the sphere
        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0) {

            Point3D P1 =ray.getPoint(t1);
            Point3D P2 =ray.getPoint(t2);
            return List.of(P1, P2);
        }
        if (t1 > 0) {

            Point3D P1 =ray.getPoint(t1);
            return List.of(P1);
        }
        if (t2 > 0) {
            Point3D P2 =ray.getPoint(t2);
            return List.of(P2);
        }
        return null;
	}

	/**
	 * return intersection points between the ray and our geometry
	 */
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
	
		List<Point3D> findInter= findIntsersections(ray);
		if(findInter==null)
			return null;
		List<GeoPoint> geopoint = List.of(new GeoPoint(this,findInter.get(1))) ;
		for(int i=1;i<findInter.size();i++)
		{
			geopoint.add(new GeoPoint(this,findInter.get(i)));
		}			
		return geopoint;
	}

}
