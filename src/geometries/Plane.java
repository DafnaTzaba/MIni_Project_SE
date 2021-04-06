package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry {
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
		Vector u1 = new Vector(v2);
		Vector u2 = new Vector(v3);
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
		return null;
	}

	@Override
	public String toString() {
		return "normal:" + normal + "point:" + q0;
	}

}
