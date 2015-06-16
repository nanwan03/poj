import java.io.*;
import java.util.*;
import java.math.*;
public class Main {
	private static final int SIZE = 1000000;
	public static void main(String[] args) {
		boolean[] isPrime = new boolean[SIZE];
		Arrays.fill(isPrime, true);
		Scanner in = new Scanner(System.in);
		init(isPrime);
		while (true) {
			int input = Integer.parseInt(in.nextLine());
			if (input == 0) {
				System.exit(0);
			}
			for (int i = 3; i <= input / 2; ++i) {
				if(isPrime[i] && isPrime[input - i]) {
					System.out.print(input + " = " + i + " + " + (input - i) + "\n");
					break;
				}
			}
		}
	}
	private static void init(boolean[] isPrime) {
		for (int i = 4; i < SIZE; i += 2) {
			isPrime[i] = false;
		}
		for (int i = 3; i < Math.sqrt((double)SIZE); i += 2) {
			if (isPrime[i]) {
				int seed = i * i;
				int times = i;
				while (seed < SIZE) {
					isPrime[seed] = false;
					times += 2;
					seed = i * times;
				}
			}
		}
	}
}