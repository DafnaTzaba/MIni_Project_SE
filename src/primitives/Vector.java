package primitives;

public class Vector {
	
	/**
	 * class to vector have one head point for the vector. all the vector begin from (0,0,0)
	 */
	private Point3D head;
	
	/**
	 * static varieble ZERO to point(0,0,0)
	 */
	public final static Point3D ZERO = new Point3D(0d, 0d, 0d);

	/**
	 * @return head point
	 */
	public Point3D getHead() {
		return head;
	}

	// Constructors///
	
	/**
	 * constructor
	 * @param x   coordinate value for x
	 * @param y   coordinate value for y
	 * @param z   coordinate value for z
	 */
	public Vector(double x, double y, double z) {
		this(new Point3D(x, y, z));

		/*
		 * if (head.equals(ZERO)) throw new
		 * IllegalArgumentException("Vector head cannot be Point(0,0,0)");
		 */

	}

	/**
	 * constructor that get point
	 * @param h point for head
	 */
	public Vector(Point3D h) {
		head = h;

		if (head.equals(ZERO))
			throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
	}

	/*
	 * public Vector(Vector b) //copy constructor { head=new Point3D(b.head); }
	 */
	
	/**
	 * add vector to excises vector
	 * @param vec to add 
	 * @return the new vector
	 */
	public Vector add(Vector vec) {
		return new Vector(head.add(vec));
	}

	/**
	 * to sub vector from vector
	 * @param vec to sub vec from the excises vector
	 * @return the new vector after sub
	 */
	public Vector subtract(Vector vec) {
		Vector sub = head.subtract(vec.head);
		return new Vector(sub.head);
	}

	/**
	 * to mult the vector at parameter
	 * @param po= @param to mult the vector variable
	 * @return the new vector
	 */
	public Vector scale(double po) {
		return new Vector(head.getX().coord * po, head.getY().coord * po, head.getZ().coord * po);
	}

	  /**
     * dot product between two vectors (scalar product)
     *
     * @param v the right vector of U.V
     * @return scalar value of dot product
     */

	public double dotProduct(Vector v) {
		return head.getX().coord * v.head.getX().coord + head.getY().coord * v.head.getY().coord
				+ head.getZ().coord * v.head.getZ().coord;
	}

	/**
	 * override equal method to vector class. equal 2 vector and return true if equal else false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Vector vector = (Vector) obj;
		return head.equals(vector.head);

	}

	  /**
	   *  crossProduct between 2 vectors
     * @param vec = second vector
     * @return new Vector resulting from cross product
     */

	public Vector crossProduct(Vector vec) {
		double x1 = head.getX().coord;
		double y1 = head.getY().coord;
		double z1 = head.getZ().coord;

		double x2 = vec.getHead().getX().coord;
		double y2 = vec.getHead().getY().coord;
		double z2 = vec.getHead().getZ().coord;

		return new Vector(y1 * z2 - z1 * y2, z1 * x2 - x1 * z2, x1 * y2 - y1 * x2);

	}

	/**
	 * Calculate the length of the vector squared 
	 * @return the length
	 */
	public double lengthSquared() {
		return (head.getX().coord * head.getX().coord + head.getY().coord * head.getY().coord
				+ head.getZ().coord * head.getZ().coord);
	}

	/**
	 * Calculate the length of the vector
	 * @return the length
	 */
	public double length() {
		return Math.sqrt(lengthSquared());
	}

	@Override
	public String toString() {

		return "{" + head + "}";
	}

	/**
	 * normalized the current vector- change the head vector to the normal vector
	 * @return
	 */
	public Vector normalize() {
		double length = this.length();
		this.head = new Point3D(head.getX().coord / length, head.getY().coord / length, head.getZ().coord / length);
		return this;
	}

	/**
	 * to normalized the current vector
	 * @return new normal vector
	 */
	public Vector normalized() {
		Vector v = new Vector(head);
		return v.normalize();
	}
	

}
