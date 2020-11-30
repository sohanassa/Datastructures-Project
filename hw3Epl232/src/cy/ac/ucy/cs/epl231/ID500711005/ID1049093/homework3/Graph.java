package cy.ac.ucy.cs.epl231.ID500711005.ID1049093.homework3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	private int V;
	private int E;
	private HashTable<Vertex> h;
	private static String startVertexID = "02";

	/**
	 * Creates new null Graph.
	 */
	public Graph() {
		h = new HashTable();
		V = 0;
		E = 0;
	}

	/**
	 * Getter method for the number of vertexes.
	 * 
	 * @return number of vertexes
	 */
	public int getV() {
		return V;
	}

	/**
	 * Getter method for the number of edges.
	 * 
	 * @return number of edges
	 */
	public int getE() {
		return E;
	}

	/**
	 * Adds node to the hashtable and creates edges between its neighbors.
	 * 
	 * @param node    Node that it is going to be added.
	 * @param maxDist The maximum distance between neighbors.
	 */
	public void add(Vertex node, int maxDist) {
		checkNull(node, 22);
		h.add(node);
		createEdges(node, maxDist);
		V++;
	}

	/**
	 * Connects the node to any other node that is its neighbor in the graph.
	 * 
	 * @param node    The node we are searching for its neighbors.
	 * @param maxDist The maximum distance between neighbors.
	 */
	private void createEdges(Vertex node, int maxDist) {
		checkNull(node, 34);
		for (int i = 0; i < h.getSizeOfArray(); i++) {
			LinkedList<Vertex> nodes = h.getListAt(i);
			if (nodes != null)

				for (int c = 0; c < nodes.size(); c++)

					if (!nodes.get(c).equals(node) && getWeight(node, nodes.get(c)) <= maxDist) {
						node.addNeighbour(nodes.get(c));
						nodes.get(c).addNeighbour(node);
						E++;
					}
		}
	}

	/**
	 * Checks if two given nodes are neighbors.
	 * 
	 * @param node1 First node to check.
	 * @param node2 Second node to check.
	 * @return True/False if they are neighbors.
	 */
	public boolean isEdge(Vertex node1, Vertex node2) {
		checkNull(node1, 49);
		checkNull(node2, 50);
		return node1.isNeightbour(node2);
	}

	/**
	 * Removes completely the vertex with the id from the graph.
	 * 
	 * @param id The id of the vertex that is going to be removed.
	 */
	public void removeVertex(String id) {
		checkNull(id, 55);
		Vertex v = getVertexFromID(id);
		for (int i = 0; i < h.getSizeOfArray(); i++) {
			LinkedList<Vertex> nodes = h.getListAt(i);

			if (nodes == null)
				continue;
			for (int c = 0; c < nodes.size(); c++)
				if (!nodes.get(c).equals(v) && nodes.get(c).getNeighbours().contains(v))
					nodes.get(c).removeNeighbour(v);

			if (nodes.contains(v)) {
				nodes.remove(v);
				V--;
			}
		}
	}

	/**
	 * Checks if a vertex with the id exists in the graph.
	 * 
	 * @param id The id for the vertex we are searching for.
	 * @return True/False if the vertex exists in graph.
	 */
	public boolean vertexExists(String id) {
		checkNull(id, 75);
		LinkedList<Vertex> list = h.getListWithKey(Integer.parseInt(id));
		if (list == null)
			return false;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getID().equals(id))
				return true;
		}
		return false;
	}

	/**
	 * Gets the weight(distance) of the two nodes.
	 * 
	 * @param node1 First node.
	 * @param node2 Second node.
	 * @return The weight(distance) between the two nodes.
	 */
	public double getWeight(Vertex node1, Vertex node2) {
		checkNull(node1, 85);
		checkNull(node2, 86);
		return Math.sqrt(Math.pow(node1.getX() - node2.getX(), 2) + Math.pow(node1.getY() - node2.getY(), 2));
	}

	private void checkNull(Object o, int line) {
		if (o == null) {
			System.out.println("NULL POINTER! at " + line);
			System.exit(-1);
		}
	}

	private LinkedList<Vertex> createArrayOfVerteces() {

		LinkedList<Vertex> tab = new LinkedList<Vertex>();

		for (int i = 0; i < h.getSizeOfArray(); i++)
			if (h.getListAt(i) != null)
				tab.addAll(h.getListAt(i));

		return tab;

	}

	/**
	 * Prim function that calculates the minimum spanning tree of the graph.
	 * 
	 * @return the minimum spanning tree.
	 */
	public ArrayList<Edge<Vertex>> prim() {

		boolean visited[] = new boolean[V];
		Vertex closest[] = new Vertex[this.V];
		double distance[] = new double[this.V];
		for (int i = 0; i < V; i++)
			distance[i] = Double.MAX_VALUE;

		ArrayList<Edge<Vertex>> tree = new ArrayList<Edge<Vertex>>();

		LinkedList<Vertex> nodes = createArrayOfVerteces();
		IndexAndVertex nodesWithIndex[] = new IndexAndVertex[V];
		for (int i = 0; i < V; i++) {
			nodesWithIndex[i] = new IndexAndVertex(i, nodes.get(i));
		}

		Vertex v = nodesWithIndex[0].node;
		visited[0] = true;
		for (int i = 1; i < V; i++) {
			LinkedList<Vertex> neighbours = v.getNeighbours();
			for (int c = 0; c < neighbours.size(); c++) {
				int indexOfVertexInArrays = getIndexOfVertexFromList(neighbours.get(c), nodesWithIndex);
				if (getWeight(v, neighbours.get(c)) < distance[indexOfVertexInArrays]) {
					distance[indexOfVertexInArrays] = getWeight(v, neighbours.get(c));
					closest[indexOfVertexInArrays] = v;
				}
			}
			v = minVertex(visited, distance);
			if (v == null) {
				System.out.println("Error: disconneced vertex!");
				System.exit(-1);
			}
			int indexOfNewVertexInArrays = getIndexOfVertexFromList(v, nodesWithIndex);
			visited[indexOfNewVertexInArrays] = true;

			Vertex firstNode;
			Vertex secondNode;
			if (getVertexFromList(tree, closest[indexOfNewVertexInArrays].getID()) == null)
				firstNode = new Vertex(closest[indexOfNewVertexInArrays]);
			else
				firstNode = getVertexFromList(tree, closest[indexOfNewVertexInArrays].getID());
			if (getVertexFromList(tree, v.getID()) == null)
				secondNode = new Vertex(v);
			else
				secondNode = getVertexFromList(tree, v.getID());

			tree.add(new Edge(firstNode, secondNode, getWeight(closest[indexOfNewVertexInArrays], v)));
		}
		return tree;
	}

	private class IndexAndVertex {
		public int index;
		public Vertex node;

		public IndexAndVertex(int i, Vertex n) {
			index = i;
			node = n;
		}
	}

	private int getIndexOfVertexFromList(Vertex vertex, IndexAndVertex indexAndVertexArray[]) {
		for (int i = 0; i < indexAndVertexArray.length; i++) {
			if (indexAndVertexArray[i].node.equals(vertex))
				return indexAndVertexArray[i].index;
		}
		return -1;
	}

	private Vertex minVertex(boolean visited[], double distance[]) {
		LinkedList<Vertex> nodes = createArrayOfVerteces();
		Vertex min = null;
		double minimum = Double.MAX_VALUE;
		for (int i = 0; i < nodes.size(); i++) {
			if (visited[i] == true)
				continue;
			if (distance[i] < minimum) {
				min = nodes.get(i);
				minimum = distance[i];
			}
		}
		return min;
	}

	/**
	 * Prints the minimum spanning tree created.
	 * 
	 * @param tree the minimum spanning tree.
	 */
	public void printMinimumSpanningTreeEdges(ArrayList<Edge<Vertex>> tree) {
		// queue to use for BFS
		Queue<Vertex> q = new LinkedList();

		// check if "02" vertex exists in tree
		if (!nodeWithIdExistsInList(h.getListWithKey(Integer.parseInt(startVertexID)), startVertexID)) {
			System.out.println("Vertex with ID 02 not in tree!");
			System.exit(-1);
		}

		System.out.println("\nID (Temperature) <-> ID (Temperature) Distance\n");
		// printed array used to not get the same edge twice
		boolean printed[] = new boolean[tree.size()]; // Initialised to false by java

		// "02" vertex added
		q.add(getVertexFromList(tree, startVertexID));

		while (!q.isEmpty()) {
			Vertex current = q.poll();
			// System.out.println(current);
			// gets an edge which has the current vertex
			Edge currentEdge = getEdgeWithVertexFromList(tree, current, printed);
			// all the vertexes that have an edge with current are added to the queue
			while (currentEdge != null) {
				// edge is printed
				System.out.println(currentEdge);
				// new vertexes are added
				q.add((Vertex) currentEdge.getOtherObject(current));
				// gets the next edge of current vertex if exists
				currentEdge = getEdgeWithVertexFromList(tree, current, printed);
			}
		}
	}

	private Vertex getVertexFromList(ArrayList<Edge<Vertex>> tree, String id) {
		for (int i = 0; i < tree.size(); i++) {
			if (tree.get(i).v1.getID().equals(id))
				return tree.get(i).v1;
			if (tree.get(i).v2.getID().equals(id))
				return tree.get(i).v2;
		}
		return null;
	}

	private Edge getEdgeWithVertexFromList(ArrayList<Edge<Vertex>> tree, Vertex node, boolean[] printed) {
		for (int i = 0; i < tree.size(); i++) {
			if (printed[i] == false && tree.get(i).hasObject(node)) {
				printed[i] = true;
				return tree.get(i);
			}
		}
		return null;
	}

	private Vertex getVertexFromID(String id) {
		for (int i = 0; i < h.getListWithKey(Integer.parseInt(id)).size(); i++) {
			if (((Vertex) h.getListWithKey(Integer.parseInt(id)).get(i)).getID().equals(id))
				return ((Vertex) h.getListWithKey(Integer.parseInt(id)).get(i));
		}
		return null;
	}

	private boolean nodeWithIdExistsInList(LinkedList<Vertex> list, String id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getID().equals(id)) {
				return true;
			}
		}
		return false;
	}

	private void addOnly(Vertex node) {
		checkNull(node, 318);
		if (!vertexExists(node.getID())) {
			h.add(node);
			V++;
		}
	}

	private void createEdge(Edge<Vertex> edge) {
		checkNull(edge, 325);
		E++;
		addOnly(edge.v1);
		addOnly(edge.v2);
		edge.v1.addNeighbour(edge.v2);
		edge.v2.addNeighbour(edge.v1);
	}

	/**
	 * Converts the minimum spanning tree from an Array List into a graph.
	 * 
	 * @param mst the minimum spanning tree in an Array List form.
	 * @return the minimum spanning tree in a Graph form.
	 */
	public Graph CreatGraphFromEdgesList(ArrayList<Edge<Vertex>> mst) {
		checkNull(mst, 334);
		Edge currentEdge;
		Graph g = new Graph();
		for (int i = 0; i < mst.size(); i++) {
			currentEdge = mst.get(i);
			g.createEdge(currentEdge);
		}
		return g;
	}

	/**
	 * To string method for the graph.
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < h.getSizeOfArray(); i++) {
			if (h.getListAt(i) != null)
				for (int j = 0; j < h.getListAt(i).size(); j++) {
					s += ((Vertex) h.getListAt(i).get(j)).getStringStatus();
					s += "\n";
				}
		}
		return s;
	}

	private int getMaxTempForVertex(String id, Vertex v, Vertex back) {
		checkNull(v, 372);

		if (!v.getID().equals(id) && v.getNeighbours().size() == 1)
			return v.getTemperture();

		LinkedList<Vertex> neighbours = v.getNeighbours();
		int maxTemp = v.getTemperture();
		for (int i = 0; i < neighbours.size(); i++) {
			if (back != null && neighbours.get(i).getID().equals(back.getID()))
				continue;
			int temp = getMaxTempForVertex(id, neighbours.get(i), v);
			if (maxTemp < temp)
				maxTemp = temp;
		}
		return maxTemp;
	}

	/**
	 * Returns the max temperature from the fire station from all nodes.
	 * 
	 * @param id The fire station id.
	 * @return max temperature.
	 */
	public int getMaxTempForVertex(String id) {
		Vertex v = getVertexFromID(id);
		checkNull(v, 388);
		return getMaxTempForVertex(id, v, null);
	}

	/**
	 * Removes a neighbor and connects if possible (if within the max distance) the
	 * neighbors of the removed node.
	 * 
	 * @param id The id of the node that is going to be removed.
	 * @param d  The maximum distance between neighbors.
	 */
	public void removeAndConnect(String id, int d) {
		Vertex toBeRemoved = getVertexFromID(id);
		for (int i = 0; i < toBeRemoved.getNeighbours().size(); i++) {
			Vertex v = toBeRemoved.getNeighbours().get(i);
			createEdges(v, d);
		}
		removeVertex(id);
	}
}
