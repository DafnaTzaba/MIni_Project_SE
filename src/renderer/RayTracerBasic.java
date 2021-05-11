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

	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;

	/**
	 * constructor. use papa constructor
	 * 
	 * @param _scene
	 */
	public RayTracerBasic(Scene _scene) {
		super(_scene);

	}

	// Fixed to the size of the transfer of first rays to shading rays
	private static final double DELTA = 0.1;

	/**
	 * Looking for cuts between our foundation and the 3D model
	 */
	@Override
	public Color traceRay(Ray ray) {
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections != null) {
			GeoPoint closestPoint = ray.getClosestGeoPoint(intersections);
			return calcColor(closestPoint, ray);
		}
		// ray did not intersect any geometrical object
		return scene.background;
	}

	/**
	 * Method return the fill / ambient lighting color of the scene
	 * 
	 * @param point
	 * @return
	 */
	private Color calcColor(GeoPoint point, Ray ray) {
		return scene.ambientLight.getIntensity().add(point.geometry.getEmmission()).add(calcLocalEffects(point, ray));
		// add calculated light contribution from all light sources)

	}

	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = Util.alignZero(n.dotProduct(v));
		if (Util.isZero(nv))
			return Color.BLACK;
		Material material = intersection.geometry.getMaterial();
		int nShininess = material.nShininess;
		double kd = material.kD;
		double ks = material.kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = Util.alignZero(n.dotProduct(l));
			if (nl * nv > 0) {// sign(nl) == sing(nv)
				if (unshaded(l,n, intersection,lightSource )){
				Color lightlntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, nl, lightlntensity),
						calcSpecular(ks, l, n, nl, v, nShininess, lightlntensity));
			}
			}
		}
		return color;

	}

	private Color calcDiffusive(double kd, double ln, Color lightlntensity) {
		double factor = Math.abs(ln) * kd;
		return lightlntensity.scale(factor);
	}

	private Color calcSpecular(double ks, Vector l, Vector n, double ln, Vector v, int nShininess,
			Color lightlntensity) {
		Vector r = l.subtract(n.scale(ln * 2)).normalized();
		double factor = Math.max(0, (v.scale(-1)).dotProduct(r));
		return lightlntensity.scale(ks * Math.pow(factor, nShininess));
	}

	/**
	 * Non-shading test operation between point and light source For each
	 * intersection point with ray we shoot “shadow rays” and check if a light
	 * source is blocked by other objects – then we know it is in shadow.
	 * 
	 * @param l
	 * @param n= normal
	 * @param gp =our geometries
	 * @return
	 */
	private boolean unshaded(Vector l, Vector n, GeoPoint gp, LightSource lights) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
		Point3D point = gp.point.add(delta);
		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		
		if (intersections == null) 
			return true;
		double lightDistance = lights.getDistance(gp.point);
		for (GeoPoint geopoint : intersections) { //move on points intersection and find if And if we encounter a cut that is closer to the first ray the distance between the point between the light source - we will return a lie
		    double dis=geopoint.point.distance(gp.point);
			if (((dis - lightDistance)) <= 0)			
			return false;
		}
		return true;


	}
}
