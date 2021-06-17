package com.programming.sort;

import java.util.ArrayList;

public class HeapSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HeapSort sort = new HeapSort();
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++)
			arr[i] = (int) (Math.random() * 100);
		sort.sort(arr);
		printArray(arr);
		sort.printHeap(arr);

	}

	private static void printArray(int[] arr) {
		for (int i : arr)
			System.out.print("" + i + " ");
		System.out.println();
	}

	private void printNode(int[] arr, int index) {
		if (index >= arr.length)
			return;
		System.out.print("" + arr[index] + " ");
		printNode(arr, left(index));
		printNode(arr, right(index));
	}

	private void printHeap(int[] arr) {
		for (int i = 0; i < arr.length / 2; i++)
			printNode(arr, i);
		System.out.println();
	}

	private static void printArray(int[] arr, int offset, int length) {
		for (int i = offset; i < offset + length; i++)
			System.out.print("" + arr[i] + " ");
		System.out.println();
	}

	public void sort(int[] arr) {

		for (int i = arr.length / 2; i >= 0; i--)
			heapify(arr, i);
	}

	private int left(int i) {
		return 2 * (i + 1) - 1;
	}

	private int right(int i) {
		return 2 * (i + 1) - 1 + 1;
	}

	private int parent(int i) {
		return i / 2;
	}

	private void heapify(int[] arr, int index) {

		int largestIndex = index;
		if (left(index) < arr.length) {
			int l = arr[left(index)];
			if (l > arr[index]) {
				largestIndex = left(index);
			}
		}
		if (right(index) < arr.length) {
			int r = arr[right(index)];
			if (r > arr[largestIndex])
				largestIndex = right(index);
		}
		if (index != largestIndex) {
			int tmp = arr[largestIndex];
			arr[largestIndex] = arr[index];
			arr[index] = tmp;
			heapify(arr, largestIndex);
		}

	}
}
