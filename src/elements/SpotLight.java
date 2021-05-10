package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

public class SpotLight extends PointLight {
	private Vector direction;

	
/**
 * Constructor
 * @param co-color that send to intensity in Light
 * @param dir-Direction Vector
 * @param pos-Point3D that send to position in PointLight
 * @param c-double that send to kC in PointLight=1
 * @param l-double that send to kL in PointLight=0
 * @param q-double that send to kQ in PointLight=0
 */
	public SpotLight(Color co,Point3D pos,Vector dir)//,double c,double l,double q) {
	{
		super(co,pos);//,c,l,q);
		direction=dir.normalized();
	}
	
	/**
	 * return Il-The intensity of the light at the point 
	 */
	public Color getIntensity(Point3D p) {
		double l= direction.dotProduct(super.getL(p));		
		if(Util.isZero(l))
			return Color.BLACK;
		Color Il=super.getIntensity(p);
		return Il.scale(Math.max(0,l)); // maximum between 0 and the dot- product between direction and vector points.
	}
	
	/**
	 * return distance between the position of lights and our points
	 */
	public double getDistance(Point3D point) {
		return super.getDistance(point);
	}

	

}
