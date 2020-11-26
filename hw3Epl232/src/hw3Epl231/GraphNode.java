package hw3Epl231;

import java.awt.List;
import java.util.ArrayList;

public class GraphNode implements NodeID {
	private int x;
	private int y;
	private int ID;
	private boolean isFireStation;
	private ArrayList<Integer> neighbours;
	
	public GraphNode(int x, int y, int id, boolean isFireStation) {
		this.x=x;
		this.y=y;
		this.ID=id;
		this.isFireStation = isFireStation;
		neighbours = new ArrayList<Integer>();
	}
	
	public int getID() {
		return ID;
	}

}
