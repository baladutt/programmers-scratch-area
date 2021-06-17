package com.programming.bit;

public class Bitmap {

	private int getAbsentInt() {
		
		for(int i=0;i<bits.length;i++)
			if(!isIntPresent(i)) return i;
		return -1;
	}

	
	private void addInt(int i) {
		bits[i/8] |= 0x80 >> i%8;
		System.out.println(""+(i/8)+": "+bits[i/8]);
	}
	
	public boolean isIntPresent(int i) {
		return (bits[i/8] & 0x80 >> i%8) != 0x00000000;
	}

	byte [] bits = new byte[(int)(0xffffffffl/8)];
	
	public static void main(String []args)
	{
		Bitmap bitmap = new Bitmap();
		System.out.println(""+0xffffffffL+", "+bitmap.bits.length);
		
		for(int i=0;i<20;i++)
			bitmap.addInt(i);
		System.out.println("Absent int : "+bitmap.getAbsentInt());
	}
}
