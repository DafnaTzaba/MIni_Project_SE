package elements;

import primitives.Color;
import primitives.Point3D;

/**
 * A department that realizes for us the sources of light in the scene
 * @author Herout Rozilyo
 *
 */
 abstract class Light 
 {
	 private Color intensity=Color.BLACK;
	 
	 /**
	  * constructor of the class
	  * @param co- The color with which we want to initialize the field intensity
	  */
	 protected Light(Color co)
	 {
		 intensity=co;
	 }
/**
 * to get the intensity
 * @return the color intensity
 */
	public Color getIntensity() {
		return intensity;
	}



}
