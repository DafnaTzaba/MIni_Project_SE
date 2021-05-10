package geometries;

import primitives.Color;
import primitives.Material;
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
	 private Material material=new Material();

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

	 //-- getter and setter for material
	public Material getMaterial() {
		return material;
	}

	/**
	 * @param material
	 * @return the object- our geometry
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}
	 
}
