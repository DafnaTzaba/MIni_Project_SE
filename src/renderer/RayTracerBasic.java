package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase {

	/**
	 * constructor. use papa constructor
	 * @param _scene
	 */
	public RayTracerBasic(Scene _scene) {
		super(_scene);
		
	}

	/**
	 * Looking for cuts between our foundation and the 3D model
	 */
	@Override
	public Color traceRay(Ray ray) {
		  List<Point3D> intersections = scene.geometries.findIntsersections(ray);
	        if (intersections != null) {
	            Point3D closestPoint = ray.findClosestPoint(intersections);
	            return calcColor(closestPoint);
	        }
	        //ray did not intersect any geometrical object
	        return scene.background;	
	        }
	
	/**
	 * Method  return the fill / ambient lighting color of the scene
	 * @param point
	 * @return
	 */
	  private Color calcColor(Point3D point) {
	        return scene.ambientLight.getIntensity();
	    }

}
