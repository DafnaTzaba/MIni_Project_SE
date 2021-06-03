package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * abstract class for scene
 * @author da077
 *
 */
public abstract class RayTracerBase {
	
	
	protected Scene scene;
	public abstract Color traceRay(Ray ray,double alfa);
	
	public RayTracerBase(Scene _scene) {
		scene=_scene;
	}

}
