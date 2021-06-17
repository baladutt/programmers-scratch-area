package com.programming.io;

import java.io.PrintStream;

public class ArrayHelper {
	public static void dump(Object [] arr, PrintStream out)
	{
		for(int i=0;i<arr.length;i++)
			out.print(arr[i]+" ");
		
		out.println();
	}

}
