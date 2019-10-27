public class MultiCercle extends ObjetCompo {
	private Cercle[] cercles;

	public MultiCercle(Point2D pO, Cercle[] cercles) {
		super(pO);
		this.cercles = cercles;
	}

	public Cercle[] getCercles() {
		return cercles;
	}

	public void setCercles(Cercle[] cercles) {
		this.cercles = cercles;
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
