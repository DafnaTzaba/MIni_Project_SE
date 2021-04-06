package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Triangle extends Polygon {

	// constructor
	/*
	 * public Triangle(Point3D p1, Point3D p2, Point3D p3) { super(p1, p2, p3); }
	 */

	/**
	 * constructor
	 * 
	 * @param vertices= 3 points to build triangle
	 */
	public Triangle(Point3D... vertices) {
		super(vertices);
	}

	@Override
	public String toString() {
		return "Triangle{" + "vertices=" + vertices + ", plane=" + plane + '}';
	}
	
	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}
}
