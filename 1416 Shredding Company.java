import java.util.*;

public class Main {
	private static boolean rejected = false;
	private static boolean error = true;
	private static int[] rst = new int[8];
	private static int[] curCut = new int[8];
	private static int rstSum = 0;
	private static int curCuttedSum = 0;
	private static int rstLen = 0;
	private static int curCuttedLen = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int target = in.nextInt();
			int input = in.nextInt();
			if (target == 0 && input == 0) {
				System.exit(0);
			}
			init();
			helper(target, input);
			if (rejected && !error) {
				System.out.println("rejected");
			} else if (error) {
				System.out.println("error");
			} else {
				System.out.print(rstSum + " ");
				for (int i = rstLen - 1; i >= 0; --i) {
					if (i == 0) {
						System.out.println(rst[i]);
					} else {
						System.out.print(rst[i] + " ");
					}
				}
			}
		}
	}
	private static void init() {
		rejected = false;
		error = true;
		rstSum = 0;
		curCuttedSum = 0;
		rstLen = 0;
		curCuttedLen = 0;
		Arrays.fill(rst, 0);
		Arrays.fill(curCut, 0);
	}
	private static void helper(int target, int input) {
		if (input == 0) {
			if (curCuttedSum <= target && rstSum < curCuttedSum) {
				rstSum = curCuttedSum;
				rstLen = curCuttedLen;
				rejected = false;
				error = false;
				for (int i = 0; i < rstLen; ++i) {
					rst[i] = curCut[i];
				}
			} else if (curCuttedSum <= target && rstSum == curCuttedSum) {
				rejected = true;
			}
			return;
		}
		if (input + curCuttedSum < rstSum) {
			return;
		}
		for (int degree = 10; degree <= input * 10; degree *= 10) {
			curCuttedSum += input % degree;
			curCut[curCuttedLen++] = input % degree;
			helper(target, input / degree);
			--curCuttedLen;
			curCuttedSum -= input % degree;
		}
	}
}