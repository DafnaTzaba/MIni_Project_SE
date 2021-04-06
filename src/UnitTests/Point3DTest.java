package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;
import static primitives.Util.isZero;

public class Point3DTest {
	

	@Test
	public void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
		Point3D p1 = new Point3D(1, 2, 3);
		Vector v = new Vector(-1, -2, -3);
	    assertTrue("ERROR: Point - Point does not work correctly", v.equals(new Point3D(0, 0, 0).subtract(p1)));

	}

	@Test
	public void testAdd() {
        // ============ Equivalence Partitions Tests ==============
		Point3D p1 = new Point3D(1, 2, 3);
		Vector v = new Vector(-1, -2, -3);
	     assertTrue("ERROR: Point + Vector does not work correctly", new Point3D(0,0,0).equals(p1.add(v)));

	}

	@Test
	public void testDistanceSquared() {
		 // ============ Equivalence Partitions Tests ==============
		Point3D p1 = new Point3D(1, 2, 3);
		Point3D p2 = new Point3D(2, 2, 10);
	     assertTrue("ERROR: Point DistanceSquared does not work correctly",isZero(p1.distanceSquared(p2)-50));

	
	}

	@Test
	public void testDistance() { 
		 // ============ Equivalence Partitions Tests ==============
		Point3D p1 = new Point3D(4, 4, 2);
		Point3D p2 = new Point3D(2, 2, 3);
	    assertTrue("ERROR: Point Distance does not work correctly",isZero(p1.distance(p2)-3));

	
	}

}
