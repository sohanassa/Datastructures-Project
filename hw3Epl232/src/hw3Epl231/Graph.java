package hw3Epl231;

import java.util.ArrayList;
import java.math.*;

public class Graph {
	private int V;
	private int E;
	private HashTable<GraphNode> h;

	public Graph() {
		h = new HashTable();
	}

	public void add(GraphNode node, int dis) {
		checkNull(node);
		h.add(node);
		createEdges(node, dis);
		V++;
	}

	private void createEdges(GraphNode node, int dis) {

	}

	public boolean isEdge(GraphNode node1, GraphNode node2) {
		checkNull(node1);
		checkNull(node2);
		return node1.isNeightbour(node2);
	}

	public void removeVertex(GraphNode node) {
		checkNull(node);
		ArrayList list = h.getListWithID(node.getID());
		for (int i = 0; i < list.size(); i++) {
			if ((Integer) list.get(i) == node.getID())
				list.remove(i);
		}
	}

	public boolean vertexExists(GraphNode node) {
		checkNull(node);
		ArrayList list = h.getListWithID(node.getID());
		for (int i = 0; i < list.size(); i++) {
			if ((Integer) list.get(i) == node.getID())
				return true;
		}
		return false;
	}

	public double getWeight(GraphNode node1, GraphNode node2) {
		checkNull(node1);
		checkNull(node2);
		return Math.sqrt(Math.pow(node1.getX() - node2.getX(), 2) + Math.pow(node1.getY() - node2.getY(), 2));
	}

	private void checkNull(Object o) {
		if (o == null) {
			System.out.println("NULL POINTER!");
			System.exit(-1);
		}
	}

}
