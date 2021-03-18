package primitives;

public class Vector {
	 private Point3D head;

	

	public Point3D getHead() {
		return head;
	}

	//Constructors///
	
public Vector(Point3D h)
{
	head=new Point3D(h);
	}
public Vector(Vector b) {
	head=new Point3D(b.head);
}

@Override
public boolean equals(Object obj) {
   
   return head.equals(obj) ;
}

}

