package threads_01_02;

public class Threads_01_02 {
	public static void main(String[] args) {
		//Create a thread using a class that implements runnable
		(new Thread(new HelloRunnable())).start();
		
		//Create a thread using a class that extends thread class
		(new HelloThread()).start();
		
		//Create a runnable object
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				//perform some work inside the thread
				System.out.println("Hello from " + Thread.currentThread().getName() + " NOT USING LAMBDA");
			}
		};
		
		//Create a runnable object using lambda notation
		//from java 8 we have this notation for creating inner anonymous classes
		Runnable r2 = ()-> System.out.println("Hello from " + Thread.currentThread().getName() + " USING LAMBDA NOTATION");
	
		/*
		 * Create and start a thread using the first runnable object
		 * This thread is also given a name in the arguments
		 */
		Thread t1 = new Thread(r1, "Thread t1");
		t1.start();
		
		/*
		 * Create and start a second thread using the runnable object with
		 * lambda notation and not a given name
		 */
		Thread t2 = new Thread(r2, "L-Thread");
		t2.start();
	}
}
