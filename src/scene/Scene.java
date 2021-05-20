package scene;

import static primitives.Util.isZero;

import java.util.LinkedList;
import java.util.List;

import elements.AmbientLight;
import elements.Camera;
import elements.Camera.BuilderCamera;
import geometries.Geometries;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import elements.LightSource;

/**
 * * A class of a scene. Holds the objects to make a picture
 * 
 * @author da077
 *
 */
public class Scene {

	public String name;
	public Color background;
	public AmbientLight ambientLight;
	public Geometries geometries;

	public List<LightSource> lights;

	/**
	 * constructor
	 * 
	 * @param _name of scene
	 */
	public Scene(String _name) {
		name = _name;
		background = Color.BLACK;
		ambientLight = new AmbientLight( Color.BLACK,0);
		geometries = new Geometries();
		lights = new LinkedList<LightSource>();
	}

	/**
	 * to update the background and return all the scene
	 * @param _background= color for background
	 */
	public Scene setBackground(Color _background) {
		this.background = _background;
		return this;
	}

	/**
	 * to update the ambientlight of scene and return all the scene
	 * @param _ambientlight= light for scene
	 */
	public Scene setAmbientLight(AmbientLight _ambientlight) {
		this.ambientLight = _ambientlight;
		return this;
	}

	public Scene setGeometries(Geometries _geometries) {
		this.geometries = _geometries;
		return this;
	}

	public Scene setlights(List<LightSource> _lights) {
		this.lights = _lights;
		return this;
	}

	
	
	

}
