package geometries;
import primitives.Ray;

import java.util.List;

import primitives.Point3D;

/**
 * interface to all geometries
 * @param ray of cutting
 * @return list of point that cutting with the bodies
 */

public interface Intersectable {
	
	List<Point3D> findIntsersections(Ray ray);
	

	/**
	 * 
	 * @param ray
	 * @return Intersections geopoint between ray and planeS
	 */
	List<GeoPoint> findGeoIntersections (Ray ray);
	/**
	 *Helper class so that we can calculate the spot lighting in the body
	 * @author Herout Rozilyo
	 *
	 */
	public static class GeoPoint {
	    public Geometry geometry;
	    public Point3D point;
	    
	    GeoPoint(Geometry geo,Point3D po)
	    {
	    	geometry=geo;
	    	point=po;
	    }
	    @Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof GeoPoint))
				return false;
			GeoPoint other = (GeoPoint) obj;
			return this.point.equals(other.point) && this.geometry.equals(other.geometry);
		}
	    
	    
	    
	    	
	    
	   
	}
}




