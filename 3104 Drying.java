import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int number = in.nextInt();
		int right = 0;
		int[] cloths = new int[number];
		for (int i = 0; i < number; ++i) {
			cloths[i] = in.nextInt();
			right = Math.max(right, cloths[i]);
		}
		int ability = in.nextInt();
		if (ability == 1) {
			System.out.println(right);
		} else {
			int left = 1;
			while (left + 1 < right) {
				int mid = left + (right - left) / 2;
				if (check(mid, cloths, ability)) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			if (check(left, cloths, ability)) {
				System.out.println(left);
			} else if (check(right, cloths, ability)){
				System.out.println(right);
			} else {
				System.out.println(right + 1);
			}
		}
	}
	private static boolean check(int maxTime, int[] cloths, int ability) {
		int total = 0;
		for (int i : cloths) {
			if (i > maxTime) {
				total += (i - maxTime - 1) / (ability - 1) + 1;
				if (total > maxTime) {
					return false;
				}
			}
		}
		return true;
	}
}