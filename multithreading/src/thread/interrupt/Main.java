package thread.interrupt;

public class Main {
	public static void main(String[] args) {
		Thread thread = new Thread(new BlockingTask());
		
		thread.start();
		
		thread.interrupt();
	}
	
	//This will wait 50 seconds to end the thread (it does nothing but doesnt let the app end)
	//we use thread.interrupt() to interrupt the wait and end the app 
	//we can end the app without waiting using daemon thread
	//a daemon thread is a thread that lets the app end without caring if the daemon thread is still in process
	public static class BlockingTask implements Runnable {
		@Override
		public void run() {
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Exiting blocking thread");
			}
		}
	}
}
