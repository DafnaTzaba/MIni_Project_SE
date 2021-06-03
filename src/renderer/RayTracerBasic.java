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
	private static final double PI = 3.141592653589793;
	

	/**
	 * constructor. use papa constructor
	 * 
	 * @param _scene
	 */
	public RayTracerBasic(Scene _scene) {
		super(_scene);

	}

	

	/**
	 * Looking for cuts between our foundation and the 3D model
	 */
	@Override
	public Color traceRay(Ray ray,double alfa) {
			GeoPoint closestPoint = findClosestIntersection(ray);
			return closestPoint == null ? scene.background : calcColor(closestPoint, ray,alfa);
		}
		
	/**
	 * Method return the fill / ambient lighting color of the scene
	 * @param gp
	 * @param ray
	 * @return
	 */
	private Color calcColor(GeoPoint gp, Ray ray,double alfa) {
		return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K,alfa)
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
	private Color calcColor(GeoPoint point, Ray ray, int level, double k,double alfa) {
		Color color= (point.geometry.getEmmission());
		color = color.add(calcLocalEffects(point, ray,k,alfa));

		return 1 == level ? color : color.add(calcGlobalEffects(point, ray, level, k,alfa));

	}
	/**
	 * Calculating global lighting- part of the recurse
	 * @param geopoint
	 * @param ray
	 * @param level
	 * @param k
	 * @return
	 */
	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k,double alfa) {
		Color color = Color.BLACK;
		Material material = geopoint.geometry.getMaterial();
		double kr = material.kR, kkr = k * kr;
		Vector normal=geopoint.geometry.getNormal(geopoint.point);
		if (kkr > MIN_CALC_COLOR_K) {
		Ray reflectedRay = constructReflectedRay(normal, geopoint.point, ray);
		GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
		if(reflectedPoint!=null)
		color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr,alfa).scale(kr));
		else
			color=color.add(scene.background.scale(kr));
		}
		double kt = material.kT, kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
		Ray refractedRay = constructRefractedRay(normal, geopoint.point, ray);
		GeoPoint refractedPoint = findClosestIntersection(refractedRay);
		if(refractedPoint!=null)
		color = color.add(calcColor(refractedPoint, refractedRay, level-1, kkt,alfa).scale(kt));
		else
			color=color.add(scene.background.scale(kt));
		}
		return color;
		}


	private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k,double alfa) {
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
				double ktr = callTransparency(lightSource, l, n, intersection,alfa);
				if (ktr * k > MIN_CALC_COLOR_K) {				
				Color lightlntensity = lightSource.getIntensity(intersection.point).scale(ktr);
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
		 
			Ray MoveRay = new Ray(pt, r,n);
		 
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
			Ray MoveRay = new Ray(pt, v,n);
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
		Ray lightRay = new Ray(gp.point, lightDirection, n); // refactored ray head move
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) 
			return true;
		double lightDistance = lights.getDistance(gp.point);
		for (GeoPoint geopoint : intersections) { //move on points intersection and find if And if we encounter a cut that is closer to the first ray the distance between the point between the light source - we will return a lie
			if (Util.alignZero(geopoint.point.distance(gp.point) - lightDistance) <= 0 &&Util.isZero(geopoint.geometry.getMaterial().kT))
					return false;
			
		}
		return true;
	}
	
	private double transparency(LightSource light, Vector l, Vector n, GeoPoint geoPoint) {
		
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geoPoint.point, lightDirection, n); // refactored ray head move
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) 
			return 1.0;
		double lightDistance = light.getDistance(geoPoint.point);
		double ktr = 1.0;

		for (GeoPoint gp : intersections) { //move on points intersection and find if And if we encounter a cut that is closer to the first ray the distance between the point between the light source - we will return a lie
			if (Util.alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0 ){
				ktr *= gp.geometry.getMaterial().kT;
			     if (ktr < MIN_CALC_COLOR_K) 
				  return 0.0;
			}
		}
		return ktr;
	}
	
	
	//////// Soft Shadow////////////////////////////
	 /**
     * the function return average of the transparency factor between the rays. 
     * create the beam of rays and calculate the
     * @param light= source light
     * @param l =the beam between the source light to the point on the geometry
     * @param n = normal
     * @param geopoint 
     * @param angle =angle between the point on the geometry and l. in order to calculate the radius of the circle around the source light. 
     */
    private double callTransparency(LightSource light, Vector l, Vector n, GeoPoint geopoint,double angle){
        Point3D p0=light.getPosition(); //position of the light
        if(p0==null||angle==0)  //if the light==direction light
         return transparency(light, l, n, geopoint) ; 
        
        //calculate radius of the circle
        double distanceL=p0.distance(geopoint.point); 
        double radius=distanceL* Math.tan(Math.toRadians(angle));
        double sumKtr=0;

        //find points on our circle at the same plane. 
        Vector v=new Vector(-l.getHead().getY(),l.getHead().getX(),0).normalized();
        Vector w=l.crossProduct(v);
        
        //send beam to calculate and sum the ktr
   for(int i=0; i<81;i++) {
	   double t = 2 * PI * Math.random();
       double r = radius * Math.random();

       double alpha = r * Math.cos(t);
       double beta = r * Math.sin(t);

       Point3D randomPoint = p0.add(v.scale(alpha).add(w.scale(beta)));
       Vector randomL = geopoint.point.subtract(randomPoint).normalized();
       sumKtr += transparency(light, randomL, n, geopoint);
   }
        return sumKtr/81;
    }



	
	
	
}
