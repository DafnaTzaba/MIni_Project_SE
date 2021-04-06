package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Plane;
import geometries.Polygon;
import primitives.Point3D;
import static primitives.Util.isZero;

public class PlaneTest {


	@Test
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
            // TC03: 2 point are equals
        	
        	
            // TC04: all the point been on the same Straight

        	
	}



	@Test
	public void testGetNormalPoint3D() {

	}

}
