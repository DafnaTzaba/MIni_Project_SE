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
	private static final double INITIAL_K = 1.0;
	

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
			GeoPoint closestPoint = findClosestIntersection(ray);
			return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
		}
		
	/**
	 * Method return the fill / ambient lighting color of the scene
	 * @param gp
	 * @param ray
	 * @return
	 */
	private Color calcColor(GeoPoint gp, Ray ray) {
		return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
				.add(scene.ambientLight.getIntensity());
	}
		
	

	
	/**
	 * recurse
	 * @param point
	 * @param ray
	 * @param level
	 * @param k
	 * @return
	 */
	private Color calcColor(GeoPoint point, Ray ray, int level, double k) {
		Color color= (point.geometry.getEmmission());
		color = color.add(calcLocalEffects(point, ray));

		return 1 == level ? color : color.add(calcGlobalEffects(point, ray, level, k));

	}
	/**
	 * Calculating global lighting- part of the recurse
	 * @param geopoint
	 * @param ray
	 * @param level
	 * @param k
	 * @return
	 */
	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k) {
		Color color = Color.BLACK;
		Material material = geopoint.geometry.getMaterial();
		double kr = material.kR, kkr = k * kr;
		Vector normal=geopoint.geometry.getNormal(geopoint.point).normalized();
		if (kkr > MIN_CALC_COLOR_K) {
		Ray reflectedRay = constructReflectedRay(normal, geopoint.point, ray);
		GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
		if(reflectedPoint==null)return color;
		color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));
		}
		double kt = material.kT, kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
		Ray refractedRay = constructRefractedRay(normal, geopoint.point, ray);
		GeoPoint refractedPoint = findClosestIntersection(refractedRay);
		if(refractedPoint==null)return color;
		color = color.add(calcColor(refractedPoint, refractedRay, level-1, kkt).scale(kt));
		}
		return color;
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
	 * Building a beam with sliding for reflection
	 * @param v vector
	 * @param n normal
	 * @param pt  start point
	 * @return the new ray
	 */
	 public Ray constructReflectedRay(Vector n,Point3D pt, Ray ray)
	 {
		 Vector v= ray.getDir();
		 double vn=(v.dotProduct(n));
		 Vector vnn=n.scale(vn);
		 Vector r=v.subtract(vnn.scale(2));
		 Vector delta = n.scale(n.dotProduct(n) > 0 ? DELTA : -DELTA);
			Point3D point = pt.add(delta);
			Ray MoveRay = new Ray(point, r);
		 
		 return MoveRay;
	
	 }
	 
	 /**
		 * Building a beam with sliding for refrected
		 * @param v vector
		 * @param n normal
		 * @param pt  start point
		 * @return the new ray
		 */
	 public Ray constructRefractedRay(Vector n,Point3D pt, Ray ray)
	 {
		 Vector v= ray.getDir();
		 Vector delta = n.scale(n.dotProduct(v) > 0 ? DELTA : -DELTA);
			Point3D point = pt.add(delta);
			Ray MoveRay = new Ray(point, v);
		 return MoveRay;
	 }
	 /**
	  * Calculate the closest cut from one place
	  * @param ray
	  * @return The closest point
	  */
	 private GeoPoint findClosestIntersection(Ray ray)
	 {
		 List<GeoPoint> intersection=scene.geometries.findGeoIntersections(ray);
		 if(intersection==null)
			 return null;
		 GeoPoint close=ray.getClosestGeoPoint(intersection);
		 return close;
	 
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
			else
				if(!Util.isZero(geopoint.geometry.getMaterial().kT))
					return false;
		}
		return true;


	}
	
	
}
