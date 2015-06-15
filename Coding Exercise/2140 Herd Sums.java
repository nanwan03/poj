import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int input = Integer.parseInt(in.nextLine());
		int rst = 0;
		for (int i = 1; i <= input / 2; i = i + 2) {
			if (input % i == 0) {
				rst++;
			}
		}
		if (input % 2 != 0) {
			rst++;
		}
		System.out.println(rst + "\n");
	}
}