package thread.interrupt;

import java.math.BigInteger;

public class Main2 {
	public static void main(String[] args) {
		Thread thread = new Thread(new LongComputationTask(new BigInteger("20000"), new BigInteger("20")));
		//since they are very big numbers we either wait for the computation to be done or
		//interrupt the the thread but we need to add an if statement in the for loop
		thread.start();
		thread.interrupt();
	}
	
	private static class LongComputationTask implements Runnable{
		private BigInteger base;
		private BigInteger power;
		
		public LongComputationTask(BigInteger base, BigInteger power) {
			this.base = base;
			this.power = power;
		}
		
		@Override
		public void run() {
			System.out.println(base+"^"+power+" = "+ pow(base,power));
		}
		
		private BigInteger pow(BigInteger base, BigInteger power) {
			BigInteger result = BigInteger.ONE;
			
			for(BigInteger i = BigInteger.ZERO; i.compareTo(power) !=0; i=i.add(BigInteger.ONE)) {
				//System.out.println(i);
				//if that needs to be here in order to be interrupted
				if(Thread.currentThread().isInterrupted()) {
					System.out.println("Thread prematurely interrupted");
					return BigInteger.ZERO;

				}
				result = result.multiply(base);
				//System.out.println(result);
			}
			
			return result;
		}
	}
}
