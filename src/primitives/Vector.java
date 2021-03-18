package primitives;


public class Vector {
	 private Point3D head;

	public Point3D getHead() {
		return head;
	}

	//Constructors///
public Vector(double x, double y, double z) 
{        
	this(new Point3D(x, y, z));	
}	

public Vector(Point3D h)
{
	Point3D zero =new Point3D(0,0,0);
    if (head.equals(zero)) 
        throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");

	head=new Point3D(h);
}

public Vector(Vector b) //copy constructor
{
	head=new Point3D(b.head);
}

@Override
public boolean equals(Object obj)
{
	   if (this == obj) return true;
       if (obj == null || getClass() != obj.getClass()) return false;
       Vector vector = (Vector) obj;
       return head.equals(vector.head);
   
}



}

