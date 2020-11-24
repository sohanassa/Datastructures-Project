package hw3Epl231;

import java.awt.List;
import java.util.ArrayList;

public class GraphNode implements NodeID {
	private int ID;
	private char type;
	private ArrayList<GraphNode> neighbours;
	
	public GraphNode(int id, char type) {
		this.ID=id;
		this.type=type;
		neighbours = new ArrayList<GraphNode>();
	}
	
	public int getID() {
		return ID;
	}

}
