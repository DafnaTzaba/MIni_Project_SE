package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public abstract class Geometry implements Intersectable  {
	/**
	 * interface to all geometries
	 * 
	 * @param point to return normal at this point
	 * @return
	 */
	 protected Color emmission=Color.BLACK;
	 public abstract Vector getNormal(Point3D point);

	 /**
	  * get the emmision's field
	  * @return the field emmission
	  */
	 public Color getEmmission()
	 {
		 return emmission;
	 }
	
	 /**
	  * To update the field emmission
	  * @return the object
	  */
	 public Geometry setEmission(Color newColor)
	 {
		 emmission=newColor;
		 return this;
	 }
	 
}
