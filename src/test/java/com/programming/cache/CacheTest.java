package com.programming.cache;

import org.junit.Test;

import com.programming.cache.Cache;
import com.programming.cache.EvictionPolicy;

import junit.framework.Assert;


public class CacheTest {

	@Test
	public void testLFU()
	{
		Cache cache = new Cache(3, EvictionPolicy.LFU);
		cache.put("1", "val1");
		cache.put("2", "val2");
		cache.put("3", "val3");
		
		
		cache.get("1");
		cache.get("2");
		cache.put("4", "val4");
		
		Assert.assertTrue("3 should have dropped off", cache.get("3")==null);
		
		
	}
	
	private void sleep(int sec)
	{
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLRU()
	{
		Cache cache = new Cache(3, EvictionPolicy.LFU);
		cache.put("1", "val1");
		cache.put("2", "val2");
		cache.put("3", "val3");
		
		
		cache.get("1");
		sleep(1);
		cache.get("2");
		sleep(1);
		cache.put("4", "val4");
		
		Assert.assertTrue("3 should have dropped off", cache.get("3")==null);
		
	}
	
	@Test
	public void testTimeOut()
	{
		Cache cache = new Cache(3, EvictionPolicy.LFU);
		cache.put("1", "val1", 1);
		cache.put("2", "val2", 2);
		cache.put("3", "val3", 3);
		
		
		cache.get("1");
		sleep(1);
		cache.get("2");
		sleep(1);
		cache.put("4", "val4");
		
		Assert.assertTrue("1 should have dropped off", cache.get("1")==null);
		
	}

}
