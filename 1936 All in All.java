import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String[] strs = in.nextLine().split("\\s+");
			String s = strs[0];
			String t = strs[1];
			int sIndex = 0;
			int tIndex = 0;
			while (sIndex < s.length() && tIndex < t.length()) {
				if (s.charAt(sIndex) == t.charAt(tIndex)) {
					sIndex++;
					tIndex++;
				} else {
					tIndex++;
				}
			}
			System.out.println(sIndex == s.length() ? "Yes" : "No");
		}
	}
}