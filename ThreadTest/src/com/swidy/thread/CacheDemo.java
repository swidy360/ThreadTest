package com.swidy.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//缓存系统:先从缓存里面拿，没有从数据库取
public class CacheDemo {
	
	private Map<String, Object> cache = new HashMap<String, Object>();
	
	public static void main(String[] args) {
		
	}
	
	/*public synchronized Object getData(String key) {
		Object value = cache.get(key);
		if(value == null) {
			value = "aaa"; //实际上去queryDB()
		}
		return value;
	}*/
	
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	
	/**
	 * 填充数据的时候上写锁，读读的时候只需要上读锁
	 * @param key
	 * @return
	 */
	public Object getData(String key) {
		rwl.readLock().lock();
		Object value = null;
		try {
		    value = cache.get(key);
			if(value == null) {
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try {
					if(value == null) {
						value = "aaa"; //实际上去queryDB()
					} 
				} finally {
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		} finally {
			rwl.readLock().unlock();
		}
		
		return value;
	}

}
