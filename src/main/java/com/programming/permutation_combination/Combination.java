package com.programming.permutation_combination;

public class Combination {

	int n;
	int r;
	int indexOfItemToIncrement = 0;
	int[] iArr;

	public Combination(int n, int r) {
		this.n = n;
		this.r = r;
		iArr = new int[r];
		for (int i = 0; i < r; i++)
			iArr[i] = i;
		indexOfItemToIncrement = -1;
	}

	public int[] getNextCombination() {
		if (indexOfItemToIncrement != -1)// For this first time do nothing
		{
			while (indexOfItemToIncrement >= 0) {
				iArr[indexOfItemToIncrement]++;
				if (iArr[indexOfItemToIncrement] >= n - (r - indexOfItemToIncrement - 1))
					indexOfItemToIncrement--;
				else
					break;
			}
			if(indexOfItemToIncrement == -1)
				return null; //No more combinations possible
			while(indexOfItemToIncrement != r-1)
			{
				indexOfItemToIncrement++;
				iArr[indexOfItemToIncrement] = iArr[indexOfItemToIncrement-1]+1;
				
			}
		}else{
			indexOfItemToIncrement =  r - 1;
		}
		if (indexOfItemToIncrement < 0)
			return null;
		return iArr;
	}

	public static void main(String[] args) {
		// Test
		test(5,3);
		test(10,5);
		test(5,2);
	}
	private static void test(int n, int r)
	{
		Combination combination = new Combination(n, r);
		int counter = 0;
		while (true) {
			int[] iArr = combination.getNextCombination();
			if (null == iArr)
				break;
			for(int i: iArr)
				System.out.print(i+", ");
			System.out.println();
			counter++;
		}
		System.out.println("\n "+n+"C"+r+" = "+counter);
	}
}
