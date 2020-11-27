package hw3Epl231;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class GraphNode implements NodeID {
	private int x;
	private int y;
	private String ID;
	private boolean isFireStation;
	private int temperture;
	private LinkedList<GraphNode> neighbours;

	public GraphNode(int x, int y, String id, boolean isFireStation, int temperture) {
		this.x = x;
		this.y = y;
		this.ID = id;
		this.isFireStation = isFireStation;
		this.temperture = temperture;
		neighbours = new LinkedList<GraphNode>();
	}

	public String getID() {
		return ID;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isFireStation() {
		return isFireStation;
	}

	public LinkedList<GraphNode> getNeighbours() {
		return neighbours;
	}

	public boolean isNeightbour(GraphNode node) {
		if (node == null)
			return false;
		for (int i = 0; i < neighbours.size(); i++) {
			if (neighbours.get(i).equals(node))
				return true;
		}
		// neighbours.contains(node);
		return false;
	}

	public boolean equals(GraphNode node) {
		if (ID == node.getID())
			return true;
		return false;
	}
	
	public String getStringStatus() {
		String s="";
		s=s+ID+"\t"+"["+x+","+y+"]"+"\t"+temperture;
		return s;
	}
	
	public String toString() {
		return ID;
	}
}
