import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			long input = in.nextLong();
			if (input == 0) {
				break;
			}
			long seed = 1;
			while (true) {
				long binary = Long.parseLong(Long.toBinaryString(seed));
				if (binary % input == 0) {
					break;
				}
				seed++;
			}
			System.out.println(Long.toBinaryString(seed));
		}
	}
}