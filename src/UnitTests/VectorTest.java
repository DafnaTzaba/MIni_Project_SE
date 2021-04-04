package UnitTests;
import static org.junit.Assert.*;


import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

public class VectorTest {

	@Test
	public void testVectorDoubleDoubleDouble() {
		fail("Not yet implemented");
	}

	@Test
	public void testVectorPoint3D() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
        // ============ Equivalence Partitions Tests ==============
		 Vector v1=new Vector(1d,1d,1d);
	     Vector v2=new Vector(-1d,-1d,-1.5d);
	     assertTrue("ERRRE- The connection between the two vectors is invalid",new Vector(0,0 ,-0.5).equals(v1.add(v2)));
	        // =============== Boundary Values Tests ==================
	       // test zero vector for add opposite vectors
	        try{
	            v1.add(new Vector(-1, -1, -1));
	            fail("the operator subtract didn't work");
	        }
	        catch(Exception e){
	        }

	}

	@Test
	public void testSubtract() {
		fail("Not yet implemented");
	}

	@Test
	public void testScale() {
		fail("Not yet implemented");
	}

	@Test
	public void testDotProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testCrossProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testLengthSquared() {
		fail("Not yet implemented");
	}

	@Test
	public void testLength() {
		fail("Not yet implemented");
	}

	@Test
	public void testNormalize() {
		fail("Not yet implemented");
	}

	@Test
	public void testNormalized() {
		fail("Not yet implemented");
	}

}
