package com.programming.problems.permutation_combinations;

import com.programming.io.ArrayHelper;
import com.programming.permutation_combination.StringShuffler;

public class ShuffleStringArrayProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String arr[] = new String[10];
		for(int i=0;i<arr.length;i++)
			arr[i]=""+i;
		StringShuffler.shuffle(arr);
		ArrayHelper.dump(arr, System.out);

	}

}
