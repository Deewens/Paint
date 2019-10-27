import java.awt.Dimension;

public class Rectangles extends ObjetBase {
	private Segment seg1;
	private Segment seg2;
	
	public Rectangles(Point2D pO, Segment seg1, Segment seg2) {
		super(pO);
		this.seg1 = seg1;
		this.seg2 = seg2;
	}


	public Segment getSeg1() {
		return seg1;
	}

	public void setSeg1(Segment seg1) {
		this.seg1 = seg1;
	}

	public Segment getSeg2() {
		return seg2;
	}

	public void setSeg2(Segment seg2) {
		this.seg2 = seg2;
	}

	public void afficher() {
		// TODO Auto-generated method stub
		
	}
}
