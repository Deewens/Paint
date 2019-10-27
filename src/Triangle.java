
public class Triangle extends ObjetBase {
	private Segment seg;

	public Triangle(Point2D pO, Segment seg) {
		super(pO);
		this.seg = seg;
	}

	public Segment getSeg() {
		return seg;
	}

	public void setSeg(Segment seg) {
		this.seg = seg;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}


}
