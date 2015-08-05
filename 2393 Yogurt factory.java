import java.util.*;

public class Main {
	private static List<Integer> cost = new ArrayList<Integer>();
	private static List<Integer> requirement = new ArrayList<Integer>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int duration = in.nextInt();
		int storageCost = in.nextInt();
		for (int i = 0; i < duration; ++i) {
			cost.add(in.nextInt());
			requirement.add(in.nextInt());
		}
		long storage = 0;
		long totalCost = 0;
		for (int i = 0; i < duration; ++i) {
			if (storage == 0) {
				totalCost += cost.get(i) * requirement.get(i);
			} else {
				storage = 0;
			}
			if (i + 1 < duration && (cost.get(i + 1) - cost.get(i) > storageCost)) {
				totalCost += (cost.get(i) * requirement.get(i + 1) + storageCost * requirement.get(i + 1));
				storage += requirement.get(i + 1);
			}
		}
		System.out.println(totalCost);
	}
}