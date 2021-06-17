package com.programming.permutation_combination;

public class StringShuffler {

	public static void shuffle(String[] arr) {
		if(null==arr)return;
		int len = arr.length;
		for(int i=0;i<len;i++)
		{
			//Which one is better?
			//int indexToMove = (int)Math.round(Math.random()*(len-1-i));
			int indexToMove = (int)(Math.random()*(len-i));
			if(i!=indexToMove)
			{
				String tmp = arr[i];
				arr[i]=arr[indexToMove+i];
				arr[indexToMove+i]=tmp;
			}
		}
	}

}
