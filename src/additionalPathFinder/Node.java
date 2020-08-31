package additionalPathFinder;
import java.util.*;
import java.awt.*;

public class Node {
	
	public int x; 
	public int y; 
	public Node parent; 
	public double hCost, gCost, fCost; 
	public boolean isSolution; 
	public Color color;
	public String id;
	public boolean isTested; 
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.isSolution = false;
		this.parent = null;
		this.color = Color.white;
		this.id = "N";
		this.fCost = Integer.MAX_VALUE;
		this.isTested = false;
	}
	
	public Node(int x, int y, Color color, String id) {
		this.x = x;
		this.y = y;
		this.isSolution = false;
		this.parent = null;
		this.color = color;
		this.id = id;
		this.fCost = Integer.MAX_VALUE;
		this.isTested = false;
	}
	
	public Node(int x, int y, Color color, String id, double fCost) {
		this.x = x;
		this.y = y;
		this.isSolution = false;
		this.parent = null;
		this.color = color;
		this.id = id;
		this.fCost = fCost;
		this.isTested = false;
	}
	
	public String toString() {
		return "[" + this.x + "," + this.y + "]";
	}
}
