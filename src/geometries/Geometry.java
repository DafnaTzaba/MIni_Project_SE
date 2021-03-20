package geometries;

import primitives.Point3D;
import primitives.Vector;

public interface Geometry {
	/**
	 * interface to all geometries
	 * 
	 * @param point to return normal at this point
	 * @return
	 */
	Vector getNormal(Point3D point);

}
