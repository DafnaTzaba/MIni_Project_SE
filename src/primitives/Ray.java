package primitives;

public class Ray 
{
private Point3D p0;
private Vector dir;
public Point3D getP0() {
	return p0;
}
public Vector getDir() {
	return dir;
}

//Constructor///

public Ray(Point3D a, Vector b)
{
	p0= a;
	dir=b;
}
public Ray(Ray a)
{
	p0= new Point3D(a.p0);
	dir=new Vector(a.dir);
}

@Override
public boolean equals(Object obj) {
  
	Ray toSend=(Ray)obj;
   return p0.equals(toSend.p0)&&dir.equals(toSend.dir);
}

}
