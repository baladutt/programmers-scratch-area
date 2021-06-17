package com.programming.permutation_combination;

public class Permutation {
	
	int n;
	boolean firstIteration = true;
	int[] iArr;

	public Permutation(int n) {
		this.n = n;
		iArr = new int[n];
		for (int i = 0; i < n; i++)
			iArr[i] = i;
	}
	
	public int [] getNextPermutation(){
	

		if(firstIteration)
		{
			//First time
			firstIteration = true;
			return iArr;
		}
	    int temp;

	    // Find largest index j with a[j] < a[j+1]

	    int j = iArr.length - 2;
	    while (j>= 0 && iArr[j] > iArr[j+1]) {
	      j--;
	    }
	    
	    if(j<0)
	    	return null; //reached end

	    // Find index k such that a[k] is smallest integer
	    // greater than a[j] to the right of a[j]

	    int k = iArr.length - 1;
	    while (iArr[j] > iArr[k]) {
	      k--;
	    }

	    // Interchange a[j] and a[k]

	    temp = iArr[k];
	    iArr[k] = iArr[j];
	    iArr[j] = temp;

	    // Put tail end of permutation after jth position in increasing order

	    int r = iArr.length - 1;
	    int s = j + 1;

	    while (r > s) {
	      temp = iArr[s];
	      iArr[s] = iArr[r];
	      iArr[r] = temp;
	      r--;
	      s++;
	    }

	    return iArr;
}

	public static void main(String[] args) {
		test(3);
		test(5);
	}
	private static void test(int n)
	{
		Permutation permutation = new Permutation(n);
		int counter = 0;
		while (true) {
			int[] iArr = permutation.getNextPermutation();
			if (null == iArr)
				break;
			for(int i: iArr)
				System.out.print(i+", ");
			System.out.println();
			counter++;
		}
		System.out.println("\n "+n+"P"+n+" = "+counter);
	}

}
