package com.programming.cache;

import java.util.Comparator;

/**
 * 
 * Assumptions: It is assumed to be heterogenous cache, otherwise CachedEntity
 * could have been a generic allowing only homogeneous types
 * 
 * @author bdutt
 * 
 */
class CachedEntity {

	// Storing realObject for remove operation of cache (in future) to be fast
	// and not require removing objects from all queues.
	// Merely setting this realObject to null will be fine as CachedEntity works
	// as a proxy
	private Object realObject;
	private Object key;
	private long lastUsedTimeInMillis = -1;
	private long expirationTimeInSecs = -1; // Sec as unit will be useful enough
	private int countOfUsed = 0;

	static Comparator<CachedEntity> expirationComparator = new Comparator<CachedEntity>() {

		@Override
		public int compare(CachedEntity e1, CachedEntity e2) {
			return (int) (e1.expirationTimeInSecs - e2.expirationTimeInSecs); // TODO:
																				// check
																				// overflow
		}

	};
	static Comparator<CachedEntity> lruComparator = new Comparator<CachedEntity>() {

		@Override
		public int compare(CachedEntity e1, CachedEntity e2) {
			return (int) (e1.lastUsedTimeInMillis - e2.lastUsedTimeInMillis); // TODO:
																				// check
																				// overflow
		}

	};

	static Comparator<CachedEntity> lfuComparator = new Comparator<CachedEntity>() {

		@Override
		public int compare(CachedEntity e1, CachedEntity e2) {
			return e1.countOfUsed - e2.countOfUsed;
		}

	};

	CachedEntity(Object realObject, Object key) {
		this.realObject = realObject;
		this.key = key;
	}

	CachedEntity(Object realObject, Object key, long timeoutInSecs) {
		this(realObject, key);
		setTimeOut(timeoutInSecs);
	}

	void remove() {
		realObject = null;
	}

	Object getRealObject() {
		return realObject;
	}

	void setRealObject(Object realObject) {
		this.realObject = realObject;

	}

	void used() {
		lastUsedTimeInMillis = System.currentTimeMillis();
		countOfUsed++;
	}

	void setTimeOut(long timeoutInSecs) {
		if (-1 != timeoutInSecs)
			expirationTimeInSecs = System.currentTimeMillis() / 1000
					+ timeoutInSecs;
		else
			expirationTimeInSecs = -1;

	}

	Object getKey() {
		return key;
	}

	long getExpirationTimeInSecs() {
		return expirationTimeInSecs;
	}

}
