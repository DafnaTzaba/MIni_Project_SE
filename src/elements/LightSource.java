package elements;

import java.util.Vector;

import primitives.Color;
import primitives.Point3D;

/**
 * Light source source interface
 * @author Herout Rozilyo
 *
 */
public interface LightSource {
	/**
	 * 
	 * @param p
	 * @returns the light intensity at the point
	 */
	public Color getIntensity(Point3D p);
	/**
	 * 
	 * @param p
	 * @return Vector that shows us the point of view (like a beam)
	 */
	public Vector getL(Point3D p);

}