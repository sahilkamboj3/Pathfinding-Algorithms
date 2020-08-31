package additionalPathFinder;
import java.util.PriorityQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

//import pathFinder.Node;

import java.awt.*;
import java.lang.Math;

public class gridLayout {
	
	
	public int startX, startY, endX, endY;
	public boolean[][] closedCells; 
	public Node[][] grid;
	public int height, width; 
	public int frameSize; 
	
	public gridLayout(int startX, int startY, int endX, int endY, int width, int height, double probability) {
		this.frameSize = 30 * width;
		this.closedCells = new boolean[width][height];
		this.grid = new Node[width][height];
		this.width = width;
		this.height = height;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (row == startY && col == startX) {
					Node curNode = new Node(col,row,Color.green,"S",0);
					curNode.gCost = (Math.abs(startX - endX) + Math.abs(startY - endY)) * 10;
					this.grid[col][row] = curNode;
				} else if (row == endY && col == endX) {
					Node curNode = new Node(col,row,Color.red,"E");
					curNode.gCost = 0;
					curNode.hCost = (Math.abs(startX - endX) + Math.abs(startY - endY)) * 10;
					this.grid[col][row] = curNode;
				} else if (Math.random() < probability) {
					Node curNode = null;
					this.grid[col][row] = curNode;
				} else {
					Node curNode = new Node(col,row);
					this.grid[col][row] = curNode;
					curNode.gCost = (Math.abs(row - endX) + Math.abs(col - endY)) * 10;
					curNode.hCost = (Math.abs(startX - endX) + Math.abs(startY - endY)) * 10;
				}
			}
		}
	}
	
	
	public void printGrid() {
		for (int row = 0; row < this.height; row++) {
			for (int col = 0; col < this.width; col++) {
				if (row == startY && col == startX) {
					System.out.print("S ");
				} else if (row == endY && col == endX) {
					System.out.print("E ");
				} else if (this.grid[col][row] == null) {
					System.out.print("W ");
				} else {
					System.out.print("X ");
				}
			}
			System.out.println();
		}
	}

	
	public void printClosedCells() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				System.out.print(this.closedCells[row][col] + " ");
			}
			System.out.println("");
		}
	}
	
	
	public void displayGrid() {
		JFrame frame = new JFrame("Grid");	
		JFrameGraphicsAStar canvas = new JFrameGraphicsAStar(this.grid);
		frame.getContentPane().add(canvas);
		frame.setSize(this.frameSize, this.frameSize + 21);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	public Node[][] getGrid() {
		return this.grid;
	}

	
	public boolean[][] getClosedCells() {
		return this.closedCells;
	}
	
}