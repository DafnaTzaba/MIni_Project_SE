package elements;

import primitives.Color;

public class AmbientLight extends Light  {
	
	
	
	
	/**
	 * constructor, send to the father the Final power of ambient lighting 
	 * @param IA Original fill light (light intensity according to RGB components)
	 * @param KA Coefficient of attenuation of filler light
	 */
	public AmbientLight(Color IA, double KA) {
		super(IA.scale(KA));
	}
	

	


}
