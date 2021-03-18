package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry
{
	private Point3D q0;
	private Vector normal;
	public Plane(Point3D v1,Point3D v2,Point3D v3)
	{
		q0=new Point3D(v1.getX(),v1.getY(),v1.getZ());
		Vector u1=new Vector(v2);
		Vector u2=new Vector(v3);
		normal= u1.crossProduct(u2);		
	}
	
	public Plane(Point3D point, Vector normal_)
	{
		q0=new Point3D(point.getX(),point.getY(),point.getZ());
		normal= new Vector(normal_.getHead());
	}
	

	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

	public Point3D getQ0() {
		return q0;
	}

	public Vector getNormal() {
		return normal;
	}

}
