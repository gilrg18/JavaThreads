package threads_01_04;

public class ThreadJoinExample {
	public static void main(String[] args) {
		Thread t1 = new TestJoinClass("t1");
		Thread t2 = new TestJoinClass("t2");
		Thread t3 = new TestJoinClass("t3");
		Thread t4 = new TestJoinClass("t4");
		Thread t5 = new TestJoinClass("t5");
		t4.start();
		t1.start();
		t2.start();
		
		try {
			t2.join();
		}catch(Exception e) {System.out.println(e);}
		
		//t3 wont start until t2 is complete
		t3.start();
		t5.start();
	}
}
