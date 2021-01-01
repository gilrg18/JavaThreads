package threads_01_04;

public class TestJoinClass extends Thread {
	// Constructor to add a user defined name to a thread
	TestJoinClass(String name) {
		super(name);
	}

	public void run() {
		for (int i = 1; i <= 5; i++) {
			try {
				//stop the thread for 1/2 seconds
				Thread.sleep(500);
				//since its a static method we have to add a try catch or add a throw statement
				// to the public void run
			}catch(Exception e) {
				System.out.println(e);
			}
			System.out.println(Thread.currentThread().getName() + " i = " +i);
		}
	}
}
