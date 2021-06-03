package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.*;
import geometries.Cylinder;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import geometries.Tube;
import primitives.*;
import renderer.*;
import scene.Scene;
public class Mini_Project_SE_picture {
	private Scene scene = new Scene("Test scene");
	private double alfa=10.0;
	@Test
	public void Mini_Project_se() {
		Camera camera = new Camera.BuilderCamera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0))
				.setViewPlaneHeight(2500).setViewPlaneWidth(2500).setDistance(10000).build(); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add(
				new Plane(new Point3D(550,150,360),new Vector(0,0,-1)).setEmission(Color.BLUE).setMaterial(new Material().setKd(0.4).setKs(0.3).setnShininess(100).setKT(0.3))
				//new Plane(new Point3D(0,150,0),new Vector(0,-1,0)).setEmission(Color.CYAN).setMaterial(new Material().setKd(0.4).setKs(0.3).setnShininess(100).setKT(0.3))
			
				
				
				);
			
		 

		scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2))
				.setKl(0.0009).setKq(0.0000006));

		ImageWriter imageWriter = new ImageWriter("mini_Project", 600, 600);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage(alfa);
		render.writeToImage();
	}

}
