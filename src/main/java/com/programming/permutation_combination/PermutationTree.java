package com.programming.permutation_combination;

public class PermutationTree {
	
	int n;
	int r;
	boolean firstIteration = true;
	int[] iArr;

	public PermutationTree(int n, int r) {
		this.n = n;
		this.r = r;
		iArr = new int[r];
		for (int i = 0; i < r; i++){
			iArr[i] = i;
		}
	}
	

	public int[] getNextPermutation() {
		
		if(firstIteration)
		{
			firstIteration = false;
			return iArr;
		}
		
		
		for(int i=iArr.length-1;i>=0;i--)
		{
			if(iArr[i] + 1 >=n)
				continue;//Time for previous counter to go up
			else{
				//find a number greater than current not taken
				int curr = iArr[i];
				boolean numbersTaken [] = new boolean[n];
				for(int j=0;j<numbersTaken.length;j++)
				{
					numbersTaken[j] = false;
				}
				for(int j=0;j<i;j++)
				{
						numbersTaken[iArr[j]]=true;
				}
				int nextCurr = -1;
				for(int j=curr+1;j<numbersTaken.length;j++)
				{
					if(!numbersTaken[j])
					{
						nextCurr = j;
						break;
					}
				}
				if(nextCurr == -1)
					continue; //Time for previous counter to go up
				else{
					iArr[i] = nextCurr;
					numbersTaken[iArr[i]] = true;
					for(int j=i+1;j<iArr.length;j++)
					{
						for(int k=0;k<numbersTaken.length;k++)
							if(!numbersTaken[k])
							{
								iArr[j]=k;
								numbersTaken[iArr[j]] = true;
								break;
							}
					}
					return iArr;
				}
			}
				
		}
		
		return null;
	}
	
	
	/**
	 * Assume that permutation was a tree with each node being set of permutations
	 * This will abandon the whole tree below
	 * 
	 */
	public void abandonSubTree(int index)
	{
		iArr[index]=n-1;
		if(firstIteration)
		{
			for(int i=index+1;i<iArr.length;i++)
				iArr[i] = i-1;
		}
		
	}

	public static void main(String[] args) {
		test(3,3,0);
		test(4,3,0);
	}
	private static void test(int n, int r, int abandonIndex)
	{
		PermutationTree permutation = new PermutationTree(n, r);
		int counter = 0;
		if(abandonIndex>=0)
			permutation.abandonSubTree(abandonIndex);
		while (true) {
			int[] iArr = permutation.getNextPermutation();
			if (null == iArr)
				break;
			for(int i: iArr)
				System.out.print(i+", ");
			System.out.println();
			counter++;
		}
		System.out.println("\n "+n+"P"+r+" = "+counter);
	}

}
