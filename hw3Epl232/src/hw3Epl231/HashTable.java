package hw3Epl231;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class HashTable<T extends VertexWithID> {
	// size of hashtable array
	private int size;
	// each list is a set of non-neighbours nodes
	private LinkedList<T> table[];
	// number of nodes in hashtbale
	private int nodes;

	public HashTable() {
		size = 5;
		nodes = 0;
		table = new LinkedList[size];
	}

	public int hashFunction(int num) {
		if (num % size < 0 || num % size >= size)
			return 0;
		// 0 ~ size-1
		return num % size;
	}

	public void rehash(int newSize) {
		int sizeTemp = size;
		size = newSize;
		LinkedList<T> temp[] = new LinkedList[newSize];
		for (int i = 0; i < sizeTemp; i++) {
			if (table[i] != null)
				for (int j = 0; j < table[i].size(); j++) {
					// position in new table
					int position = hashFunction(getIntValue(table[i].get(j).getID()));
					if (temp[position] == null)
						temp[position] = new LinkedList<T>();
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
		int position = hashFunction(getIntValue(node.getID()));
		if (table[position] == null)
			table[position] = new LinkedList<T>();
		table[position].add(node);
		if (table[position].size() >= 20)
			rehash(size * 10);
	}

	public LinkedList getListAt(int index) {
		if (index < 0 || index >= size)
			return null;
		return table[index];
	}

	public LinkedList getListWithID(int id) {
		return table[hashFunction(id)];
	}

	public int getIntValue(String s) {
		return Integer.parseInt(s);
	}

	public void addLinkedList(LinkedList newNodes, int index) {
		if (!newNodes.isEmpty() && index >= 0 && index < size)
			table[index] = newNodes;
	}
}