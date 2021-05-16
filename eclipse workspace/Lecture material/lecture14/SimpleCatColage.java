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
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author sehall
 */
public class SimpleCatColage extends JPanel{
    public final int PANEL_WIDTH = 400;
    public final int PANEL_HEIGHT = 400;
    private DrawingPanel drawPanel;  //innerclass
    private ImageIcon icon;
    
    public SimpleCatColage() {
        super(new BorderLayout());   //invoke super class Jpanel constructor use BorderLayout
        icon = new ImageIcon("cat.jpg");
        drawPanel = new DrawingPanel();     //create DrawingPanel and add to center
        add(drawPanel, BorderLayout.CENTER);  
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
            Image i = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            icon.setImage(i);
            icon.paintIcon(this, g, 0, 0);
        }
    }
    public static void main(String[] args) {
        SimpleCatColage myPanel = new SimpleCatColage();
        JFrame frame = new JFrame("Custom Drawing"); //create frame to hold our JPanel subclass	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(myPanel);  //add instance of MyGUI to the frame
        frame.pack(); //resize frame to fit our Jpanel
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
	//show the frame	
        frame.setVisible(true);
    }
}

