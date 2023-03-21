
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class AllHandlers 
{
	JFrame frame;
	DrawingPanel canvas;
	public AllHandlers(){
		frame = new JFrame("AllHandlers.java");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new DrawingPanel();
		frame.getContentPane().add(canvas);
		frame.setVisible(true);
	}
	public static void main (String[] args) {
		AllHandlers kt = new AllHandlers();
	}
}
class DrawingPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
	private Timer cartimer;
	boolean car_direction = false;
	boolean color_moved = false;
	boolean color_dragged = false;
	boolean color_clicked = false;
	boolean color_pressed = false;
	boolean color_entered = false;
	boolean color_exited = false;
	boolean color_released = false;
	boolean color_letter  = false;
	boolean color_number = false;
	boolean color_char = false;
	boolean color_keycode = false;
	int [] xval = {260, 290,320, 290};
	int [] yval = {500, 470, 500, 530};
	public DrawingPanel() {
		carMover carmover = new carMover();
		cartimer = new Timer(5, carmover);
		cartimer.start();
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	class carMover implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (car_direction) {
				if (xval[0] < 0) {
					xval[0] = 540;
					xval[1] = 570;
					xval[2] = 600;
					xval[3] = 570;
				} else {
					xval[0]--;
					xval[1]--;
					xval[2]--;
					xval[3]--;
				}
			}
			else {
				if (xval[3] > 600) {
					xval[0] = 0;
					xval[1] = 30;
					xval[2] = 60;
					xval[3] = 30;
				} else {
					xval[0]++;
					xval[1]++;
					xval[2]++;
					xval[3]++;
				}
			}
			cartimer.stop();
			cartimer.start();
			repaint();
			grabFocus();
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int count = 0;
		int x_val= 10;
		g.drawString("Mouse", 10, 50);
		while (count!=4){
			((Graphics2D)g).setStroke(new BasicStroke(3));
			g.drawRect(x_val, 70, 125, 50);
			x_val +=125;
			count++;
		}
		count= 0;
		x_val = 10;
		while (count!=3){
			g.drawRect(x_val, 150, 125, 50);
			x_val +=125;
			count++;
		}
		count=0;
		x_val = 10;
		g.drawString("Keyboard",10, 300);
		while (count!=4){
			g.drawRect(x_val, 320, 125, 50);
			x_val +=125;
			count++;
		}
		x_val=40;
		String[] labels = {"Moved", "Dragged", "Entered", "Exited", "Clicked", "Pressed", "Released", "Letter", "Number", "Character", "KeyCode"};
		boolean[] colors = {color_moved, color_dragged, color_entered, color_exited, color_clicked, color_pressed, color_released, color_letter, color_number, color_char, color_keycode};
		Color inactiveColor = Color.black; // Set the inactive color
		for (int i = 0; i < labels.length; i++) {
			if (colors[i]) {
				g.setColor(Color.red);
			} else {
				g.setColor(inactiveColor); // Use the inactive color if no event is detected
			}
			if (i<4)
				g.drawString(labels[i], x_val, 100);
			else if(i==4) {
				x_val=40;
				g.drawString(labels[i], x_val, 180);
			}
			else if (i>4 && i<7) {
				g.drawString(labels[i], x_val, 180);
			}
			else if (i==7) {
				x_val=40;
				g.drawString(labels[i], x_val, 350);
			}
			else {
				g.drawString(labels[i], x_val, 350);
			}
			x_val+=125;
		}
		g.setColor(Color.blue);
		g.fillPolygon(xval, yval, 4);
	}

	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if ((int)c>=0 && (int)c<=127&& (int)c!=81 && (int)c!=113) {
			color_char = true;
			if ((int)c>= 65 && (int)c<=90|| (int)c>=97 && (int)c<=122) 
				color_letter = true;
			else if ((int)c>=48 && (int)c<=57) 
				color_number = true;
		}
	} 
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key==81||key == 113) {
			color_letter= false;
			color_number= false;
			color_char= false;
			color_keycode= false;
			color_moved= false;
			color_dragged= false;
			color_released = false;
			color_entered= false;
			color_exited= false;
			color_pressed= false;
			color_clicked=  false;
		}
		if (key == 16 || key == 17 || key==18 || key==38 || 
				key == 37|| key==39 ||key==40|| key==157||key==20) {
			color_keycode = true;
		}
		switch (key) {
		case 17:
			car_direction = !car_direction;
			break;
		}
		repaint();
	}
	public void keyReleased(KeyEvent e) {
	}
	public void mouseDragged(MouseEvent e) {
		color_dragged = true;
		repaint();
	}
	public void mouseMoved(MouseEvent e) {
		color_moved = true;
		repaint();
	}
	public void mouseClicked(MouseEvent e) {
		color_clicked = true;
		repaint();
	}
	public void mousePressed(MouseEvent e) {
		color_pressed = true;
		repaint();
	}
	public void mouseReleased(MouseEvent e) {
		color_released = true;
		repaint();
	}
	public void mouseEntered(MouseEvent e) {
		color_entered =true;
		repaint();
	}
	public void mouseExited(MouseEvent e) {
		color_exited= true;
		repaint();
	}
}



