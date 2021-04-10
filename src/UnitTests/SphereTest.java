package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Sphere;
import primitives.Point3D;
import primitives.Vector;

public class SphereTest {

	@Test
	/**
     * Test method for {@link geometrie.Sphere#getNormal(Point3D) }.
     */
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere sph = new Sphere(new Point3D(0, 1, 0),1);
        assertEquals("Bad normal to sphere",new Vector(0, 1, 0), sph.getNormal(new Point3D(0, 2, 0)));
	
	}

}



