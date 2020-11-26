package hw3Epl231;

import java.util.ArrayList;

public class HashTable<T extends NodeID> {
	// size of hashtable array
	private int size;
	// each list is a set of non-neighbours nodes
	private ArrayList<T> table[];
	// number of nodes in hashtbale
	private int nodes;

	public HashTable() {
		size = 5;
		nodes = 0;
		table = new ArrayList[size];
	}

	public int hashFunction(int num) {
		if (num % size < 0 || num % size >= size)
			return 0;
		return num % size;
	}

	public void rehash(int newSize) {
		int sizeTemp = size;
		size = newSize;
		ArrayList<T> temp[] = new ArrayList[newSize];
		for (int i = 0; i < sizeTemp; i++) {
			for (int j = 0; j < table[i].size(); j++) {
				// position in new table
				int position = hashFunction(table[i].get(j).getID());
				if (temp[position] == null)
					temp[position] = new ArrayList<T>();
				temp[position].add(table[i].get(j));
			}
		}
		table = temp;
	}

	public int getNodes() {
		return nodes;
	}

	public int getSizeOfArray() {
		return size;
	}

	public void add(T node) {
		nodes++;
		int position = hashFunction(node.getID());
		if (table[position] == null)
			table[position] = new ArrayList<T>();
		table[position].add(node);
		if (table[position].size() >= 20)
			rehash(size * 10);
	}

	public ArrayList getListAt(int index) {
		if (index < 0 || index >= size)
			return null;
		return table[index];
	}

	public ArrayList getListWithID(int id) {
		return table[hashFunction(id)];
	}

}
