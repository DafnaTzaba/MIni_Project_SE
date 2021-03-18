package primitives;


public class Vector {
	 private Point3D head;
	 public final static Point3D ZERO = new Point3D(0d, 0d, 0d);
	public Point3D getHead() {
		return head;
	}

	//Constructors///
public Vector(double x, double y, double z) 
{  
	this(new Point3D(x, y, z));	
	/*if (head.equals(ZERO)) 
        throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");*/

	
}	

public Vector(Point3D h)
{
	
    if (head.equals(ZERO)) 
        throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
	head = h;
}

/*
public Vector(Vector b) //copy constructor
{
	head=new Point3D(b.head);
}
*/
public Vector add(Vector vec)
{
	return new Vector(head.add(vec));
}
public Vector subtract(Vector vec)
{
	Vector sub=head.subtract(vec.head);
	return new Vector(sub.head);
}
public Vector scale(double po)
{
	return new Vector(head.getX().coord*po,head.getY().coord*po,head.getZ().coord*po);
	}

public double dotProduct (Vector v){
    return head.getX().coord * v.head.getX().coord +
           head.getY().coord * v.head.getY().coord +
           head.getZ().coord * v.head.getZ().coord;
}
@Override
public boolean equals(Object obj)
{
	   if (this == obj) return true;
       if (obj == null || getClass() != obj.getClass()) return false;
       Vector vector = (Vector) obj;
       return head.equals(vector.head);
   
}
public Vector crossProduct(Vector vec)
{
	double x1= head.getX().coord;
	double y1= head.getY().coord;
	double z1= head.getZ().coord;
	
	double x2=vec.getHead().getX().coord;
	double y2=vec.getHead().getY().coord;
	double z2=vec.getHead().getZ().coord;
	
	return new Vector(y1*z2-z1*y2,z1*x2-x1*z2,x1*y2-y1*x2 );
	
	
	}
public double lengthSquared()
{
	return head.distanceSquared(head); 
}
public double length()
{
	return Math.sqrt(lengthSquared()); 
}
@Override
public String toString() 
{
	
	
    return "{" + head + "}";
}

public Vector normalize()
{
	double length=this.length();
	this.head=new Point3D(head.getX().coord/length,head.getY().coord/length,head.getZ().coord/length);
	return this;}

public Vector normalized() 
{
	 Vector v = new Vector(head);
     return v.normalize();
}


}

