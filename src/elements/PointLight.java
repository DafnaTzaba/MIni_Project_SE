package elements;

import java.util.Vector;

import primitives.Color;
import primitives.Point3D;

public class PointLight extends Light implements LightSource {

	private Point3D position;
	private double kC;
	private double kL;
	private double kQ;
	
	/**
	 * Constructor
	 * @param co-color that send to intensity in Light 
	 * @param pos-Point3D for position
	 * @param c-kC
	 * @param l-kL
	 * @param q-kQ
	 */
	public PointLight(Color co,Point3D pos,double c,double l,double q) {
		super(co);
		position=pos;
		kC=c;
		kL=l;
		kQ=q;
	}

	/**
	 * return Il-The intensity of the light at the point 
	 */
	public Color getIntensity(Point3D p) {
		Color I0=super.getIntensity();
		double distance=position.distance(p);
		double mechane=kC+kL*distance+kQ*position.distanceSquared(p);
		Color Il=I0.scale((double)1/mechane);
		return Il;
	}

	
	public Vector getL(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

}
