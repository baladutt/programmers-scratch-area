package com.programming.linkedlists;


class Node<T>
{
	T data;
	Node<T> next = null;
	
	public Node(T data)
	{
		this.data = data;
	}
}

/**
 * Terminology:
 * 	Current
 * 	Head
 * 	Tail
 * 	Next
 * 
 * Invariants:
 * 	Tail should be pointing to null
 * 
 * @author bdutt
 *
 */
public class LinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		for(int i=0;i<10;i++)
			list.add(new Node<String>(""+i));
		
		list.dump();
		list.reverseRecursion();
		list.dump();
		list.reverseRecursion();
		list.dump();
		list.reverseIteration();
		list.dump();

	}

	Node head;
	public void add(Node node) {
		if(null==head){
			head= node;
			return;
		}
		Node curr = head;
		while(curr.next!=null)
		{
			curr=curr.next;
		}
		curr.next = node;
	}
	
	public void dump()
	{
		Node curr = head;
		while(curr!=null)
		{
			System.out.print(curr.data+" -> ");
			curr=curr.next;
		}
		System.out.println("null");
	}
	
	public void reverseRecursion()
	{
		Node tail = reverseRecursionAtNode(head);
		tail.next = null;
	}
	
	public Node reverseRecursionAtNode(Node curr)
	{
		if(null==curr)
		{
			return null;
		}
		if(null==curr.next){
			head=curr;
		}else
		{
			Node tail = reverseRecursionAtNode(curr.next);
			tail.next = curr;
			tail=curr;
		}
		return curr;
		
	}
	
	public void reverseIteration()
	{
		Node curr = head;
		Node newHead = null;
		while(curr!=null)
		{
			Node next = curr.next;
			if(null==newHead){
				newHead = curr;
			}else{
				
				curr.next = newHead;
				newHead = curr;
			}
			curr=next;
		}
		
		if(null!=newHead)
		{
			head.next = null;
			head=newHead;
		}
	}

}
