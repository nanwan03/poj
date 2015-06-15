import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] total = new int[26];
		for (int i = 0; i < 4; i++) {
			String str = in.nextLine();
			for (char c : str.toCharArray()) {
				if (Character.isLetter(c)) {
					total[c - 'A']++;
				}
			}
		}
		while (true) {
			int max = 0;
			for (int i = 0; i < 26; i++) {
				max = Math.max(max, total[i]);
			}
			if (max == 0) {
				for (int i = 0; i < 26; i++) {
					System.out.print((char)('A' + i));
					if (i < 25) {
						System.out.print(" ");
					} else {
						System.out.print("\n");
					}
				}
				break;
			} else {
				for (int i = 0; i < 26; i++) {
					if (total[i] == max) {
						System.out.print("*");
						total[i]--;
					} else {
						System.out.print(" ");
					}
					if (i < 25) {
						System.out.print(" ");
					} else {
						System.out.print("\n");
					}
				}
			}
		}
	}
}