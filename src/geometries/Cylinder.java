package geometries;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
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

	//-------------constructor
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

	/**
	 * constructor
	 * @param d radius
	 * @param r ray
	 * @param h height
	 * @param c emession colour
	 */
	/*
	public Cylinder(Color c,double d, Ray r,double h) {
		super(c,d, r);
		height=h;
	}*/

	@Override
	public Vector getNormal(Point3D p) {
		if (p.equals((this.getAxisRay().getP0())) || p.subtract(this.getAxisRay().getP0()).normalize().equals(this.getAxisRay().getDir().normalize())) //if it is on the normal
			return this.getAxisRay().getDir().normalize();
		Vector v= new Vector(super.getNormal(p).getHead());
		if(v.length()==this.getRadius()) //if it is not on a base
			return v.normalize();
		return this.getAxisRay().getDir().normalize(); //if it is on the base

	}

	public String toString() {
		return super.toString() + "height: " + height;
	}
	
	public List<GeoPoint> findGeoIntsersections(Ray ray){
		return super.findGeoIntersections(ray);
	}

}
