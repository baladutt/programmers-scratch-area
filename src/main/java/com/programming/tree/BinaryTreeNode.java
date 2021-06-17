package com.programming.tree;

public class BinaryTreeNode<T> {
	BinaryTreeNode<T> left;
	BinaryTreeNode<T> right;
	T data;
	
	public BinaryTreeNode()
	{
		
	}
	
	public BinaryTreeNode(BinaryTreeNode<T> left, BinaryTreeNode<T> right, T data)
	{
		setChildren(left, right);
		setData(data);
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setChildren(BinaryTreeNode<T> left, BinaryTreeNode<T> right)
	{
		this.left = left;
		this.right = right;
	}
}
