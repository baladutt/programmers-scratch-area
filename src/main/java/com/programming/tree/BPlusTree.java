package com.programming.tree;

class Bucket {
	int[] keys;
	Object[] values;
	Bucket next = null;
	Bucket prev = null;
	boolean isLeaf = true;
	int countFilled = 0;

	public Bucket(int size) {
		keys = new int[size];
		values = new Object[size];
		if (size < 2) {
			System.out.println("Error: how can bucket be less than 2");

			// TODO: throw exception
		}
	}

	private void splitAndAdd() {
		// Split
		Bucket child1 = new Bucket(keys.length);
		Bucket child2 = new Bucket(keys.length);
		int mid = keys.length / 2;

		for (int i = 0; i < mid; i++) {
			child1.add(keys[i], values[i]);
		}
		for (int i = mid; i < keys.length; i++) {
			child2.add(keys[i], values[i]);
		}
		child2.next = this.next;
		child1.next = child2;
		child1.prev = this.prev;
		child2.prev = child1;
		if (this.prev != null) {
			this.prev.next = child1;
		}
		if (this.next != null) {
			this.next.prev = child2;
		}
		this.next = null;

		child1.isLeaf = isLeaf;
		child2.isLeaf = isLeaf;

		isLeaf = false;

		// Set my children
		countFilled = 2;
		values[0] = child1;
		values[1] = child2;
		keys[1] = keys[mid];
	}

	public void add(int key, Object value) {

		if (isLeaf) {
			if (countFilled < keys.length) {
				for (int i = 0; i < countFilled; i++) {
					if (keys[i] > key) {
						// push everything down by one
						for (int j = countFilled; j > i; j--) {
							keys[j] = keys[j - 1];
							values[j] = values[j - 1];
						}
						keys[i] = key;
						values[i] = value;
						countFilled++;
						return;
					}else if (keys[i]==key)
					{
						keys[i] = key;
						values[i] = value;
						return;
					}
				}
				keys[countFilled] = key;
				values[countFilled] = value;
				countFilled++;
				return;
			} else {
				splitAndAdd();
				add(key, value);
			}
		} else {// Not a leaf

			// If it's in the range just give it to correct child
			for (int i = 0; i < countFilled; i++) {
				if (keys[i] >= key) {
					((Bucket) values[i]).add(key, value);
					return;
				}
			}

			if (countFilled < keys.length) {
				// Need to create a child
				keys[countFilled] = key;
				Bucket bucket = new Bucket(keys.length);
				values[countFilled] = bucket;
				bucket.add(key, value);
				Bucket lastMinus1 = ((Bucket) values[countFilled - 1]).getRightLeaf();
				lastMinus1.next = bucket;
				bucket.prev = lastMinus1;
				countFilled++;
			} else {
				splitAndAdd();
				add(key, value);
			}
		}
	}
	
	private Bucket getRightLeaf()
	{
		if(isLeaf)return this;
		if(countFilled<=0)return this;
		return ((Bucket)values[countFilled-1]).getRightLeaf();
	}

	public void scan(StringBuffer buf) {
		if (isLeaf) {
			for (int i = 0; i < countFilled; i++)
				buf.append(keys[i]).append(",").append(values[i]).append(" | ");
			if (next != null)
				next.scan(buf);
		} else {
			if (countFilled > 0)
				((Bucket) values[0]).scan(buf);// Keep going to left most node
		}
	}

	public void dump(StringBuffer buf, String indent) {
		buf.append("\n" + indent + this + " [" + isLeaf + "] : next : " + next
				+ " : ");
		if (isLeaf) {
			for (int i = 0; i < countFilled; i++)
				buf.append(keys[i]).append(",").append(values[i]).append(" | ");
		} else {
			for (int i = 0; i < countFilled; i++)
				((Bucket) values[i]).dump(buf, indent + "    ");// Keep going to
																// left most
																// node
		}

	}
}

public class BPlusTree {

	int iTemsInBucket = 3;
	Bucket root = new Bucket(iTemsInBucket);

	public static void main(String[] args) {
		BPlusTree tree = new BPlusTree();
		for (int i = 0; i < 10; i++)
			tree.add(i, "" + i);

		tree.scan();
		tree.dump();

		tree.get(5);
	}

	public void get(int i) {

	}

	public void scan() {
		StringBuffer buf = new StringBuffer();
		root.scan(buf);
		System.out.println(buf.toString());
	}

	public void dump() {
		StringBuffer buf = new StringBuffer();
		root.dump(buf, "");
		System.out.println(buf.toString());
	}

	public void add(int i, String string) {
		root.add(i, string);
	}

}
