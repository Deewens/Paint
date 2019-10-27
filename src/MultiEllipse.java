
public class MultiEllipse extends ObjetCompo {
	private Ellipse[] ellipses;

	public MultiEllipse(Point2D pO, Ellipse[] ellipses) {
		super(pO);
		this.ellipses = ellipses;
	}

	public Ellipse[] getEllipses() {
		return ellipses;
	}

	public void setEllipses(Ellipse[] ellipses) {
		this.ellipses = ellipses;
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
