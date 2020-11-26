package hw3Epl231;

import java.util.ArrayList;

public class Graph {

	private HashTable<GraphNode> h;

	public Graph() {
		h = new HashTable();
	}

	public void add(GraphNode node) {
		h.add(node);
	}

	public boolean areNeighbours(GraphNode node1, GraphNode node2) {
		ArrayList neighbours1 = h.getListWithID(node1.getID());
		ArrayList neighbours2 = h.getListWithID(node1.getID());
		ArrayList neighbours;
		if(neighbours1.size()<neighbours2.size()) {
			neighbours = neighbours1;
		}
		else {
			neighbours = neighbours2;
		}
		for (int i = 0; i < neighbours.size(); i++) {
			if ((Integer) neighbours.get(i) == node2.getID())
				return true;
			
		}
		return false;
	}
	
	public void removeNode(GraphNode node) {
		ArrayList list = h.getListWithID(node.getID());
		for (int i = 0; i < list.size(); i++) {
			if ((Integer) list.get(i) == node.getID())
				list.remove(i);
		}
	}
	
	public boolean nodeExists(GraphNode node) {
		ArrayList list = h.getListWithID(node.getID());
		for (int i = 0; i < list.size(); i++) {
			if ((Integer) list.get(i) == node.getID())
				return true;
		}
		return false;
	}
}
