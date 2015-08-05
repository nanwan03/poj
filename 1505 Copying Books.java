import java.util.*;

public class Main {
	private static List<Long> pages = new ArrayList<Long>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = in.nextInt();
		while (testcase-- > 0) {
			pages.clear();
			int bookNumber = in.nextInt();
			int peopleNumber = in.nextInt();
			long left = 0;
			long right = 0;
			for (int i = 0; i < bookNumber; ++i) {
				long page = in.nextLong();
				pages.add(page);
				right += page;
				left = Math.max(left, page);
			}
			while (left + 1 < right) {
				long mid = left + (right - left) / 2;
				if (check(mid, peopleNumber)) {
					right = mid;
				} else {
					left = mid + 1;
				}
			}
			long maxPage = 0;
			if (check(left, peopleNumber)) {
				maxPage = left;
			} else {
				maxPage = right;
			}
			print(bookNumber - 1, maxPage, peopleNumber - 1, 0);
			System.out.println("");
		}
	}
	private static boolean check(long page, int peopleNumber) {
		int people = 0;
		long now = 0;
		for (int i = pages.size() - 1; i >= 0; --i) {
			if (now + pages.get(i) > page) {
				++people;
				now = pages.get(i);
			} else {
				now += pages.get(i);
			}
		}
		if (now > 0) {
			++people;
		}
		return people <= peopleNumber;
	}
	private static void print(int bookIndex, long maxPage, int peopleNumber, long curPage) {
		boolean seperate = false;
		if (bookIndex < 0) {
			return;
		}
		if (bookIndex == peopleNumber - 1 || curPage + pages.get(bookIndex) > maxPage) {
			print(bookIndex - 1, maxPage, peopleNumber - 1, pages.get(bookIndex));
			seperate = true;
		} else {
			print(bookIndex - 1, maxPage, peopleNumber, curPage + pages.get(bookIndex));
		}
		if (bookIndex > 0) {
			System.out.print(" " + pages.get(bookIndex));
		} else {
			System.out.print(pages.get(bookIndex));
		}
		if (seperate) {
			System.out.print(" /");
		}
	}
}