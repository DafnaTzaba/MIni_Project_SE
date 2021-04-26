package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

public class Camera {
	private Point3D p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double height;
	private double distance;
		
	/**
	 * Constructor
	 * 
	 * @param p=p0
	 * @param vto=vTo
	 * @param vup=vUp
	 */
	/* 
	public Camera(Point3D p, Vector vto, Vector vup) {
		if (isZero(alignZero(vto.dotProduct(vup)))) {
			// doing normlized to vto and vup
			vto.normalize();
			vup.normalize();
			vTo = vto;
		vUp = vup;
			// find vRight
			vRight = (vto.crossProduct(vup)).normalize();
			p0 = p;

		}

	}*/
	
	
	/**
	 * update width and height to the size of ViewPlane
	 * 
	 * @param _width
	 * @param _height
	 * @return this
	 */
	public Camera setViewPlaneSize(double _width, double _height) {
		width = _width;
		height = _height;

		return this;
	}

	/**
	 * update distance of the ViewPlane from the camera
	 * 
	 * @param _distance
	 * @return this
	 */
	public Camera setDistance(double _distance) {
		distance = _distance;
		return this;
	}

	/**
	 * constructing a ray passing through pixel(i,j) of the view plane
	 * 
	 * @param nX-     count of columns
	 * @param nY-     count of rows
	 * @param j-pixel of columns
	 * @param i-pixel of rows
	 * @return Cutting ray with pixel
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
		Point3D Pc = p0.add(vTo.scale(distance));// center of the ViewPlane

		// size of pixel
		double Rx = width / nX;
		double Ry = height / nY;

		Point3D Pij = Pc;// center Point of the pixel

		// find the center of specific pixel
		double Xj = (j - (nX - 1) / 2d) * Rx;
		double Yi = -(i - (nY - 1) / 2d) * Ry;

		// pc is the center of the pixel
		if (isZero(Xj) && isZero(Yi)) {
			return new Ray(p0, Pij.subtract(p0));
		}

		if (isZero(Xj)) {
			Pij = Pij.add(vUp.scale(Yi));
			return new Ray(p0, Pij.subtract(p0));
		}

		if (isZero(Yi)) {
			Pij = Pij.add(vRight.scale(Xj));
			return new Ray(p0, Pij.subtract(p0));
		}

		Pij = Pij.add(vRight.scale(Xj).add(vUp.scale(Yi)));
		return new Ray(p0, Pij.subtract(p0));

	}

	/**
	 * get P0
	 * 
	 * @return p0
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * get vUp
	 * 
	 * @return vUp
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * get vTo
	 * 
	 * @return vTo
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * get vRight
	 * 
	 * @return vRight
	 */
	public Vector getvRight() {
		return vRight;
	}

	public double getWidgh() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public double getDistance() {
		return distance;
	}

	 
	
	 private Camera(BuilderCamera builder) {
	        p0 = builder.p0;
	        vTo = builder.vTo;
	        vUp = builder.vUp;
	        vRight = builder.vRight;
	        height = builder.height;
	        width = builder.width;
	        distance = builder.distance;
	    }
	
	
	
	/**
	 * Builder Class for Camera
	 */
	public static class BuilderCamera {
		 private Point3D p0;
		 private Vector vTo;
		 private Vector vUp;
		 private Vector vRight;

		private double distance = 10;
		private double width = 1;
		private double height = 1;

		public BuilderCamera setDistance(double _distance) {
			distance = _distance;
			return this;
		}

		public BuilderCamera setViewPlaneWidth(double _width) {
			width = _width;
			return this;
		}

		public BuilderCamera setViewPlaneHeight(double _height) {
			height = _height;
			return this;
		}

		public Camera build() {
			Camera camera = new Camera(this);
			return camera;
		}

		public BuilderCamera(Point3D _p0, Vector _vTo, Vector _vUp) {
			p0 = _p0;

			if (!isZero(_vTo.dotProduct(_vUp))) {
				throw new IllegalArgumentException("vto and vup are not orthogonal");
			}

			vTo = _vTo.normalized();
			vUp = _vUp.normalized();

			vRight = vTo.crossProduct(vUp);

		}

	}
}
