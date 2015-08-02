import java.util.*;

public class Main {
	private static boolean[] used = new boolean[65];
	private static List<Integer> sticks = new ArrayList<Integer>(65);
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int number = in.nextInt();
			if (number == 0) {
				System.exit(0);
			}
			int totalLength = 0;
			sticks.clear();
			for (int i = 0; i < number; ++i) {
				int temp = in.nextInt();
				sticks.add(temp);
				totalLength += temp;
			}
			Collections.sort(sticks, Collections.reverseOrder());
			int length = sticks.get(0);
			for (length = sticks.get(0); length <= totalLength / 2; ++length) {
				if (totalLength % length != 0) {
					continue;
				}
				Arrays.fill(used, false);
				if (helper(number, number, length, length, 0)) {
					System.out.println(length);
					break;
				}
			}
			if (length > totalLength / 2) {
				System.out.println(totalLength);
			}
		}
	}
	private static boolean helper(int unusedSticks, int totalNumber, int lengthLeft, int expectLength, int lastIndex) {
		if (unusedSticks == 0 && lengthLeft == 0) {
			return true;
		}
		if (lengthLeft == 0) {
			lengthLeft = expectLength;
		}
		int startIndex = 0;
		if (lengthLeft != expectLength) {
			startIndex = lastIndex + 1;
		}
		for (int i = startIndex; i < totalNumber; ++i) {
			if (!used[i] && sticks.get(i) <= lengthLeft) {
				if (i > 0 && !used[i - 1] && sticks.get(i - 1) == sticks.get(i)) {
					continue;
				}
				used[i] = true;
				lastIndex = i;
				if (helper(unusedSticks - 1, totalNumber, lengthLeft - sticks.get(i), expectLength, lastIndex)) {
					return true;
				} else {
					used[i] = false;
					if (sticks.get(i) == lengthLeft || lengthLeft == expectLength) {
						return false;
					}
				}
			}
		}
		return false;
	}
}