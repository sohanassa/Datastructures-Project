package cy.ac.ucy.cs.epl231.ID500711005.ID1049093.homework3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class HashTable<T extends VertexWithID> {
	// size of hashtable array
	private int size;
	// each list is a set of non-neighbours nodes
	private LinkedList<T> table[];
	// number of objects in hashtbale
	private int cnt;

	public HashTable() {
		size = 5;
		cnt = 0;
		table = new LinkedList[size];
	}

	/**
	 * Hashfunction of hashtable.
	 * 
	 * @param num intiger key
	 * @return hashvalue between 0 and size-1
	 */
	public int hashFunction(int num) {
		if (num % size < 0 || num % size >= size)
			return 0;
		// 0 ~ size-1
		return num % size;
	}

	/**
	 * Function that rehashes the hashtable with a new size.
	 * 
	 * @param newSize new size of table
	 */
	public void rehash(int newSize) {
		int sizeTemp = size;
		size = newSize;
		LinkedList<T> temp[] = new LinkedList[newSize];
		for (int i = 0; i < sizeTemp; i++) {
			if (table[i] != null)
				for (int j = 0; j < table[i].size(); j++) {
					// position in new table
					int position = hashFunction(Integer.parseInt(table[i].get(j).getID()));
					if (temp[position] == null)
						temp[position] = new LinkedList<T>();
					temp[position].add(table[i].get(j));
				}
		}
		table = temp;
	}

	/**
	 * Getter for nnumber of objects.
	 * 
	 * @return number of objects
	 */
	public int getCnt() {
		return cnt;
	}

	/**
	 * Getter for size.
	 * 
	 * @return size
	 */
	public int getSizeOfArray() {
		return size;
	}

	/**
	 * Function that adds the given object to the hashtable.
	 * 
	 * @param obj the object to b added
	 */
	public void add(T obj) {
		cnt++;
		int position = hashFunction(Integer.parseInt((obj.getID())));
		if (table[position] == null)
			table[position] = new LinkedList<T>();
		table[position].add(obj);
		if (table[position].size() >= 20)
			rehash(size * 10);
	}

	/**
	 * Returns the list at the given index.
	 * 
	 * @param index
	 * @return the list
	 */
	public LinkedList getListAt(int index) {
		if (index < 0 || index >= size)
			return null;
		return table[index];
	}

	/**
	 * Returns the list with the give key.
	 * 
	 * @param key the key
	 * @return the list
	 */
	public LinkedList getListWithKey(int key) {
		return table[hashFunction(key)];
	}

	/**
	 * Adds the given list to the hashtable.
	 * 
	 * @param newObjects the list to be added
	 * @param index      the index in which it will be added
	 */
	public void addLinkedList(LinkedList newObjects, int index) {
		if (!newObjects.isEmpty() && index >= 0 && index < size)
			table[index] = newObjects;
	}
}