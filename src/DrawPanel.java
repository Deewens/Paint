import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	
	private int xd;
	private int yd;
	
	public DrawPanel() {
        // addMouseListener(new MouseClick());
       // addMouseMotionListener(new MouseDragged());
		
	}
	
    class MouseClick extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            xd = e.getX();
            yd = e.getY();
            Graphics g = getGraphics();
            //g.drawRect(xd, yd, 50, 50);
            g.dispose();
        }
    }
    
    class MouseDragged extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            int x = e.getX(), y = e.getY();
            Graphics g = getGraphics();
            g.drawLine(xd, yd, x, y);
            //g.drawOval(xd, yd, x, y);
            xd = x;
            yd = y;
            g.dispose();
        }
    }    
	
}
