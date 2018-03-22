package syn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 空中网面试题3： 现有程序同时启动了四个线程去调用TestDo.doSome(key,value)方法； 
 * 由于TestsDo.doSome(key,value)方法内的代码是先暂停1秒，然后再输出以秒为单位的当前时间值， 
 * 所以会打印出四个相同的时间值，如下所示：4：4 1258199615 1:1 1258199615 3:3 1258199615 2:2 
 * 1258199615 ；请修改代码，如果有几个线程调用TestDo.doSome(key,value)方法时； 
 * 传递进去的key值相等（equals比较为true），则这几个线程应互斥输出结果，即当有两个线程的key都为1时， 
 * 它们中的一个要比其他线程晚一步输出结果，如下所示：4：4 1258199615 1:1 1258199615 3:3 1258199615 1:2 
 * 1258199616 ；总之每个线程中指定的key相等时；这些相等的线程应每隔1秒输出时间值（要用互斥）， 
 * key不同，则并行执行（相互之间不互斥）
 * @author Administrator
 *
 */
//不能改动此Test类	
public class Test extends Thread{
	
	private TestDo testDo;
	private String key;
	private String value;
	
	public Test(String key,String key2,String value){
		this.testDo = TestDo.getInstance();
		/*常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
		以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果*/
//		this.key = key;
		this.key = key+key2; 
/*		a = "1"+"";
		b = "1"+""
*/
		this.value = value;
	}


	public static void main(String[] args) throws InterruptedException{
		Test a = new Test("1","","1");
		Test b = new Test("1","","2");
		Test c = new Test("3","","3");
		Test d = new Test("4","","4");
		System.out.println("begin:"+(System.currentTimeMillis()/1000));
		a.start();
		b.start();
		c.start();
		d.start();

	}
	
	public void run(){
		testDo.doSome(key, value);
	}
}

class TestDo {

	private TestDo() {}
	private static TestDo _instance = new TestDo();	
	public static TestDo getInstance() {
		return _instance;
	}

	//private ArrayList keys = new ArrayList();
	private CopyOnWriteArrayList keys = new CopyOnWriteArrayList();
	public void doSome(Object key, String value) {
		Object o = key;
		if(!keys.contains(o)){
			keys.add(o);
		}else{

			for(Iterator iter=keys.iterator();iter.hasNext();){
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Object oo = iter.next();
				if(oo.equals(o)){
					o = oo;
					break;
				}
			}
		}
		synchronized(o)
		// 以大括号内的是需要局部同步的代码，不能改动!
		{
			try {
				Thread.sleep(1000);
				System.out.println(key+":"+value + ":"
						+ (System.currentTimeMillis() / 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

