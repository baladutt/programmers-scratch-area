package com.programming.cache;

import java.util.Hashtable;
import java.util.PriorityQueue;

enum EvictionPolicy {
	LRU, LFU
}

/**
 * The cache removes objects on hitting maxSize by going through following
 * steps: 1. Find out if any objects timedout 2. Follow the EvictionPolicy
 * 
 * @author bdutt
 * 
 */
public class Cache {

	Hashtable<Object, CachedEntity> cachedEntityTable = new Hashtable<Object, CachedEntity>();
	int maxSize;
	PriorityQueue<CachedEntity> expirationQueue;// For large scale -
												// priorityQueues should be
												// implemented by hand as
												// MinHeap with int/long keys
	PriorityQueue<CachedEntity> usageQueue;

	/**
	 * 
	 * @param maxSize
	 * @param policy
	 */
	public Cache(int maxSize, EvictionPolicy policy) {
		this.maxSize = maxSize;
		if (policy == EvictionPolicy.LRU) {
			usageQueue = new PriorityQueue<CachedEntity>(maxSize,
					CachedEntity.lruComparator);
		} else if (policy == EvictionPolicy.LFU) {
			usageQueue = new PriorityQueue<CachedEntity>(maxSize,
					CachedEntity.lfuComparator);
		}
		expirationQueue = new PriorityQueue<CachedEntity>(maxSize,
				CachedEntity.expirationComparator);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Object get(Object key) {
		CachedEntity entity = cachedEntityTable.get(key);
		if (entity != null) {	
			usageQueue.remove(entity);
			entity.used();
			long expTime = entity.getExpirationTimeInSecs();
			if(-1 != expTime && expTime >= System.currentTimeMillis()*1000)
			{
				expirationQueue.remove(entity);
				return null;
			}
			usageQueue.add(entity);
			return entity.getRealObject(); // This could be a field access too
		}
		return null;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void put(Object key, Object value) {
		put(key, value, -1);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param timeoutInSecs
	 */
	public void put(Object key, Object value, long timeoutInSecs) {
		// Reusing the object bcoz,
		// Some of these objects would be in mature generation of JVM heap -
		// save need to GC
		// Assumption: a put should not disturb the existing metrics

		CachedEntity entity = cachedEntityTable.get(key);
		if (entity != null) {
			entity.setRealObject(value);
			expirationQueue.remove(entity);//The entity may or may not be there
			entity.setTimeOut(timeoutInSecs);
			if (-1 != timeoutInSecs) {
				expirationQueue.add(entity);

			}
		} else {
			if (cachedEntityTable.size() >= maxSize) {

				// In a server side enviornment i would not have a strict
				// maxsize but a band and trigger would
				// result in a low priority thread cleaning up objects and not
				// costing the main request thread
				// The number of entries to be removed could be more causing
				// unpredictable delays

				boolean bMoreRemovalsPossible = false;
				do {
					bMoreRemovalsPossible = false;
					CachedEntity entityToRemove = expirationQueue.poll();
					if (null != entityToRemove
							&& entityToRemove.getExpirationTimeInSecs() <= System
									.currentTimeMillis() / 1000) {
						bMoreRemovalsPossible = true;
						cachedEntityTable.remove(entityToRemove.getKey());
					}
				} while (bMoreRemovalsPossible);

			}

			if (cachedEntityTable.size() >= maxSize) {
				CachedEntity entityToRemove = usageQueue.poll();
				cachedEntityTable.remove(entityToRemove.getKey());
				expirationQueue.remove(entityToRemove);
			}

			// TODO: have a pool of entity objects as they will be in mature
			// generation and
			// reusing objects will prevent full gc
			entity = new CachedEntity(value, key, timeoutInSecs);

			cachedEntityTable.put(key, entity);
			usageQueue.add(entity);
			if (-1 != timeoutInSecs)
				expirationQueue.add(entity);
		}
	}

}
