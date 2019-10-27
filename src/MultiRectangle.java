
public class MultiRectangle extends ObjetCompo {
	private Rectangles[] rectangle;

	public MultiRectangle(Point2D pO, Rectangles[] rectangle) {
		super(pO);
		this.rectangle = rectangle;
	}

	public Rectangles[] getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangles[] rectangle) {
		this.rectangle = rectangle;
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
