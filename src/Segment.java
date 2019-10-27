
public class Segment extends ObjetBase {
	private Point2D pE;
	
	public Segment(Point2D pO, Point2D pE) {
		super(pO);
		this.pE = pE;
		
		
	}
	
	public Point2D getpE() {
		return pE;
	}


	public void setpE(Point2D pE) {
		this.pE = pE;
	}

	public void afficher() {}
	
}
