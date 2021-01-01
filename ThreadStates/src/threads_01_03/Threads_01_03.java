package threads_01_03;

public class Threads_01_03 {
	public static void main(String[] args) throws InterruptedException {
		// Print information about the current thread
		System.out.printf("%s is %s alive and in %s " + "state%n and priority %d \n", Thread.currentThread().getName(),
				Thread.currentThread().isAlive() ? "" : "not", Thread.currentThread().getState(),
				Thread.currentThread().getPriority());

		// Create a runnable object that prints info about the thread
		Runnable r1 = () -> {
			Thread thd = Thread.currentThread();
			System.out.printf("%s is %s alive and in %s " + "state%n and priority %d \n", thd.getName(),
					thd.isAlive() ? "" : "not", thd.getState(), thd.getPriority());
		};

		// Create a thread and show the info before calling start
		Thread t1 = new Thread(r1, "Thread t1");
		System.out.printf("%s is %s alive and in %s " + "state%n and priority %d \n", t1.getName(),
				t1.isAlive() ? "" : "not", t1.getState(), t1.getPriority());
		t1.start();

		// Create a thread t2 with only a runnable object and no name
		Thread t2 = new Thread(r1);
		t2.start();

		// Put main thread to sleep for 2 seconds
		Thread.sleep(2000);

		// Change name of t2 and print out its info;
		t2.setName("Thread t2");
		System.out.printf("%s is %s alive and in %s " + "state%n and priority %d \n", t2.getName(),
				t2.isAlive() ? "" : "not", t2.getState(), t2.getPriority());

		// Print information about the current thread
		System.out.printf("%s is %s alive and in %s " + "state%n and priority %d \n", Thread.currentThread().getName(),
				Thread.currentThread().isAlive() ? "" : "not", Thread.currentThread().getState(),
				Thread.currentThread().getPriority());

	}

}
