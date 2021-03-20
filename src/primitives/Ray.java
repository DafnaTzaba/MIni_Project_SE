package primitives;

public class Ray {
	
	/**
	 * ray class. tow variables- point start and vector
	 */
	private Point3D p0;
	private Vector dir;

	/**
	 * getter
	 */
	public Point3D getP0() {
		return p0;
	}

	public Vector getDir() {
		return dir;
	}

//Constructor///

	/**
	 * constructor
	 * @param a point for the start point(p0)
	 * @param b vector for dir
	 */
	public Ray(Point3D a, Vector b) {
		p0 = a;
		if (b.length() == 1)
			dir = b;
		else
			dir = b.normalized();
	}

	/*
	 * public Ray(Ray a) //copy constructor { p0= new Point3D(a.p0); dir=new
	 * Vector(a.dir); }
	 */
	
	/**
	 * equal method for ray.  
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray toSendRay = (Ray) obj;

		return p0.equals(toSendRay.p0) && dir.equals(toSendRay.dir);
	}

	@Override
	public String toString() {
		return "{" + p0 + " " + dir + "}";
	}

}
