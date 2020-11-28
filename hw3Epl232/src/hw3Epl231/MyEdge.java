package hw3Epl231;

public class MyEdge<E> {
	E v1;
	E v2;
	double weight;

	MyEdge(E v1, E v2, double weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}

	public E getV1() {
		return v1;
	}

	public E getV2() {
		return v2;
	}

	public double getWeight() {
		return weight;
	}

	public boolean hasObject(E obj) {
		return v1.equals(obj) || v2.equals(obj);
	}

	public String toString() {
		return v1 + " - " + v2;
	}

	public E getOtherObject(E obj) {
		if (v1.equals(obj))
			return v2;
		if (v2.equals(obj))
			return v1;
		return null;
	}
}