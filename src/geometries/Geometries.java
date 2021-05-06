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
	
	
	public List<Intersectable> getGeo() {
		return geoIntersectable;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		
		 List<GeoPoint> result = null;
	        for (Intersectable item : geoIntersectable) {
	            //get intersections points of a particular item from _intersectables
	            List<GeoPoint> itemPoints = item.findGeoIntersections(ray);
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

}
