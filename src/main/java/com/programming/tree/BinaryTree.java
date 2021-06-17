package com.programming.tree;

import java.util.Stack;

public class BinaryTree<T> {

BinaryTreeNode<T> root;

public BinaryTree(BinaryTreeNode<T> root)
{
	this.root = root;
}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTreeNode<String> node8 = new BinaryTreeNode<String>(null, null, "8");
		BinaryTreeNode<String> node9 = new BinaryTreeNode<String>(null, null, "9");
		BinaryTreeNode<String> node4 = new BinaryTreeNode<String>(node8, node9, "4");
		BinaryTreeNode<String> node5 = new BinaryTreeNode<String>(null, null, "5");
		BinaryTreeNode<String> node6 = new BinaryTreeNode<String>(null, null, "6");
		BinaryTreeNode<String> node7 = new BinaryTreeNode<String>(null, null, "7");
		BinaryTreeNode<String> node2 = new BinaryTreeNode<String>(node4, node5, "2");
		BinaryTreeNode<String> node3 = new BinaryTreeNode<String>(node6, node7, "3");
		BinaryTreeNode<String> node1 = new BinaryTreeNode<String>(node2, node3, "1");
		
		BinaryTree<String> tree = new BinaryTree<String>(node1);
		tree.traverseZigZag();

	}
	private void traverseZigZag() {
		Stack<BinaryTreeNode<T>> currStack = new Stack<BinaryTreeNode<T>>();
		Stack<BinaryTreeNode<T>> nextStack = new Stack<BinaryTreeNode<T>>();
		
		currStack.push(root);
		boolean bDirectionLeftToRight = true;
		while(currStack.size()!=0)
		{
			BinaryTreeNode<T> node = currStack.pop();
			if(bDirectionLeftToRight)
			{
				if(node.left != null)nextStack.push(node.left);
				if(node.right != null)nextStack.push(node.right);
			}else{
				if(node.right != null)nextStack.push(node.right);
				if(node.left != null)nextStack.push(node.left);
			}
			System.out.print(node.getData()+" ");
			if(currStack.size() == 0)
			{
				bDirectionLeftToRight = ! bDirectionLeftToRight;
				Stack<BinaryTreeNode<T>> tmp = currStack;
				currStack = nextStack;
				nextStack = tmp;
				System.out.println();
			}
		}
		
	}

}
