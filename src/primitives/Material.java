package primitives;

/**
 * class of material has parameters of shininess and specular 
 * @author da077
 *
 */
public class Material {

	public double kS, kD;
	public int nShininess;
	
	public Material() {
		this.kS=0;
		this.kD=0;
		this.nShininess=0;

	}
	
	//---setters-----
	//return the object in order to Allow threading
	public Material setKd(double kd) {
		this.kD=kd;
		return this;
	}
	public Material setKs(double ks) {
		this.kS=ks;
		return this;
	}
	public Material setnShininess(int _nShininess) {
		this.nShininess=_nShininess;
		return this;
	}
}
