import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Window extends JFrame {
	
	static Point compCoords; // Contient les coordonnées de la souris : pour déplacer la fenêtre sans utiliser la barre de Windows
	
	int xd, yd;
	int sxd, syd;
	int choice;
	int tx = 0, ty = 0;
	
	Point2D origine = new Point2D(0, 0);
	Point2D inter = new Point2D(1000, 0);
	Point2D extrem = new Point2D(1000, 700);
	
	Segment segment1 = new Segment(origine, inter);
	Segment segment2 = new Segment(inter, extrem);
	
	int ouirect = 1;
	boolean first = true;  // Boolean permettant de détécter quel point a été placé lors de la création des formes
	
	boolean test = true;
	int comp = 0;	
	int xdc, ydc;
	int o = 0;
	
	int xs = 0, ys = 0;
	
	int ouicercle = 1;
	int ouiellipse = 1;
	
	int xt, yt;
	
	int cx, cy;
	
	String shape;
	int shapeId;
	
	String tailleS;
	int taille;
	Color color;
	
	boolean oui = true;
	
	int kl = 1;
	int ko = 1;
	
	int i = 0;
	
	int nb = 0;
	boolean compo = false;
    boolean compoSeg = false;
    boolean compoRect = false;
    boolean compoCercle = false;
    boolean compoEllipse = false;
	Segment[] tabSeg = new Segment[6];
    Rectangles[] gTabRect = new Rectangles[6];
    Cercle[] gTabCercle = new Cercle[6];
    Ellipse[] gTabEllipse = new Ellipse[6];
		
	ArrayList<ObjetBase> listObjetBase = new ArrayList<ObjetBase>(); // Contient la liste des objets de base
	ArrayList<ObjetCompo> listObjetCompo = new ArrayList<ObjetCompo>(); // Contient la liste des objets complexes
	
	
	Point2D po = new Point2D(xd, yd);
	
	/* --------------------MenuBar : paramètres concernant la barre de menu------------------------ */

	private JMenuBar menuBar = new JMenuBar();

	private JMenu item1 = new JMenu("Fichier"); // Menu "Fichier"
	private JMenuItem item1_1 = new JMenuItem("Ouvrir"); // Fichier -> Ouvrir
	private JMenuItem item1_2 = new JMenuItem("Enregistrer"); // Fichier -> Enregistrer
	private JMenuItem item1_3 = new JMenuItem("Enregistrer sous"); // Fichier -> Enregistrer sous
	private JMenuItem item1_4 = new JMenuItem("Fermer"); // Fichier -> Fermer

	private JMenu item2 = new JMenu("Edition"); // Menu "Edition"
	private JMenuItem item2_1 = new JMenuItem("Annuler");
	private JMenuItem item2_2 = new JMenuItem("Refaire");

	private JMenu item3 = new JMenu("Aide"); // Menu "Aides"

	private JButton exit = new JButton("X");
	private JButton extend = new JButton("H");
	private JButton minimize = new JButton("_");

	/* ---------------------Toolsbar : paramètre concernant la barre d'outils permettant de créer les formes---------------------- */

	final JPanel toolsBar = new JPanel();

	final JButton bTriangle = new JButton("Triangle");
	final JButton bLine = new JButton("Ligne");
	final JButton bQuadrangle = new JButton("Quadrangle");
	final JButton bLosange = new JButton("Losange");
	final JButton bCercle = new JButton("Cercle");
	final JButton bElipse = new JButton("Ellipse");
	final JButton bRectangle = new JButton("Rectangle");
	final JButton bArcCercle = new JButton("Arc de Cercle");
	final JButton bCrayon = new JButton("Crayon");
	final JButton bMultiSeg = new JButton("MultiSegment");
	final JButton bMultiRec = new JButton("MultiRectangle");
	final JButton bMultiEl = new JButton("MultiEllipse");
	final JButton bMultiCer = new JButton("MultiCercle");
	final JButton bErase = new JButton("Effacer");
	final JButton bEraseZone = new JButton("Effacer Zone");
	final JButton bSave = new JButton("Sauvegarder");

	/* ------------------- Création du drawPanel : panel ou l'on dessine --------------------*/
	final DrawPanel drawPanel = new DrawPanel();
	
	/* ------------------- bottomToolsBar : barre d'outil du bas de la fenêtre------------- */
	final JPanel bottomToolsBar = new JPanel();
	final JButton bColor = new JButton("Couleur");
	final JButton bSize = new JButton("Taille");
	final JButton bMove = new JButton("Déplacement");
	final JButton bFinCompo = new JButton("Fin");

	public Window() {
		
		this.setLayout(new BorderLayout());
		this.initMenu();
		

		/* Association des boutons aux différents choix possibles */
		bErase.addActionListener(new BEraseListener());
		bTriangle.addActionListener(new Choice());
		bLine.addActionListener(new Choice());
		bQuadrangle.addActionListener(new Choice());
		bLosange.addActionListener(new Choice());
		bCercle.addActionListener(new Choice());
		bElipse.addActionListener(new Choice());
		bRectangle.addActionListener(new Choice());
		bArcCercle.addActionListener(new Choice());
		bCrayon.addActionListener(new Choice());
		bMultiSeg.addActionListener(new Choice());
		bMultiRec.addActionListener(new Choice());
		bMultiCer.addActionListener(new Choice());
		bMultiEl.addActionListener(new Choice());
		bSize.addActionListener(new Choice());
		bColor.addActionListener(new Choice());
		bMove.addActionListener(new Choice());
		bEraseZone.addActionListener(new Choice());
		bFinCompo.addActionListener(new Choice());
		bSave.addActionListener(new Choice());
		
		/*----------------------------------------------------------*/
		
		
		drawPanel.setBackground(Color.WHITE);
		


		/* Dessine sur le draw panel avec la souris en fonction du choix des boutons */
		drawPanel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				
				Graphics2D g = (Graphics2D) drawPanel.getGraphics();
				g.setStroke(new BasicStroke(taille));
				g.setColor(color);
				
				if (choice == 1) { // Triangle
					
					test = true;
					
					if (first) { // Premier point
						
						Point2D pex = new Point2D(0, 0);
						
						pex.setX(e.getX());
						pex.setY(e.getY());
						
						xd = pex.getX();
						yd = pex.getY();
						
						
						
						sxd = xd;
						syd = yd;
						
						drawPoint();
						
						first = false;
						
					} else { // Deuxième point
						
						int x = 0, y = 0;
						
						Point2D pe = new Point2D(x, y);
						
						pe.setX(e.getX());
						pe.setY(e.getY());
						
						x = pe.getX();
						y = pe.getY();
						
						g.drawLine(xd, yd, x, y);
						
						xt = xd;
						yt = yd;
						
						xd = x;
						yd = y;
						drawPoint();
						comp++;
						
						if (comp == 2) {
							
							Point2D po = new Point2D(x, y);
							
							Point2D pex = new Point2D(sxd, syd);
							
							Segment seg = new Segment(pex, pe);
							
							g.drawLine(x, y, sxd, syd); 
							
							g.dispose();
							
							Triangle tri = new Triangle(pe, seg);
							
							first = true;
							
							comp = 0;
							
							listObjetBase.add(tri);
							System.out.println(listObjetBase.toString());
						}
	
					}
					
				}
				
				if (choice == 2) { // Segment
					
					test = true;
					
					if (first) { // Premier point
						
						po.setX(e.getX());
						po.setY(e.getY());
						
						xd = (int) po.getX();
						yd = (int) po.getY();
						
						drawPoint();
						
						first = false;
						
					} else { // Deuxième point
						
						int x = 0, y = 0;
						
						Point2D pe = new Point2D(x, y);
						
						pe.setX(e.getX());
						pe.setY(e.getY());
						
						x = (int) pe.getX();
						y = (int) pe.getY();
																	
						Segment segment = new Segment(po, pe);
						
						g.drawLine(xd, yd, x, y);
						
						xd = x;
						yd = y;
						drawPoint();
						g.dispose();	
						
						listObjetBase.add(segment);
						System.out.println(listObjetBase.toString());
						
						first = true; // Segment simple si je laisse le commentaires

					}
					
				}
				
				if (choice == 3) { // Quadrangle
					
					test = true;
					
					if (first) { // Premier point
						
						po.setX(e.getX());
						po.setY(e.getY());
						
						xd = po.getX();
						yd = po.getY();
						
						sxd = xd;
						syd = yd;
						
						// Point qu'on met
						drawPoint();
						
						first = false;
						
					} else { // Deuxième point
						
						int x = 0, y = 0;
						
						Point2D pe = new Point2D(x, y);
						
						pe.setX(e.getX());
						pe.setY(e.getY());
						
						x = pe.getX();
						y = pe.getY();
						
						g.drawLine(xd, yd, x, y);
						xd = x;
						yd = y;
						
						drawPoint();
						
						comp++;
						
						if (comp == 3) { // Troisième point
							
							g.drawLine(x, y, sxd, syd);
							
							Quadrangle quad = new Quadrangle(po, 1, pe, pe, pe);
							
							g.dispose();
							
							first = true;
							
							comp = 0;
						}
	
					}
					
				}
				
				if (choice == 4) { // Losange
					
					test = true;
					
					if (first) { // Premier point
						
						po.setX(e.getX());
						po.setY(e.getY());
						
						xd = (int) po.getX();
						yd = (int) po.getY();
						
						first = false;
						
					} else { // Deuxième point
						
						if (comp == 0) {
							
							int x = 0, y = 0;
							int a = 0, b = 0;
							
							Point2D pe = new Point2D(x, y);
							
							pe.setX(e.getX());
							pe.setY(e.getY());
							
							x = (int) pe.getX();
							y = (int) pe.getY();
							
							a = (xd+x)/2;
							b = (yd+y)/2;
							
							g.drawLine(xd, b, a, yd);
							g.drawLine(a, yd, x, b);
							g.drawLine(x, b, a, y);
							g.drawLine(a, y, xd, b);
							
							int h = 0, l = 0;
							
							h = y-yd;
							l = x-xd;
							
							po.setX(a);
							po.setY(yd);
							
							Losange los = new Losange(po, h, l);
							
							// g.drawLine(a, yd, a, y); // Ligne intérieure verticale
							// g.drawLine(xd, b, x, b); // Ligne intérieure horizontale
							
							g.dispose();
							
							listObjetBase.add(los);
							System.out.println(listObjetBase.toString());
							
						} 	
						
						first = true;
						
					}
					
				}
				
				if (choice == 5) { // Cercle
					
					test = true;
					
					if (first) { // Premier point
						
						po.setX(e.getX());
						po.setY(e.getY());
						
						xd = (int) po.getX();
						yd = (int) po.getY();
						
						first = false;
						
					} else { // Deuxième point
						
						int x = 0, y = 0;
						
						int h = 0, w = 0;
						
						Point2D pe = new Point2D(x, y);
						
						pe.setX(e.getX());
						pe.setY(e.getY());
						
						x = (int) pe.getX();
						y = (int) pe.getY();
						
						//g.drawLine(xd, yd, x, y);
						
						w = x - xd;
						h = w;
						
						//System.out.println(h);
						
						g.drawOval(xd, yd, w, h);
						
						g.dispose();
						
						Cercle cer = new Cercle(po, w);
						
						listObjetBase.add(cer);
						System.out.println(listObjetBase.toString());
						
						first = true; // Segment simple si je laisse le commentaire

					}
					
				}
				
				if (choice == 6) { // Ellipse
					
					test = true; 
					
					if (first) { // Premier point
						
						po.setX(e.getX());
						po.setY(e.getY());
						
						xd = (int) po.getX();
						yd = (int) po.getY();
						
						
						first = false;
						
					} else { // Deuxième point
						
						if (comp == 0) {
							
							int x = 0, y = 0;
							int a = 0, b = 0;
							int w = 0, h = 0;
							
							Point2D pe = new Point2D(x, y);
							
							pe.setX(e.getX());
							pe.setY(e.getY());
							
							x = (int) pe.getX();
							y = (int) pe.getY();
							
							a = (xd+x)/2;
							b = (yd+y)/2;
							
							w = (x-xd);
							h = (y-yd);
							
							g.drawOval(xd, yd, w, h);
							
							//po.setX(a);
							//po.setY(yd);
							
							//g.drawLine(a, yd, a, y);
							//g.drawLine(xd, b, x, b);
							//xd = po.getX();
							//yd = po.getY();

							// drawPoint();
							
							g.dispose();
							
							Ellipse eli = new Ellipse(po, w, h);
							
							listObjetBase.add(eli);
							System.out.println(listObjetBase.toString());
							
						} 	
						
						first = true; 
						
					}
					
				}
				
				if (choice == 7) { // Rectangle
					
					test = true;
					
					if (first) { // Premier point
						
						po.setX(e.getX());
						po.setY(e.getY());
						
						xd = po.getX();
						yd = po.getY();
						
						drawPoint();
						
						first = false;
						
					} else { // Deuxième point
						
						int x = 0, y = 0;
						
						Point2D pe = new Point2D(x, y);
						
						pe.setX(e.getX());
						pe.setY(e.getY());
						
						x = pe.getX();
						y = pe.getY();
						
						int w = 0, h = 0;
						
							
						w = Math.abs((x - xd));
							
						h = Math.abs((y - yd));
						
						Point2D pt = new Point2D(w, h);
						
						Segment seg1 = new Segment(po, pt);
						Segment seg2 = new Segment(pt, pe);
						
						Rectangles rect = new Rectangles(po, seg1, seg2);
						
						
						g.drawRect(xd, yd, w, h); // x, y, w, h
						xd = x;
						yd = y;
						
						drawPoint();
						g.dispose();
						
						listObjetBase.add(rect);
						System.out.println(listObjetBase.toString());
						
						first = true;
						
					}
					
				}
				
				if (choice == 8) { // Arc de cercle
					
					test = true;
					
					String angleS;
					int angle = 0;
					
					if (first) { // Premier point
						
						po.setX(e.getX());
						po.setY(e.getY());
						
						xd = (int) po.getX();
						yd = (int) po.getY();
						
						first = false;
						
					} else { // Deuxième point
						
						/* Choix du rayon de l'Arc */
						JOptionPane jop = new JOptionPane();
					    angleS = jop.showInputDialog(null, "Merci de saisir l'angle de l'arc en degré :", "Arc de cercle", JOptionPane.QUESTION_MESSAGE);
					    angle = Integer.parseInt(angleS);
					    /* ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ */
						
						int x = 0, y = 0;
						int h = 0, w = 0;

						Point2D pe = new Point2D(x, y);
						
						pe.setX(e.getX());
						pe.setY(e.getY());
						
						x = (int) pe.getX();
						y = (int) pe.getY();				
						
						w = Math.abs((x - xd));
						h = Math.abs((y - yd));
						
						g.drawArc(xd, yd, w, w, angle, angle);
						
						g.dispose();
						
						ArcDeCercle adc = new ArcDeCercle(po, w, angle);
						
						first = true; 
						
						listObjetBase.add(adc);
						System.out.println(listObjetBase.toString());

					}
				}
				
				if (choice == 9) { // Crayon, ne fonctionne pas
					
					test = true;
					
					drawPanel.addMouseListener(new MouseClick());
					drawPanel.addMouseMotionListener(new MouseDragged());

					
				}
				
				if (choice == 11) { // Multisegment
					
					compoSeg = true;
					
					compo = true;
					if (test) { // Premier point
						
						po.setX(e.getX());
						po.setY(e.getY());
						
						xd = (int) po.getX();
						yd = (int) po.getY();
						
						sxd = xd;
						syd = yd;
					
						//drawPoint();
						
						test = false;
						
					} else { // MultiSegment
						
						int x = 0, y = 0;
						
						Point2D pe = new Point2D(x, y);
						
						pe.setX(e.getX());
						pe.setY(e.getY());
						
						x = (int) pe.getX();
						y = (int) pe.getY();
						
						g.drawLine(xd, yd, x, y);
						
						xd = x;
						yd = y;
						
						po.setX(x);
						po.setY(y);
						
						nb++;
						Segment segment = new Segment(po, pe);
						tabSeg[nb] = segment;
						
						//drawPoint();
						g.dispose();
						
						listObjetBase.add(segment);
						
						System.out.println(nb);
						System.out.println(listObjetBase.toString());	
					}
				}
				
				if (choice == 12) { // MultiRectangle

					test = true;
					compoRect = true;

					if (first) { // Premier point

						po.setX(e.getX());
						po.setY(e.getY());

						xd = po.getX();
						yd = po.getY();

						// drawPoint();

						first = false;

					} else { // Second point

						int x = 0, y = 0;

						Point2D pe = new Point2D(x, y);

						pe.setX(e.getX());
						pe.setY(e.getY());

						x = pe.getX();
						y = pe.getY();

						int w = 0, h = 0;

						w = Math.abs((x - xd));

						h = Math.abs((y - yd));

						Point2D pt = new Point2D(w, h);

						Segment seg1 = new Segment(po, pt);
						Segment seg2 = new Segment(pt, pe);
						
						if (ouirect == 1) {
							tx = pe.getX();
							ty = pe.getY();
							ouirect++;
						}


						nb++;
						Rectangles rect = new Rectangles(po, seg1, seg2);
						gTabRect[nb] = rect;

						g.drawRect(xd, yd, w, h); // x, y, w, h

						xd = x;
						yd = y;

						// drawPoint();
						g.dispose();

						listObjetBase.add(rect);
						System.out.println(nb);
						System.out.println(listObjetBase.toString());

					}

				}

				if (choice == 13) { // MultiCercle

					test = true;
					compoCercle = true;

					if (first) {

						po.setX(e.getX());
						po.setY(e.getY());

						xd = (int) po.getX();
						yd = (int) po.getY();
						
						sxd = xd;
						syd = yd;

						first = false;

					} else {
						
						int x = 0, y = 0;

						int h = 0, w = 0;
						


						Point2D pe = new Point2D(x, y);

						pe.setX(e.getX());
						pe.setY(e.getY());
						

						x = (int) pe.getX();
						y = (int) pe.getY();

						if (ouicercle == 1) {
							xs = x;
							ys = y;
							ouicercle++;
						} 
						
						w = x - xd;
						h = w;

						// System.out.println(h);
						
						g.drawOval(xd, yd, w, h);

						g.dispose();

						nb++;
							
						if (kl == 1) {
							
							Cercle cer = new Cercle(po, w);
							gTabCercle[nb] = cer;							
							listObjetBase.add(cer);
							kl++;
							
						} else {
							
	
							
							Point2D npt = new Point2D(xs, ys);
							
							if (ouicercle == 2) {
								xs = x;
								ys = y;
								ouicercle++;
							}
							
							
							Cercle cer = new Cercle(npt, w);
							gTabCercle[nb] = cer;							
							listObjetBase.add(cer);
						}

							

							//Cercle cer = new Cercle(po, w);
							//gTabCercle[nb] = cer;																
							//listObjetBase.add(cer);
							
						
						

						System.out.println(nb);
						System.out.println(listObjetBase.toString());

						xd = x;
						yd = y;
						
						//po.setX(10);
						//po.setY(10);
							
						
						
						
					}

				}

				if (choice == 14) { // MultiEllipse

					test = true;
					compoEllipse = true;

					if (first) {

						po.setX(e.getX());
						po.setY(e.getY());

						xd = (int) po.getX();
						yd = (int) po.getY();
						
						sxd = xd;
						syd = yd;

						first = false;

					} else {

						if (comp == 0) {

							int x = 0, y = 0;
							int a = 0, b = 0;
							int w = 0, h = 0;

							Point2D pe = new Point2D(x, y);

							pe.setX(e.getX());
							pe.setY(e.getY());

							x = (int) pe.getX();
							y = (int) pe.getY();
							
							if (ouiellipse == 1) {
								xs = x;
								ys = y;
								ouiellipse++;
							} 

							a = (xd + x) / 2;
							b = (yd + y) / 2;

							w = (x - xd);
							h = (y - yd);

							g.drawOval(xd, yd, w, h);

							po.setX(a);
							po.setY(yd);

							g.dispose();

							nb++;
							
							if (ko == 1) {
								
								Ellipse eli = new Ellipse(po, w, h);
								gTabEllipse[nb] = eli;							
								listObjetBase.add(eli);
								ko++;
								
							} else {
								
		
								
								Point2D npt = new Point2D(xs, ys);
								
								if (ouiellipse == 2) {
									xs = x;
									ys = y;
									ouiellipse++;
								}
								
								
								Ellipse eli = new Ellipse(npt, w, h);
								gTabEllipse[nb] = eli;							
								listObjetBase.add(eli);
							}


							System.out.println(nb);
							System.out.println(listObjetBase.toString());

							xd = x;
							yd = y;

						}
					}

				}
				
				if (choice == 20) {
					
					Segment seg = (Segment) listObjetBase.get(o); // On récupère le bon ID
					  
					  int valX1 = 0 , valY1 = 0;
					  				  
					  String point = "pO";
					  
					  valX1 = e.getX();
					  valY1 = e.getY();
					  /* ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
					  
					  Point2D pt = new Point2D(valX1, valY1);
					  
					  
					  
					  g.setColor(Color.WHITE);
					  g.setStroke(new BasicStroke(6)); // Pour effacer les points d'extrémité, on met la même taille
					  drawPointE(seg.getpO().getX(), seg.getpO().getY()); // Effacement de l'ancien point
					  g.setStroke(new BasicStroke(taille)); // On remet la taille par défaut
					  g.drawLine(seg.getpO().getX(), seg.getpO().getY(), seg.getpE().getX(), seg.getpE().getY()); // Effacement de l'ancienne ligne
					  
					  /* On récupère l'ancienne couleur */
					  if(g.getColor() != Color.WHITE) {
						  g.setColor(color);
					  }
					  else {
						  g.setColor(Color.BLACK);
					  }
					  
					  
					  /* Création du nouveau point et reliage */
					
						  
					  	  seg.setpO(pt);	
						  
					  	  int xo = seg.getpO().getX();
						  int yo = seg.getpO().getY();
						  
						  
						  int xn = seg.getpE().getX();
						  int yn = seg.getpE().getY();
						  
						  xd = xo;
						  yd = yo;
						  drawPoint(xd, yd);

						  g.drawLine(xo, yo, xn, yn);
						  
						  xd = xn;
						  yd = yn;
						  drawPoint(xd, yd);
					  

				}
				
				if (choice == 22) {
					
					  Losange los = (Losange) listObjetBase.get(o); // Séléction du bon ID
					  
					  int valX1 = 0 , valY1 = 0;
					  
					  valX1 = e.getX();
					  valY1 = e.getY();
					  
					  /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
					  
					  Point2D pt = new Point2D(valX1, valY1);
					  
					  int demiL = (int) (los.getL() / 2);
					  int demiH = (int) (los.getH() / 2);
					  
					  g.setColor(Color.WHITE);
					  g.setStroke(new BasicStroke(5));
					  
					  g.drawLine(los.getpO().getX(), los.getpO().getY(), los.getpO().getX() - demiL, los.getpO().getY() + demiH);
					  g.drawLine(los.getpO().getX(), los.getpO().getY(), los.getpO().getX() + demiL, los.getpO().getY() + demiH);
					  g.drawLine(los.getpO().getX(), los.getpO().getY() + 2 * demiH, los.getpO().getX() - demiL, los.getpO().getY() + demiH);
					  g.drawLine(los.getpO().getX(), los.getpO().getY() + 2 * demiH, los.getpO().getX() + demiL, los.getpO().getY() + demiH);
					  
					  los.setpO(pt);
					  
					  /* On récupère l'ancienne couleur */
					  if(g.getColor() != Color.WHITE) {
						  g.setColor(color);
					  }
					  else {
						  g.setColor(Color.BLACK);
					  }
					  
					  g.setColor(Color.BLACK);
					  g.setStroke(new BasicStroke(taille));
					  
					  g.drawLine(los.getpO().getX(), los.getpO().getY(), los.getpO().getX() - demiL, los.getpO().getY() + demiH);
					  g.drawLine(los.getpO().getX(), los.getpO().getY(), los.getpO().getX() + demiL, los.getpO().getY() + demiH);
					  g.drawLine(los.getpO().getX(), los.getpO().getY() + 2 * demiH, los.getpO().getX() - demiL, los.getpO().getY() + demiH);
					  g.drawLine(los.getpO().getX(), los.getpO().getY() + 2 * demiH, los.getpO().getX() + demiL, los.getpO().getY() + demiH);

				}
				
				if (choice == 23) {
					
					Triangle tri = (Triangle) listObjetBase.get(o); // On récupère le bon ID
					  
					  int valX1 = 0 , valY1 = 0;
					  
					  valX1 = e.getX();
					  valY1 = e.getY();
					  				  
					  /* ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
					  
					  Point2D pt = new Point2D(valX1, valY1);
					  
					  g.setColor(Color.WHITE);
					  g.setStroke(new BasicStroke(5)); // Pour effacer les points d'extrémité, on met la même taille
					  drawPointE(tri.getpO().getX(), tri.getpO().getY());
					  g.setStroke(new BasicStroke(taille)); // On remet la taille par défaut
					  g.drawLine(tri.getSeg().getpE().getX(), tri.getSeg().getpE().getY(), tri.getSeg().getpO().getX(), tri.getSeg().getpO().getY()); // Effacement de l'ancienne ligne
					  g.drawLine(xt, yt, tri.getpO().getX(), tri.getpO().getY()); // Effacement de l'ancienne ligne
					  
					  
					  /* Remise de la bonne couleur */
					  if(g.getColor() != Color.WHITE) {
						  g.setColor(color);
					  }
					  else {
						  g.setColor(Color.BLACK);
					  }
					  
					  /* Création du nouveau point */						  
					tri.getSeg().getpE().setX(valX1);
					tri.getSeg().getpE().setY(valY1);
						  
					g.drawLine(xt, yt, tri.getpO().getX(), tri.getpO().getY());
					g.drawLine(tri.getSeg().getpE().getX(), tri.getSeg().getpE().getY(), tri.getSeg().getpO().getX(), tri.getSeg().getpO().getY());
						  
				}
				
				if (choice == 24) {
					
					Cercle cer = (Cercle) listObjetBase.get(o); // Séléction du bon ID
					  
					  int valX1 = 0 , valY1 = 0;		  
			  
					  valX1 = e.getX();
					  valY1 = e.getY();	  
					  				  
					  /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
					  
					  Point2D pt = new Point2D(valX1, valY1);
					  
					  g.setColor(Color.WHITE);
					  g.drawOval(cer.getpO().getX(), cer.getpO().getY(), cer.getD(), cer.getD()); // Effacement de l'ancien cercle
					  
					  /* On récupère l'ancienne couleur */
					  if(g.getColor() != Color.WHITE) {
						  g.setColor(color);
					  }
					  else {
						  g.setColor(Color.BLACK);
					  }
					  
					  cer.setpO(pt);
					  
					  g.drawOval(valX1, valY1, cer.getD(), cer.getD());
					  					  
				}
				
				if (choice == 25) {
					
					 Ellipse eli = (Ellipse) listObjetBase.get(o);
					  
					  int valX1 = 0 , valY1 = 0;
					  
					  valX1 = e.getX();
					  valY1 = e.getY();
					  
					  /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
					  
					  Point2D pt = new Point2D(valX1, valY1);
					  
					  g.setColor(Color.WHITE);
					  g.drawOval(eli.getpO().getX(), eli.getpO().getY(), eli.getD1(), eli.getD2());

					  /* On récupère l'ancienne couleur */
					  if(g.getColor() != Color.WHITE) {
						  g.setColor(color);
					  }
					  else {
						  g.setColor(Color.BLACK);
					  }
					  
					  eli.setpO(pt);
					  
					  g.drawOval(valX1, valY1, eli.getD1(), eli.getD2());
					  
					  
				}
				
				if (choice == 26) {
					
					Rectangles rect = (Rectangles) listObjetBase.get(o);
					  
					  int valX1 = 0 , valY1 = 0;
					  
					  valX1 = e.getX();
					  valY1 = e.getY();
					  
					  /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
					  
					  Point2D pt = new Point2D(valX1, valY1);
			  
					  int w = Math.abs(rect.getSeg2().getpE().getX() - rect.getpO().getX());
					  int h = Math.abs(rect.getSeg2().getpE().getY() - rect.getpO().getY());

					  g.setColor(Color.WHITE);
					  g.drawRect(rect.getpO().getX(), rect.getpO().getY(), w, h); // Effacement de l'ancien rectangle

					  drawPointE(rect.getpO().getX(), rect.getpO().getY());
					  drawPointE(rect.getpO().getX() + w, rect.getpO().getY() + h);
				  
					  /* On récupère l'ancienne couleur */
					  if(g.getColor() != Color.WHITE) {
						  g.setColor(color);
					  }
					  else {
						  g.setColor(Color.BLACK);
					  }
					  
					  rect.setpO(pt);
					  g.drawRect(valX1, valY1, w, h);  
					  
					   Object[] options = {"OK"};
					   int n = JOptionPane.showOptionDialog(null,
					                   "Veuillez chosir une nouvelle forme à placer","Ok",
					                   JOptionPane.PLAIN_MESSAGE,
					                   JOptionPane.QUESTION_MESSAGE,
					                   null,
					                   options,
					                   options[0]);
					   
					   choice = 0;
					  
				}
				
				if (choice == 27) {
				
					ArcDeCercle adc = (ArcDeCercle) listObjetBase.get(o);
					  
					  int angle, w;
					  
					  angle = adc.getAngle();
					  w = adc.getD();
					  
					  int a = 0, b = 0;
					  
					  a = adc.getpO().getX();
					  b = adc.getpO().getY();
					  
					  Point2D po = new Point2D(a,b);
					  
					  g.setColor(Color.WHITE);
					  g.drawArc(a, b, w, w, angle, angle);
					  
					  /* On récupère l'ancienne couleur */
					  if(g.getColor() != Color.WHITE) {
						  g.setColor(color);
					  }
					  else {
						  g.setColor(Color.BLACK);
					  }
					  
					  int valX1 = 0 , valY1 = 0;
					  
					  valX1 = e.getX();
					  valY1 = e.getY();
					  
					  /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
					  
					  Point2D pt = new Point2D(valX1, valY1);
					  
					  g.drawArc(pt.getX(), pt.getY(), w, w, angle, angle);
					  
					  adc.setpO(pt);
	  
					  
				}
				
				if (choice == 33) {
					
					int valX1 = 0, valY1 = 0;
					
					valX1 = e.getX();
					valY1 = e.getY();
					
					
					MultiSegment mulseg = (MultiSegment) listObjetCompo.get(o);
					
					int save = 0;
					

                    Segment[] tseg = new Segment[mulseg.getSegment().length];
                    
                    tseg = mulseg.getSegment();
                    
                    int h = 0;
                    
                    for(int j = 0; j < tseg.length; j++) {
                    
                    	if (tseg[j] != null) {
                            h++;
                    	}
                     }
                    
                    
                    Segment[] ntabseg = new Segment[h+1];
                    
                    for (int i = 1; i < h+1; i++) {
                    	ntabseg[i] = tseg[i];
                    }
                    
                    if (h == 1) {
                    	
                        g.setColor(Color.WHITE);
                        g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY(), sxd, syd);
                        g.setColor(Color.BLACK);
                        
                        if (valY1 < syd) {
                        	g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() - valY1, sxd, syd - valY1);
                        }
                        
                        if (valY1 > syd) {
                        	g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() + valY1/2, sxd, syd + valY1/2);
                        }
                    }
                    
                    if (h == 2) {
                    	
                    	g.setColor(Color.WHITE);
                        g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY(), ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY());
                        g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY(), sxd, syd);
                        g.setColor(Color.BLACK);	
                        
                        if (valY1 < syd) {
                        	g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() - valY1, sxd, syd - valY1);
                        	g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() - valY1, ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() - valY1);
                        }
                        
                        if (valY1 > syd) {
                        	g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() + valY1/2, sxd, syd + valY1/2);
                        	g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() + valY1/2, ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() + valY1/2);
                        }
                    }
                    
                    if (h == 3) {
                    	
                        g.setColor(Color.WHITE);
                        g.drawLine(ntabseg[1].getpO().getX(), ntabseg[1].getpO().getY(), ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY());
                        g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY(), ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY());
                        g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY(), sxd, syd);
                        g.setColor(Color.BLACK);
                        
                        if (valY1 < syd) {
                        	
                        	g.drawLine(ntabseg[1].getpO().getX(), ntabseg[1].getpO().getY() - valY1, ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() - valY1);
                        	g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() - valY1, sxd, syd - valY1);
                        	g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() - valY1, ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() - valY1);
                        }
                        
                        if (valY1 > syd) {
                        	
                        	g.drawLine(ntabseg[1].getpO().getX(), ntabseg[1].getpO().getY() + valY1, ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() + valY1);
                        	g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() + valY1/2, sxd, syd + valY1/2);
                        	g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() + valY1/2, ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() + valY1/2);
                        }
                        
                    }
                    
                    if (h == 4) {
                    	
                        g.setColor(Color.WHITE);
                        g.drawLine(ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY(), ntabseg[3].getpE().getX(), ntabseg[3].getpE().getY());
                        g.drawLine(ntabseg[3].getpE().getX(), ntabseg[3].getpE().getY(), ntabseg[1].getpO().getX(), ntabseg[1].getpO().getY());
                        g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY(), ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY());
                        g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY(), sxd, syd);
                        g.setColor(Color.BLACK);
                    	
                        
                        if (valY1 < syd) {
                            g.drawLine(ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() - valY1, ntabseg[3].getpE().getX(), ntabseg[3].getpE().getY() - valY1);
                            g.drawLine(ntabseg[3].getpE().getX(), ntabseg[3].getpE().getY() - valY1, ntabseg[1].getpO().getX(), ntabseg[1].getpO().getY() - valY1);
                            g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() - valY1, ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() - valY1);
                            g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() - valY1, sxd, syd - valY1);
                        }
                        
                        if (valY1 > syd) {
                            g.drawLine(ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() + valY1/2, ntabseg[3].getpE().getX(), ntabseg[3].getpE().getY() + valY1/2);
                            g.drawLine(ntabseg[3].getpE().getX(), ntabseg[3].getpE().getY() + valY1/2, ntabseg[1].getpO().getX(), ntabseg[1].getpO().getY() + valY1/2);
                            g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() + valY1/2, ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() + valY1/2);
                            g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() + valY1/2, sxd, syd + valY1/2);
                        }
                    }
                    
                    if (h == 5) {
                    	
                        g.setColor(Color.WHITE);
                        g.drawLine(ntabseg[4].getpE().getX(), ntabseg[4].getpE().getY(), ntabseg[2].getpO().getX(), ntabseg[3].getpO().getY());
                        g.drawLine(ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY(), ntabseg[3].getpE().getX(), ntabseg[3].getpE().getY());
                        g.drawLine(ntabseg[3].getpE().getX(), ntabseg[3].getpE().getY(), ntabseg[4].getpE().getX(), ntabseg[4].getpE().getY());
                        g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY(), ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY());
                        g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY(), sxd, syd);
                        g.setColor(Color.BLACK);
                    	
                        
                        if (valY1 < syd) {
                        	
                            g.drawLine(ntabseg[4].getpE().getX(), ntabseg[4].getpE().getY() - valY1, ntabseg[2].getpO().getX(), ntabseg[3].getpO().getY() - valY1);
                            g.drawLine(ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() - valY1, ntabseg[3].getpE().getX(), ntabseg[3].getpE().getY() - valY1);
                            g.drawLine(ntabseg[3].getpE().getX(), ntabseg[3].getpE().getY() - valY1, ntabseg[4].getpE().getX(), ntabseg[4].getpE().getY() - valY1);
                            g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() - valY1, ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() - valY1);
                            g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() - valY1, sxd, syd - valY1);
                        }
                        
                        if (valY1 > syd) {
                        	
                            g.drawLine(ntabseg[4].getpE().getX(), ntabseg[4].getpE().getY() + valY1/2, ntabseg[2].getpO().getX(), ntabseg[3].getpO().getY() + valY1/2);
                            g.drawLine(ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() + valY1/2, ntabseg[3].getpE().getX(), ntabseg[3].getpE().getY() + valY1/2);
                            g.drawLine(ntabseg[3].getpE().getX(), ntabseg[3].getpE().getY() + valY1/2,ntabseg[4].getpE().getX(), ntabseg[4].getpE().getY() + valY1/2);
                            g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() + valY1/2, ntabseg[2].getpE().getX(), ntabseg[2].getpE().getY() + valY1/2);
                            g.drawLine(ntabseg[1].getpE().getX(), ntabseg[1].getpE().getY() + valY1/2, sxd, syd + valY1/2);
                        }
                        
                    }
					
					g.setColor(Color.BLACK);
					
				}
				
				if (choice == 34) {
					
					int valX1 = 0, valY1 = 0;
					
					valX1 = e.getX();
					valY1 = e.getY();
					
					
					MultiRectangle mulrect = (MultiRectangle) listObjetCompo.get(o);
					
					int save = 0;
					

                    Rectangles[] trec = new Rectangles[mulrect.getRectangle().length];
                    
                    trec = mulrect.getRectangle();
                    
                    int h = 0;
                    
                    for(int j = 0; j < trec.length; j++) {
                    
                    	if (trec[j] != null) {
                            h++;
                    	}
                     }
                    
                    
                    Rectangles[] ntabrec = new Rectangles[h+1];
                    
                    for (int i = 1; i < h+1; i++) {
                    	ntabrec[i] = trec[i];
                    }
                    
                    if (h == 1) {
                    	
                    	g.setColor(Color.WHITE);
                    	g.drawRect(ntabrec[1].getSeg1().getpO().getX(), ntabrec[1].getSeg1().getpO().getY(), ntabrec[1].getSeg2().getpO().getX(), ntabrec[1].getSeg2().getpO().getY());
                    	g.setColor(Color.BLACK);
                    	
                    	if (valY1 < syd) {
                    		g.drawRect(ntabrec[1].getSeg1().getpO().getX(), ntabrec[1].getSeg1().getpO().getY() - valY1, ntabrec[1].getSeg2().getpO().getX(), ntabrec[1].getSeg2().getpO().getY());
                    	}
                    	
                    	if (valY1 > syd) {
                    		g.drawRect(ntabrec[1].getSeg1().getpO().getX(), ntabrec[1].getSeg1().getpO().getY() + valY1/2, ntabrec[1].getSeg2().getpO().getX(), ntabrec[1].getSeg2().getpO().getY());
                    	}
                    }
                    
                    if (h == 2) {
                    	
                    	g.setColor(Color.WHITE);
                    	g.drawRect(ntabrec[1].getSeg1().getpO().getX(), ntabrec[1].getSeg1().getpO().getY(), ntabrec[1].getSeg2().getpO().getX(), ntabrec[1].getSeg2().getpO().getY());
                    	g.drawRect(tx, ty,ntabrec[2].getSeg2().getpO().getX(), ntabrec[2].getSeg2().getpO().getY());
                    	g.setColor(Color.BLACK);
                    	
                    	if (valY1 < syd) {
                        	g.drawRect(ntabrec[1].getSeg1().getpO().getX() - valY1, ntabrec[1].getSeg1().getpO().getY(), ntabrec[1].getSeg2().getpO().getX(), ntabrec[1].getSeg2().getpO().getY());
                        	g.drawRect(tx, ty - valY1,ntabrec[2].getSeg2().getpO().getX(), ntabrec[2].getSeg2().getpO().getY());
                    	}
                    	
                    	if (valY1 > syd) {
                        	g.drawRect(ntabrec[1].getSeg1().getpO().getX(), ntabrec[1].getSeg1().getpO().getY() + valY1/2, ntabrec[1].getSeg2().getpO().getX(), ntabrec[1].getSeg2().getpO().getY());
                        	g.drawRect(tx, ty + valY1/2, ntabrec[2].getSeg2().getpO().getX(), ntabrec[2].getSeg2().getpO().getY());
                    	}
                    } 
				
				}
				
				
				
				if (choice == 35) {
					
					int valX1 = 0, valY1 = 0;
					
					valX1 = e.getX();
					valY1 = e.getY();
					
					
					MultiCercle mulcer = (MultiCercle) listObjetCompo.get(o);
					
					int save = 0;
					

                    Cercle[] tcer = new Cercle[mulcer.getCercles().length];
                    
                    tcer = mulcer.getCercles();
                    
                    int h = 0;
                    
                    for(int j = 0; j < tcer.length; j++) {
                    
                    	if (tcer[j] != null) {
                            h++;
                    	}
                     }
                    
                    
                    Cercle[] ntabcer = new Cercle[h+1];
                    
                    for (int i = 1; i < h+1; i++) {
                    	ntabcer[i] = tcer[i];
                    }
                    
                    if (h == 1) {
                    	                   	
                    	g.setColor(Color.WHITE);
                    	g.drawOval(ntabcer[1].getpO().getX(), ntabcer[1].getpO().getY(), ntabcer[1].getD(),ntabcer[1].getD());
                    	g.setColor(Color.BLACK);
                    	
                                  	
                    	if (valY1 < syd) {
                        	g.drawOval(ntabcer[1].getpO().getX(), ntabcer[1].getpO().getY() - valY1, ntabcer[1].getD(),ntabcer[1].getD());
                    	}
                    	
                    	if (valY1 > syd) {
                        	g.drawOval(ntabcer[1].getpO().getX(), ntabcer[1].getpO().getY() + valY1/2, ntabcer[1].getD(),ntabcer[1].getD());
                    	}
                    }
                    
                    if (h == 2) {
                    	
                    	g.setColor(Color.WHITE);
                    	g.drawOval(ntabcer[1].getpO().getX(), ntabcer[1].getpO().getY(), ntabcer[1].getD(),ntabcer[1].getD());
                    	g.drawOval(ntabcer[2].getpO().getX(), ntabcer[2].getpO().getY(), ntabcer[2].getD(),ntabcer[2].getD());
                    	g.setColor(Color.BLACK);
                    	
                    	if (valY1 < syd) {
                        	g.drawOval(ntabcer[1].getpO().getX(), ntabcer[1].getpO().getY() - valY1, ntabcer[1].getD(),ntabcer[1].getD());
                        	g.drawOval(ntabcer[2].getpO().getX(), ntabcer[2].getpO().getY() - valY1, ntabcer[2].getD(),ntabcer[2].getD());
                    	}
                    	
                    	if (valY1 > syd) {
                        	g.drawOval(ntabcer[1].getpO().getX(), ntabcer[1].getpO().getY() + valY1/2, ntabcer[1].getD(),ntabcer[1].getD());
                        	g.drawOval(ntabcer[2].getpO().getX(), ntabcer[2].getpO().getY() + valY1/2, ntabcer[2].getD(),ntabcer[2].getD());
                    	}
                    }
                    
                    if (h == 3) {
                    	
                       	g.setColor(Color.WHITE);
                    	g.drawOval(ntabcer[1].getpO().getX(), ntabcer[1].getpO().getY(), ntabcer[1].getD(),ntabcer[1].getD());
                    	g.drawOval(ntabcer[2].getpO().getX(), ntabcer[2].getpO().getY(), ntabcer[2].getD(),ntabcer[2].getD());
                    	g.drawOval(ntabcer[3].getpO().getX(), ntabcer[3].getpO().getY(), ntabcer[3].getD(),ntabcer[3].getD());
                    	g.setColor(Color.BLACK);
    
                    	
                    	if (valY1 < syd) {
                        	g.drawOval(ntabcer[1].getpO().getX(), ntabcer[1].getpO().getY() - valY1, ntabcer[1].getD(),ntabcer[1].getD());
                        	g.drawOval(ntabcer[2].getpO().getX(), ntabcer[2].getpO().getY() - valY1, ntabcer[2].getD(),ntabcer[2].getD());
                        	g.drawOval(ntabcer[3].getpO().getX(), ntabcer[3].getpO().getY() - valY1, ntabcer[3].getD(),ntabcer[3].getD());
                    	}
                    	
                    	if (valY1 > syd) {
                        	g.drawOval(ntabcer[1].getpO().getX(), ntabcer[1].getpO().getY() + valY1/2, ntabcer[1].getD(),ntabcer[1].getD());
                        	g.drawOval(ntabcer[2].getpO().getX(), ntabcer[2].getpO().getY() + valY1/2, ntabcer[2].getD(),ntabcer[2].getD());
                        	g.drawOval(ntabcer[3].getpO().getX(), ntabcer[3].getpO().getY() + valY1/2, ntabcer[3].getD(),ntabcer[3].getD());
                    	}
                    }
                    
                    
                    
				}
				
				if (choice == 36) {

					int valX1 = 0, valY1 = 0;
					
					valX1 = e.getX();
					valY1 = e.getY();
					
					
					MultiEllipse muleli = (MultiEllipse) listObjetCompo.get(o);
					
					int save = 0;
					

                    Ellipse[] teli = new Ellipse[muleli.getEllipses().length];
                    
                    teli = muleli.getEllipses();
                    
                    int h = 0;
                    
                    for(int j = 0; j < teli.length; j++) {
                    
                    	if (teli[j] != null) {
                            h++;
                    	}
                     }
                    
                    
                    Ellipse[] ntabeli = new Ellipse[h+1];
                    
                    for (int i = 1; i < h+1; i++) {
                    	ntabeli[i] = teli[i];
                    }
                    
                    if (h == 1) {
	                   	
                    	g.setColor(Color.WHITE);
                    	g.drawOval(ntabeli[1].getpO().getX() - ntabeli[1].getD1()/2, ntabeli[1].getpO().getY(), ntabeli[1].getD1(), ntabeli[1].getD2());
                    	g.setColor(Color.BLACK);
                    	
                                  	
                    	if (valY1 < syd) {
                        	g.drawOval(ntabeli[1].getpO().getX(), ntabeli[1].getpO().getY() - valY1, ntabeli[1].getD1(), ntabeli[1].getD2());
                    	}
                    	
                    	if (valY1 > syd) {
                        	g.drawOval(ntabeli[1].getpO().getX(), ntabeli[1].getpO().getY() + valY1/2, ntabeli[1].getD1(), ntabeli[1].getD2());
                    	}
                    }
                    
                    if (h == 2) {
                    	
                    	g.setColor(Color.WHITE);
                    	g.drawOval(sxd, syd, ntabeli[1].getD1(), ntabeli[1].getD2());
                    	g.drawOval(ntabeli[2].getpO().getX(), ntabeli[2].getpO().getY(), ntabeli[2].getD1(), ntabeli[2].getD2());
                    	g.setColor(Color.BLACK);
                    	
                    	if (valY1 < syd) {
                        	g.drawOval(sxd, syd - valY1, ntabeli[1].getD1(), ntabeli[1].getD2());
                        	g.drawOval(ntabeli[2].getpO().getX(), ntabeli[2].getpO().getY() - valY1, ntabeli[2].getD1(), ntabeli[2].getD2());
                    	}
                    	
                    	if (valY1 > syd) {
                        	g.drawOval(sxd, syd + valY1/2, ntabeli[1].getD1(), ntabeli[1].getD2());
                        	g.drawOval(ntabeli[2].getpO().getX(), ntabeli[2].getpO().getY() + valY1/2, ntabeli[2].getD1(), ntabeli[2].getD2());
                    	}
                    }
                    
                    if (h == 3) {
                    	
                       	g.setColor(Color.WHITE);
                    	g.drawOval(sxd, syd, ntabeli[1].getD1(), ntabeli[1].getD2());
                    	g.drawOval(ntabeli[2].getpO().getX(), ntabeli[2].getpO().getY(), ntabeli[2].getD1(), ntabeli[2].getD2());
                    	g.drawOval(ntabeli[3].getpO().getX(), ntabeli[3].getpO().getY(), ntabeli[3].getD1(), ntabeli[3].getD2());
                    	g.setColor(Color.BLACK);
    
                    	
                    	if (valY1 < syd) {
                        	g.drawOval(sxd, syd - valY1, ntabeli[1].getD1(), ntabeli[1].getD2());
                        	g.drawOval(ntabeli[2].getpO().getX(), ntabeli[2].getpO().getY() - valY1, ntabeli[2].getD1(), ntabeli[2].getD2());
                        	g.drawOval(ntabeli[3].getpO().getX(), ntabeli[3].getpO().getY() - valY1, ntabeli[3].getD1(), ntabeli[3].getD2());
                    	}
                    	
                    	if (valY1 > syd) {
                        	g.drawOval(sxd, syd + valY1/2, ntabeli[1].getD1(), ntabeli[1].getD2());
                        	g.drawOval(ntabeli[2].getpO().getX(), ntabeli[2].getpO().getY() + valY1/2, ntabeli[2].getD1(), ntabeli[2].getD2());
                        	g.drawOval(ntabeli[3].getpO().getX(), ntabeli[3].getpO().getY() + valY1/2, ntabeli[3].getD1(), ntabeli[3].getD2());
                    	}
                    }
                    
				}
				
				
				
				
				if (choice == 18) { // Effacer une partie de ce que l'on veut avec un rectangle
					
					test = true;
					
					if (first) {
						
						po.setX(e.getX());
						po.setY(e.getY());
						
						xd = po.getX();
						yd = po.getY();
						
						drawPoint();
						
						first = false;
						
					} else { 
						
						int x = 0, y = 0;
						
						Point2D pe = new Point2D(x, y);
						
						pe.setX(e.getX());
						pe.setY(e.getY());
						
						x = pe.getX();
						y = pe.getY();
						
						int w = 0, h = 0;
						
							
						w = Math.abs((x - xd));
							
						h = Math.abs((y - yd));

						g.clearRect(xd-2, yd-2, w+2, h+2);
						g.setBackground(Color.WHITE);
						
						first = true;
					}
				}

			}
			
			public void mouseEntered(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}

		});


		this.initLeftToolBar(); // Initialise la barre de menu à gauche
		this.initBottomToolBar(); // Initialise le menu du bas

		this.getContentPane().add(drawPanel, BorderLayout.CENTER);
		this.setTitle("Paint : Francois Arthur - Dudon Adrien");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 700);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(true);
		
	}
	
	/* Dessine simplement un point */
	private void drawPoint() {
		
		Graphics2D g = (Graphics2D) drawPanel.getGraphics();
		
		Stroke s = g.getStroke();

		g.setStroke(new BasicStroke(5));
		g.drawLine(xd, yd, xd, yd);
		g.setStroke(s);
	}
	
	/* Dessine un point à des coordonnées donné */
	private void drawPoint(int x, int y) {
		Graphics2D g = (Graphics2D) drawPanel.getGraphics();
		
		Stroke s = g.getStroke();

		g.setStroke(new BasicStroke(5));
		g.drawLine(x, y, x, y);
		g.setStroke(s);
		g.dispose();
		g.setColor(color);
	}
	
	
	
	/* Dessine un point à des coordonnées donné */
	private void drawPointE(int x, int y) {
		Graphics2D g = (Graphics2D) drawPanel.getGraphics();
		
		Stroke s = g.getStroke();

		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(5));
		g.drawLine(x, y, x, y);
		g.setStroke(s);
		g.dispose();
		g.setColor(color);
	}

	private void initMenu() {
		
		/* Déplacement de la fenêtre (du JFrame) */
		compCoords = null;
		menuBar.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) { /* Récupère l'endroit ou se trouve la souris au clic dans le menu */
				compCoords = e.getPoint();

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				compCoords = null;

			}
		});

		menuBar.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) { // Lorsque l'on bouge la souris après avoir cliqué, permet de bouger la fenêtre en bougant la souris
				Point currCoords = arg0.getLocationOnScreen();
				setLocation(currCoords.x - compCoords.x, currCoords.y - compCoords.y);
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		/* ---------------------------------------------------------------------------------------- */
		
		/* Initialisation de la barre de menu sur le JFrame */
		this.menuBar.add(item1);
		this.menuBar.add(item2);
		this.menuBar.add(item3);
		this.menuBar.add(Box.createHorizontalGlue());
		this.menuBar.add(minimize);
		this.menuBar.add(extend);
		this.menuBar.add(exit);
		this.setJMenuBar(menuBar);

		// Ajout SOUS-MENU ITEM 1 :

		this.item1.add(item1_1); // Ajout du sous-menu "Ouvrir"
		this.item1.add(item1_2); // Ajout du sous-menu "Enregistrer"
		this.item1.add(item1_3); // Ajout du sous-menu "Enregistrer sous"
		this.item1.addSeparator(); // SÃ©parateur entre "Enregistrer sous" et "Fermer"
		this.item1.add(item1_4); // Ajout du sous-menu "Fermer"

		// Ajout sous-menu ITEM 2 :

		this.item2.add(item2_1); // Ajout "Annuler"
		this.item2.add(item2_2); // Ajout "Refaire"

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		minimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});

		this.setJMenuBar(menuBar);
		
		/* ---------------------------------------------------------------------------------------- */
	}
	
	/* Initialise la barre de menu du bas */
	private void initBottomToolBar() {
		bottomToolsBar.setPreferredSize(new Dimension(150, 50));
		bottomToolsBar.setBackground(Color.GRAY);
		
		bColor.setPreferredSize(new Dimension(130, 30));
		bMove.setPreferredSize(new Dimension(130, 30));
		bFinCompo.setPreferredSize(new Dimension(130, 30));
		bSave.setPreferredSize(new Dimension(130, 30));
		
		/* Initialisation du slider pour changer la taille */
		 JSlider slide = new JSlider();
		 slide.setMaximum(100);
		 slide.setMinimum(0);
		 slide.setValue(0);
		 slide.setPaintTicks(true);
		 slide.setPaintLabels(true);
		 slide.setMinorTickSpacing(10);
		 slide.setMajorTickSpacing(20);
		 slide.addChangeListener(new ChangeListener(){
		 public void stateChanged(ChangeEvent event){
			 taille = ((JSlider)event.getSource()).getValue();
			 }
		 });
		
		 slide.setPreferredSize(new Dimension(200, 40));
		 /* ---------------------------------------------------------------- */
		 
		bottomToolsBar.add(slide);
		bottomToolsBar.add(bColor);
		bottomToolsBar.add(bMove);
		bottomToolsBar.add(bFinCompo);
		bottomToolsBar.add(bSave);
		
		this.getContentPane().add(bottomToolsBar, BorderLayout.SOUTH);
	}
	
	/* Initisalition de la barre du menu de gauche */
	private void initLeftToolBar() {
		
		toolsBar.setBackground(Color.GRAY);
		toolsBar.setPreferredSize(new Dimension(150, 150)); // Taille de la toolsBar
		
		/* Taille de chaque boutons */
		bTriangle.setPreferredSize(new Dimension(130, 35));
		bLine.setPreferredSize(new Dimension(130, 35));
		bQuadrangle.setPreferredSize(new Dimension(130, 35));
		bLosange.setPreferredSize(new Dimension(130, 35));
		bErase.setPreferredSize(new Dimension(130, 35));
		bCercle.setPreferredSize(new Dimension(130, 35));
		bElipse.setPreferredSize(new Dimension(130, 35));
		bRectangle.setPreferredSize(new Dimension(130, 35));
		bArcCercle.setPreferredSize(new Dimension(130, 35));
		bCrayon.setPreferredSize(new Dimension(130, 35));
		bMultiSeg.setPreferredSize(new Dimension(130, 35));
		bMultiRec.setPreferredSize(new Dimension(130, 35));
		bMultiCer.setPreferredSize(new Dimension(130, 35));
		bMultiEl.setPreferredSize(new Dimension(130, 35));
		bEraseZone.setPreferredSize(new Dimension(130, 35));

		toolsBar.add(bTriangle);
		toolsBar.add(bLine);
		toolsBar.add(bQuadrangle);
		toolsBar.add(bLosange);
		toolsBar.add(bCercle);
		toolsBar.add(bElipse);
		toolsBar.add(bRectangle);
		toolsBar.add(bArcCercle);
		toolsBar.add(bCrayon);
		toolsBar.add(bMultiSeg);
		toolsBar.add(bMultiRec);
		toolsBar.add(bMultiCer);
		toolsBar.add(bMultiEl);
		toolsBar.add(bErase);
		toolsBar.add(bEraseZone);
		

		this.getContentPane().add(toolsBar, BorderLayout.WEST);
	}
	
	// Bouton "Effacer"
	class BEraseListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			drawPanel.repaint();
			drawPanel.setBackground(Color.WHITE);
			listObjetBase.clear();
			listObjetCompo.clear();
			test = true;
		}
	}
	
	
    class MouseClick extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            cx = e.getX();
            cy = e.getY();
            Graphics g = drawPanel.getGraphics();
            // g.dispose();
        }
    }
	
    class MouseDragged extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            int x = e.getX(), y = e.getY();
            Graphics g = drawPanel.getGraphics();
            g.drawLine(cx, cy, x, y);
            cx = x;
            cy = y;
            g.dispose();
        }
    } 
	
	/* Permet de détecter quand on appuie sur un bouton */
	class Choice implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("Triangle")) {
				choice = 1;
			}

			if (e.getActionCommand().equals("Ligne")) {
				choice = 2;
			}

			if (e.getActionCommand().equals("Quadrangle")) {
				choice = 3;
			}

			if (e.getActionCommand().equals("Losange")) {
				choice = 4;
			}

			if (e.getActionCommand().equals("Cercle")) {
				choice = 5;
			}

			if (e.getActionCommand().equals("Ellipse")) {
				choice = 6;
			}

			if (e.getActionCommand().equals("Rectangle")) {
				choice = 7;			
			}

			if (e.getActionCommand().equals("Arc de Cercle")) {
				choice = 8;
			}

			if (e.getActionCommand().equals("Crayon")) { // Ne fonctionne pas bien
				choice = 9;
				
			    Object[] options = {"OK"};
			    int n = JOptionPane.showOptionDialog(null,
			                   "Cliquer sur la zone de dessin pour commencer ","Cliquer",
			                   JOptionPane.PLAIN_MESSAGE,
			                   JOptionPane.QUESTION_MESSAGE,
			                   null,
			                   options,
			                   options[0]);

				  
				
			}

			if (e.getActionCommand().equals("Effacer")) {
				choice = 10;
			}
			
			if (e.getActionCommand().equals("MultiSegment")) {
				choice = 11;
			}
			
			if (e.getActionCommand().equals("MultiRectangle")) {
				choice = 12;
			}
			
			if (e.getActionCommand().equals("MultiCercle")) {
				choice = 13;
			}
			
			if (e.getActionCommand().equals("MultiEllipse")) {
				choice = 14;
			}
			
			if(e.getActionCommand().equals("Taille")) {
				choice = 15;
			}
			
			if(e.getActionCommand().equals("Sauvegarder")) {
				choice = 40;
				
				System.out.println(drawPanel.getLocationOnScreen());
				System.out.println(drawPanel.getLocationOnScreen());
				
				try {
					Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
					BufferedImage capture = new Robot().createScreenCapture(screenRect);
					capture = capture.getSubimage((int) drawPanel.getLocationOnScreen().getX(), 100, 850, 600);
					File imagefile = new File("./screen.png");
					imagefile.createNewFile();
					ImageIO.write(capture, "png", imagefile);
					
					System.out.println("Screen !");
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,  ex);
				}
				
			}
			
			if(e.getActionCommand().equals("Couleur")) {
				choice = 16;
						
				color = JColorChooser.showDialog(null,  "Choisissez une couleur:", Color.BLACK);
			}
			
			if(e.getActionCommand().equals("Déplacement")) {
				choice = 17;
				
				Graphics2D g = (Graphics2D) drawPanel.getGraphics();
				
				  String[] f = new String[2];
				  
				  f[0] = "Objet Base";
				  f[1] = "Objet Compo";
				
				  JOptionPane jope = new JOptionPane();
				  String choix = (String) jope.showInputDialog(null, "Que voulez-vous déplacer ?", "Objet : ", JOptionPane.QUESTION_MESSAGE, null, f, f[0]); // Affiche la liste des objets à déplacer
				  
				if(choix.equals("Objet Base")) {
				
				if(!listObjetBase.isEmpty()) { // Si la liste des objets est vide, aucune boite de dialogue ne s'affichera
					int somme = 0;
					
					somme = listObjetBase.size();

					String[] t = new String[somme];
					
					/* On met la liste de tout les objets dans un tableau afin d'être utilisé dans une boite de dialogue */
					for (shapeId = 0; shapeId < somme; shapeId++) {
						t[shapeId] = listObjetBase.get(shapeId).toString();
					}
					
					
				/* Boite de dialogue affichant la liste des objets à séléctionner pour déplacer */
				  JOptionPane jop = new JOptionPane();
				  String shape = (String) jop.showInputDialog(null, "Choisissez l'objet à déplacer", "Objet", JOptionPane.QUESTION_MESSAGE, null, t, t[0]); // Affiche la liste des objets à déplacer
				  
									  

				  for (shapeId = 0; shapeId < listObjetBase.size(); shapeId++) {
					 if (listObjetBase.get(shapeId).toString().equals(shape)) {
						 o = shapeId; // On récupère l'ID de l'objet pour séléctionner celui qu'on déplace
					 }
				  }
				  
				  String[] save = shape.split("@");
				  
				  String nshape = save[0];

				  /* Déplacement en fontion du type de formes */
				  if (nshape.equals("Triangle")) {
					  
					  choice = 23;
				  }
				  
				  if (nshape.equals("Segment")) {
					  
					  choice = 20;
					  
				  }
				  
				  if (nshape.equals("Quandrangle")) {
					  
					  System.out.println("Quandrangle");
					  
				  }
				  
				  if (nshape.equals("Losange")) {
					  
					  choice = 22;
				  }
				  
				  if (nshape.equals("Cercle")) {
					  
					  choice = 24;
				  }
				 					  
				  if (nshape.equals("Ellipse")) {
					  
					 choice = 25;
					 
				  }
				  
				  if (nshape.equals("Rectangle")) {
					  
					  choice = 26;
					  
				  }
				  
				  if (nshape.equals("ArcDeCercle")) {
					  
					  choice = 27;
				  }
				  	
				}
			} else {
				
				if(!listObjetCompo.isEmpty()) { // Si la liste des objets est vide, aucune boite de dialogue ne s'affichera
					int somme = 0;
					
					somme = listObjetCompo.size();

					String[] t = new String[somme];
					
					/* On met la liste de tout les objets dans un tableau afin d'être utilisé dans une boite de dialogue */
					for (shapeId = 0; shapeId < somme; shapeId++) {
						t[shapeId] = listObjetCompo.get(shapeId).toString();
					}
					
					
				/* Boite de dialogue affichant la liste des objets à séléctionner pour déplacer */
				  JOptionPane jop = new JOptionPane();
				  String shape = (String) jop.showInputDialog(null, "Choisissez l'objet à déplacer", "Objet", JOptionPane.QUESTION_MESSAGE, null, t, t[0]); // Affiche la liste des objets à déplacer
				  
									  

				  for (shapeId = 0; shapeId < listObjetCompo.size(); shapeId++) {
					 if (listObjetCompo.get(shapeId).toString().equals(shape)) {
						 o = shapeId; // On récupère l'ID de l'objet pour séléctionner celui qu'on déplace
					 }
				  }
				  
				  String[] save = shape.split("@");
				  
				  String nshape = save[0];

				  /* Déplacement en fontion du type de formes */
				  if (nshape.equals("MultiSegment")) {
					  
					  choice = 33;
				  }
				  
				  if (nshape.equals("MultiRectangle")) {
					  
					  choice = 34;
					  
				  }
				  
				  if (nshape.equals("MultiCercle")) {
					  
					  choice = 35;
				  }
				  
				  if (nshape.equals("MultiEllipse")) {
					  
					  choice = 36;
				  }
		  	
				}
					
				}
			}
			
			if(e.getActionCommand().equals("Effacer Zone")) {
				choice = 18;
			}
			
			if(e.getActionCommand().equals("Fin")) {
				
				if (e.getActionCommand().equals("Fin")) {
					choice = 30;
					System.out.println(choice);
					
					if (compoSeg) {
						MultiSegment multiSeg = new MultiSegment(po, tabSeg);
						listObjetCompo.add(multiSeg);
						System.out.println(listObjetCompo.toString());

						nb = 0;
						compoSeg = false;
					} else if (compoRect) {
						MultiRectangle multiRect = new MultiRectangle(po, gTabRect);
						listObjetCompo.add(multiRect);
						System.out.println(listObjetCompo.toString());

						nb = 0;
						compoRect = false;
					} else if (compoCercle) {
						MultiCercle multiCercle = new MultiCercle(po, gTabCercle);
						listObjetCompo.add(multiCercle);
						System.out.println(listObjetCompo.toString());

						nb = 0;
						compoCercle = false;
					} else if (compoEllipse) {
						MultiEllipse multiEllipse = new MultiEllipse(po, gTabEllipse);
						listObjetCompo.add(multiEllipse);
						System.out.println(listObjetCompo.toString());

						nb = 0;
						compoEllipse = false;
					}
				}
				
				choice = 30;
				System.out.println(choice);
				if(compo) {
					MultiSegment multiSeg = new MultiSegment(po, tabSeg);
					listObjetCompo.add(multiSeg);
					System.out.println(listObjetCompo.toString());
					
					compo = false;
				}
			}
		}
	}
}
