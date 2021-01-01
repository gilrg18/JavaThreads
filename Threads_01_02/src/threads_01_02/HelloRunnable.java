package threads_01_02;

public class HelloRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("Hello from " + Thread.currentThread().getName() + " a thread created by "
				+ "implementing a runnable interface");
	}

}
