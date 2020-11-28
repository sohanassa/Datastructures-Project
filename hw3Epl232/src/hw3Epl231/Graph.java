package hw3Epl231;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.math.*;

public class Graph {
	private int V;
	private int E;
	private HashTable<GraphNode> h;
	private static String startVertex = "02";

	public Graph() {
		h = new HashTable();
		V = 0;
		E = 0;
	}

	public void add(GraphNode node, int maxDist) {
		checkNull(node);
		h.add(node);
		createEdges(node, maxDist);
		V++;
	}

	// connects the given node to other existing nodes in the graph aka hashtable
	// does that by inserting the given node in the neighbour list of each node in
	// the
	// graph that has a smaller distance than maxDist
	private void createEdges(GraphNode node, int maxDist) {

		for (int i = 0; i < h.getSizeOfArray(); i++) {
			LinkedList<GraphNode> nodes = h.getListAt(i);
			for(int c=0; c<nodes.size();i++)
				if(!nodes.get(c).equals(node) && getWeight(node, nodes.get(c))<=maxDist)
					node.getNeighbours().add(nodes.get(c));
		}

	}

	public boolean isEdge(GraphNode node1, GraphNode node2) {
		checkNull(node1);
		checkNull(node2);
		return node1.isNeightbour(node2);
	}

	public void removeVertex(GraphNode node) {
		V--;
		checkNull(node);
		// werk here

		LinkedList list = h.getListWithID(Integer.parseInt(node.getID()));
		for (int i = 0; i < list.size(); i++) {
			if ((Integer) list.get(i) == Integer.parseInt(node.getID())) {
				list.remove(i);
				break;
			}
		}
	}

	public boolean vertexExists(GraphNode node) {
		checkNull(node);
		LinkedList list = h.getListWithID(Integer.parseInt(node.getID()));
		for (int i = 0; i < list.size(); i++) {
			if ((Integer) list.get(i) == Integer.parseInt(node.getID()))
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

	public LinkedList<GraphNode> createArrayOfNodes() {

		LinkedList<GraphNode> tab = new LinkedList<GraphNode>();

		for (int i = 0; i < h.getSizeOfArray(); i++)
			if (h.getListAt(i) != null)
				tab.addAll(h.getListAt(i));

		return tab;

	}

	public LinkedList<GraphNode> getClosestNeighbours(GraphNode n) {
		LinkedList<GraphNode> closest = new LinkedList<GraphNode>();
		for (int i = 0; i < n.getNeighbours().size(); i++)
			closest.add(n.getNeighbours().get(i));
		return closest;
	}

	public LinkedList<Integer> getDistance(GraphNode n, LinkedList<GraphNode> closest) {
		LinkedList<Integer> distance = new LinkedList<Integer>();
		for (int i = 0; i < closest.size(); i++)
			distance.add(new Integer((int) getWeight(n, closest.get(i))));
		return distance;
	}

	public int getIndexOfNode(GraphNode node) {
		int index = 0;

		for (int i = 0; i < h.getNodes(); i++) {
			if (!h.getListAt(i).contains(node))
				index += h.getListAt(i).size();
			else
				return index + h.getListAt(i).indexOf(node);
		}
		return -1;
	}

	public LinkedList<Integer> visitedNeighbours(boolean[] visited, LinkedList<GraphNode> closest, GraphNode node) {
		LinkedList<Integer> visitedNeighbours = new LinkedList<Integer>();

		for (int i = 0; i < closest.size(); i++) {
			int index = getIndexOfNode(closest.get(i));
			if (visited[index])
				visitedNeighbours.add(new Integer(1));
			else
				visitedNeighbours.add(new Integer(0));
		}

		return visitedNeighbours;
	}

	public int findNextNode(LinkedList<Integer> visitedNeighbours, LinkedList<GraphNode> closest,
			LinkedList<Integer> distance) {

		if (!visitedNeighbours.contains(new Integer(0)))
			return -1;

		Integer min = new Integer(-1);
		int index = -1;
		for (int i = 0; i < visitedNeighbours.size(); i++)
			if (visitedNeighbours.get(i).equals(new Integer(0)))
				if (distance.get(i).compareTo(min) < 0) {
					min = distance.get(i);
					index = i;
				}
		return index;

	}

	public boolean allVisited(boolean[] visited) {
		for (int i = 0; i < visited.length; i++)
			if (!visited[i])
				return false;
		return true;
	}

	public int findNonVisited(boolean[] visited) {
		for (int i = 0; i < visited.length; i++)
			if (!visited[i])
				return i;
		return -1;
	}

	public ArrayList<MyEdge<GraphNode>> prim() {

		double weight = 0;

		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

		GraphNode closest[] = new GraphNode[this.V];

		double distance[] = new double[this.V];
		for (int i = 0; i < V; i++)
			distance[i] = Double.MAX_VALUE;

		ArrayList<MyEdge<GraphNode>> tree = new ArrayList<MyEdge<GraphNode>>();

		LinkedList<GraphNode> nodes = createArrayOfNodes();
		IndexAndVertex nodesWithIndex[] = new IndexAndVertex[nodes.size()];

		for (int i = 0; i < nodes.size(); i++) {
			nodesWithIndex[i] = new IndexAndVertex(i, nodes.get(i));
		}

		GraphNode v = nodes.get(0);
		visited[0] = true;

		for (int i = 0; i < V; i++) {
			LinkedList<GraphNode> neighbours = v.getNeighbours();
			for (int c = 0; c < neighbours.size(); c++) {
				int indexOfVertexInArrays = getIndexOfVertexFromList(neighbours.get(c), nodesWithIndex);
				if (getWeight(v, neighbours.get(c)) < distance[indexOfVertexInArrays])
					distance[indexOfVertexInArrays] = getWeight(v, neighbours.get(c));
				closest[indexOfVertexInArrays] = nodes.get(i);
			}
			v = minVertex(visited, distance);
			int indexOfNewVertexInArrays = getIndexOfVertexFromList(v, nodesWithIndex);
			visited[indexOfNewVertexInArrays] = true;
			tree.add(new MyEdge(closest[indexOfNewVertexInArrays], v, getWeight(closest[indexOfNewVertexInArrays], v)));
		}
		return tree;
	}

	private class IndexAndVertex {
		public int index;
		public GraphNode node;

		public IndexAndVertex(int i, GraphNode n) {
			index = i;
			node = n;
		}
	}

	private int getIndexOfVertexFromList(GraphNode vertex, IndexAndVertex indexAndVertexArray[]) {
		for (int i = 0; i < indexAndVertexArray.length; i++) {
			if (indexAndVertexArray[i].equals(vertex))
				return indexAndVertexArray[i].index;
		}
		return -1;
	}

	private GraphNode minVertex(boolean visited[], double distance[]) {
		LinkedList<GraphNode> nodes = createArrayOfNodes();
		GraphNode min = nodes.get(0);
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

	public void printMinimumSpanningTree(ArrayList<MyEdge<GraphNode>> tree) {
		// queue to use for BFS
		Queue<GraphNode> q = new LinkedList();

		// check if "02" vertex exists in tree
		if (nodeWithIdExistsInList(h.getListWithID(h.getIntValue(startVertex)), startVertex)) {
			System.out.println("Vertex with ID 02 not in tree!");
			System.exit(-1);
		}

		// printed array used to not get the same edge twice
		boolean printed[] = new boolean[tree.size()]; // Initialised to false by java

		// "02" vertex added
		q.add(getVertexFromID(startVertex));

		while (!q.isEmpty()) {
			GraphNode current = q.poll();
			// gets an edge which has the current vertex
			MyEdge currentEdge = getEdgeWithVertexFromList(tree, current, printed);
			// all the vertexes that have an edge with current are added to the queue
			while (currentEdge != null) {
				// edge is printed
				System.out.println(currentEdge);
				// new vertexes are added
				q.add((GraphNode) currentEdge.getOtherObject(current));
				// gets the next edge of current vertex if exists
				currentEdge = getEdgeWithVertexFromList(tree, current, printed);
			}
		}
	}

	private MyEdge getEdgeWithVertexFromList(ArrayList<MyEdge<GraphNode>> tree, GraphNode node, boolean[] printed) {
		for (int i = 0; i < tree.size(); i++) {
			if (printed[i] == false && tree.get(i).hasObject(node)) {
				printed[i] = true;
				return tree.get(i);
			}
		}
		return null;
	}

	private GraphNode getVertexFromID(String id) {
		for (int i = 0; i < h.getListWithID(h.getIntValue(id)).size(); i++) {
			if (((GraphNode) h.getListWithID(h.getIntValue(id)).get(i)).getID().equals(id))
				return ((GraphNode) h.getListWithID(h.getIntValue(id)).get(i));
		}
		return null;
	}

	private boolean nodeWithIdExistsInList(LinkedList<GraphNode> list, String id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getID().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public void addOnly(GraphNode node) {
		checkNull(node);
		h.add(node);
	}

	public void createEdge(MyEdge<GraphNode> edge) {
		addOnly(edge.v1);
		// working here...
	}

	public Graph CreatGraphFromEdgesList(ArrayList<MyEdge<GraphNode>> mst) {
		checkNull(mst);
		MyEdge currentEdge;
		Graph g = new Graph();
		for (int i = 0; i < mst.size(); i++) {
			currentEdge = mst.get(i);
			g.createEdge(currentEdge);
		}
		return g;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < h.getSizeOfArray(); i++) {
			if (h.getListAt(i) != null)
				for (int j = 0; j < h.getListAt(i).size(); j++) {
					s += ((GraphNode) h.getListAt(i).get(j)).getStringStatus();
					s += "\n";
				}
		}
		return s;
	}
}
