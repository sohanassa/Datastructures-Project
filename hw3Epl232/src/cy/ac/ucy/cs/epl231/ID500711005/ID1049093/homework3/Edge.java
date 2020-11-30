package cy.ac.ucy.cs.epl231.ID500711005.ID1049093.homework3;

/**
 * The Edge class represents an weighted edge between vertexes. The edge consist
 * of two vertexes and the distance between the two vertexes. The weight of the
 * edge is their distance.
 *
 */

public class Edge<E> {
	E v1;
	E v2;
	double weight;

	Edge(E v1, E v2, double weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}

	/**
	 * Getter method for the first vertex of the edge.
	 * 
	 * @return the first vertex.
	 */
	public E getV1() {
		return v1;
	}

	/**
	 * Getter method for the second vertex of the edge.
	 * 
	 * @return the second vertex.
	 */
	public E getV2() {
		return v2;
	}

	/**
	 * Getter method for the weight of the edge(the distance between the two
	 * vertexes).
	 * 
	 * @return the weight of the edge.
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Check method if the object(vertex) is in this edge.
	 * 
	 * @param obj The object(vertex) we check for.
	 * @return True/False if the object(vertex) is in this edge.
	 */
	public boolean hasObject(E obj) {
		return ((E) v1).equals(obj) || ((E) v2).equals(obj);
	}

	/**
	 * To string method to print an edge.
	 */
	public String toString() {
		return v1 + "  <->  " + v2 + "    " + Math.round(weight);
	}

	/**
	 * Gets the other pair of vertex of the edge.
	 * 
	 * @param obj The one of two vertexes in the edge.
	 * @return The other of two vertexes in the edge.
	 */
	public E getOtherObject(E obj) {
		if (v1.equals(obj))
			return v2;
		if (v2.equals(obj))
			return v1;
		return null;
	}
}