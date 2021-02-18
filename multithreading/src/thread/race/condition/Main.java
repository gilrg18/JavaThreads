package thread.race.condition;

/*
 * Let me demonstrate the problem we have here.
 * 
 * We have a very simple example.
 * 
 * What we have here is a simple case of an eCommerce warehouse.
 * 
 * We have a product and we want to keep count of how many items we
 * 
 * currently have of that product.
 * 
 * We store the number of items in the items, member variable.
 * 
 * We can increment the number of items when we get a new delivery
 * 
 * to our warehouse by calling the increment method in decremented.
 * 
 * When somebody purchased an item by calling the decrement method.
 * 
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		InventoryCounter ic = new InventoryCounter();
		IncrementingThread it = new IncrementingThread(ic);
		DecrementingThread dt = new DecrementingThread(ic);

		it.start();
		// wait for it to finish with join
		
		// it.join();

		dt.start();

		it.join();
		dt.join();
		// After both threads are finished we print the number of items we currently
		// have
		// we are specting to have 0 items in the end
		// one thread added 10000 items and another thread removed them
		// lets move join after dt.start so both threads start at the same time;
		// now we have unexpected result, we have different results everytime
		// now that we add the synchronized keyword on the increment, decrement and getItems methods
		// the correct output is seen
		System.out.println("We currently have " + ic.getItems() + " items");
	}

	public static class IncrementingThread extends Thread {
		private InventoryCounter inventoryCounter;

		public IncrementingThread(InventoryCounter inventoryCounter) {
			this.inventoryCounter = inventoryCounter;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				inventoryCounter.increment();
			}
		}
	}

	public static class DecrementingThread extends Thread {
		private InventoryCounter inventoryCounter;
		
		public DecrementingThread(InventoryCounter inventoryCounter) {
			this.inventoryCounter = inventoryCounter;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				inventoryCounter.decrement();
			}
		}
	}

	private static class InventoryCounter {
		private int items = 0;
		//Synchronize - Lock
		Object lock = new Object();
		public  void increment() {
			synchronized(this.lock) {
				items++;
			}
		}
		//Synchronize - Monitor
		//public synchronized void decrement() {
		//	items--;
		//}
		
		//Synchronize - Lock
		public void decrement() {
			synchronized(this.lock) {
				items--;
			}
		}
		
		//Synchronize - Monitor
		//public synchronized int getItems() {
		//	return items;
		//}
		
		//Synchronize - Lock
		public int getItems() {
			synchronized(this.lock) {
				return items;
			}
		}
	}
}
