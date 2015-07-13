import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String str = in.nextLine();
			int[] next = getNext(str);
			int[] ans = new int[str.length() + 1];
			ans[0] = str.length();
			int i = str.length();
			int n = 0;
			while (next[i] > 0) {
				ans[++n] = next[i];
				i = next[i];
			}
			for (i = n; i >= 0; --i) {
				System.out.print(ans[i]);
				if (i != 0) {
					System.out.print(" ");
				}
			}
			System.out.println(" ");
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