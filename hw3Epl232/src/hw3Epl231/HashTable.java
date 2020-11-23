package hw3Epl231;

//hashtable<int,list<list<Node>>>
public class HashTable<K extends Integer, V> {
	private int size;
	private HashNode<K, V> table[];
	private int used;

	public HashTable() {
		size = 5;
		used = 0;
		table = new HashNode[5];
	}

	public int hashFunction(K num) {
		return num % size;
	}

	public void rehash(int newSize) {
		size = newSize;
		HashNode<K, V> temp[] = new HashNode[newSize];
		for (int i = 0; i < used; i++) {
			temp[i] = table[i];
		}
		table = temp;
	}

	public int getUsed() {
		return used;
	}

	public int getSizeOfArray() {
		return size;
	}

	public void add(K key, V value) {
		used++;
		if(used>size) {
			System.out.println("Not enough space in Hashtable, rehashing with double the previous size!");
			rehash(size*2);
		}
		int position = hashFunction(key);
		table[position] = new HashNode(key, value);
	}
	
	public V getValueWithKey(K i) {
		if(table[hashFunction(i)]==null)
			return null;
		return table[hashFunction(i)].getValue();
	}
}
