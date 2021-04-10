package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Plane;
import primitives.Point3D;
import primitives.Vector;

public class PlaneTest {


	@Test
	/**
     * Test method for {@link geometries.Plane#Plane(Point3D, Point3D, Point3D)}.
     */
	public void testPlanePoint3DPoint3DPoint3D() {
        // ============ Equivalence Partitions Tests ==============
		Point3D p1=new Point3D(1,2,3);
		Point3D p2=new Point3D(4,5,6);
		Point3D p3=new Point3D(-1,8,3);
		
		 // TC01: Check that the plane is built properly
        try {
        	
    		Plane plane= new Plane(p1,p2,p3);
    		
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Plane");
        }

        // TC02: if the normal length equal 1
        	Plane plane= new Plane(p1,p2,p3);
        	assertEquals("uncorrect normal",plane.getNormal().length(),1,0.0001);
            // TC03: 2 point are equals. if they are equal we will have throw from vector because the 2 points create zero vector
            try {
            	
        		Plane plane1= new Plane(p1,p1,p3);
        		   fail("Failed constructing a correct Plane");
            } catch (IllegalArgumentException e) {
             
            }
	
        	
            // TC04: all the point been on the same Straight
            try {           	
    		Plane plane2= new Plane(new Point3D(2,1,0),new Point3D(5,0,3),new Point3D(3.5,0.5,1.5));
            fail("Failed constructing a correct Plane");
            } catch (IllegalArgumentException e) {
            }
        	
	}



	@Test
	/**
     * Test method for {@link geometries.Plane#getNormal(Point3D)}.
     */
	public void testGetNormalPoint3D() {
		 // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane pl = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( "Bad normal to plane",new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
	}

}
