package com.programming.permutation_combination;
import java.util.Vector;

public class RecursiveCombination {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int n = 10;
		int p = 5;

		Vector<Vector<Integer>> cArr = combination(n, p);

		System.out.println("Found combinations : " + cArr.size());

		for (Vector<Integer> c : cArr) {
			for (Integer i : c) {
				System.out.print(i.toString() + " ");
			}
			System.out.println();
		}

	}

	public static Vector<Vector<Integer>> combination(int n, int p) {
		Vector<Vector<Integer>> cArr = new Vector<Vector<Integer>>();
		if (n > 0) {
			if (n == p) {
				Vector<Integer> c = new Vector<Integer>();
				for (int i = 0; i < n; i++)
					c.add(new Integer(i + 1));
				cArr.add(c);

			} else if(p==1){
				
				for (int i = 0; i < n; i++)
				{
					Vector<Integer> c = new Vector<Integer>();
					c.add(new Integer(i + 1));
					cArr.add(c);
				}
			} else {
				// Combinations in which n is present
				cArr = combination(n - 1, p - 1);
				for (Vector<Integer> c : cArr) {
					c.add(new Integer(n));
				}

				// Combinations in which n is not present
				Vector<Vector<Integer>> cArr1 = combination(n - 1, p);
				cArr.addAll(cArr1);
			}
		}
		return cArr;
	}

}
