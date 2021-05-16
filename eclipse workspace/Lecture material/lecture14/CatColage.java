/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecture14;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author sehall
 */
public class CatColage extends JPanel implements ActionListener{
    public final int PANEL_WIDTH = 400;
    public final int PANEL_HEIGHT = 400;
    private DrawingPanel drawPanel;  //innerclass
    private ImageIcon icon;
    private Timer timer;
    private File[] listOfFiles;
    Random gen;
    private AudioClip clip;
    public CatColage() {
        super(new BorderLayout());   //invoke super class Jpanel constructor use BorderLayout
        gen = new Random();
        File folder = new File("cats");
        listOfFiles = folder.listFiles();
        for(File f:listOfFiles)
            System.out.println("READING "+f.getName());
        System.out.println("NUM FILES READ "+listOfFiles.length);
        drawPanel = new DrawingPanel();     //create DrawingPanel and add to center
        add(drawPanel, BorderLayout.CENTER);  
         timer = new Timer(250,this);
        timer.start();
         try {
             clip = Applet.newAudioClip(new URL("file", "localhost", "cat.wav"));
            clip.loop();
            
        } catch (MalformedURLException ex) {
             System.out.println("WHOOPS COULD NOT LOAD FILE");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        int index = gen.nextInt(listOfFiles.length);
        icon = new ImageIcon("cats/"+listOfFiles[index].getName());
        drawPanel.repaint();
    }
    //seperate inner class for graphics
    private class DrawingPanel extends JPanel
    {   
        public DrawingPanel()
        {   setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            setBackground(Color.WHITE);   
        }
        @Override
        public void paintComponent(Graphics g){
            //super.paintComponent(g);
            if(icon != null)
            {   int width = Math.min(getWidth(), icon.getIconWidth());
                int height = Math.min(getHeight(), icon.getIconHeight());
                int scalar = gen.nextInt(5)+1;
                width = width/scalar;
                height = height/scalar;
                Image i = icon.getImage();
                if(width > height)
                    i = i.getScaledInstance(width, -1, Image.SCALE_SMOOTH);
                else
                   i = i.getScaledInstance(-1, height, Image.SCALE_SMOOTH); 
                icon.setImage(i);
                icon.paintIcon(this, g, gen.nextInt(Math.abs(getWidth()-width)+1), gen.nextInt(Math.abs(getHeight()-height)+1));
            }
        }
    }
    public static void main(String[] args) {
        CatColage myPanel = new CatColage();
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

