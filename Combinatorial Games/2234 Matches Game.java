import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		while(true) {
			String[] strs = in.nextLine().split("\\s+");
			int number = Integer.parseInt(strs[0]);
			int xor = 0;
			for (int i = 1; i < strs.length; ++i) {
				xor ^= Integer.parseInt(strs[i]);
			}
			System.out.println(xor == 0 ? "No" : "Yes");
		}
	}
}