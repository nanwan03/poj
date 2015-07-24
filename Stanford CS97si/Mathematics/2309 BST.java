import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = Integer.parseInt(in.nextLine());
		while (testcase-- > 0) {
			int input = in.nextInt();
			int diff = (input & (-input)) - 1;
			System.out.println((input - diff) + " " + (input + diff));
		}
	}
}