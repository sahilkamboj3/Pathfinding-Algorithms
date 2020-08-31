package additionalPathFinder;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.*;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;

public class window extends JPanel implements ActionListener{
	
	public int startX, startY, endX, endY, width, height;
	public Node[][] grid;
	public double prob;
	
	public window() {    
		
		// default values
		
		this.startX = 0;
		this.startY = 0;
		this.endX = 4;
		this.endY = 4;
		this.prob = 0.25;
		this.width = 5;
		this.height = 5;
		gridLayout origLayout = new gridLayout(startX, startY, endX, endY, width, height, prob);
		grid = origLayout.getGrid();
		
		
		JFrame f = new JFrame("Inputs"); 
		this.startX = 0;
		
		//buttons

		JButton aStarButton = new JButton("A*");    
		aStarButton.setBounds(65, 80, 130, 40); 
		
		JButton dijkstraButton = new JButton("Dijkstra");    
		dijkstraButton.setBounds(215, 80, 130, 40);
		
		JButton newGridButton = new JButton("New Grid");    
		newGridButton.setBounds(140, 30, 130, 40);    
		
		//inputs
		
		JTextField startXField = new JTextField();
		startXField.setBounds(85, 130, 130, 30);
		
		JTextField startYField = new JTextField();
		startYField.setBounds(285, 130, 130, 30);
		
		JTextField endXField = new JTextField();
		endXField.setBounds(85, 180, 130, 30);
		
		JTextField endYField = new JTextField();
		endYField.setBounds(285, 180, 130, 30);
		
		JTextField probField = new JTextField();
		probField.setBounds(200, 280, 65, 30);
		
		//labels
		
		JLabel sxLabel = new JLabel();		
		sxLabel.setText("Start X :");
		sxLabel.setBounds(25, 130, 130, 30);
		
		JLabel syLabel = new JLabel();		
		syLabel.setText("Start Y :");
		syLabel.setBounds(225, 130, 130, 30);
		
		JLabel exLabel = new JLabel();		
		exLabel.setText("End X :");
		exLabel.setBounds(25, 180, 130, 30);
		
		JLabel eyLabel = new JLabel();		
		eyLabel.setText("End Y :");
		eyLabel.setBounds(225, 180, 130, 30);
		
		JLabel sizeLabel = new JLabel();
		sizeLabel.setText("Sizes :");
		sizeLabel.setBounds(115, 230, 130, 30);
		
		JLabel probLabel = new JLabel();
		probLabel.setText("Probability :");
		probLabel.setBounds(120, 280, 130, 30);
		
		//drop down
		
		String[] sizes = {"10 x 10","20 x 20","30 x 30","50 x 50","100 x 100"};
		JComboBox options = new JComboBox(sizes);   
		options.setBounds(165, 230, 130, 40);  
		
		//responses
		
		// fields check to make sure only numbers are inputted
		
		startXField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!isNumber(c)) {
					e.consume();
				}
			}
			
			private boolean isNumber(char c) {
				return c >= '0' && c <= '9';
			}
		});
		
		startYField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!isNumber(c)) {
					e.consume();
				}
			}
			
			private boolean isNumber(char c) {
				return c >= '0' && c <= '9';
			}
		});
		
		endXField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!isNumber(c)) {
					e.consume();
				}
			}
			
			private boolean isNumber(char c) {
				return c >= '0' && c <= '9';
			}
		});
		
		endYField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!isNumber(c)) {
					e.consume();
				}
			}
			
			private boolean isNumber(char c) {
				return c >= '0' && c <= '9';
			}
		});
		
		probField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!isNumber(c)) {
					e.consume();
				}
			}
			
			private boolean isNumber(char c) {
				return c >= '0' && c <= '9' || c == '.';
			}
		});
		
		newGridButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				
				startX = Integer.parseInt(startXField.getText());
				startY = Integer.parseInt(startYField.getText());
				endX = Integer.parseInt(endXField.getText());
				endY = Integer.parseInt(endYField.getText());
				prob = Double.parseDouble(probField.getText());
				int index = options.getSelectedIndex();
				
				startXField.setText("");
				startYField.setText("");
				endXField.setText("");
				endYField.setText("");
				probField.setText("");
				
				switch (index) {
					case 0:
						height = 10;
						width = 10;
						break;
						
					case 1:
						height = 20;
						width = 20;
						break;
					
					case 2:
						height = 30;
						width = 30;
						break;
					
					case 3:
						height = 50;
						width = 50;
						break;
					
					case 4:
						height = 100;
						width = 100;
						break;
					
					default:
						height = 5;
						width = 5;
						break;
				}

				gridLayout gridLayout = new gridLayout(startX, startY, endX, endY, width, height, prob);
				gridLayout.displayGrid();
				grid = gridLayout.getGrid();
				
			}
		});
		
		aStarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				
				aStar aStarPathFind = new aStar(grid, startX, startY, endX, endY);
				long startTime = System.currentTimeMillis();
				aStarPathFind.findPath();
				long endTime = System.currentTimeMillis();
				System.out.println("A* took " + (endTime - startTime) + " milliseconds.");
				aStarPathFind.formSolution();
				
			}
		});
		
		dijkstraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				// uses values to display the solution, even has a timer
				
				dijkstra dPathFind = new dijkstra(grid, startX, startY, endX, endY);
				long startTime = System.currentTimeMillis();
				dPathFind.findPath();
				long endTime = System.currentTimeMillis();
				System.out.println("D took " + (endTime - startTime) + " milliseconds.");
				dPathFind.formSolution();
				
			}
		});
		
		//add to frame
		
		f.add(dijkstraButton);
		f.add(aStarButton);    
		f.add(newGridButton);
		f.add(startXField);
		f.add(endYField);
		f.add(endXField);
		f.add(startYField);
		f.add(probField);
		f.add(probLabel);
		f.add(eyLabel);
		f.add(exLabel);
		f.add(syLabel);
		f.add(sxLabel);
		f.add(options);
		f.add(sizeLabel);
		f.setSize(450,375);    
		f.setLayout(null);    
		f.setVisible(true);    
		f.setResizable(false);
		f.setBackground(Color.red);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		
		new window();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
