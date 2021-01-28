package thread.join;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		List<Long> inputNumbers = Arrays.asList(100000000L,3435L,2324L,4656L,23L,2435L,5566L);
		// We want to calculate the factorial of each number with each number
		// being calculated in a different thread, every thread is running in paralel
		
		// In the main thread we want to capture all the results of all the factorials threads and print them all out
		List<FactorialThread> threads = new ArrayList<>();
		
		for(long inputNumber: inputNumbers) {
			threads.add(new FactorialThread(inputNumber));
		}
		
		for(Thread t: threads) {
			//we will set all threads daemon so it will let our program terminate if the calculation
			//is too big and it exceds the 2 second waiting time in the join method
			t.start();
		}
		// we will have the factorial threads and the main thread running concurrently
		// the next step for the main thread is to try and get the results of the factorial
		// threads and print them out
		
		//the main thread and the factorial threads are racing towards their goals independently
		//and we dont know which one will be in which stage by the time the main thread is checking
		//for the results
		//so we add a thread.join method in between the lines of code where the race is happening:
		//between t.start() and if(factorialThread.isFinished()) and resolve that race condition 
		//by forcing the main thread to wait until all the factorial threads are finished 
		
		for(Thread thread: threads) {
			//Everytime we use the join method we have to decide how long are we
			//willing to wait for each of the worker threads
			//we will tolerate 2 seconds per computation
			thread.join(2000);
			
		}
		
		for(int i = 0; i<inputNumbers.size(); i++) {
			//Check if the calculation of each thread has finished
			FactorialThread factorialThread = threads.get(i);
			if(factorialThread.isFinished()) {
				System.out.println("Factorial of "+ inputNumbers.get(i) + " is " + factorialThread.getResult());
			}else {
				//to interrupt thread that takes too long and let the application terminate
				factorialThread.interrupt();
				System.out.println("The calculation of " + inputNumbers.get(i) +" took too long so i interrupted it: " + factorialThread.getResult());
			}
		}
	}
	
	public static class FactorialThread extends Thread{
		private long inputNumber;
		//We cannot use long as it might overflow, so we use BigInteger to calculate the factorial 
		private BigInteger result = BigInteger.ZERO;
		private boolean isFinished = false;
		
		public FactorialThread(long inputNumber) {
			this.inputNumber = inputNumber;
		}
		
		@Override
		public void run() {
			this.result = factorial(inputNumber);
			this.isFinished = true;
		}
		
		public BigInteger factorial(long n) {
			BigInteger tempResult = BigInteger.ONE;
			
			for (long i = n;i>0; i--) {
				//to interrupt thread that takes too long and let the application terminate
				if(Thread.currentThread().isInterrupted()) {
					return BigInteger.ZERO;
				}
				tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
			}
			return tempResult;
		}
		
		public boolean isFinished() {
			return isFinished;
		}
		
		public BigInteger getResult() {
			return result;
		}
	}
}
