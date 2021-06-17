package com.programming.problems;

public class StringDuplicatesRemoval {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "abbcddddeeef";
		
		s = removeDuplicates(s);
		System.out.println(s);
		
		s = "xyzzyxt";
		s = removeDuplicates(s);
		System.out.println(s);

	}

	/**
	 * Does case insensitive duplicates in-place removal
	 * assumes only alphabets in string
	 * 
	 * @param s
	 * @return
	 */
	private static String removeDuplicates(String s) {
		if(null==s)return s;
		
		boolean [] cArr = new boolean[26];
		for(int i=0;i<cArr.length;i++) cArr[i]=false;
		
		s=s.toLowerCase();
		char []cBuf = s.toCharArray();
		int offset = 0;
		for(int i=0;i<cBuf.length;i++)
		{
			if(cArr[cBuf[i]-'a'])offset++;
			else {
				cBuf[i-offset]=cBuf[i];
				cArr[cBuf[i]-'a']=true;
			}
		}
		return new String(cBuf, 0, cBuf.length-offset);
	}

}
