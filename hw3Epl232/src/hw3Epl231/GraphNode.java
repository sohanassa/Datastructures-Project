package hw3Epl231;

import java.awt.List;
import java.util.ArrayList;

public class GraphNode implements NodeID {
	private int x;
	private int y;
	private int ID;
	private boolean isFireStation;
	private int temperture;
	private ArrayList<Integer> neighbours;

	public GraphNode(int x, int y, int id, boolean isFireStation, int temperture) {
		this.x = x;
		this.y = y;
		this.ID = id;
		this.isFireStation = isFireStation;
		this.temperture = temperture;
		neighbours = new ArrayList<Integer>();
	}

	public int getID() {
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

	public ArrayList<Integer> getNeighbours() {
		return neighbours;
	}
	
	public boolean isNeightbour(GraphNode node) {
		if(node==null)
			return false;
		for(int i=0; i<neighbours.size(); i++) {
			if(neighbours.get(i) == node.getID())
				return true;
		}
		return false;
	}
}














