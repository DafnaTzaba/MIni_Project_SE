package primitives;

/**
 * class of material has parameters of shininess and specular
 * 	kT-Promotes transparency שקיפות מקדם
	-kR-Coefficient of reflection מקדם השתקפות 
	kD- diffuse
 * @author da077
 *
 */
public class Material {

	
	public double kS, kD,kT,kR ;
	public int nShininess;
	
	public Material() {
		this.kS=0;
		this.kD=0;
		this.nShininess=0;
		this.kT=0.0;
		this.kR=0.0;

	}
	
	//---setters-----
	//return the object in order to Allow threading
	/**
	 * setter
	 * @param kd
	 * @return object of the class
	 */
	public Material setKd(double kd) {
		this.kD=kd;
		return this;
	}
	/**
	 * setter
	 * @param ks
	 * @return object of the class
	 */
	public Material setKs(double ks) {
		this.kS=ks;
		return this;
	}
	/**
	 * setter
	 * @param _nShininess
	 * @return object of the class
	 */
	public Material setnShininess(int _nShininess) {
		this.nShininess=_nShininess;
		return this;
	}
	
	/**
	 * setter
	 * @param kt
	 * @return object of the class
	 */
	public Material setKT(double kt) {
		this.kT=kt;
		return this;
	}
	/**
	 * setter
	 * @param kr
	 * @return object of the class
	 */
	public Material setKR(double kr) {
		this.kR=kr;
		return this;
	}
}
