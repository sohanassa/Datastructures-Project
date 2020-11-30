package cy.ac.ucy.cs.epl231.ID500711005.ID1049093.homework3;

/**
 * The Vertex class represents a vertex of the graph. The vertex consists
 * its ID, its position (x,y), a boolean field if it is a fire station,
 * the temperature of the vertex and its neighbors.
 *
 */

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Vertex implements VertexWithID {
	private int x;
	private int y;
	private String ID;
	private boolean isFireStation;
	private int temperature;
	private LinkedList<Vertex> neighbours;

	/**
	 * Constructor for the vertex.
	 * 
	 * @param x             The x position of the vertex.
	 * @param y             The y position of the vertex.
	 * @param id            The ID of the vertex.
	 * @param isFireStation If the vertex is a fire station.
	 * @param temperture    The temperature of the vertex.
	 */
	public Vertex(int x, int y, String id, boolean isFireStation, int temperture) {
		this.x = x;
		this.y = y;
		this.ID = id;
		this.isFireStation = isFireStation;
		this.temperature = temperture;
		neighbours = new LinkedList<Vertex>();
	}

	/**
	 * Creates a vertex by coping another vertex.
	 * 
	 * @param cpy The copied vertex.
	 */
	public Vertex(Vertex cpy) {
		this.x = cpy.x;
		this.y = cpy.y;
		this.ID = cpy.ID;
		this.isFireStation = cpy.isFireStation;
		this.temperature = cpy.temperature;
		neighbours = new LinkedList<Vertex>();
	}

	/**
	 * Setter method for the ID of the vertex.
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Getter method for the x of the position of the vertex.
	 * 
	 * @return The x.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter method for the y of the position of the vertex.
	 * 
	 * @return The y.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Getter method for the temperature of the vertex.
	 * 
	 * @return The temperature.
	 */
	public int getTemperture() {
		return temperature;
	}

	/**
	 * Setter method for the temperature of the vertex.
	 * 
	 * @param newTemp The new temperature.
	 */
	public void setTemperture(int newTemp) {
		temperature = newTemp;
	}

	/**
	 * Getter method for if the vertex is a fire station.
	 * 
	 * @return True/False if the vertex is a fire station.
	 */
	public boolean isFireStation() {
		return isFireStation;
	}

	/**
	 * Getter method for the neighbors of the vertex.
	 * 
	 * @return the neighbors.
	 */
	public LinkedList<Vertex> getNeighbours() {
		return neighbours;
	}

	/**
	 * Checks if the node is a neighbor to this vertex.
	 * 
	 * @param node The node that we are going to check for.
	 * @return True/False if the node is a neighbor to this vertex.
	 */
	public boolean isNeightbour(Vertex node) {
		if (node == null)
			return false;
		for (int i = 0; i < neighbours.size(); i++) {
			if (neighbours.get(i).equals(node))
				return true;
		}
		return false;
	}

	/**
	 * Equals method for vertex.
	 * 
	 * @param node The node that we are going to check if it equals to.
	 * @return True/False if the node is equal to this vertex.
	 */
	public boolean equals(Object node) {
		if (node instanceof Vertex) {
			Vertex o = (Vertex) node;
			if (ID.equals(o.getID()))
				return true;
		}

		return false;
	}

	/**
	 * Gets in a string form the ID, position and temperature of this vertex.
	 * 
	 * @return string of the ID, position and temperature of this vertex.
	 */
	public String getStringStatus() {
		String s = "";
		s = s + ID + "\t" + "[" + x + ", " + y + "]" + "\t" + temperature;
		return s;
	}

	/**
	 * Adds newNode as a neighbor to this vertex if it is not already.
	 * 
	 * @param newNode The new neighbor.
	 */
	public void addNeighbour(Vertex newNode) {
		if (!neighbours.contains(newNode))
			neighbours.add(newNode);
	}

	/**
	 * To string method for vertex where it prints the temperature of the vertex.
	 */
	public String toString() {
		return ID + " (" + temperature + ")";
	}

	/**
	 * Removes neighbor node.
	 * 
	 * @param node The neighbor we are going to remove.
	 */
	public void removeNeighbour(Vertex node) {
		neighbours.remove(node);
	}

}