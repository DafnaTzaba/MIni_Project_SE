package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
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
		  List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
	        if (intersections != null){
	        	GeoPoint closestPoint = ray.getClosestGeoPoint(intersections);
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
	  private Color calcColor(GeoPoint point) {
	        return scene.ambientLight.getIntensity().add(point.geometry.getEmmission());
	    }

}
