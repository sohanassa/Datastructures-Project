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

	private void createEdges(GraphNode node, int maxDist) {
	}

	public boolean isEdge(GraphNode node1, GraphNode node2) {
		checkNull(node1);
		checkNull(node2);
		return node1.isNeightbour(node2);
	}

	public void removeVertex(GraphNode node) {
		V--;
		checkNull(node);
		ArrayList list = h.getListWithID(Integer.parseInt(node.getID()));
		for (int i = 0; i < list.size(); i++) {
			if ((Integer) list.get(i) == Integer.parseInt(node.getID()))
				list.remove(i);
		}
		// werk here
	}

	public boolean vertexExists(GraphNode node) {
		checkNull(node);
		ArrayList list = h.getListWithID(Integer.parseInt(node.getID()));
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

	public ArrayList<GraphNode> createArrayOfNodes() {

		ArrayList<GraphNode> tab = new ArrayList<GraphNode>(V);

		for (int i = 0; i < h.getSizeOfArray(); i++)
			if (h.getListAt(i) != null)
				tab.addAll(h.getListAt(i));

		return tab;

	}

	public ArrayList<GraphNode> getClosestNeighbours(GraphNode n) {
		ArrayList<GraphNode> closest = new ArrayList<GraphNode>();
		for (int i = 0; i < n.getNeighbours().size(); i++)
			closest.add(n.getNeighbours().get(i));
		return closest;
	}

	public ArrayList<Integer> getDistance(GraphNode n, ArrayList<GraphNode> closest) {
		ArrayList<Integer> distance = new ArrayList<Integer>();
		for (int i = 0; i < closest.size(); i++)
			distance.add(new Integer((int) getWeight(n, closest.get(i))));
		return distance;
	}

	public int getIndexOfNode(GraphNode node) {
		int index=0;
		
		for(int i=0; i<h.getNodes(); i++) {
			if(!h.getListAt(i).contains(node))
				index+=h.getListAt(i).size();
			else
				return index+h.getListAt(i).indexOf(node);
		}
		return -1;
	}

	public ArrayList<Integer> visitedNeighbours(boolean[] visited, ArrayList<GraphNode> closest, GraphNode node){
		ArrayList<Integer> visitedNeighbours = new ArrayList<Integer>();
		
		for(int i=0; i<closest.size(); i++) {
			int index=getIndexOfNode(closest.get(i));
			if(visited[index])
				visitedNeighbours.add(new Integer(1));
			else
				visitedNeighbours.add(new Integer(0));
		}
		
		return visitedNeighbours;
	}

	public int findNextNode(ArrayList<Integer> visitedNeighbours, ArrayList<GraphNode> closest, ArrayList<Integer> distance) {
		
		if(!visitedNeighbours.contains(new Integer(0)))
			return -1;
		
		Integer min = new Integer(-1);
		int index = -1;
		for(int i=0; i<visitedNeighbours.size();i++)
			if(visitedNeighbours.get(i).equals(new Integer(0)))
				if(distance.get(i).compareTo(min)<0) {
					min = distance.get(i);
					index = i;
				}
		return index;
		
	}
	
	public boolean allVisited(boolean[] visited) {
		for(int i=0; i<visited.length; i++)
			if(!visited[i])
				return false;
		return true;
	}
	
	public int findNonVisited(boolean[] visited) {
		for(int i=0; i<visited.length; i++)
			if(!visited[i])
				return i;
		return -1;
	}
	
	public ArrayList<Edge<GraphNode>> prim() {

		double weight = 0;	//i didnt use this wtf im supposed to use it
		
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

		ArrayList<Edge<GraphNode>> tree = new ArrayList<Edge<GraphNode>>();

		ArrayList<GraphNode> nodes = createArrayOfNodes();

		boolean allVisited=false;
		int i=0;
		while(!allVisited) {
			visited[i]=true;
			ArrayList<GraphNode> closest = getClosestNeighbours(nodes.get(i));
			ArrayList<Integer> distance = getDistance(nodes.get(i), closest);
			ArrayList<Integer> visitedNeighbours = visitedNeighbours(visited, closest, nodes.get(i));
			int index = findNextNode(visitedNeighbours, closest, distance);
			if(index!=-1) {
				tree.add(new Edge(nodes.get(i), nodes.get(index), getWeight(nodes.get(i), nodes.get(index))));
				i=index;}
			else
				if(allVisited(visited))
					allVisited=true;
				else
					i=findNonVisited(visited);
		}
		
		return tree;
	
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
	
	public int getV() {
		return V;
	}
	
	public int getE() {
		return E;
	}
	

	private void printMinimumSpanningTree(Set<Edge<GraphNode>> tree) {
		Queue q = new LinkedList();
		// S O  I A M  C O N F U S S I O N 
		
	}
	
	public String toString() {
		String s="";
		for(int i=0; i<h.getSizeOfArray(); i++) {
			if(h.getListAt(i)!=null)
				for(int j=0; j<h.getListAt(i).size(); j++) {
					s+=h.getListAt(i).get(j).toString();
					s+="\n";
				}
		}
		return s;
	}

}
