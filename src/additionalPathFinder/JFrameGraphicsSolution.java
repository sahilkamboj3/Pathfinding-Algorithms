package additionalPathFinder;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.*;


public class JFrameGraphicsSolution extends JPanel{
	
	private Node[][] grid;
	private int frameSize;
	
	public JFrameGraphicsSolution(Node[][] grid) {
		this.grid = grid;
		this.frameSize = 30 * this.grid.length;
	}
	
	public void paint(Graphics g){
	
		int xCount = 0;
		int yCount = 0;
		
		for (int row = 0; row < this.grid.length; row++) {
			for (int col = 0; col < this.grid[0].length; col++) {
				g.setColor(Color.black);
				g.drawRect(xCount, yCount, this.frameSize / this.grid[0].length, this.frameSize / this.grid.length);
				if (this.grid[row][col] == null) {
					g.setColor(Color.black);
					g.fillRect(xCount, yCount, this.frameSize / this.grid[0].length, this.frameSize / this.grid.length);
				} else if(this.grid[row][col].isSolution || this.grid[row][col].id.equals("S") || this.grid[row][col].id.equals("E")) {
					g.setColor(Color.yellow);
					g.fillRect(xCount, yCount, this.frameSize / this.grid[0].length, this.frameSize / this.grid.length);
				} else if (this.grid[row][col].id.equals("S")) {
					g.setColor(Color.green); // starting node
					g.fillRect(xCount, yCount, this.frameSize / this.grid[0].length, this.frameSize / this.grid.length);
				} else if (this.grid[row][col].id.equals("E")) {
					g.setColor(Color.red); // ending node
					g.fillRect(xCount, yCount, this.frameSize / this.grid[0].length, this.frameSize / this.grid.length);
				}   else {
					g.setColor(Color.white);
					g.fillRect(xCount, yCount, this.frameSize / this.grid[0].length, this.frameSize / this.grid.length);
				}
				xCount += ( this.frameSize / this.grid[0].length );
			}
			xCount = 0;
			yCount += ( this.frameSize / this.grid.length );
		}
	
	}
}