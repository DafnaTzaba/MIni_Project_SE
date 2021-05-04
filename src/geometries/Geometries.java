package geometries;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

public class Geometries implements Intersectable  {

	private List<Intersectable> geoIntersectable;
	
	/**
	 * We chose a linked list to implement the operation because it has minimal complication in the insertion operation,
	 *  which is the main operation we will perform
	 */
	public  Geometries() {
		geoIntersectable=new LinkedList<Intersectable>(); 
	}
	
	public Geometries(Intersectable... geometries) {
		geoIntersectable= new LinkedList<Intersectable>(); 
		add(geometries);

		
		
	}
	
	public void add(Intersectable... geometries) {
        Collections.addAll(geoIntersectable, geometries);

	}
	
	/**
	 * return all points intersection that we have with the geometries that we get
	 */
	@Override
	public List<Point3D> findIntsersections(Ray ray) {
		 List<Point3D> result = null;
	        for (Intersectable item : geoIntersectable) {
	            //get intersections points of a particular item from _intersectables
	            List<Point3D> itemPoints = item.findIntsersections(ray);
	            if(itemPoints!= null){
	                //first time initialize result to new LinkedList
	                if(result== null){
	                    result= new LinkedList<>();
	                }
	                //add all item points to the resulting list
	                result.addAll(itemPoints);
	            }
	        }
	        return result;
	}
	public List<Intersectable> getGeo() {
		return geoIntersectable;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
