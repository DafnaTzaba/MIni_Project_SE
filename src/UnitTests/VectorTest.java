package UnitTests;
import static java.lang.System.out;
import static org.junit.Assert.*;


import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;
import static primitives.Util.isZero;
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
	            fail("the new vector (0,0,0) is not valid");
	        }
	        catch(Exception e){
	        }

	}

	@Test
	public void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
		 Vector v1=new Vector(1d,1d,1d);
	     Vector v2=new Vector(-1d,-1d,-1.5d);
	     assertTrue("ERRRE- The connection between the two vectors is invalid",new Vector(2,2 ,2.5).equals(v1.subtract(v2)));
	        // =============== Boundary Values Tests ==================
	       // test zero vector for add opposite vectors
	        try{
	            v1.subtract(new Vector(1, 1, 1));
	            fail("the new vector (0,0,0) is not valid");
	        }
	        catch(Exception e){
	        }
	}

	@Test
	public void testScale() {
		// ============ Equivalence Partitions Tests ==============
				 Vector v1=new Vector(1d,1d,1d);
			     assertTrue("ERRRE- The connection between the two vectors is invalid",new Vector(3,3,3).equals(v1.scale(3)));
			        // =============== Boundary Values Tests ==================
			       // test zero vector for add opposite vectors
			        try{
			            v1.scale(0);
			            fail("the new vector (0,0,0) is not valid");
			        }
			        catch(Exception e){
			        }
	}

	@Test
	public void testDotProduct() {
		 // ============ Equivalence Partitions Tests ==============
		 Vector v1=new Vector(1d,1d,1d);
	     Vector v2=new Vector(-2d,-1d,-1.5d);
	   
	     //Test that the dotProduct vector works correct
	        double v = v1.dotProduct(v2)+4.5;
	        assertEquals("ERRRE- The dot product between the two vectors is invalid",v, 0.0, 0.00001);
	   
	        // =============== not have a Boundary case
	
	     
	}

	@Test
	public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v2)));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows("crossProduct() for parallel vectors does not throw an exception",
                IllegalArgumentException.class, () -> v1.crossProduct(v3));
        // try {
        //     v1.crossProduct(v2);
        //     fail("crossProduct() for parallel vectors does not throw an exception");
        // } catch (Exception e) {}
    }

	     
	

	@Test
	public void testLengthSquared() {
		   // ============ Equivalence Partitions Tests ==============
				 Vector v1=new Vector(1d,6d,9d);
				  double lengh = v1.lengthSquared()- 118;
			     assertEquals("ERRRE- The Length Squared between the two vectors is invalid",lengh, 0.0, 0.00001);
			   
	
	}

	@Test
	public void testLength() {
		   // ============ Equivalence Partitions Tests ==============
		 Vector v1=new Vector(1d,2d,2d);
	     assertTrue("lengh result is not orthogonal to 1st operand", isZero(v1.length()-9));
	
	}

	@Test
	public void testNormalize() {
		   // ============ Equivalence Partitions Tests ==============
		Vector v = new Vector(1, 2, 3);
		//TC01 if the length change to be 1
		Vector vCopyNormalize = v.normalize();
	    assertTrue("Normalize result is not 1 length", isZero(v.length()-1));
	    //TC02 if the vector not change
	     assertEquals("ERROR: normalize() function creates a new vector",vCopyNormalize.equals(v), 0.00001);
	}

	@Test
	public void testNormalized() {
		   // ============ Equivalence Partitions Tests ==============
				Vector v = new Vector(1, 2, 3);
				//TC01 if the length change to be 1
			    assertTrue("Normalize result is not 1 length", isZero(v.length()-1));
			    //TC02 if the vector change
				Vector u = v.normalized();
			     assertEquals("ERROR: normalize() function creates a new vector",u.equals(v), 0.00001);

	}

}
