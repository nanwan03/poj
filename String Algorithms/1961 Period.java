import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = 1;
		while (true) {
			int length = Integer.parseInt(in.nextLine());
			if (length == 0) {
				System.exit(0);
			}
			System.out.println("Test case #" + testcase++);
			String str = in.nextLine();
			int[] next = getNext(str);
			for (int i = 1; i <= length; ++i) {
				if (i % (i - next[i]) == 0 && i != i - next[i]) {
					System.out.println(i + " " + i / (i - next[i]));
				}
			}
			System.out.println("");
		}
	}
	private static int[] getNext(String str) {
		int[] next = new int[str.length() + 1];
		next[0] = -1;
		int i = 0;
		int j = -1;
		while (i < str.length()) {
			if (j == -1 || str.charAt(i) == str.charAt(j)) {
				i++;
				j++;
				next[i] = j;
			} else {
				j = next[j];
			}
		}
		return next;
	}
}