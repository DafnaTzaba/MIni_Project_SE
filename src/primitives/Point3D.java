package primitives;





public class Point3D
{
	
public final static Point3D ZERO = new Point3D(0d, 0d, 0d);
	
 private Coordinate x;
 private Coordinate y;
 private Coordinate z;

public Coordinate getX() {
	return x;
}

public Coordinate getY() {
	return y;
}

public Coordinate getZ() {
	return z;
}

// Constructor///
public Point3D(Coordinate a,Coordinate b,Coordinate c)
{
	x=a;
	y=b;
	z=c;
}

/*
public Point3D(Point3D a) //copy constructor
{
	x=a.x;
	y=a.y;
	z=a.z;
}
*/

/**
 * primary constructor for Point3D
 * @param x coordinate value for X axis
 * @param y coordinate value for Y axis
 * @param z coordinate value for Z axis
 */
public Point3D(double a, double b, double c)
{
    x = new Coordinate(a);
    y = new Coordinate(b);
    z = new Coordinate(c);
}





@Override
public boolean equals(Object obj) {
   if (this == obj) return true;
   if (obj == null ) return false;
   if (!(obj instanceof Point3D)) return false;
   Point3D other = (Point3D)obj;
   return this.x.equals(other.x) && this.y.equals(other.y) && this.z.equals(other.z);
}

@Override
public String toString() 
{
    return "(" + x + "," + y + "," + z + ")" ;
}

//functions

public Vector subtract(Point3D point)
{
	Point3D v= new Point3D((x.coord - point.x.coord), (y.coord - point.y.coord), (z.coord - point.z.coord));
	if(v==ZERO)
        throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
	else
		return new Vector(v);
 
	
}

public Point3D add(Vector v)
{
	Point3D point= new Point3D(x.coord+v.getHead().x.coord ,y.coord+v.getHead().y.coord , z.coord+v.getHead().z.coord);	
	return point;
}


public double distanceSquared(Point3D point)
{
	Point3D Pdistance=new Point3D(x.coord-point.x.coord,y.coord-point.y.coord,z.coord-point.z.coord);
	return (Pdistance.x.coord*Pdistance.x.coord+Pdistance.y.coord*Pdistance.y.coord+Pdistance.z.coord*Pdistance.z.coord);	
}

public double distance(Point3D point)
{
	return Math.sqrt(distanceSquared(point));
}

}
