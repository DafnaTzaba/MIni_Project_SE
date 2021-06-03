package UnitTests;

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

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");
	private double alfa=10.0;

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */

	@Test
	public void twoSpheres() {
		Camera camera = new Camera.BuilderCamera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
				.setViewPlaneHeight(150).setViewPlaneWidth(150).setDistance(1000).build();

		scene.geometries.add(
				new Sphere(new Point3D(0, 0, -50), 50).setEmission(new Color(java.awt.Color.BLUE))
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setnShininess(100).setKT(0.3)),
				new Sphere(new Point3D(0, 0, -50), 25).setEmission(new Color(java.awt.Color.RED))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100)));
		scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2))
				.setKl(0.0004).setKq(0.0000006));

		Render render = new Render().setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage(alfa);
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */

	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera.BuilderCamera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0))
				.setViewPlaneHeight(2500).setViewPlaneWidth(2500).setDistance(10000).build(); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add(
				new Sphere(new Point3D(-950, -900, -1000), 400).setEmission(new Color(0, 0, 100))
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20).setKT(0.5)),
				new Sphere(new Point3D(-950, -900, -1000), 200).setEmission(new Color(100, 20, 20))
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(670, 670, 3000)).setEmission(new Color(20, 20, 20))
								.setMaterial(new Material().setKR(1)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(-1500, -1500, -2000)).setEmission(new Color(20, 20, 20))
								.setMaterial(new Material().setKR(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4))
				.setKl(0.00001).setKq(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage(alfa);
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */

	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera.BuilderCamera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
				.setViewPlaneHeight(200).setViewPlaneWidth(200).setDistance(1000).build();

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add(
				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				new Sphere(new Point3D(60, 50, -50), 30).setEmission(new Color(java.awt.Color.BLUE))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(30).setKT(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1))
				.setKl(4E-5).setKq(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		Render render = new Render().setImageWriter(imageWriter) //
				.setCamera(camera).setRayTracer(new RayTracerBasic(scene));

		render.renderImage(alfa);
		render.writeToImage();
	}

	@Test
	public void ourPicture4() {
		Camera camera = new Camera.BuilderCamera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0))
				.setViewPlaneHeight(2500).setViewPlaneWidth(2500).setDistance(10000).build(); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add(
				// ball down
				new Sphere(new Point3D(-500, -900, -1000), 400).setEmission(new Color(0, 50, 100))
				.setMaterial(new Material().setKd(0.25).setKs(0.75).setnShininess(150).setKT(0.7)),
				new Sphere(new Point3D(-500, -900, -1000), 200).setEmission(Color.RED)
				.setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20).setKR(0.7)),
// boll middle
				new Sphere(new Point3D(-500, -200, -1000), 300).setEmission(new Color(0, 50, 100))
				.setMaterial(new Material().setKd(0.4).setKs(0.3).setnShininess(100).setKT(0.7)),
				new Sphere(new Point3D(-500, -200, -1000), 150).setEmission(new Color(150, 70, 90))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100).setKT(0.3)),
//ball up
				new Sphere(new Point3D(-500, 300, -1000), 200).setEmission(new Color(0, 50, 100))
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20).setKT(0.7)),
				new Sphere(new Point3D(-560, 350, -1000), 40).setEmission(Color.RED)
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20).setKR(1)),
				new Sphere(new Point3D(-440, 350, -1000), 40).setEmission(Color.RED)
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20).setKR(1)),

				// mouse+ nose
				new Triangle(new Point3D(-500, 310, -1000), new Point3D(-470, 280, -1000), new Point3D(530, 280, -1000))
						.setEmission(new Color(150, 70, 90)).setMaterial(new Material().setKR(0.5)),

				new Triangle(new Point3D(-500, 240, -1000), new Point3D(-570, 230, -1000), new Point3D(-440, 220, -1000))
						.setEmission(Color.CYAN).setMaterial(new Material().setKR(0.5)),

					//background
				new Triangle(new Point3D(-2000, -2000, -1800), new Point3D(-1400, 1400, -1900), new Point3D(1500, 1500, -2000))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)));
		 

		scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2))
				.setKl(0.0009).setKq(0.0000006));

		ImageWriter imageWriter = new ImageWriter("ourPicture4", 600, 600);
		Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage(alfa);
		render.writeToImage();
	}

}
