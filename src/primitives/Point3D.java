package primitives;

public class Point3D
{
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
public Point3D(Point3D a)
{
	x=a.x;
	y=a.y;
	z=a.z;
}


@Override
public boolean equals(Object obj) {
   if (this == obj) return true;
   if (obj == null) return false;
   if (!(obj instanceof Point3D)) return false;
   Point3D other = (Point3D)obj;
   return this.x.equals(other.x) && this.y.equals(other.y) && this.z.equals(other.z);
}

}
