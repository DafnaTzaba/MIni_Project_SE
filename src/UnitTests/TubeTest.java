package UnitTests;

import static org.junit.Assert.*;


import org.junit.Test;

import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import static primitives.Util.isZero;

public class TubeTest {




	@Test
	/**
     * Test method for {@link geometries.Tube#getNormal(Point3D) }.
     */
	public void testGetNormal() {
		Point3D p0=new Point3D(0,0,1);
		Vector v= new Vector(0,-1,0);
	    Ray ray= new Ray(p0, v);    
	    Tube tube= new Tube(ray,1.0);
	    
	    //============ Equivalence Partitions Tests ==============
	    //we do it according to power-point of Tube
	    Point3D p=new Point3D(0,0.5,2);
	    double t=(p.subtract(p0)).dotProduct(v);
	    Point3D o=null;
	    Vector normal=null;
	    if (!Util.isZero(t)) //if equal to zero. so vector(p-p0) ortogonal to vector v. so vector(p-p0) is normal
	    {     o =p0.add(v.scale(t));
	          normal=(p.subtract(o)).normalize();
	   double w=normal.dotProduct(v);
	    	
	         
	          assertTrue( "ERROR, Tube's GetNormal() - wrong result ",isZero(normal.dotProduct(v)));
	    }
	    // =============== Boundary Values Tests ==================
	    else
	    {
	    	normal=p.subtract(p0).normalize();	
	        assertEquals("ERROR, Tube's GetNormal() - wrong result ", tube.getNormal(p) ,normal);

	    }


	    	
	}


}


