package additionalPathFinder;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import additionalPathFinder.JFrameGraphicsDjikstra;
import java.awt.*;
import java.lang.Math;

public class dijkstra {
	
	private static final int V_H_COST = 10;
	private static final int DIAG_COST = 14;
	private int startX, startY, endX, endY;
	private Node[][] grid;
	private boolean[][] closedCells;
	private PriorityQueue<Node> openCells;
	private int frameSize;
	
	public dijkstra(Node[][] grid, int startX, int startY, int endX, int endY) {
		this.frameSize = 30 * grid[0].length;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.grid = grid;
		this.closedCells = new boolean[this.grid.length][this.grid[0].length];
		openCells = new PriorityQueue<Node>((Node n1, Node n2) -> {
			return n1.fCost < n2.fCost ? -1 : n1.fCost > n2.fCost ? 1 : 0;
		});
	}
	
	public void updateCost(Node current, Node n, double cost) {
		if (n == null || closedCells[n.x][n.y]) {
			return;
		}
		double newFinalCost = n.hCost + cost;
		boolean isOpen = openCells.contains(n);
		
		if (!isOpen || newFinalCost < n.fCost) {
			n.fCost = newFinalCost;
			n.parent = current;
			n.isTested = true;
			
			if(!isOpen) {
				openCells.add(n);
			}
		}
	}
	
	public void findPath() {
		
		if (startX >= this.grid.length || startY >= this.grid[0].length) {
			return;
		}
		
		
		openCells.add(this.grid[startX][startY]);
		Node current;
		
		while (true) {
			
			
			current = openCells.poll();
			
			if (current == null) {
				return;
			}
			
			closedCells[current.x][current.y] = true;
			Node n;
			
			if (current.x - 1 >= 0) {
				n = this.grid[current.x - 1][current.y];
				updateCost(current, n, current.fCost + V_H_COST);
				
				if (current.y - 1 >= 0) {
					n = this.grid[current.x - 1][current.y - 1];
					updateCost(current, n, current.fCost + DIAG_COST);
				}
				
				if (current.y + 1 < this.grid[0].length) {
					n = this.grid[current.x - 1][current.y + 1];
					updateCost(current, n, current.fCost + DIAG_COST);
				}
			}
			
			if (current.x + 1 < this.grid.length) {
				n = this.grid[current.x + 1][current.y];
				updateCost(current, n, current.fCost + V_H_COST);
				
				if (current.y - 1 >= 0) {
					n = this.grid[current.x + 1][current.y - 1];
					updateCost(current, n, current.fCost + DIAG_COST);
				}
				
				if (current.y + 1 < this.grid[0].length) {
					n = this.grid[current.x + 1][current.y + 1];
					updateCost(current, n, current.fCost + DIAG_COST);
				}
			} 
			
			if (current.y + 1 < this.grid[0].length) {
				n = this.grid[current.x][current.y + 1];
				updateCost(current, n, current.fCost + V_H_COST);
				
			}
			
			if (current.y - 1 >= 0) {
				n = this.grid[current.x][current.y - 1];
				updateCost(current, n, current.fCost + V_H_COST);
				
			}
			
			if (current.id.equals("E")) {
				return;
			}
			
		}
		
	}
	
	public void formSolution() {
		
		if (startX >= this.grid.length || startY >= this.grid[0].length) {
			System.out.println("Invalid startX / startY. Make sure they are within 0 to width - 1  and 0 to height - 1. There is no path.");
			return;
		}
		
		if (this.closedCells[endX][endY]) {
			System.out.print("Dijkstra : ");
			Node current = this.grid[endX][endY];
			current.isSolution = true;
			System.out.print(current.toString());
			while (current.parent != null) {
				current = current.parent;
				current.isSolution = true;
				System.out.print(" -> " + current.toString());
			}
			System.out.println(" -> Start");
			
			JFrame frame = new JFrame("Dijkstra Solution");	
			JFrameGraphicsDjikstra canvas = new JFrameGraphicsDjikstra(this.grid);
			frame.getContentPane().add(canvas);
			frame.setSize(this.frameSize, this.frameSize + 21);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		} else { 
			String str = "There is no Dijkstra's path.";
			System.out.println(str);
			JFrame noDijkstraPath = new JFrame("Dijkstra");
			JLabel noPath = new JLabel();
			noPath.setText("<html><body><nobr>"+ str +"</nobr></body></html>");
			noPath.setBounds(10, 10, 200, 30);
			noDijkstraPath.add(noPath);
			noDijkstraPath.setSize(200, 80);
			noDijkstraPath.setLayout(null);    
			noDijkstraPath.setVisible(true);    
			noDijkstraPath.setResizable(false);
			noDijkstraPath.setLocationRelativeTo(null);
			noDijkstraPath.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
	}
	
 }
