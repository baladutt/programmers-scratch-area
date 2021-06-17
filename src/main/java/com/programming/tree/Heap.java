package com.programming.tree;

class HeapNode {
	int index;
	int key;
	Object value;

	public HeapNode(int key, Object value) {
		this.key = key;
		this.value = value;
	}
}

public class Heap {

	public static final int INITIAL_CAPACITY = 20;
	HeapNode[] arr = new HeapNode[INITIAL_CAPACITY];
	int currFilled = 0;

	protected HeapNode getNodeAt(int i) {
		if (i < currFilled)
			return arr[i];
		return null;
	}

	public HeapNode left(HeapNode node) {
		return getNodeAt(2 * (node.index + 1) - 1);
	}

	public HeapNode right(HeapNode node) {
		return getNodeAt(2 * (node.index + 1) - 1 + 1);
	}

	public HeapNode parent(HeapNode node) {
		if(0==node.index)return null;
		return getNodeAt(node.index / 2);
	}

	public HeapNode append(int key, Object value) {
		HeapNode node = new HeapNode(key, value);
		if (currFilled >= arr.length) {
			;// TODO: resize
			return null; // for now we have hard coded
		}
		arr[currFilled++] = node;
		node.index = currFilled -1;
		return node;

	}
	
	protected void swap(HeapNode parent, HeapNode node) {
		arr[parent.index]=node;
		arr[node.index] = parent;
		int tmp = parent.index;
		parent.index=node.index;
		node.index = tmp;
		
	}

	public void printNode(HeapNode node) {
		if (null == node)
			return;
		System.out.print("{" + node.index+","+node.key + ","+node.value+ "} ");
		printNode(left(node));
		printNode(right(node));
	}

	public void printHeap() {
		for (int i = 0; i < currFilled / 2; i++)
			printNode(getNodeAt(i));
		System.out.println();
	}

	private static void printArray(int[] arr, int offset, int length) {
		for (int i = offset; i < offset + length; i++)
			System.out.print("" + arr[i] + " ");
		System.out.println();
	}

	private static void printArray(int[] arr) {
		printArray(arr, 0, arr.length);
	}

}
