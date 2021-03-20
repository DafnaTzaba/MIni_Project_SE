package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

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

		return null;
	}

	@Override
	public String toString() {
		return "axisRay: " + axisRay + "radius " + radius;
	}

}
