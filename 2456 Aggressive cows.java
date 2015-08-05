import java.util.*;

public class Main {
	private static List<Long> stalls = new ArrayList<Long>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int stallNum = in.nextInt();
		int cowNum = in.nextInt();
		for (int i = 0; i < stallNum; ++i) {
			stalls.add(in.nextLong());
		}
		Collections.sort(stalls);
		long prev = stalls.get(0);
		long right = stalls.get(stalls.size() - 1) - prev;
		long left = 1;
		for (int i = 1; i < stallNum; ++i) {
			long dif = stalls.get(i) - prev;
			left = Math.min(left, dif);
		}
		if (cowNum == stallNum) {
			System.out.println(left);
		}
		while (left + 1 < right) {
			long mid = left + (right - left) / 2;
			if (check(mid, cowNum)) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}
		if (check(right, cowNum)) {
			System.out.println(right);
		} else {
			System.out.println(left);
		}
	}
	private static boolean check(long dist, int cowNum) {
		long cur = stalls.get(0);
		int num = 1;
		for (int i = 1 ; i < stalls.size(); ++i) {
			if (stalls.get(i) - cur >= dist) {
				cur = stalls.get(i);
				num++;
			}
		}
		return num >= cowNum;
	}
}