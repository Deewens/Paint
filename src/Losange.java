
public class Losange extends ObjetBase {
	private double h;
	private double l;
	
	public Losange(Point2D pO, double h, double l) {
		super(pO);
		this.h = h;
		this.l = l;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getL() {
		return l;
	}

	public void setL(double l) {
		this.l = l;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}


}
