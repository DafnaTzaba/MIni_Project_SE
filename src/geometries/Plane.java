package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

public class Plane extends Geometry {
	/**
	 * point on the plane and normal
	 */
	private Point3D q0;
	private Vector normal;

	/**
	 * getter
	 */
	public Point3D getQ0() {
		return q0;
	}

	public Vector getNormal() {
		return normal;
	}

	// constructor

	/**
	 * constructor
	 * 
	 * @param v1 first point to q0
	 * @param v2 second point to create vector
	 * @param v3 third point to create second vector at the plane
	 */
	public Plane(Point3D v1, Point3D v2, Point3D v3) {
		q0 = new Point3D(v1.getX(), v1.getY(), v1.getZ());
		Vector u1 = v2.subtract(v1);
		Vector u2 = v3.subtract(v2);

		normal = u1.crossProduct(u2).normalize();
	}

	/**
	 * constructor
	 * 
	 * @param point   variable to q0
	 * @param normal_ variable for normal to the plane
	 */
	public Plane(Point3D point, Vector normal_) {
		q0 = new Point3D(point.getX(), point.getY(), point.getZ());
		normal = new Vector(normal_.getHead());
	}

	@Override
	public Vector getNormal(Point3D point) {
		return normal;
	}

	@Override
	public String toString() {
		return "normal:" + normal + "point:" + q0;
	}

	public List<Point3D> findIntsersections(Ray ray) {
		Point3D P0 = ray.getP0();
		Vector v = ray.getDir();

		Vector n = normal;

		if (q0.equals(P0)) {
			return null;
		}

		Vector P0_Q0 = q0.subtract(P0);

		// numerator
		double nP0Q0 = alignZero(n.dotProduct(P0_Q0));

		// ray is lying in the plane axis. because the vectors are ortogonal
		if (isZero(nP0Q0)) {
			return null;
		}

		// denominator
		double nv = alignZero(n.dotProduct(v));

		// ray is lying in the plane axis
		if (isZero(nv)) {
			return null;
		}

		double t = alignZero(nP0Q0 / nv);

		if (t <= 0) {
			return null;
		}

		Point3D point = ray.getPoint(t);

		return List.of(point);
	}///

	/**
	 * return intersection points between the ray and our geometry
	 */
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		List<Point3D> findInt = findIntsersections(ray);
		if (findInt == null)
			return null;
		List<GeoPoint> ListGeoPoint = List.of(new GeoPoint(this, findInt.get(0)));

		return ListGeoPoint;
	}
}
