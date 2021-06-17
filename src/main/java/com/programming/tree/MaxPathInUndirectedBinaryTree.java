package com.programming.tree;

import java.util.ArrayList;

public class MaxPathInUndirectedBinaryTree {

	static class Node {
		Node parent;
		Node leftChild;
		Node rightChild;
		int value;

		public Node(Node leftChild, Node rightChild, int value) {
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.value = value;
		}
	}

	private int max(int a, int b) {
		return a > b ? a : b;
	}

	int traverseForMaxPath(Node node, int maxPathLength, ArrayList<Node> nodesVisited) {

		if(nodesVisited.contains(node))
		{
			//An alternative to coloring
			return Integer.MIN_VALUE;
		}
		if (null == node) {
			return Integer.MIN_VALUE; // I don't contribute anything
		}
		nodesVisited.add(node);

		int maxPathLengthWithMe = maxPathLength + node.value;
		
		if(maxPathLengthWithMe > 0)
		{
			maxPathLength = maxPathLengthWithMe;
		}else{
			maxPathLength = 0;// A new path should start
		}
		
		int maxPathLengthWithParent = traverseForMaxPath(node.parent,
				maxPathLength, nodesVisited);
		int maxPathLengthWithLeftChild = traverseForMaxPath(node.leftChild,
				maxPathLength, nodesVisited);
		int maxPathLengthWithRightChild = traverseForMaxPath(node.rightChild,
				maxPathLength, nodesVisited);
		int myPathLength = maxPathLengthWithLeftChild;
		if(maxPathLengthWithRightChild != Integer.MIN_VALUE || maxPathLengthWithLeftChild != Integer.MIN_VALUE)
			myPathLength += maxPathLengthWithRightChild - maxPathLength; //Path may be me, my left and my right
		maxPathLength = max(
				maxPathLengthWithMe,
				max(myPathLength,
				max(maxPathLengthWithParent,
						max(maxPathLengthWithLeftChild,
								maxPathLengthWithRightChild))));
		return maxPathLength;
	}
	
	public static void main(String []args)
	{
		MaxPathInUndirectedBinaryTree treeTraversal = new MaxPathInUndirectedBinaryTree();
		Node node1 = new Node( null, null, -6);
		Node node2 = new Node(null, null, -10);
		Node root = new Node(node1, node2, -5);
		System.out.println(treeTraversal.traverseForMaxPath(root, 0, new ArrayList<MaxPathInUndirectedBinaryTree.Node>()));
		
		node1 = new Node( null, null, -6);
		node2 = new Node(null, null, 10);
		root = new Node(node1, node2, -5);
		System.out.println(treeTraversal.traverseForMaxPath(root, 0, new ArrayList<MaxPathInUndirectedBinaryTree.Node>()));
		
		node1 = new Node( null, null, 2);
		node2 = new Node(null, null, 3);
		root = new Node(node1, node2, 5);
		System.out.println(treeTraversal.traverseForMaxPath(root, 0, new ArrayList<MaxPathInUndirectedBinaryTree.Node>()));
		
		node1 = new Node( null, null, -1);
		node2 = new Node(null, null, 2);
		node1 = new Node( null, null, 2);
		node2 = new Node(node1, node2, 3);
		root = new Node(node1, node2, 5);
		System.out.println(treeTraversal.traverseForMaxPath(root, 0, new ArrayList<MaxPathInUndirectedBinaryTree.Node>()));
	
	}

}
