import java.util.*;

public class Main {
	private static int minArea = 0x3f3f3f3f;
	private static int[] minV = new int[30];
	private static int[] minA = new int[30];
	private static int vol = 0;
	private static int level = 0;
	private static int curArea = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		vol = in.nextInt();
		level = in.nextInt();
		minV[0] = 0;
		minA[0] = 0;
		for (int i = 1; i <= level; ++i) {
			minV[i] = minV[i - 1] + i * i * i;
			minA[i] = minA[i - 1] + 2 * i * i;
		}
		if (minV[level] > vol) {
			System.out.println(0);
		} else {
			int maxH = (vol - minV[level - 1]) / (level * level) + 1;
			int maxR = (int)Math.sqrt((vol - minV[level - 1]) / level) + 1;
			helper(vol, level, maxR, maxH);
			minArea = (minArea == 0x3f3f3f3f) ? 0 : minArea;
			System.out.println(minArea);
 		}
	}
	private static void helper(int volLeft, int curLevel, int maxR, int maxH) {
		if (curLevel == 0) {
			if (volLeft == 0) {
				minArea = Math.min(minArea, curArea);
			}
			return;
		}
		if (volLeft <= 0) {
			return;
		}
		if (minV[curLevel] > volLeft) {
			return;
		}
		if (curArea + minA[curLevel] >= minArea) {
			return;
		}
		if (maxH < curLevel || maxR < curLevel) {
			return;
		}
		if (maxVLeft(curLevel, maxH, maxR) < volLeft) {
			return;
		}
		for (int i = maxR; i >= curLevel; --i) {
			if (curLevel == level) {
				curArea = i * i;
			}
			for (int j = maxH; j >= curLevel; --j) {
				curArea += 2 * i * j;
				helper(volLeft - i * i * j, curLevel - 1, i - 1, j - 1);
				curArea -= 2 * i * j;
			}
		}
	}
	private static int maxVLeft(int curLevel, int curH, int curR) {
		int rst = 0;
		for (int i = 0; i < curLevel; ++i) {
			rst += (curR - i) * (curR - i) * (curH - i);
		}
		return rst;
	}
}