package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere implements Geometry {
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

		return null;
	}

}
