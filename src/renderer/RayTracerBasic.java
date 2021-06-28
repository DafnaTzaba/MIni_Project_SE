package renderer;

import java.util.LinkedList;
import java.util.List;
import java.lang.Math;
import elements.LightSource;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import scene.Scene;
import geometries.Intersectable;
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
	public Color traceRay(Ray ray, double alfa) {
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ? scene.background : calcColor(closestPoint, ray, alfa);
	}

	/**
	 * Method return the fill / ambient lighting color of the scene
	 * 
	 * @param gp
	 * @param ray
	 * @return
	 */
	private Color calcColor(GeoPoint gp, Ray ray, double alfa) {
		return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K, alfa).add(scene.ambientLight.getIntensity());
	}

	/**
	 * recurse
	 * 
	 * @param point
	 * @param ray
	 * @param level
	 * @param k
	 * @return
	 */
	private Color calcColor(GeoPoint point, Ray ray, int level, double k, double alfa) {
		Color color = (point.geometry.getEmmission());
		color = color.add(calcLocalEffects(point, ray, k, alfa));

		return 1 == level ? color : color.add(calcGlobalEffects(point, ray, level, k, alfa));

	}

	/**
	 * Calculating global lighting- part of the recurse
	 * 
	 * @param geopoint
	 * @param ray
	 * @param level
	 * @param k
	 * @return
	 */
	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k, double alfa) {
		Color color = Color.BLACK;
		Material material = geopoint.geometry.getMaterial();
		double kr = material.kR, kkr = k * kr;
		Vector normal = geopoint.geometry.getNormal(geopoint.point);
		if (kkr > MIN_CALC_COLOR_K) {
			Ray reflectedRay = constructReflectedRay(normal, geopoint.point, ray);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			if (reflectedPoint != null)
				color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr, alfa).scale(kr));
			else
				color = color.add(scene.background.scale(kr));
		}
		double kt = material.kT, kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(normal, geopoint.point, ray);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			if (refractedPoint != null)
				color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt, alfa).scale(kt));
			else
				color = color.add(scene.background.scale(kt));
		}
		return color;
	}

	private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k, double alfa) {
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
				double ktr = callTransparency(lightSource, l, n, intersection, alfa);
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
	 * 
	 * @param v  vector
	 * @param n  normal
	 * @param pt start point
	 * @return the new ray
	 */
	public Ray constructReflectedRay(Vector n, Point3D pt, Ray ray) {
		Vector v = ray.getDir();
		double vn = (v.dotProduct(n));
		Vector vnn = n.scale(vn);
		Vector r = v.subtract(vnn.scale(2));

		Ray MoveRay = new Ray(pt, r, n);

		return MoveRay;

	}

	/**
	 * Building a beam with sliding for refrected
	 * 
	 * @param v  vector
	 * @param n  normal
	 * @param pt start point
	 * @return the new ray
	 */
	public Ray constructRefractedRay(Vector n, Point3D pt, Ray ray) {

		Vector v = ray.getDir();
		Ray MoveRay = new Ray(pt, v, n);
		return MoveRay;

	}

	/**
	 * Calculate the closest cut from one place
	 * 
	 * @param ray
	 * @return The closest point
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intersection = scene.geometries.findGeoIntersections(ray);
		if (intersection == null)
			return null;
		GeoPoint close = ray.getClosestGeoPoint(intersection);
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
		for (GeoPoint geopoint : intersections) { // move on points intersection and find if And if we encounter a cut
													// that is closer to the first ray the distance between the point
													// between the light source - we will return a lie
			if (Util.alignZero(geopoint.point.distance(gp.point) - lightDistance) <= 0
					&& Util.isZero(geopoint.geometry.getMaterial().kT))
				return false;

		}
		return true;
	}

	private double transparency(LightSource light, Vector l, Vector n, GeoPoint geoPoint, Point3D p0) {

		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geoPoint.point, lightDirection, n); // refactored ray head move
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null)
			return 1.0;
		 double lightDistance =0;
		if(p0==null)
		{
			lightDistance = light.getDistance(geoPoint.point);
		}
		
		else lightDistance = p0.distance(geoPoint.point);

		double ktr = 1.0;

		for (GeoPoint gp : intersections) { // move on points intersection and find if And if we encounter a cut that is
											// closer to the first ray the distance between the point between the light
											// source - we will return a lie
			if (Util.alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
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
	 * @param light=   source light
	 * @param l  =the beam between the source light to the point on the geometry
	 * @param n = normal
	 * @param geopoint
	 * @param angle =angle between the point on the geometry and l. in order to calculate the radius of the circle around the source light.
	 */
	private double callTransparency(LightSource light, Vector l, Vector n, GeoPoint geopoint, double angle) {
		Point3D p0 = light.getPosition(); // position of the light
		if (p0 == null || angle == 0) // if the light==direction light
			return transparency(light, l, n, geopoint,p0);

		// calculate radius of the circle
		double distanceL = p0.distance(geopoint.point);
		double radius = distanceL * Math.tan(Math.toRadians(angle));
		double sumKtr = 0;

		// find points on our circle at the same plane.
		Vector v = new Vector(-l.getHead().getY(), l.getHead().getX(), 0).normalized(); // (-y,x,0)
		Vector w = l.crossProduct(v);

		return AdaptiveSuperamplingcallTransparency(light, n, radius, p0, v, w, geopoint, 2 * PI, 1);
		
//		double a= AdaptiveSuperamplingcallTransparency(light, n, radius, p0, v, w, geopoint, 2 * PI, 1);
//		if(a!=-1)
//			return a;
//
//        
//        //send beam to calculate and sum the ktr
//   for(int i=0; i<81;i++) {
//	   double t = 2 * PI * Math.random();
//       double r = radius * Math.random();
//
//     
//       double alpha = r * Math.cos(t);
//       double beta = r * Math.sin(t);
//       if(alpha!=0&&beta!=0)
//       {       
//       Point3D randomPoint = p0.add(v.scale(alpha).add(w.scale(beta)));
//       Vector randomL = geopoint.point.subtract(randomPoint).normalized();
//       sumKtr += transparency(light, randomL, n, geopoint,p0);
//       }
//       else
//    	   i--;
//   }
//        return sumKtr/81;
	}

	/**
	 * the first func of adaptive super sampling. Department the circle to 4 part
	 * and find if all the transparency at all parts are same. if not call to
	 * recursive adaptive super sampling.
	 * 
	 * @param light         =light source
	 * @param n             = normal to the circle
	 * @param radius        = radius of the circle around the light
	 * @param positionLight = position of the light
	 * @param v             = vector at the circle
	 * @param w             =vector at the circle
	 * @param geopoint      = our body
	 * @param angle         = Opening angle
	 * @param depth         = depth of the recurse
	 * @return
	 */
	private double AdaptiveSuperamplingcallTransparency(LightSource light, Vector n, double radius,
			Point3D positionLight, Vector v, Vector w, GeoPoint geopoint, double angle, int depth) {

		double ktr = 0; // sum ktr
		List<Point3D> circlePoint = new LinkedList<>(); // list of 4 points in a circle
		for (int i = 0; i < 4; i++) {
			double t = 0.25 * PI + 0.5 * PI * i; // our angle in circle. the first point in angle PI/4
if(Math.cos(t)!=0&&Math.sin(t)!=0) {
			// calculate a point in circle
			double alpha = radius * Math.cos(t);
			double beta = radius * Math.sin(t);

			Point3D point = positionLight.add(v.scale(alpha).add(w.scale(beta)));

			//Vector newL = geopoint.point.subtract(point).normalized(); // ray between our point at the circle and the
																		// point on our body
			circlePoint.add(i, point); // add to our list
			//ktr += transparency(light, newL, n, geopoint,point); // sum ktr of all points
}
		}
		circlePoint.add(4, positionLight);
		return AdaptiveSquare(circlePoint, light, n, positionLight, geopoint, 6); // call to recurse adaptive sampling
	}

	/**
	 * function recursive that calculate ktr at 4 points in the circle. The function
	 * check if the ktr at 4 points are same. if equals return ktr. else Department
	 * the square to 4 piece and send them to adaptive super sampling.
	 * 
	 * @param points        = lists of the 4 points at the circle
	 * @param light         = source light
	 * @param n             = normal to the circle
	 * @param positionLight = position of light source
	 * @param geopoint      = our body
	 * @param depth         = depth of the recurse
	 * @return
	 */
	private double AdaptiveSquare(List<Point3D> points, LightSource light, Vector n, Point3D positionLight,
			GeoPoint geopoint, int depth) {

		double ktr = 0; // locale ktr
		double sumKtr = 0;

		boolean flag = true; // check if we need to do recursive call or not

		// points from our list
		Point3D A = points.get(0);
		Point3D B = points.get(1);
		Point3D C = points.get(2);
		Point3D D = points.get(3);
		Point3D E = points.get(4); //center circle

		
		for (Point3D point : points) { // move on the list

			Vector SquareL = geopoint.point.subtract(point).normalized(); // ray from our point to the body
			if (!point.equals(A)) { // if this the first point we check
				double kt = transparency(light, SquareL, n, geopoint,point); // ktr point
				sumKtr += kt; // sum ktr
				if (kt != ktr) { // if all the ktr are same. if not equal flag=false
					flag = false;
				}

			} else { // the first time
				ktr = transparency(light, SquareL, n, geopoint,point);
				sumKtr += ktr;
			}
		}

		if (!flag && depth > 0) // if all the points are not same and depth>0
		{

			Point3D middleAC = new Point3D(0.5 * (A.getX() + C.getX()), 0.5 * (A.getY() + C.getY()),
					0.5 * (A.getZ() + C.getZ()));
			Point3D middleAB = new Point3D(0.5 * (A.getX() + B.getX()), 0.5 * (A.getY() + B.getY()),
					0.5 * (A.getZ() + B.getZ()));
			Point3D middleAD = new Point3D(0.5 * (A.getX() + D.getX()), 0.5 * (A.getY() + D.getY()),
					0.5 * (A.getZ() + D.getZ()));
			Point3D middleDC = new Point3D(0.5 * (D.getX() + C.getX()), 0.5 * (D.getY() + C.getY()),
					0.5 * (D.getZ() + C.getZ()));
			Point3D middleBC = new Point3D(0.5 * (B.getX() + C.getX()), 0.5 * (B.getY() + C.getY()),
					0.5 * (B.getZ() + C.getZ()));

			// create 4 lists and insert to them 4 points of a quarter of the square
			List<Point3D> square1 = new LinkedList<>();
			List<Point3D> square2 = new LinkedList<>();
			List<Point3D> square3 = new LinkedList<>();
			List<Point3D> square4 = new LinkedList<>();
			square1.add(0, middleAB);
			square1.add(1, B);
			square1.add(2, middleBC);
			square1.add(3, middleAC);
			Point3D center1 = new Point3D(0.5 * (middleAB.getX() + middleBC.getX()), 0.5 * (middleAB.getY() + middleBC.getY()),
					0.5 * (middleAB.getZ() + middleBC.getZ()));
			square1.add(4, center1);

			square2.add(0, A);
			square2.add(1, middleAB);
			square2.add(2, middleAC);
			square2.add(3, middleAD);
			Point3D center2 = new Point3D(0.5 * (middleAC.getX() + C.getX()), 0.5 * (middleAC.getY() + C.getY()),
					0.5 * (middleAC.getZ() + C.getZ()));
			square2.add(4, center2);
			
			
			square3.add(0, middleAD);
			square3.add(1, middleAC);
			square3.add(2, middleDC);
			square3.add(3, D);
			Point3D center3 = new Point3D(0.5 * (middleAD.getX() + middleDC.getX()), 0.5 * (middleAD.getY() + middleDC.getY()),
					0.5 * (middleAD.getZ() + middleDC.getZ()));
			square3.add(4, center3);
			
			

			square4.add(0, middleAC);
			square4.add(1, middleBC);
			square4.add(2, C);
			square4.add(3, middleDC);
			Point3D center4 = new Point3D(0.5 * (A.getX() + middleAC.getX()), 0.5 * (A.getY() + middleAC.getY()),
					0.5 * (A.getZ() + middleAC.getZ()));
			square4.add(4, center4);
			
			

			// call recursive to 4 part of the square and sum the result
			return (AdaptiveSquare(square1, light, n, positionLight, geopoint, depth - 1)
					+ AdaptiveSquare(square2, light, n, positionLight, geopoint, depth - 1)
					+ AdaptiveSquare(square3, light, n, positionLight, geopoint, depth - 1)
					+ AdaptiveSquare(square4, light, n, positionLight, geopoint, depth - 1)) * 0.25;

		} else // if we dont do recursive call return ktr
			return sumKtr * 0.2;

	}

}
