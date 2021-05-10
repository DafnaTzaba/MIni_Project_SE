package elements;


import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

	private Point3D position;
	private double kC;
	private double kL;
	private double kQ;
	
	public PointLight setKc(double kc) {
		kC=kc;
		return this;
	}
	public PointLight setKl(double kl) {
		kL=kl;
		return this;

	}	
	public PointLight setKq(double kq) {
		kQ=kq;
		return this;

	}
	
	
	/**
	 * Constructor
	 * @param co-color that send to intensity in Light 
	 * @param pos-Point3D for position
	 * @param c-kC
	 * @param l-kL
	 * @param q-kQ
	 */
	public PointLight(Color co,Point3D pos)//,double c,double l,double q) {
	{
		super(co);
		position=pos;
		kC=1;
		kL=0;
		kQ=0;
	}

	/**
	 * return Il-The intensity of the light at the point 
	 */
	@Override
	public Color getIntensity(Point3D p) {
		Color I0=super.getIntensity();
		double distance=position.distance(p);
		double mechane=kC+kL*distance+kQ*position.distanceSquared(p);
		Color Il=I0.scale((double)1/mechane);
		return Il;
	}
	
	/**
	 * func that return normal vector between the 2 points. 
	 */
	@Override
	public Vector getL(Point3D p) {
		if(p.equals(position)) //if the point and the position of the light are equals, there is no distance
			return null;
		return p.subtract(position).normalize();
	}



}
