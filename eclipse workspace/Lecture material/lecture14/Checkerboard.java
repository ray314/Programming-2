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
public class Checkerboard extends JPanel implements ActionListener{
    public final int PANEL_WIDTH = 700;
    public final int PANEL_HEIGHT = 700;
    private DrawingPanel drawPanel;  //innerclass
    private JRadioButton draughtsButton, chessButton;
    private Color alternateColor;
    
    public Checkerboard() {
        super(new BorderLayout());   //invoke super class Jpanel constructor use BorderLayout
        drawPanel = new DrawingPanel();     //create DrawingPanel and add to center
        //create JRadioButtons add listeners, add to button group, set draughts initial selection
        draughtsButton = new JRadioButton("draughts board",true);
        chessButton = new JRadioButton("chess board");
        alternateColor = Color.RED;  //set colour initially red
        draughtsButton.addActionListener(this);
        chessButton.addActionListener(this);
        ButtonGroup group = new ButtonGroup();
        group.add(draughtsButton);
        group.add(chessButton);
        //create new JPanel add radio buttons to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(draughtsButton);
        buttonPanel.add(chessButton);
        //add buttonPanel to main outer panel in south
        add(buttonPanel, BorderLayout.SOUTH);
        add(drawPanel, BorderLayout.CENTER); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == draughtsButton)
        {   alternateColor = Color.RED; 
        }
        else if(source == chessButton)
        {   alternateColor = Color.WHITE;
        }
        //call DrawingPanel to update itself
        drawPanel.repaint();
    }
    //seperate inner class for graphics
    private class DrawingPanel extends JPanel
    {    public DrawingPanel()
        {   setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            setBackground(Color.WHITE);
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            //calculate size of box to be 1/8 width and height
            int boxSizeX = getWidth()/8;
            int boxSizeY = getHeight()/8;
            //loop y coordinate
            for(int i=0;i<8;i++)
            {   //loop x coordinate, used to shift 
                for(int j=0;j<8;j++)
                {   if((i+j) %2 == 0)
                         g.setColor(alternateColor);
                    else g.setColor(Color.BLACK);
                    g.fillRect(j*boxSizeX,i*boxSizeY,boxSizeX,boxSizeY);
                }
            }
            
        }
    }
    public static void main(String[] args) {
        Checkerboard myPanel = new Checkerboard();
        JFrame frame = new JFrame("Checkerboard"); //create frame to hold our JPanel subclass	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(myPanel);  //add instance of MyGUI to the frame
        frame.pack(); //resize frame to fit our Jpanel
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
	//show the frame	
        frame.setVisible(true);
    }
}
