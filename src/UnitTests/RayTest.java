package UnitTests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class RayTest {

	@Test
	public void testFindClosestPoint() {
		Ray ray1 = new Ray(new Point3D(0, 0, 10), new Vector(1, 10, -100));

        List<Point3D> list = new LinkedList<Point3D>();
        list.add(new Point3D(1, 1, -100));
        list.add(new Point3D(-1, 1, -99));
        list.add(new Point3D(0, 2, -10));
        list.add(new Point3D(0.5, 0, -100));
		
        // ============ Equivalence Partitions Tests ==============
		//T01: A point in the middle of the list is closest to the beginning of the fund
        assertEquals("point in the middle of the list", new Point3D(0, 2, -10) ,ray1.findClosestPoint(list));
        
        // =============== Boundary Values Tests ==================
		//T02: empty point list
        List<Point3D> list2 = null;
        assertNull(" empty point list",ray1.findClosestPoint(list2));

		//T03: The first point is closest to the beginning of the foundation
		Ray ray3 = new Ray(new Point3D(1, 1.5, -100), new Vector(1, 10, -100));
        assertEquals("first point is closest", list.get(0) ,ray3.findClosestPoint(list));
       
		//T04: The last point is closest to the beginning of the foundation
		Ray ray4 = new Ray(new Point3D(0, 0, -100), new Vector(1, 10, -100));
        assertEquals(" last point is closest", list.get(3) ,ray4.findClosestPoint(list));

		

		
	}
	
	@Test
	public void testgetClosestGeoPoint() {
		Ray ray1 = new Ray(new Point3D(0, 0, 10), new Vector(1, 10, -100));
	

        List<GeoPoint> list = new LinkedList<GeoPoint>();
        list.add(new GeoPoint(new Sphere(new Point3D(1,1,-100),2),new Point3D(1, 1, -100)));
        list.add(new GeoPoint( new Sphere(new Point3D(-1,1,-99),2),new Point3D(-1, 1, -99)));
        list.add(new GeoPoint(new Sphere(new Point3D(0,2,-10),2),new Point3D(0, 2, -10)));
        list.add(new GeoPoint(new Sphere(new Point3D(0.5,0,-100),2),new Point3D(0.5, 0, -100)));
		
        // ============ Equivalence Partitions Tests ==============
		//T01: A point in the middle of the list is closest to the beginning of the fund
        assertEquals("point in the middle of the list", new Point3D(0, 2, -10) ,ray1.getClosestGeoPoint(list).point);
        
        // =============== Boundary Values Tests ==================
		//T02: empty point list
        List<GeoPoint> list2 = null;
        assertNull(" empty point list",ray1.getClosestGeoPoint(list2));

		//T03: The first point is closest to the beginning of the foundation
		Ray ray3 = new Ray(new Point3D(1, 1.5, -100), new Vector(1, 10, -100));
        assertEquals("first point is closest", list.get(0) ,ray3.getClosestGeoPoint(list));
       
		//T04: The last point is closest to the beginning of the foundation
		Ray ray4 = new Ray(new Point3D(0, 0, -100), new Vector(1, 10, -100));
        assertEquals(" last point is closest", list.get(3) ,ray4.getClosestGeoPoint(list));

		

		
	}

}
