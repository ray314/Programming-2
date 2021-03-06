import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class testing1 extends JFrame implements ActionListener {

	private JPanel contentPane;
	JButton btnNewButton = new SouthPanel("Testing");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testing1 frame = new testing1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testing1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		
		btnNewButton.addActionListener(this);
		panel.add(btnNewButton);
		btnNewButton.repaint();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == btnNewButton)
		{
			btnNewButton.repaint();
		}
	}
	
	public class SouthPanel extends JButton
	{
		public SouthPanel(String text)
		{
			super(text);
			setBackground(Color.RED);
		}
		
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.RED);
		}
	}

}
