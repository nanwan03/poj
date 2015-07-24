import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		while (true) {
			long a = in.nextLong();
			long b = in.nextLong();
			if (a == 0 && b == 0) {
				System.exit(0);
			}
			int rst = 0;
			while (true) {
				if (a < b) {
					long temp = b;
					b = a;
					a = temp;
				}
				if (a % b == 0 || a > b * 2) {
					break;
				}
				a -= b;
				rst ^= 1;
			}
			if (rst == 0) {
				System.out.println("Stan wins");
			} else {
				System.out.println("Ollie wins");
			}
		}
	}
}