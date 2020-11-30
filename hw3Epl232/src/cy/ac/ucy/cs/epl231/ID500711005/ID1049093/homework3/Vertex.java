package cy.ac.ucy.cs.epl231.ID500711005.ID1049093.homework3;

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

	public Vertex(int x, int y, String id, boolean isFireStation, int temperture) {
		this.x = x;
		this.y = y;
		this.ID = id;
		this.isFireStation = isFireStation;
		this.temperature = temperture;
		neighbours = new LinkedList<Vertex>();
	}

	public Vertex(Vertex cpy) {
		this.x = cpy.x;
		this.y = cpy.y;
		this.ID = cpy.ID;
		this.isFireStation = cpy.isFireStation;
		this.temperature = cpy.temperature;
		neighbours = new LinkedList<Vertex>();
	}

	public String getID() {
		return ID;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getTemperture() {
		return temperature;
	}

	public void setTemperture(int newTemp) {
		temperature = newTemp;
	}

	public boolean isFireStation() {
		return isFireStation;
	}

	public LinkedList<Vertex> getNeighbours() {
		return neighbours;
	}

	public boolean isNeightbour(Vertex node) {
		if (node == null)
			return false;
		for (int i = 0; i < neighbours.size(); i++) {
			if (neighbours.get(i).equals(node))
				return true;
		}
		return false;
	}

	public boolean equals(Object node) {
		if (node instanceof Vertex) {
			Vertex o = (Vertex) node;
			if (ID.equals(o.getID()))
				return true;
		}

		return false;
	}

	public String getStringStatus() {
		String s = "";
		s = s + ID + "\t" + "[" + x + ", " + y + "]" + "\t" + temperature;
		return s;
	}

	public void addNeighbour(Vertex newNode) {
		if (!neighbours.contains(newNode))
			neighbours.add(newNode);
	}

	public String toString() {
		return ID+" ("+temperature+")";
	}

	public void removeNeighbour(Vertex node) {
		neighbours.remove(node);
	}

}