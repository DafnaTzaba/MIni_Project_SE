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
		// TODO Auto-generated method stub
		return null;
	}

	public Vector getL(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

}
