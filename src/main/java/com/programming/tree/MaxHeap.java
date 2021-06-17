package com.programming.tree;

public class MaxHeap extends Heap {

	public void add(int key, Object value) {
		
		//Add the child to rightmost position
		HeapNode node = append(key, value);
		
		//Heapify
		heapify(node);
	}
	
	public void heapify(HeapNode node)
	{
		if(null==node)return; //TODO: add error message
		HeapNode parent = parent(node);
		if(null!=parent)
		{
			if(parent.key < node.key)
			{
				//swap
				swap(parent, node);
				heapify(node);
				return;
			}
		}
		
		HeapNode left = left(node);
		HeapNode right = right(node);
		
		HeapNode larger = null;
		if(null == left)larger = right;
		else if(null == right)larger = left;
		else if(right.key > left.key) larger = right;
		else larger = left;
		
		if(null!=larger)
		{
			if(larger.key >= node.key)
			{
				swap(larger, node);
				heapify(node);
			}
		}
		return;
	}

	public void delete(int key) {
		HeapNode node = getNodeAt(0);
		delete(key, node);
	}
	
	public boolean delete(int key, HeapNode node) {
		if(null==node)return true;
		if(node.key==key)
		{
			removeNode(node);
			return true;
		}
		if(node.key < key)return false;
		HeapNode left = left(node);
		HeapNode right = right(node);
		
		HeapNode larger = null;
		HeapNode smaller = null;
		if(null == left)larger = right;
		else if(null == right)larger = left;
		else if(right.key > left.key){
			larger = right;
			smaller = left;
		}
		else {
			larger = left;
			smaller = right;
		}
		if(null==larger)return false; //No Children
		if(key>larger.key)return false;
		else {
			delete(key, larger);
			if(null!=smaller)
			{
				if(key>smaller.key) return false;
				delete(key, smaller);
			}
		}
		
		return false;
		
		
	}

	private void removeNode(HeapNode node)
	{
		HeapNode node1 = getNodeAt(currFilled-1);
		currFilled--;
		if(node1==null)System.out.println("Error: do not have any children? : "+currFilled);
		swap(node,node1);
		heapify(node1);
	}
	public Object remove() {
		HeapNode node = getNodeAt(0);
		if(null==node)return null;
		removeNode(node);
		return node.value;
	}
	
	public static void main(String []args)
	{
		MaxHeap heap = new MaxHeap();
		for(int i=0;i<10;i++)
			heap.add(i, ""+i);
		heap.printHeap();
		for(int i=0;i<5;i++)
			System.out.print(heap.remove()+" ");
		System.out.println(" -> removed");
		heap.printHeap();
		for(int i=10;i<15;i++)
			heap.add(i, ""+i);
		heap.printHeap();
		heap.delete(0);
		heap.delete(3);
		heap.delete(11);
		heap.printHeap();
	}

}
