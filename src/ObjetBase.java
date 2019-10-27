
public abstract class ObjetBase extends PointOrigine {

	public ObjetBase(Point2D pO) {
		super(pO);
		// TODO Auto-generated constructor stub
		
		
	}
	
	public void deplacer(Point2D p) {
		this.pO = p;
	}
	
	public abstract void afficher();

}
