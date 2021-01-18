package thread.creation.example;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// code that will run in a new thread;
				System.out.println("We are now in thread " + Thread.currentThread().getName());
				System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
				// Testing the exception handler
				throw new RuntimeException("Intentional Exception");
			}
		});

		/*
		 * Normally unchecked exceptions that happen in Java, simply bring down the
		 * entire
		 * 
		 * thread unless we catch them explicitly and handle them in a particular way.
		 * 
		 * 
		 * We can set an exception handler for the entire thread at its inception.
		 * 
		 * That handler will be called if an exception was thrown inside the thread
		 * 
		 * and did not get caught anywhere in this case, we're simply going to print out
		 * the
		 * 
		 * thing, the red and the exception that was not caught, but more realistically,
		 * this
		 * 
		 * would be a place where we could clean up some of the resources or log
		 * additional
		 * 
		 * data to enable us troubleshoot this issue.
		 */
		thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(
						"A critical error happened in thread " + t.getName() + " the error is " + e.getMessage());

			}
		});
		// Thread creation using java 8 lambda
		Thread thread2 = new Thread(() -> {
			System.out.println("This is a thread created using lambda: " + Thread.currentThread().getName());
		});

		System.out.println("We are in thread: " + thread.currentThread().getName() + " before starting a new thread");
		thread.setName("New Worker Thread");
		thread.setPriority(Thread.MAX_PRIORITY);
		thread2.setName("Lambda Thread");
		thread.start();
		thread2.start();
		System.out.println("We are in thread: " + thread.currentThread().getName() + " after starting a new thread");
		// During this time that thread is not consuming any cpu
		//Thread.sleep(10000);
		
		
		//Now we can just do this:
		Thread thread3 = new NewThread();
		thread3.setName("New Thread class thread");
		thread3.start();
	}

	/*
	 * The second
	 * 
	 * way of creating a thread is instead of creating a separate object for the
	 * thread and another
	 * 
	 * object for the runnable, we can directly create a new class that extends
	 * thread.
	 */
	
	private static class NewThread extends Thread{
		@Override
		public void run() {
			//Code that executes on the new thread
			System.out.println("Hello from " +this.getName());
		}
	}

}
