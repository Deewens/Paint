
public class Quadrangle extends ObjetBase {
	private double ray;
	private Point2D a1, a2, a3;
	
	public Quadrangle(Point2D pO, double ray, Point2D a1, Point2D a2, Point2D a3) {
		super(pO);
		this.ray = ray;
		this.setA1(a1);
		this.setA2(a2);
		this.setA3(a3);
	}

	public double getRay() {
		return ray;
	}

	public void setRay(double ray) {
		this.ray = ray;
	}



	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}

	public Point2D getA1() {
		return a1;
	}

	public void setA1(Point2D a1) {
		this.a1 = a1;
	}

	public Point2D getA2() {
		return a2;
	}

	public void setA2(Point2D a2) {
		this.a2 = a2;
	}

	public Point2D getA3() {
		return a3;
	}

	public void setA3(Point2D a3) {
		this.a3 = a3;
	}




}
