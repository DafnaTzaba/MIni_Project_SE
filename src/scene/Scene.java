package scene;

import static primitives.Util.isZero;

import elements.AmbientLight;
import elements.Camera;
import elements.Camera.BuilderCamera;
import geometries.Geometries;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class Scene {

	public String name;
	public Color background;
	public AmbientLight ambientLight;
	public Geometries geometries;
	
	
	public Scene(String _name) {
		name= _name;
		background=Color.BLACK;
		ambientLight=new AmbientLight(Color.BLACK,0);
		geometries=new Geometries();
				
	}


	 public Scene setBackground(Color _background) {
	        this.background = _background;
	        return  this;
	    }

	    public Scene setAmbientLight(AmbientLight _ambientlight) {
	        this.ambientLight = _ambientlight;
	        return this;
	    }

	    public Scene setGeometries(Geometries _geometries) {
	        this.geometries = _geometries;
	        return  this;
	    }

	
	
	
	
	
	/*
	  private Scene(BuilderScene builder) {
		 name = builder.name;
		 background = builder.background;
		 ambientLight = builder.ambientLight;
		 geometries = builder.geometries;
	    }
	
	 /**
	  * Builder Class for Scene. ????
	  */
	/*
	public static class BuilderScene {
		public String name;
		public Color background;
		public AmbientLight ambientLight;
		public Geometries geometries;

		public BuilderScene setBackground(Color _background) {
			background = _background;
			return this;
		}

		
		public BuilderScene setAmbientLight(AmbientLight _ambientLight) {
			ambientLight = _ambientLight;
			return this;
		}

		public BuilderScene setGeometries(Geometries _geometries) {
			geometries = _geometries;
			return this;
		}

		public Scene build() {
			Scene scene = new Scene(this);
			return scene;
		}
		
		public BuilderScene(String _name) {
			name= _name;
			background=Color.BLACK;
			ambientLight=new AmbientLight(Color.BLACK,0);
			geometries=new Geometries();
					
		}

		

	}
	
	*/
}
