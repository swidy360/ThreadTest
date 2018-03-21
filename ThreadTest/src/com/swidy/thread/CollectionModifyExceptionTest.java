package com.swidy.thread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Java并发集合
 * ConcurrentHashMap/CopyOnWriteArrayList/CopyOnWriteArraySet/...
 * 或者使用Collections.synchronizedMap(map)/Collections.synchronizedList(list)
 * @author Administrator
 *
 */
public class CollectionModifyExceptionTest {
	public static void main(String[] args) {
		//线程安全
	/*	Collection users = new CopyOnWriteArrayList();
		users.add(new User("张三",28));	
		users.add(new User("李四",25));			
		users.add(new User("王五",31));	
		Iterator itrUsers = users.iterator();
		while(itrUsers.hasNext()){
			System.out.println("aaaa");
			User user = (User)itrUsers.next();
			if("李四".equals(user.getName())){
				users.remove(user);
				//itrUsers.remove();
			} else {
				System.out.println(user);				
			}
		}*/
		
		//线程不安全
		Collection users = new ArrayList();
		users.add(new User("张三",28));	
		users.add(new User("李四",25));			
		users.add(new User("王五",31));	
		Iterator itrUsers = users.iterator();
		while(itrUsers.hasNext()){
			System.out.println("aaaa");
			User user = (User)itrUsers.next();
			if("张三".equals(user.getName())){
//				users.remove(user);
				itrUsers.remove();
			} else {
				System.out.println(user);				
			}
		}
	}
}	 
