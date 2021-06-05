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
	private double alfa = 0;

	@Test
	public void Mini_Project_se() {

		
		Camera camera = new Camera.BuilderCamera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
				.setViewPlaneHeight(150).setViewPlaneWidth(150).setDistance(1000).build();
		//scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.3));

		scene.geometries.add(
				
				new Triangle(
						new Point3D(48, 50, -80),
						new Point3D(-52, -40, -80),
						new Point3D(48, -40, -80) 
						)
				.setEmission(Color.DARKGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				new Triangle(
						new Point3D(48,50,-80),
						new Point3D(-52,50,-80),
						new Point3D(-52,-40,-80) 
						)
				.setEmission(Color.DARKGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
				
				new Triangle(
						new Point3D(48,50,-80),
						new Point3D(81,-83,-80),
						new Point3D(48,-40,-80) 
						)
				.setEmission(Color.DARKGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
				new Triangle(
						new Point3D(81,83,-80),
						new Point3D(48,50,-80),
						new Point3D(81,-83,-80) 
						)
				.setEmission(Color.DARKGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				

				new Triangle(
						new Point3D(-81,83,-80),
						new Point3D(-52,50,-80),
						new Point3D(-52, -40, -80) 
						)
				.setEmission(Color.DARKGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
				new Triangle(
						new Point3D(-81,83,-80),
						new Point3D(-52,-40,-80),
						new Point3D(-81,-83,-80) 
						)
				.setEmission(Color.DARKGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
				
				new Triangle(
						new Point3D(-81,83,-80),
						new Point3D(48,50,-80),
						new Point3D(-52,50,-80) 
						)
				.setEmission(Color.GREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.7).setnShininess(100).setKT(1)),
				

				new Triangle(
						new Point3D(81,83,-80),
						new Point3D(-81,83,-80),
						new Point3D(48,50,-80) 
						)
				.setEmission(Color.GREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.7).setnShininess(100).setKT(1)),
				
				new Sphere(new Point3D(12, 43, -10), 10).setEmission(new Color(300, 0, 0))
				.setMaterial(new Material().setKd(0.25).setKs(0.7).setnShininess(20).setKT(0.5)),
		new Sphere(new Point3D(12, 43, -10), 5).setEmission(new Color(255,255,255))
				.setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20)),
				
				
		new Sphere(new Point3D(20, 10, -20), 5).setEmission(new Color(400, 300, 150))
				.setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20).setKT(1)),
				new Sphere(new Point3D(20, 10, -20), 10).setEmission(new Color(0, 0, 100))
				.setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20).setKT(1)),
				
			
				
		new Sphere(new Point3D(-30, 0, -10), 20).setEmission(new Color(60, 20, 20))
				.setMaterial(new Material().setKd(0.25).setKs(1).setnShininess(20)),
				new Sphere(new Point3D(-30, 60, -10), 10).setEmission(new Color(0, 0, 100))
				.setMaterial(new Material().setKd(0.25).setKs(1).setnShininess(20).setKT(0.5))
				
				
//				new Triangle(
//						new Point3D(-72,-70,-80),
//						new Point3D(-38,-83,-80),
//						new Point3D(-81,-83,-80) 
//						)
//				.setEmission(Color.WHITE)
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
//				
//				
//				
//				new Triangle(
//						new Point3D(-38,-70,-80),
//						new Point3D(-72,-70,-80),
//						new Point3D(-38,-83,-80) 
//						)
//				.setEmission(Color.WHITE)
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
//				
//				
//				new Triangle(
//						new Point3D(-68,-60,-80),
//						new Point3D(-38,-70,-80),
//						new Point3D(-72,-70,-80) 
//						)
//				.setEmission(Color.ORANGE)
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
//				
//				
//				
//				new Triangle(
//						new Point3D(-38,-70,-80),
//						new Point3D(-72,-70,-80),
//						new Point3D(-38,-83,-80) 
//						)
//				.setEmission(Color.WHITE)
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60))
				
				);
		
		
		
		
		for (int j = 0; j < 4; j++) {
			for (int i =0; i < 4; i++) {
				if(i%2==0)
				{
					if(j%2==0)
					{
				scene.addGeometries(new Triangle(
						new Point3D(-81+40*i ,-83+13*j,-80 ),
						new Point3D(-38+40*i,-83+13*j, -80),
						new Point3D(-72+33*i, -70+13*j, -80))
						.setEmission(Color.WHITE)
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)),
						new Triangle(
								new Point3D(-72+33*i, -70+13*j, -80),
								new Point3D(-38+40*i, -70+13*j, -80),
								new Point3D(-38+40*i,-83+13*j, -80))
						.setEmission(Color.WHITE)
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)));
					}
					else
						scene.addGeometries(new Triangle(
								new Point3D(-81+40*i ,-83+13*j,-80 ),
								new Point3D(-38+40*i,-83+13*j, -80),
								new Point3D(-72+33*i, -70+13*j, -80))
								.setEmission(Color.BLACK)
								.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)),
								new Triangle(
										new Point3D(-72+33*i, -70+13*j, -80),
										new Point3D(-38+40*i, -70+13*j, -80),
										new Point3D(-38+40*i,-83+13*j, -80))
								.setEmission(Color.BLACK)
								.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)));
				}
				else
				{
					if(j%2==0)
					{
				scene.addGeometries(new Triangle(
						new Point3D(-81+40*i ,-83+13*j,-80 ),
						new Point3D(-38+40*i,-83+13*j, -80),
						new Point3D(-72+33*i, -70+13*j, -80))
						.setEmission(Color.BLACK)
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)),
						new Triangle(
								new Point3D(-72+33*i, -70+13*j, -80),
								new Point3D(-38+40*i, -70+13*j, -80),
								new Point3D(-38+40*i,-83+13*j, -80))
						.setEmission(Color.BLACK)
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)));
					}
					else
						scene.addGeometries(new Triangle(
								new Point3D(-81+40*i ,-83+13*j,-80 ),
								new Point3D(-38+40*i,-83+13*j, -80),
								new Point3D(-72+33*i, -70+13*j, -80))
								.setEmission(Color.WHITE)
								.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)),
								
								new Triangle(
										new Point3D(-72+33*i, -70+13*j, -80),
										new Point3D(-38+40*i, -70+13*j, -80),
										new Point3D(-38+40*i,-83+13*j, -80))
								.setEmission(Color.WHITE)
								.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)));
				}
					
		
					
						
			}
		}
						
						
						
						
					
						
		
		

		scene.lights.add(new SpotLight(new Color(500, 255, 255), new Point3D(500, 500, 500), new Vector(-1, -1, -2))
				.setKl(0.0004).setKq(0.0000006));
		scene.lights.add(new DirectionalLight(new Color(0, 255, 255),new Vector( 1,5,-3)));
		
		
 

    scene.lights.add(new PointLight(Color.MAGENTA, new Point3D(100, 1000, -130)) //
			.setKl(0.0005).setKq(0.0005));

		Render render = new Render().setImageWriter(new ImageWriter("triangle", 500, 500)).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage(alfa);
		render.writeToImage();
		
		
		
	
		
	}
}
//	@Test
//	public void catTest() {
//		Scene scene = new Scene("Test scene");
//		Camera camera = new Camera.BuilderCamera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0))
//				.setViewPlaneHeight(2500).setViewPlaneWidth(2500).setDistance(10000).build(); 
//
//	//	scene.set_distance(1000);
//		scene.setBackground(new Color(java.awt.Color.BLACK));
//		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
//		//scene.setSuperSampling(true);
//		
//		scene.addGeometries( //
//				new Triangle( // back
//						new Point3D(-50, -50, 500),
//						new Point3D(50, -50, 500),
//						new Point3D(-50, 50, 500)).setEmission(new Color(255,72,84))
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setKT(0)),
//				
//				new Triangle(
//						new Point3D(50, 50, 500), new Point3D(50, -50, 500), new Point3D(-50, 50, 500))
//				.setEmission(new Color(255,72,84))
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setKT(0)),
//				new Triangle(
//						new Point3D(50, 50, 500), new Point3D(0, 100, 400), new Point3D(-50, 50, 500))
//				.setEmission(new Color(255,72,84))
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setKT(0)),
//				new Triangle(
//						new Point3D(0, 100, 400), new Point3D(-50, 50, 500), new Point3D(-100, 100, 400))
//				.setEmission(new Color(255,72,84))
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setKT(0)),
//				new Triangle(
//						new Point3D(-50, -50, 500), new Point3D(-50, 50, 500), new Point3D(-100, 100, 400))	
//				.setEmission(new Color(255,72,84))
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setKT(0)),
//				new Triangle(
//						new Point3D(-50, -50, 500), new Point3D(-100, 0, 400), new Point3D(-100, 100, 400))	
//				.setEmission(new Color(255,72,84))
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setKT(0)),
//				new Triangle(
//						new Point3D(-50, -50, 500), new Point3D(-100, 0, 400), new Point3D(0, 0, 400))
//				.setEmission(new Color(255,72,84))
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setKT(0.3)),
//				new Triangle(
//						new Point3D(-50, -50, 500), new Point3D(50, -50, 500), new Point3D(0, 0, 400))
//				.setEmission(new Color(255,72,84))
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setKT(0.3)),
//				new Triangle(
//						new Point3D(0, 100, 400), new Point3D(50, -50, 500), new Point3D(0, 0, 400))
//				.setEmission(new Color(255,72,84))
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setKT(0.3)),
//				new Triangle(
//						new Point3D(0, 100, 400), new Point3D(50, -50, 500), new Point3D(50, 50, 500))
//				.setEmission(new Color(255,72,84))
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setKT(0.3)),
//				new Triangle(
//						new Point3D(0, 100, 400), new Point3D(-100, 100, 400), new Point3D(0, 0, 400))
//				.setEmission(new Color(255,72,84))
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setKT(0.3)),
//				new Triangle(
//						new Point3D(-100, 0, 400), new Point3D(-100, 100, 400), new Point3D(0, 0, 400))
//				.setEmission(new Color(255,72,84))
//				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setKT(0.3)),
////				new Sphere(new Material(0.2, 0.6, 30, 0, 0), new Color(java.awt.Color.BLACK), //middle front )
////						8, new Point3D(-50,50, 400)),
////				new Sphere(new Material(0.2, 0.6, 30, 0, 0), new Color(java.awt.Color.BLACK), //left up )
////						8, new Point3D(-75,25, 400)),
////				new Sphere(new Material(0.2, 0.6, 30, 0, 0), new Color(java.awt.Color.BLACK), // )
////						8, new Point3D(-75,75, 400)),
////				new Sphere(new Material(0.2, 0.6, 30, 0, 0), new Color(java.awt.Color.BLACK), // )
////						8, new Point3D(-25,25, 400)),
////				new Sphere(new Material(0.2, 0.6, 30, 0, 0), new Color(java.awt.Color.BLACK), // )
////						8, new Point3D(-25,75, 400)),
////				new Sphere(new Material(0.2, 0.6, 30, 0, 0), new Color(java.awt.Color.BLACK), // )//left side
////						8, new Point3D(25, 25, 450)),
////				new Sphere(new Material(0.2, 0.6, 30, 0, 0), new Color(java.awt.Color.BLACK), // )//up side
////						8, new Point3D(-25, -25, 450)),
////				new Sphere(new Material(0.2, 0.6, 30, 0, 0), new Color(java.awt.Color.BLACK), // )//up side
////						8, new Point3D(-50+25/2, -50+25/2, 475)),
////				new Sphere(new Material(0.2, 0.6, 30, 0, 0), new Color(java.awt.Color.BLACK), // )//up side
////						8, new Point3D(-25/2.0, -25/2.0, 425)),
//			new Plane(new Point3D(0,0,550),new Vector(0,0,-1)).setEmission(Color.BLACK).setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(30).setKT(0).setKR(1)),
//				new Plane(new Point3D(0,150,0),new Vector(0,-1,0)).setEmission(Color.BLACK).setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(30).setKT(0).setKR(0))
//);
//		
//		
//		int z;
//		for (int j = -12; j < 1; j++) {
//			if (j % 2 == 0)
//				z = 1;
//			else
//				z = 0;
//			for (int i = -1; i < 4; i++) {
//				scene.addGeometries(new Triangle(
//						new Point3D(-50 * 2 + i * 200 + z * 100, 150, 450 + j * 100),
//						new Point3D(0 + i * 200 + z * 100, 150, 450 + j * 100),
//						new Point3D(-50 * 2 + i * 200 + z * 100, 150, 100 + 450 + j * 100))
//						.setEmission(Color.WHITE)
//						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)),						
//						new Triangle(
//								new Point3D(0 + i * 200 + z * 100, 150, 100 + 450 + j * 100),
//								new Point3D(0 + i * 200 + z * 100, 150, 450 + j * 100),
//								new Point3D(-50 * 2 + i * 200 + z * 100, 150, 100 + 450 + j * 100))
//						.setEmission(Color.WHITE)
//						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)));
//						
//			}
//		}
//		
//		for (int j = -12; j < 1; j++) {
//			if (j%2==0) 
//				z=0;
//			else
//				z=1;
//			for (int i = -1; i < 3; i++) {
//				scene.addGeometries(new Triangle(
//						new Point3D(-50*2+i*200+z*100,150, 450+j*100), 
//						new Point3D(0+i*200+z*100, 150, 450+j*100),
//						new Point3D(-50*2+i*200+z*100, 150, 100+450+j*100))
//						.setEmission(Color.BLACK)
//						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)),					
//						new Triangle(
//						new Point3D(0+i*200+z*100,150, 100+450+j*100),
//						new Point3D(0+i*200+z*100, 150, 450+j*100),
//						new Point3D(-50*2+i*200+z*100, 150, 100+450+j*100))
//						.setEmission(Color.BLACK)
//						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)));
//			}	
//		}
//		
//		
//		
//
//		scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2))
//				.setKl(0.0009).setKq(0.0000006));
//
//		ImageWriter imageWriter = new ImageWriter("mini_Project", 600, 600);
//		Render render = new Render().setImageWriter(imageWriter).setCamera(camera)
//				.setRayTracer(new RayTracerBasic(scene));
//
//		render.renderImage(alfa);
//		render.writeToImage();
//	}
