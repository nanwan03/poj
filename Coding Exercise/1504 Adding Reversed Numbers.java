import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = Integer.parseInt(in.nextLine());
		while (testcase-- > 0) {
			String[] str = in.nextLine().split("\\s+");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			System.out.print(reverse(reverse(a) + reverse(b)) + "\n");
		}
	}
	private static int reverse(int digit) {
		int rst = 0;
		while (digit != 0) {
			rst = rst * 10 + digit % 10;
			digit /= 10;
		}
		return rst;
	}
}