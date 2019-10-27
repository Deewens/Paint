
public class ArcDeCercle extends ObjetBase {
	private int d;
	private int angle;
	
	public ArcDeCercle(Point2D pO, int d, int angle) {
		super(pO);
		this.d = d;
		this.angle = angle;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}

	
	
}
