
public class Ellipse extends ObjetBase {
	
	private int d1;
	private int d2;
	
	public Ellipse(Point2D pO, int d1, int d2) {
		super(pO);
		this.d1 = d1;
		this.d2 = d2;
	}

	public int getD1() {
		return d1;
	}

	public void setD1(int d1) {
		this.d1 = d1;
	}

	public int getD2() {
		return d2;
	}

	public void setD2(int d2) {
		this.d2 = d2;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}

}
