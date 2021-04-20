package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Geometries;
import geometries.Intersectable;
import geometries.Plane;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import geometries.Sphere;
import java.util.List;

public class GeometriesTests {

	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindIntsersections() {

		Geometries geo=new Geometries();
		Ray ray;

		
		// =============== Boundary Values Tests ==================
		//TC0_   Empty body collection
		ray= new Ray(new Point3D(1,0,0),new Vector(1,1,1));
		assertNull("Body collection is empty",geo.findIntsersections(ray));
		
		
		
		geo.add(new Triangle(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(1,1,0)));
		geo.add(new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(1,1,0)));
		geo.add(new Sphere(new Point3D(1,0,0),1));
		
		// ============ Equivalence Partitions Tests ==============

	
		//TC0_   No shape is cut
		ray=new Ray(new Point3D(0,0,10),new Vector(1,2,0));
	    assertNull("Body collection is empty",geo.findIntsersections(ray));
	    
		//TC0_   Only one shape is cut - only  sphere
		ray=new Ray(new Point3D(0,0,0.5),new Vector(1,1,0));
	    assertEquals("Body collection is empty",2 ,geo.findIntsersections(ray).size());
	    
		//TC0_   Only one shape is cut
	    assertEquals("Body collection is empty",1,geo.findIntsersections(ray).size());
	}

}
