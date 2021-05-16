/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecture16;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author sehall
 */
public class MultiButtonGridWithLayouts extends JPanel implements ActionListener{
    
    private JButton[][] buttons;
    private JLabel labelSelection;
    private JRadioButton linuxButton,windowsButton,macButton;
    private JList list;
    private DefaultListModel model;
    private JCheckBox box100,box200,box300;
    private JTextField sumField;
    private JButton evaluateButton;
    private JScrollPane pane;
    
    public MultiButtonGridWithLayouts()
    {   super(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridLayout(3,3));
        centerPanel.setPreferredSize(new Dimension(500,500));
        buttons = new JButton[3][3];
        for(int i=0;i<3;i++)
        {   for(int j=0;j<3;j++)
            {   buttons[i][j] = new JButton("["+i+"]"+"["+j+"]");
                buttons[i][j].addActionListener(this);
                centerPanel.add(buttons[i][j]);
            }
        }
        add(centerPanel, BorderLayout.CENTER);
        
        //create radio buttons, set linuxButton default to true
        linuxButton = new JRadioButton("Linux");
        windowsButton = new JRadioButton("Windows");
        macButton = new JRadioButton("Macintosh");
        labelSelection = new JLabel(">>Select your favourite OS");
        
        //use a 
        MyRadioListener listener = new MyRadioListener();
        linuxButton.addActionListener(listener);
        windowsButton.addActionListener(listener);
        macButton.addActionListener(listener);
        //Put radio buttons in button group, then add to underlying panel
        ButtonGroup group = new ButtonGroup();
        group.add(linuxButton);
        group.add(windowsButton);
        group.add(macButton);
        JPanel northPanel = new JPanel();
        northPanel.add(linuxButton);
        northPanel.add(windowsButton);
        northPanel.add(macButton);
        northPanel.add(labelSelection);
        add(northPanel,BorderLayout.NORTH);
        
        model = new DefaultListModel();
        list = new JList(model);
        list.setFixedCellWidth(100);
       // list.setPreferredSize(new Dimension(50,500));
         pane = new JScrollPane(list);
        add(pane,BorderLayout.WEST);
     
        JPanel southPanel = new JPanel();
        box100 = new JCheckBox("$100");
        box200 = new JCheckBox("$200");
        box300 = new JCheckBox("$300");
        evaluateButton = new JButton("Calulate");
        evaluateButton.addActionListener(this);
        sumField = new JTextField("$0",10);
        southPanel.add(box100);
        southPanel.add(box200);
        southPanel.add(box300);
        southPanel.add(sumField);
        southPanel.add(evaluateButton);
        add(southPanel,BorderLayout.SOUTH);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {   
        if(e.getSource() == evaluateButton)
        {
            int sum=0;
            if(box100.isSelected()) sum+=100;
            if(box200.isSelected()) sum+=200;
            if(box300.isSelected()) sum+=300;
            sumField.setText("$"+sum);
        }
        else
        {   StringTokenizer tokenizer = new StringTokenizer(e.getActionCommand(), "[]");
            System.out.println("BUTTON WITH LABEL "+e.getActionCommand()+" PRESSED ");
            model.addElement(tokenizer.nextToken()+","+tokenizer.nextToken());
        }
    }
    private class MyRadioListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {   Object source = e.getSource();
            
            if(source == linuxButton)
            {    labelSelection.setText(">>Linux, Good choice"); 
               
            }
            else if(source == windowsButton)
            {    labelSelection.setText(">>Windows, hmmmm okay"); 
                
            }else if(source == macButton)
            {    labelSelection.setText(">>Mac, yuck!!!! I hate you"); 
                 
            }
        }
    }
    public static void main(String[] args) {
        MultiButtonGridWithLayouts myPanel = new MultiButtonGridWithLayouts();
        JFrame frame = new JFrame("MultiButton Grid"); //create frame to hold our JPanel subclass	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(myPanel);  //add instance of MyGUI to the frame
        frame.pack(); //resize frame to fit our Jpanel
        //Position frame on center of screen 
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        frame.setLocation(new Point((screenWidth / 2) - (frame.getWidth() / 2), 
                    (screenHeight / 2) - (frame.getHeight() / 2)));
	//show the frame	
        frame.setVisible(true);
    }
}
