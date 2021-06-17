package com.programming.problems.permutation_combinations;

//Given n numbers 1...n and a sum X
//find all combinations of numbers that will add upto X
public class FindCombinationForASum {

	public static void main(String[] args) {
		int x = 6;
		int n = 5;

		findSum1(x, 1, n, "");
		findSum1(10, 1, n, "");
		findSum1(8, 1, n, "");
		findSum1(10, 1, 9, "");

	}

	// Ignore this
	// Doesn't prevent duplicate combinations
	private static void findSum(int x, int n, String buf) {
		if (0 == x)
			return;
		if (n > x) {
			findSum(x, x, buf);
			return;
		}
		if (!buf.isEmpty())
			buf += "+";
		for (int i = n; i > 0; i--) {
			if (i == x)
				continue;
			System.out.println(buf + i + "+" + (x - i));
			findSum(i, n, buf + (x - i));
		}
	}

	// All numbers are unique
	private static void findSum1(int x, int start, int n, String buf) {
		if (start > x)
			return;
		else if (start == x) {
			System.out.println(buf + start);
			return;
		}
		if (n > x) {
			findSum1(x, start, x, buf);
			return;
		}

		if (start < (x - n))
			start = x - n;

		for (int i = start; i <= n; i++) {
			if (0 == i)
				continue;
			String buf1 = buf + i + "+";

			if (i + 1 < (x - i)) {
				System.out.println(buf1 + (x - i));
				findSum1((x - i), i + 1, (x - i), buf1);
			}

		}
	}
}
