package com.programming.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class FuzzyTableReader {
	int counter = 0;
	public interface HandleRecord{
		void handleRecord(String []fields);
	}
	
	public int getNumOfRawRecords()
	{
		return counter;
	}
	public FuzzyTableReader(String filename, HandleRecord handler)
	{
		File file = new File(filename);
		if(!file.exists())
		{
			System.out.println(file.getAbsolutePath()+": does not exist");//Do nothing
			return;
		}
		dirOrFile(file, handler);
		
	}
	private void dirOrFile(File file, HandleRecord handler)
	{
		if(file.isDirectory())
		{
			for(File file1 : file.listFiles())
			{
				dirOrFile(file1, handler);
			}
		}else
		{
			readFile(file, handler);
		}
	}
	private void readFile(File file, HandleRecord handler)
	{
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine())!=null)
			{
				counter++;
				String []fields = line.split(" ");
				if(null!=fields && fields.length >0)
				{
					handler.handleRecord(fields);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(null!=reader)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
