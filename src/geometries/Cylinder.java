package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {

	/**
	 * have height variable and 2 variable from Tube class(axisRay and radius)
	 */
	private double height;

	/**
	 * getter
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * constructor
	 * 
	 * @param ray  variable for exisRay
	 * @param rad  variable for radius
	 * @param heig variable for height
	 */
	public Cylinder(Ray ray, double rad, double heig) {
		super(ray, rad);
		height = heig;
	}

	@Override
	public Vector getNormal(Point3D point) {

		return null;
	}

	@Override
	public String toString() {
		return super.toString() + "height: " + height;
	}

}
