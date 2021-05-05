package UnitTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Sphere;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class SphereTest {

	@Test
	/**
	 * Test method for {@link geometrie.Sphere#getNormal(Point3D) }.
	 */
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Sphere sph = new Sphere(new Point3D(0, 1, 0), 1);
		assertEquals("Bad normal to sphere", new Vector(0, 1, 0), sph.getNormal(new Point3D(0, 2, 0)));

	}

	/**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point3D(1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull("Ray's line out of sphere",sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0),  new Vector(3, 1, 0)));
        List<Point3D> exp = List.of(p1, p2);   
        
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));      
        assertEquals("Ray crosses sphere" ,exp, result);
        
        // TC03: Ray starts inside the sphere (1 point)
        List<Point3D> result1 = sphere.findIntsersections(new Ray(new Point3D(0.5,0.5, 0),  new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 1, result1.size());
        assertEquals("Ray from inside sphere",List.of(p2),result1);
        
        // TC04: Ray starts after the sphere (0 points)
        assertNull("Sphere behind Ray",sphere.findIntsersections(new Ray(new Point3D(2.5, 1, 0), new Vector(3, 1, 0))));
        
        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        assertEquals("Ray from sphere inside",List.of(new Point3D(2,0,0)),sphere.findIntsersections(new Ray(new Point3D(1,-1, 0), new Vector(1, 1, 0))));
        
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull("Ray from sphere inside",sphere.findIntsersections(new Ray(new Point3D(2,0,0), new Vector(1, -1, 0))));

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
      
        
        Point3D p13 = new Point3D(1, -1, 0);
        Point3D p23 = new Point3D(1, 1, 0);
        List<Point3D> result13 = sphere.findIntsersections(new Ray(new Point3D(1, -2, 0), new Vector(0, 1, 0)));
        List<Point3D> exp13 = List.of(p13, p23);   
        
        assertEquals("Wrong number of points", 2, result13.size());
        if (result13.get(0).getX() > result13.get(1).getX())
            result13 = List.of(result13.get(1), result13.get(0));      
        assertEquals("Ray crosses sphere" ,exp13, result13);
        
     
       
             
        // TC14: Ray starts at sphere and goes inside (1 points)
        assertEquals("Ray starts at sphere and goes inside",List.of(new Point3D(0,0,0)),sphere.findIntsersections(new Ray(new Point3D(2,0,0), new Vector(-1, 0, 0))));

        // TC15: Ray starts inside (1 points)
        assertEquals("Ray starts inside",List.of(new Point3D(0,0,0)),sphere.findIntsersections(new Ray(new Point3D(0.5,0,0), new Vector(-0.5, 0, 0))));

        // TC16: Ray starts at the center (1 points)
        assertEquals("Ray starts at the center",List.of(new Point3D(0,0,0)),sphere.findIntsersections(new Ray(new Point3D(1,0,0), new Vector(-1, 0, 0))));

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull("Ray starts at sphere and goes outside",sphere.findIntsersections(new Ray(new Point3D(2,0,0), new Vector(1, 0, 0))));

        // TC18: Ray starts after sphere (0 points)
        assertNull("Ray starts after sphere",sphere.findIntsersections(new Ray(new Point3D(3,0,0), new Vector(1, 0, 0))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
      //radoius of our sphere = (-1,0,0)
        // TC19: Ray starts before the tangent point
        assertNull("Ray starts before the tangent point",sphere.findIntsersections(new Ray(new Point3D(-1,0,0), new Vector(0,1,1))));

        // TC20: Ray starts at the tangent point
        assertNull("Ray starts at the tangent point",sphere.findIntsersections(new Ray(new Point3D(2,0,0), new Vector(0,1,1))));
        
        // TC21: Ray starts after the tangent point
        assertNull("Ray starts after the tangent point",sphere.findIntsersections(new Ray(new Point3D(4,0,0), new Vector(0,1,1))));

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull("Ray orthogonal to ray head -> O line",sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(0, 0, 1))));
    }

}
