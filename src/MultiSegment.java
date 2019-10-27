
public class MultiSegment extends ObjetCompo {
	
	private Segment[] segment;

	public MultiSegment(Point2D pO, Segment[] segment) {
		super(pO);
		this.segment = segment;
	}

	public Segment[] getSegment() {
		return segment;
	}

	public void setSegment(Segment[] segment) {
		this.segment = segment;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deplacer(Point2D p) {
		// TODO Auto-generated method stub
		
	}
}
