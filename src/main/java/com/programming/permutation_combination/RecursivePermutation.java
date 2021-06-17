package com.programming.permutation_combination;

import java.util.Vector;

public class RecursivePermutation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int n = 4;
		int p = 4;

		Vector<Vector<Integer>> cArr = permutation(n, p);

		System.out.println("Found permutations : " + cArr.size());

		for (Vector<Integer> c : cArr) {
			for (Integer i : c) {
				System.out.print(i.toString() + " ");
			}
			System.out.println();
		}

	}

	private static Vector<Integer> clone(Vector<Integer> cArr) {
		Vector<Integer> cArrRet = new Vector<Integer>();
		cArrRet.addAll(cArr);
		return cArrRet;
	}

	public static Vector<Vector<Integer>> permutation(int n, int p) {
		Vector<Vector<Integer>> cArr = new Vector<Vector<Integer>>();
		if (n <= 0 || p > n)
			return cArr;
		if (p == 1) {

			for (int i = 0; i < n; i++) {
				Vector<Integer> c = new Vector<Integer>();
				c.add(new Integer(i + 1));
				cArr.add(c);
				
			}
			return cArr;
		}
		// Permutations in which n is present
		Vector<Vector<Integer>> cArr1 = permutation(n - 1, p - 1);
		for (Vector<Integer> c : cArr1) {
			for (int i = 0; i < c.size(); i++) {
				Vector<Integer> c1 = clone(c);
				c1.insertElementAt(new Integer(n), i);
				cArr.add(c1);
			}
			Vector<Integer> c1 = clone(c);
			c1.add(new Integer(n));
			cArr.add(c1);
		}

		// Permutations in which n is not present

		cArr1 = permutation(n - 1, p);
		cArr.addAll(cArr1);

		return cArr;
	}

}
