import java.util.*;

class Block {
	int color;
	int length;
	Block(int color, int length) {
		this.color = color;
		this.length = length;
	}
}

public class Main {
	private static int[][][] score = new int[200][200][200];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = in.nextInt();
		for (int index = 1; index <= testcase; ++index) {
			List<Block> blocks = new ArrayList<Block>();
			int boxNumber = in.nextInt();
			int color = in.nextInt();
			int end = 0;
			blocks.add(new Block(color, 1));
			for (int i = 1; i < boxNumber; ++i) {
				 color = in.nextInt();
				 if (color == blocks.get(end).color) {
					 ++blocks.get(end).length;
				 } else {
					 ++end;
					 blocks.add(new Block(color, 1));
				 }
			}
			for (int i = 0; i < 200; ++i) {
				for (int j = 0; j < 200; ++j) {
					for (int k = 0; k < 200; ++k) {
						score[i][j][k] = 0;
					}
				}
			}
			System.out.println("Case " + index +": " + helper(blocks, 0, end, 0));
		}
	}
	private static int helper(List<Block> blocks, int start, int end, int exLen) {
		if (score[start][end][exLen] > 0) {
			return score[start][end][exLen];
		}
		int result = blocks.get(end).length + exLen;
		result = result * result;
		if (start == end) {
			score[start][end][exLen] = result;
			return score[start][end][exLen];
		}
		result += helper(blocks, start, end - 1, 0);
		for (int i = end - 1; i >= start; --i) {
			if (blocks.get(i).color == blocks.get(end).color) {
				int tmp = helper(blocks, start, i, blocks.get(end).length + exLen) + helper(blocks, i + 1, end - 1, 0);
				result = Math.max(result, tmp);
			}
		}
		score[start][end][exLen] = result;
		return score[start][end][exLen];
	}
}