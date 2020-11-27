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

	public void add(GraphNode node, int maxDist) {
		checkNull(node);
		h.add(node);
		createEdges(node, maxDist);
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
		ArrayList list = h.getListWithID(Integer. parseInt(node.getID()));
		for (int i = 0; i < list.size(); i++) {
			if ((Integer) list.get(i) == Integer. parseInt(node.getID()))
				list.remove(i);
		}
		// werk here
	}

	public boolean vertexExists(GraphNode node) {
		checkNull(node);
		ArrayList list = h.getListWithID(Integer. parseInt(node.getID()));
		for (int i = 0; i < list.size(); i++) {
			if ((Integer) list.get(i) == Integer. parseInt(node.getID()))
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
	
	public ArrayList<GraphNode> getClosestNeighbours(GraphNode n){
		ArrayList<GraphNode> closest = new ArrayList<GraphNode>();
		for(int i=0; i<n.getNeighbours().size(); i++)
			closest.add(n.getNeighbours().get(i));
		return closest;
	}
	
	public ArrayList<Integer> getDistance(GraphNode n, ArrayList<GraphNode> closest){
		ArrayList<Integer> distance = new ArrayList<Integer>();
		for(int i=0; i<closest.size(); i++)
			distance.add(new Integer((int) getWeight(n, closest.get(i))));
		return distance;
	}
	
	public void prim() {

		double weight = 0;
		
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

/*		int closest[] = new int[this.V];
		for (int i = 0; i < V; i++)
			closest[i] = -1;	*/

	/*	double distance[] = new double[this.V];
		for (int i = 0; i < V; i++)
			distance[i] = Double.MAX_VALUE;	*/

		ArrayList<Edge<GraphNode>> tree = new ArrayList<Edge<GraphNode>>();

		ArrayList<GraphNode> nodes = createArrayOfNodes();

		/*GraphNode v = nodes.get(0);
		visited[0] = true; 	*/

		for(int i=0; i<nodes.size(); i++) {
			visited[i]=true;
			ArrayList<GraphNode> closest = getClosestNeighbours(nodes.get(i));
			ArrayList<Integer> distance = getDistance(nodes.get(i), closest);
			
			
		}
			
	

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
