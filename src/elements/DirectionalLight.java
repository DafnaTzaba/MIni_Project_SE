package elements;



import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

	private Vector direction; 
	
	/**
	 * Constructor 
	 * @param co- color that send to intensity in Light 
	 * @param dir-Direction Vector
	 */
	public DirectionalLight(Color co, Vector dir) {
		super(co);
		direction=dir.normalized();
	}

	public Color getIntensity(Point3D p) {
		Color I0=super.getIntensity();
		return I0;
		}

	/**
	 * like sun. return the direction of the light. 
	 */
	@Override
	public Vector getL(Point3D p) {
		return direction; //return normal direction
	}

	/**
	 * return POSITIVE_INFINITY. because its like the sun
	 */
	@Override
	public double getDistance(Point3D point) {		
		return Double.POSITIVE_INFINITY;
	}

	
}
