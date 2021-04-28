package elements;

import primitives.Color;

public class AmbientLight {
	
	private Color intensity ;

	public Color getIntensity() {
		return intensity;
	}
	
	/**
	 * constructor
	 * @param IA Original fill light (light intensity according to RGB components)
	 * @param KA Coefficient of attenuation of filler light
	 */
	public AmbientLight(Color IA, double KA) {
		intensity= IA.scale(KA);
	}
	

	


}
