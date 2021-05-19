package primitives;
import static primitives.Util.*;

import geometries.Geometry;
import geometries.Intersectable.GeoPoint;

import java.util.List;
public class Ray {
	
	/**
	 * ray class. two variables- point start and vector
	 */
	private Point3D p0;
	private Vector dir;

	/**
	 * getter
	 */
	public Point3D getP0() {
		return p0;
	}

	public Vector getDir() {
		return dir;
	}
	
	// Fixed to the size of the transfer of first rays to shading rays
		private static final double DELTA = 0.1;

//Constructor///

	/**
	 * constructor
	 * @param a point for the start point(p0)
	 * @param b vector for dir
	 */
	public Ray(Point3D a, Vector b) {
		p0 = a;
		if (b.length() == 1)
			dir = b;
		else
			dir = b.normalized();
	}

	public Ray(Point3D head, Vector direction, Vector normal) {
		if(direction.dotProduct(normal)>0)
			p0=head.add(normal.scale(DELTA));
		if(direction.dotProduct(normal)<0)
			p0=head.add(normal.scale(DELTA*-1));
		if(Util.isZero(direction.dotProduct(normal)))
				p0=head;
		if (direction.length() == 1) //normalize
			dir = direction;
		else
			dir = direction.normalized();		
	}

	
	/**
	 * equal method for ray.  
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray toSendRay = (Ray) obj;

		return p0.equals(toSendRay.p0) && dir.equals(toSendRay.dir);
	}

	
	@Override
	public String toString() {
		return "{" + p0 + " " + dir + "}";
	}
	
	
	/**
	 * return new point that p0+v*scalar
	 */
	   public Point3D getPoint(double t ){
	        if (isZero(t)){
	            return  p0;
	        }
	        return p0.add(dir.scale(t));
	    }
	   
	   /**
	    * func to find the closer point to the head of the ray.
	    * @param points list of points on ray
	    * @return the closer point to p0- ray head
	    */
	   public Point3D findClosestPoint (List<Point3D> points) {
		   double minDistance=999999;
		   Point3D closerPoint=null;
		   double distance=0;
		   
		   if(points== null){
	            return null;
	        }
		   for(int i=0;i<points.size();i++ )
		   {
			   distance=p0.distance(points.get(i));
			   if(distance<minDistance)
			   {
				   minDistance=distance;
				   closerPoint=points.get(i);
			   }
		   }
		   		   
		return closerPoint;
	   }
	   
	   public GeoPoint getClosestGeoPoint(List<GeoPoint> geo) {
		   
		   double minDistance=999999;
		   Point3D closerPoint=null;
		   Geometry geometr=null;
		   double distance=0;
		   
		   if(geo== null){
	            return null;
	        }
		   for(int i=0;i<geo.size();i++ )
		   {
			   distance=p0.distance(geo.get(i).point);
			   if(distance<minDistance)
			   {
				   minDistance=distance;
				   closerPoint=geo.get(i).point;
				   geometr=geo.get(i).geometry;
			   }
		   }
		   		 
		return (new GeoPoint(geometr,closerPoint));
		   
	   }
	   
	   
	   
}
	 


