package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight {
/**
 * Constructor
 * @param co-color that send to intensity in Light
 * @param dir-Direction Vector
 * @param pos-Point3D that send to position in PointLight
 * @param c-double that send to kC in PointLight
 * @param l-double that send to kL in PointLight
 * @param q-double that send to kQ in PointLight
 */
	public SpotLight(Color co,Point3D pos,Vector dir,double c,double l,double q) {
		super(co,pos,c,l,q);
		direction=dir.normalized();
	}
	
	/**
	 * return Il-The intensity of the light at the point 
	 */
	public Color getIntensity(Point3D p) {
		
		Color Il=super.getIntensity(p);
		return Il;
	}
	
	private Vector direction;

}
