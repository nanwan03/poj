import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		int testcase = Integer.parseInt(in.nextLine());
		while(testcase-- > 0) {
			int num = Integer.parseInt(in.nextLine());
			int[] input = new int[num];
			String[] strs = in.nextLine().split("\\s+");
			for (int i = 0; i < input.length; ++i) {
				input[i] = Integer.parseInt(strs[i]);
			}
			Arrays.sort(input);
			int sum = 0;
			if ((num & 1) == 1) {
				sum = input[0] - 1;
			}
			for (int i = (num & 1); i < num; i = i + 2) {
				sum ^= (input[i + 1] - input[i] - 1); 
			}
			System.out.println(sum != 0 ? "Georgia will win" : "Bob will win");
		}
	}
}