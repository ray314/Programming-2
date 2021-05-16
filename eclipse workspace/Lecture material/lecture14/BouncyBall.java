/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecture14;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

/**
 *
 * @author sehall
 */
public class BouncyBall extends JPanel implements ActionListener{
    public final int PANEL_WIDTH = 400; public final int PANEL_HEIGHT = 400;
    private DrawingPanel drawPanel;  //innerclass
    private Timer timer;
    private int x, y, xMovement,yMovement,ballSize;
    
    public BouncyBall() {
        super(new BorderLayout());   //invoke super class Jpanel constructor use BorderLayout
        x = PANEL_WIDTH/2;
        y = PANEL_HEIGHT/2;
        xMovement = 3;
        yMovement = 2;
        ballSize = 30;
        drawPanel = new DrawingPanel();     //create DrawingPanel and add to center
        add(drawPanel, BorderLayout.CENTER); 
        timer = new Timer(5,this);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == timer)
        {   x += xMovement;
            y += yMovement;
            //check x and y not outside world bounds and invert movement if so
            if(x <=0 || x+ballSize >= drawPanel.getWidth()) xMovement = -xMovement;
            if(y <=0 || y+ballSize >= drawPanel.getHeight()) yMovement = -yMovement;
        }
        drawPanel.repaint();
    }
    //seperate inner class for graphics
    private class DrawingPanel extends JPanel
    {   public DrawingPanel()
        {   setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            setBackground(Color.WHITE);
            
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.fillOval(x,y,ballSize,ballSize); 
        }
    }
    public static void main(String[] args) {
        BouncyBall myPanel = new BouncyBall();
        JFrame frame = new JFrame("Bouncy Ball"); //create frame to hold our JPanel subclass	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(myPanel);  //add instance of MyGUI to the frame
        frame.pack(); //resize frame to fit our Jpanel
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
	//show the frame	
        frame.setVisible(true);
    }
}