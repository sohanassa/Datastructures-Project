package hw3Epl231;

import java.util.ArrayList;

import java.math.*;

public class Graph {
	private int V;
	private int E;
	private HashTable<GraphNode> h;

	public Graph() {
		h = new HashTable();
		V=0;
		E=0;
	}

	public void add(GraphNode node, int weight) {
		checkNull(node);
		h.add(node);
		createEdges(node, weight);
		V++;
	}

	private void createEdges(GraphNode node, int maxDist) {

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
		// werk here
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

	public ArrayList<GraphNode> createArrayOfNodes() {
		
		ArrayList<GraphNode> tab = new  ArrayList<GraphNode>(V);
		
		for(int i=0; i<h.getSizeOfArray(); i++)
			if(h.getListAt(i)!=null)
				tab.addAll(h.getListAt(i));
		
		return tab;
		
	}
	
	public void prim() {

		double weight = 0;
		
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

		int closest[] = new int[this.V];
		for (int i = 0; i < V; i++)
			closest[i] = -1;

		double distance[] = new double[this.V];
		for (int i = 0; i < V; i++)
			distance[i] = Double.MAX_VALUE;

		ArrayList<Edge<GraphNode>> tree = new ArrayList<Edge<GraphNode>>();


		GraphNode v = h.get;
		visited[v] = true; 

		
		/** ADD YOUR CODE HERE **/

		System.out.println("Τελικό Ελάχιστο Γεννητορικό Δένδρο (ΕΓΔ) με βάρος " + weight);
		for (int i = 0; i < tree.size(); i++)
			System.out.println(tree.get(i));

	}

	private class Edge<E> {
		E v1;
		E v2;
		double weight;

		Edge(E v1, E v2, double weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}
	}

}
