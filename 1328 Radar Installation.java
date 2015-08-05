import java.util.*;

class Interval {
	double left;
	double right;
	Interval(double left, double right) {
		this.left = left;
		this.right = right;
	}
}

class IntervalComparator implements Comparator<Interval> {
	public int compare(Interval a, Interval b) {
		return a.left - b.left < 0 ? -1 : 1;
	}
}
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = 0;
		List<Interval> intervals = new ArrayList<Interval>();
		while (true) {
			++testcase;
			intervals.clear();
			int num = in.nextInt();
			int r = in.nextInt();
			if (num == 0 && r == 0) {
				System.exit(0);
			}
			boolean flag = true;
			for (int i = 0; i < num; ++i) {
				int x = in.nextInt();
				int y = in.nextInt();
				if (y > r) {
					flag = false;
				}
				double dist = Math.sqrt(r * r - y * y);
				intervals.add(new Interval(x - dist, x + dist));
			}
			if (flag) {
				Collections.sort(intervals, new IntervalComparator());
				System.out.println("Case " + testcase +": " + solve(intervals, r));
			} else {
				System.out.println("Case " + testcase +": -1");
			}
		}
	}
	private static int solve(List<Interval> lists, int r) {
		int rst = 1;
		double cur = lists.get(0).right;
		for (int i = 1; i < lists.size(); ++i) {
			if (lists.get(i).left > cur) {
				++rst;
				cur = lists.get(i).right;
			} else {
				cur = Math.min(cur, lists.get(i).right);
			}
		}
		return rst;
	}
}