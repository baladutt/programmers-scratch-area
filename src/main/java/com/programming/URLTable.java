package com.programming;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import com.programming.io.FuzzyTableReader;
import com.programming.io.FuzzyTableReader.HandleRecord;

/**
 * 
 * Given a long list of URLs find out a datastructure for things like:
 *    Find the first unique
 *    Count set size of urls 
 *    
 *    
 * What is tried here,
 *   1. Store URL as a String in hashtable with count. Looks like if we had a counting Hashtable in JDK it would have helped.
 *   What this means is that Hashtable will have value as int, preventing client to create Integer objects and also prevent the get
 *   increment and put operation into 1
 *   
 *   2. Store URL as a URL. I was expecting the hashing function to be smart, but looks like it is a very bad hashing function.
 *   Also parsing has a huge cost. Benefit being that malformed URLs are caught
 *   
 *   3. Strings could be made smaller as URLs on avg are 30char (60 bytes) wide. This could become a problem at very high numbers.
 *   Here are some ways to achieve same
 *      a)  Remove fragements like "http://", "www" etc that do not differentiate urls. (Assumption)
 *      b) Shorten urls/compress urls
 *   
 *   4. Store things in two level Hashtables. One with FQDN and other with rest of the path till query string.
 *   Query strings could be removed as they are mostly done with.
 *   
 *   5. In the given dataset, i found that total number of urls are 10 times the unique ones - i.e. on avg 10 repetitions.
 *   
 *   6. For finding first unique position store the location in file too.
 *   
 *   How will Trie work?
 *   How does bit.ly do at scale?
 *   How does java.net.URL do? Looks like it is not a great implementation.
 * @author bdutt
 *
 */
public class URLTable {
	public static void main(String []args)
	{
		
		//1. Warm up
		FuzzyTableReader reader = new FuzzyTableReader("data/proxylogs", new HandleRecord() {
			
			@Override
			public void handleRecord(String[] fields) {
				if(fields.length > 3)
					addUrl(fields[3], false);
			}
		});
		
		//2. Measure string hashing
		urls.clear();
		long startTime = System.currentTimeMillis();
		reader = new FuzzyTableReader("data/proxylogs", new HandleRecord() {
			
			@Override
			public void handleRecord(String[] fields) {
				if(fields.length > 3)
					addUrl(fields[3], false);
			}
		});
		long duration = System.currentTimeMillis() - startTime;
		
		System.out.println("Read count of urls using string hashing : "+urls.size()+", of : "+reader.getNumOfRawRecords());
		System.out.println("Time (ms) : "+duration);
		
		//3. Measure URL hashing
		urls.clear();
		startTime = System.currentTimeMillis();
		reader = new FuzzyTableReader("data/proxylogs", new HandleRecord() {
			
			@Override
			public void handleRecord(String[] fields) {
				if(fields.length > 3)
					addUrl(fields[3], true);
			}
		});
		
		duration = System.currentTimeMillis() - startTime;
		
		System.out.println("Read count of urls using url hashing : "+urls.size()+", of : "+reader.getNumOfRawRecords());
		System.out.println("Time (ms) : "+duration);
		}
	
	private static Hashtable<Object, Integer> urls = new Hashtable<Object, Integer>();
	
	public static void addUrl(String url, boolean useUrlHashing)
	{
		//Remove quotes;
		if(null == url || url.length()<0)return;
		if(url.length()>2)
		{
			url=url.substring(1, url.length()-1);
		}
		
		Object urlKey = null;
		if(useUrlHashing)
		{
			try {
				urlKey = new URL(url);
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
				return;
			}
		}else
			urlKey = url;
		Integer count = urls.get(urlKey);
		if(null==count)
		{
			count = new Integer(1);
		}else
		{
			count = new Integer(count.intValue()+1);
		}
		urls.put(urlKey, count);
		
	}

}
