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
	private double alfa =10;

	@Test
	public void Mini_Project_se() {

		
		Camera camera = new Camera.BuilderCamera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
				.setViewPlaneHeight(150).setViewPlaneWidth(150).setDistance(1000).build();
		
		
		
		
		//scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.3));
//front
		scene.geometries.add(
				
				new Triangle(
						new Point3D(48, 50, -10),
						new Point3D(-52, -40, -10),
						new Point3D(48, -40, -10) 
						)
				.setEmission(new Color(255,245,221))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				new Triangle(
						new Point3D(48,50,-10),
						new Point3D(-52,50,-10),
						new Point3D(-52,-40,-10) 
						)
				.setEmission(new Color(255,245,221))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
				//right
				new Triangle(
						new Point3D(48,50,-10),
						new Point3D(81,-100,-50),
						new Point3D(48,-40,-10) 
						)
				.setEmission(Color.LIGHTGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
				new Triangle(
						new Point3D(81,83,-50),
						new Point3D(48,50,-10),
						new Point3D(81,-100,-50) 
						)
				.setEmission(Color.LIGHTGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
//left
				new Triangle(
						new Point3D(-81,83,-50),
						new Point3D(-52,50,-10),
						new Point3D(-52, -40, -10) 
						)
				.setEmission(Color.LIGHTGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
				new Triangle(
						new Point3D(-80,83,-50),
						new Point3D(-52,-40,-10),
						new Point3D(-81,-83,-50) 
						)
				.setEmission(Color.LIGHTGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
				//ceiling
				new Triangle(
						new Point3D(-82,83,-55),
						new Point3D(48,50,-15),
						new Point3D(-52,50,-15) 
						)
				.setEmission(Color.LIGHTGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.7).setnShininess(100).setKT(1)),
				

				new Triangle(
						new Point3D(81,83,-55),
						new Point3D(-82,83,-55),
						new Point3D(48,50,-15) 
						)
				.setEmission(Color.LIGHTGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.7).setnShininess(100).setKT(1)),
				
				//Mirror
				new Polygon(new Point3D(50,30,11),new Point3D(90,23,80), new Point3D(90,-50,80),new Point3D(50,-32,11)) //ABCD
                .setMaterial(new Material().setKT(0).setKR(1).setKs(1).setnShininess(600))
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
								.setEmission(new Color(161,15,15))
								.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)),
								new Triangle(
										new Point3D(-72+33*i, -70+13*j, -80),
										new Point3D(-38+40*i, -70+13*j, -80),
										new Point3D(-38+40*i,-83+13*j, -80))
								.setEmission(new Color(161,15,15))
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
						.setEmission(new Color(161,15,15))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)),
						new Triangle(
								new Point3D(-72+33*i, -70+13*j, -80),
								new Point3D(-38+40*i, -70+13*j, -80),
								new Point3D(-38+40*i,-83+13*j, -80))
						.setEmission(new Color(161,15,15))
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
						
						
			
		//ceiling light
		scene.addGeometries(
				new Triangle(
				new Point3D(-27.5,73,-30),
				new Point3D(24,63,51),
				new Point3D(-26.5,63,51) 
				)
		.setEmission(Color.WHITE)
		.setMaterial(new Material().setKd(0.5).setKs(0.7).setnShininess(100).setKT(1)),
		
		

		new Triangle(
				new Point3D(27.5,73,-30),
				new Point3D(-27.5,73,-30),
				new Point3D(24,63,51) 
				)
		.setEmission(Color.WHITE)
		.setMaterial(new Material().setKd(0.5).setKs(0.7).setnShininess(100).setKT(1)));
					
				scene.lights.add(new DirectionalLight(new Color(50, 50, 50),new Vector(0.53365,-0.10362,-0.83933)));
				 scene.geometries.add(
			               new Sphere(new Point3D(-29, -19, 5), 25)
			                       .setEmission(new Color(0, 25, 50))
			                       .setMaterial(new Material().setKd(0.8).setKs(0.8).setnShininess(200).setKT(0).setKR(0)),
			                       
			                       new Sphere(new Point3D(-5, -35, 45), 15)
			                       .setEmission(new Color(0, 25, 50))
			                       .setMaterial(new Material().setKd(0.8).setKs(0.8).setnShininess(200).setKT(0.1).setKR(0)),
			                       
			                       new Sphere(new Point3D(8, -25, 15), 18)
			                       .setEmission(new Color(0, 25, 50))
			                       .setMaterial(new Material().setKd(0.8).setKs(0.8).setnShininess(200).setKT(0.4).setKR(0))
			                       );


		 scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(48, 50, -10)
	               , new Vector(-150, -10,10)).setKl(0.00001).setKq(0.00005));
		
	       scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-52, 50, -10)
	               , new Vector(133, 133, -40)).setKl(0.00001).setKq(0.00009));
	       
	       scene.lights.add(new DirectionalLight(new Color(java.awt.Color.darkGray),new Vector(-0.5, 0.5, 0)));


		Render render = new Render().setImageWriter(new ImageWriter("triangle", 500, 500)).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene)).setMultithreading(3)
						.setDebugPrint();
		render.renderImage(alfa);
		render.writeToImage();
		
		
		
	
		
	}
	

	
	
}

