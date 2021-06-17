package com.programming.sort;

import java.util.ArrayList;

public class MergeSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MergeSort sort = new MergeSort();
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++)
			arr[i] = (int) (Math.random() * 100);
		int[] sortedArr = sort.sort(arr);
		printArray(sortedArr);
		
		System.out.println(" ------- Now in place merge sorting --------");
		for (int i = 0; i < arr.length; i++)
			arr[i] = (int) (Math.random() * 100);
		sort.sortInPlace(arr, 0, arr.length);
		printArray(arr);

	}
	
	private static void printArray(int [] arr)
	{
		for (int i : arr)
			System.out.print(""+i+" ");
		System.out.println();
	}
	private static void printArray(int [] arr, int offset, int length)
	{
		for (int i=offset; i<offset+length;i++)
			System.out.print(""+arr[i]+" ");
		System.out.println();
	}

	public int[] sort(int[] arr) {

		if (arr.length <= 1)
			return arr;
		int len = arr.length / 2;
		int[] arr1 = new int[len];
		int[] arr2 = new int[arr.length - len];
		for (int i = 0; i < arr1.length; i++)
			arr1[i] = arr[i];
		for (int i = 0; i < arr2.length; i++)
			arr2[i] = arr[i + len];
		arr1 = sort(arr1);
		arr2 = sort(arr2);

		// Merge the two
		int counter1 = 0;
		int counter2 = 0;
		for (int i = 0; i < arr.length; i++) {
			if (counter1 == arr1.length)
				arr[i] = arr2[counter2++];
			else if (counter2 == arr2.length)
				arr[i] = arr1[counter1++];
			else
				arr[i] = arr1[counter1] > arr2[counter2] ? arr2[counter2++]
						: arr1[counter1++];
		}
		return arr;
	}

	private void reverse(int[] arr, int offset, int length) {
		System.out.println("Reverse: "+offset+","+length);
		printArray(arr, offset,length);
		int len = length / 2;
		for (int i = 0; i < len; i++) {
			int tmp = arr[i+offset];
			arr[offset + i] = arr[offset + length - i - 1];
			arr[offset + length - i - 1] = tmp;
		}
		printArray(arr, offset,length);
	}

	private void mergeInPlace(int[] arr, int offset, int length1, int length2) {
		// for counter1 just increment main counter
		// Whenever counter2 is next min, reverse both arrays and reverse the
		// whole array

		// Merge the two
		int counter1 = offset;
		int counter2 = offset+length1;
		int nChars = length1 + length2;
		for (int i = offset; i < nChars; i++) {
			if (counter1 == offset+length1 || counter2 == offset+length1+length2)
				return;
			else if (arr[counter1] < arr[counter2])
				counter1++;
			else {
				reverse(arr, counter1, offset+length1 - counter1);
				reverse(arr, counter2, offset+length1+length2 - counter2);
				reverse(arr, counter1, offset+length1 + length2  - counter1);
				counter1++;
				//What is counter2?
				counter2= length2+offset - (length1+offset-counter1);
				int tmp = length1;
				length1 = length2;
				length2 = tmp;
			}
		}
	}

	
	//TODO: debug this
	public void sortInPlace(int[] arr, int offset, int length) {

		if (length <= 1)
			return;
		int len = length / 2;
		sortInPlace(arr, offset, len);
		sortInPlace(arr, offset + len, length - len);
		System.out.println("To merge : offset: "+offset+", split at : "+len);
		printArray(arr, offset, len);
		printArray(arr, offset+len, length - len);
		mergeInPlace(arr, offset, len, length - len);
		printArray(arr, offset, length);
	}

}
