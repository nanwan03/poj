import java.io.*;
import java.util.*;
import java.math.*;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int testcase = in.nextInt();
			if (testcase == 0) {
				System.exit(0);
			}
			double min = Double.POSITIVE_INFINITY;
			while (testcase-- > 0) {
				int v = in.nextInt();
				int t = in.nextInt();
				if (t < 0) {
					continue;
				}
				double arriveTime = 4.5 / v * 3600 + t;
				min = Math.min(min, arriveTime);
			}
			System.out.print((int)(Math.ceil(min)) + "\n");
		}
	}
}