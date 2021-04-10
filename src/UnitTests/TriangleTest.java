package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;

public class TriangleTest {

	@Test
	/**
     * Test method for {@link geometries.Triangle#getNormal(Point3D) }.
     */
	public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle pl = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( "Bad normal to triangle" ,new Vector(sqrt3, sqrt3, sqrt3),pl.getNormal(new Point3D(0, 0, 1)));
	}



}
