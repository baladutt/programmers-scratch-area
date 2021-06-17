package com.programming.bit;

public class BitRepresentation {
	
	public void printBits(int i)
	{
		int mask = 0x1<<31;
		for(int j=0;j<32;j++)
		{
			System.out.print((i&mask) == 0 ? 0:1);
			i=i<<1;
		}
		System.out.println();
	}
	public static void main(String []args)
	{
		BitRepresentation repr = new BitRepresentation();
		repr.printBits(0);
		repr.printBits(1);
		repr.printBits(2);
		repr.printBits(-1);
		repr.printBits(Integer.MAX_VALUE);
		repr.printBits(Integer.MIN_VALUE);
	}

}
