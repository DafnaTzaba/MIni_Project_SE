package UnitTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
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
	
	   @Test
	   /**
	     * Test method for {@link geometries.Triangle#findIntsersections(primitives.Ray)}.
	     */
	    public void testFindIntersections() {
		   Triangle triangle = new Triangle(new Point3D(1, 0, 0), new Point3D(3, 0, 0), new Point3D(2, 2, 0));
		   Ray ray;
	        // ============ Equivalence Partitions Tests ==============
	        // TC01: Inside polygon/triangle
		   ray = new Ray(new Point3D(2, 1, 3), new Vector(0, 0, -1));
		   List<Point3D> result=triangle.findIntsersections(ray);
	        assertEquals("Bad intersection",List.of(new Point3D(2 , 1, 0)),result );
		   
	        // TC02: Outside against edge
	        ray = new Ray(new Point3D(3, 1, 3), new Vector(0, 0, -1));
	        assertNull("Rays line out of triangle",triangle.findIntsersections(ray));
	        // TC03: Outside against vertex
	        ray = new Ray(new Point3D(0, 0, 3), new Vector(0, 0, -1));
	        assertNull("Rays line out of triangle",triangle.findIntsersections(ray));
	        // =============== Boundary Values Tests ==================
		   // Three cases (the ray begins "before" the plane)
		   // TC04: On edge
	        ray = new Ray(new Point3D(2, 0, 3), new Vector(0, 0, -1));
	        assertNull("Rays line out of triangle",triangle.findIntsersections(ray));
	        // TC05: In vertex
	        ray = new Ray(new Point3D(1, 0, 3), new Vector(0, 0, -1));
	        assertNull("Rays line out of triangle",triangle.findIntsersections(ray));
	        // TC06: On edge's continuation
	        ray = new Ray(new Point3D(0, 0, 3), new Vector(0, 0, -1));
	        assertNull("Rays line out of triangle",triangle.findIntsersections(ray));
		   
		   
	   
	   


	   
	   
	   }



}
