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
}
