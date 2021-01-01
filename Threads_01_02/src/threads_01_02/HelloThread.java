package threads_01_02;

public class HelloThread extends Thread{
	@Override
	public void run() {
		System.out.println("Hello from " + Thread.currentThread().getName() + " a thread created by "
				+ "extending the Thread class");
	}
}
