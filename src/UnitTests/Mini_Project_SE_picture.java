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
//front
		scene.geometries.add(
				
				new Triangle(
						new Point3D(48, 50, -10),
						new Point3D(-52, -40, -10),
						new Point3D(48, -40, -10) 
						)
				.setEmission(Color.LIGHTGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				new Triangle(
						new Point3D(48,50,-10),
						new Point3D(-52,50,-10),
						new Point3D(-52,-40,-10) 
						)
				.setEmission(Color.LIGHTGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
				//right
				new Triangle(
						new Point3D(48,50,-10),
						new Point3D(81,-83,-50),
						new Point3D(48,-40,-10) 
						)
				.setEmission(Color.DARKGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
				new Triangle(
						new Point3D(81,83,-50),
						new Point3D(48,50,-10),
						new Point3D(81,-83,-50) 
						)
				.setEmission(Color.DARKGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
//left
				new Triangle(
						new Point3D(-81,83,-50),
						new Point3D(-52,50,-10),
						new Point3D(-52, -40, -10) 
						)
				.setEmission(Color.DARKGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
				new Triangle(
						new Point3D(-80,83,-50),
						new Point3D(-52,-40,-10),
						new Point3D(-81,-83,-50) 
						)
				.setEmission(Color.DARKGREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
				
				//ceiling
				new Triangle(
						new Point3D(-82,83,-55),
						new Point3D(48,50,-15),
						new Point3D(-52,50,-15) 
						)
				.setEmission(Color.GREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.7).setnShininess(100).setKT(1)),
				

				new Triangle(
						new Point3D(81,83,-55),
						new Point3D(-82,83,-55),
						new Point3D(48,50,-15) 
						)
				.setEmission(Color.GREY)
				.setMaterial(new Material().setKd(0.5).setKs(0.7).setnShininess(100).setKT(1)),
				
				//Mirror
				new Polygon(new Point3D(50,30,11),new Point3D(90,23,80), new Point3D(90,-50,80),new Point3D(50,-32,11)) //ABCD
                .setMaterial(new Material().setKT(0).setKR(1).setKs(1).setnShininess(600))
				
				
//                new Polygon(new Point3D(-30,0,100),new Point3D(-30,-40,100), new Point3D(0,-40,100),new Point3D(0,0,100))//adcb
//                .setEmission(new Color(204,102,255))
//                .setMaterial(new Material().setKd(0.1).setKs(0.1).setnShininess(40)),
//        new Polygon(new Point3D(-20,-10,110),new Point3D(-20,-50,110), new Point3D(10,-50,110),new Point3D(10,-10,110))//ehgf
//              .setEmission(new Color(204,102,255))
//              .setMaterial(new Material().setKd(0.1).setKs(0.1).setnShininess(40)),
//        new Polygon(new Point3D(-30,0,100),new Point3D(0,0,100), new Point3D(10,-10,110),new Point3D(-20,-10,110))//abfe
//                .setEmission(new Color(204,102,255))
//                .setMaterial(new Material().setKd(0.1).setKs(0.1).setnShininess(300)),
//        new Polygon(new Point3D(-30,0,100),new Point3D(-30,-40,100), new Point3D(-20,-50,110),new Point3D(-20,-10,110))//adhe
//                .setEmission(new Color(204,102,255))
//                .setMaterial(new Material().setKd(0.1).setKs(0.1).setnShininess(300)),
//        new Polygon(new Point3D(0,-40,100),new Point3D(0,0,100), new Point3D(10,-10,110),new Point3D(10,-50,110))//cbfg
//                .setEmission(new Color(204,102,255))
//                .setMaterial(new Material().setKd(0.1).setKs(0.1).setnShininess(300)),
//        new Polygon(new Point3D(-20,-50,110),new Point3D(-30,-40,100), new Point3D(0,-40,100),new Point3D(10,-50,110))//hdcg
//                .setEmission(new Color(204,102,255))
//                .setMaterial(new Material().setKd(0.1).setKs(0.1).setnShininess(300)),
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
//				new Sphere(new Point3D(12, 43, -10), 10).setEmission(new Color(300, 0, 0))
//				.setMaterial(new Material().setKd(0.25).setKs(0.7).setnShininess(20).setKT(0.5)),
//		new Sphere(new Point3D(12, 43, -10), 5).setEmission(new Color(255,255,255))
//				.setMaterial(new Material().setKs(0.95).setnShininess(500)),
//				
//				
//		new Sphere(new Point3D(20, 10, -20), 5).setEmission(new Color(400, 300, 150))
//				.setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20).setKT(1)),
//				new Sphere(new Point3D(20, 10, -20), 10).setEmission(new Color(0, 0, 100))
//				.setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20).setKT(1)),
//				
//			
//				
//		new Sphere(new Point3D(-20, -25, -20), 20).setEmission(new Color(60, 20, 20))
//		 .setMaterial(new Material().setKT(0.5).setKR(0.95).setKs(0.95).setnShininess(500))
//				new Sphere(new Point3D(-30, 60, -10), 10).setEmission(new Color(0, 0, 100))
//				.setMaterial(new Material().setKd(0.25).setKs(1).setnShininess(20).setKT(0.5))
				
				

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
								.setEmission(Color.LIGHTGREY)
								.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)),
								new Triangle(
										new Point3D(-72+33*i, -70+13*j, -80),
										new Point3D(-38+40*i, -70+13*j, -80),
										new Point3D(-38+40*i,-83+13*j, -80))
								.setEmission(Color.LIGHTGREY)
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
						.setEmission(Color.LIGHTGREY)
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(20).setKT(0)),
						new Triangle(
								new Point3D(-72+33*i, -70+13*j, -80),
								new Point3D(-38+40*i, -70+13*j, -80),
								new Point3D(-38+40*i,-83+13*j, -80))
						.setEmission(Color.LIGHTGREY)
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
		.setEmission(Color.GREY)
		.setMaterial(new Material().setKd(0.5).setKs(0.7).setnShininess(100).setKT(1)),
		

		new Triangle(
				new Point3D(27.5,73,-30),
				new Point3D(-27.5,73,-30),
				new Point3D(24,63,51) 
				)
		.setEmission(Color.GREY)
		.setMaterial(new Material().setKd(0.5).setKs(0.7).setnShininess(100).setKT(1)));
					
		
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
                       


//       scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(0, 300, -400)
//               , new Vector(-1, 1, 2)).setKl(0.00001).setKq(0.000005));
       scene.lights.add(new DirectionalLight(new Color(java.awt.Color.darkGray),new Vector(-0.5, 0.5, 0)));

		
		
		
		

		

		
						
		
		
		scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point3D(-8.25, 6, -15), new Vector(0, 6480, 5346)) //
				.setKl(0.00001).setKq(0.00000001));
		
		
		scene.lights.add(new SpotLight(new Color(255, 0, 0), new Point3D(40.5,40,-50), new Vector(-0, -5, 1))
				.setKl(0.0004).setKq(0.000006));
		scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point3D(250, 500, 500), new Vector(-1, -1, -2))
				.setKl(0.0004).setKq(0.0000006));
		scene.lights.add(new DirectionalLight(new Color(255, 255, 255),new Vector( 1,1,1)));
		//scene.lights.add(new DirectionalLight(new Color(255, 255, 255),new Vector( 0, 1, 0)));
		//scene.lights.add(new DirectionalLight(new Color(255, 255, 255),new Vector( -1,1,1)));
		scene.lights.add(new DirectionalLight(new Color(255, 255, 255),new Vector( -1,-1,1)));
		
		
 
//
//    scene.lights.add(new PointLight(Color.WHITE, new Point3D(-30, 0, -10)) //
//			.setKl(0.0005).setKq(0.0005));

		Render render = new Render().setImageWriter(new ImageWriter("triangle", 500, 500)).setCamera(camera)
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage(alfa);
		render.writeToImage();
		
		
		
	
		
	}
	

	
	
}

