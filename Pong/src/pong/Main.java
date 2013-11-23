package pong;

import java.applet.AudioClip;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.text.StyledEditorKit.BoldAction;

public class Main extends JComponent implements 
         MouseMotionListener 
{
	URL hitAdress = getClass().getResource("hit.wav");
	AudioClip wavFile = JApplet.newAudioClip(hitAdress);
	JFrame awesomeLizzy;
	int ballx = 0;
	int awesomelizzyY = 60;
	int mouseY = 300;
	int elizabethXspeed = 1;
	int elizabethYspeed = 2;
	int awesomelizzyWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	int awesomelizzyHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	int paddleY = awesomelizzyHeight / 2;
	int score = 0;
	Rectangle2D.Double paddle = new Rectangle2D.Double(0, 200, 100, 300);
	Rectangle2D.Double otherpaddle = new Rectangle2D.Double(0, 200, 100, 300);
	Ellipse2D.Double ball = new Ellipse2D.Double(300, 300, 50, 50);

	public static void main(String[] args)
	{
		new Main().getGoing();
	}

	private void getGoing()
	{
		awesomeLizzy = new JFrame("Elizabeth's JFrame");
		awesomeLizzy.setVisible(true);
		awesomeLizzy.setSize(awesomelizzyWidth, awesomelizzyHeight);
		awesomeLizzy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		awesomeLizzy.add(this);
		awesomeLizzy.addMouseMotionListener(this);
	}

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.pink);
		g2.fill(ball);
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(5f));
		g2.draw(ball);
		ball.x = ball.x + elizabethXspeed;
		if (ball.x < 0)
		{
			g2.setFont(new Font("Arial", Font.PLAIN, 200));
			g2.drawString("You Lose!", 500, 500);
		}
		if (ball.x > awesomelizzyWidth)// right wall
		{
			elizabethXspeed = -elizabethXspeed;
		}
		ball.y = ball.y + elizabethYspeed;
		if (ball.y > awesomelizzyHeight - 200)// bottom wall
		{
			elizabethYspeed = -elizabethYspeed;
		}
		if (ball.y < 0)// top wall
		{
			elizabethYspeed = -elizabethYspeed;
		}
           paddle.y = mouseY;
		g2.setColor(Color.yellow);
		g2.fill(paddle);
		g2.setStroke(new BasicStroke(8f));
		g2.setColor(Color.black);
		g2.draw(paddle);
		
		repaint();
		if (ball.intersects(paddle))//ball hits paddle
		{
			wavFile.play();
			elizabethXspeed = -elizabethXspeed;
			score = score + 1;
			ball.x = 200; 
			elizabethXspeed = elizabethXspeed + 1;
			elizabethYspeed = elizabethYspeed + 1;
		}
		g2.setFont(new Font("Arial", Font.PLAIN, 200));
		g2.drawString(score + "", 1500, 200);
	}


	@Override
	public void mouseDragged(MouseEvent e)
	{
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		mouseY = e.getY();
	}
}
