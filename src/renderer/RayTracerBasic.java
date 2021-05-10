package renderer;

import java.util.List;

import elements.LightSource;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
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
	            return calcColor(closestPoint,ray);
	        }
	        //ray did not intersect any geometrical object
	        return scene.background;
	        }
	
	/**
	 * Method  return the fill / ambient lighting color of the scene
	 * @param point
	 * @return
	 */
	  private Color calcColor(GeoPoint point,Ray ray) {
	        return scene.ambientLight.getIntensity().add(point.geometry.getEmmission()).add(calcLocalEffects(point, ray));
	     // add calculated light contribution from all light sources)
	        

	    }

	  private Color calcLocalEffects(GeoPoint intersection,Ray ray) {
		  Vector v= ray.getDir();
		  Vector n=intersection.geometry.getNormal(intersection.point);
		  double nv=Util.alignZero(n.dotProduct(v));
		  if(Util.isZero(nv))
			  return Color.BLACK;
		  Material material=intersection.geometry.getMaterial();
		  int nShininess=material.nShininess;
		  double kd=material.kD;
		  double ks=material.kS;
		  Color color=Color.BLACK;
		  for(LightSource lightSource :scene.lights) {
			  Vector l=lightSource.getL(intersection.point);
			  double nl=Util.alignZero(n.dotProduct(l));
			  if(nl*nv>0) {//sign(nl) == sing(nv)
				  Color lightlntensity=lightSource.getIntensity(intersection.point);
				  color = color.add(calcDiffusive(kd, l, n, lightlntensity),
						  calcSpecular(ks, l, n, v, nShininess, lightlntensity));
						  }
		  }
						  return color;

	  }

	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightlntensity) {
		double factor=Math.abs(l.dotProduct(n))*kd;
		return lightlntensity.scale(factor);
	}

	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightlntensity) {
		Vector r=l.subtract(n.scale(l.dotProduct(n)*2)).normalized();
		double factor=Math.max(0, (v.scale(-1)).dotProduct(r));		
		return lightlntensity.scale(ks*Math.pow(factor,nShininess));
	}
}
