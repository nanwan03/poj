import java.io.*;
import java.util.*;
import java.math.*;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = Integer.parseInt(in.nextLine());
		while (testcase-- > 0) {
			int input = Integer.parseInt(in.nextLine());
			long rst = 0;
			while (input != 0) {
				rst += input / 5;
				input /= 5;
			}
			System.out.print(rst + "\n");
		}
	}
}