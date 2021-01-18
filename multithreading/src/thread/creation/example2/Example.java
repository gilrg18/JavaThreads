package thread.creation.example2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Example {

	/*
	 * Let's say I want to design a secure vault where I'm planning to store my
	 * 
	 * money and I want to see how long it would take the hackers to break into the
	 * volt.
	 * 
	 * By guessing my code, we will have a few hacker threads trying to
	 * 
	 * brute force my code concurrently.
	 * 
	 * In addition to that, we're going to have a police thread.
	 * 
	 * That thread is going to come to our rescue by counting down 10 seconds.
	 * 
	 * And if the hackers haven't broken into the vault by then and ran away
	 * 
	 * with the money, the policemen is going to arrest them while counting
	 * 
	 * down the police thread is going to show us the progress of its arrival.
	 * 
	 * 
	 */

	public static final int MAX_PASSWORD = 9999;

	private static class Vault {
		private int password;

		public Vault(int password) {
			this.password = password;
		}

		public boolean isCorrectPassword(int guess) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}
			return this.password == guess;
		}
	}

	private static class HackerThread extends Thread {
		protected Vault vault;

		public HackerThread(Vault vault) {
			this.vault = vault;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(Thread.MAX_PRIORITY);
		}

		@Override
		public void start() {
			System.out.println("Starting thread " + this.getName());
			super.start();
		}
	}

	public static class AscendingHackerThread extends HackerThread {

		public AscendingHackerThread(Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			for (int guess = 0; guess < MAX_PASSWORD; guess++) {
				if (vault.isCorrectPassword(guess)) {
					System.out.println(this.getName() + " guessed the password " + guess);
					System.exit(0);
				}
			}
		}
	}

	public static class DescendingHackerThread extends HackerThread {

		public DescendingHackerThread(Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			for (int guess = MAX_PASSWORD; guess >= 0; guess--) {
				if (vault.isCorrectPassword(guess)) {
					System.out.println(this.getName() + " guessed the password " + guess);
					System.exit(0);
				}
			}
		}
	}

	public static class PoliceThread extends Thread {
		@Override
		public void run() {
			for (int i = 10; i > 0; i--) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				System.out.println(i);
			}
			System.out.println("Game over for you hackers");
			// stop the program
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		Random random = new Random();
		Vault vault = new Vault(random.nextInt(MAX_PASSWORD));
		List<Thread> threads = new ArrayList<>();
		threads.add(new AscendingHackerThread(vault));
		threads.add(new DescendingHackerThread(vault));
		threads.add(new PoliceThread());
		for(Thread thread: threads) {
			thread.start();
		}
	}
}
