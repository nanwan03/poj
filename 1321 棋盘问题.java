import java.util.*;

public class Main {
	private static char[][] board = new char[8][8];
	private static int rst = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			rst = 0;
			String[] strs = in.nextLine().split("\\s+");
			int size = Integer.parseInt(strs[0]);
			int queens = Integer.parseInt(strs[1]);
			if (size == -1 && queens == -1) {
				System.exit(0);
			}
			for (int i = 0; i < size; ++i) {
				char[] chars = in.nextLine().toCharArray();
				for (int j = 0; j < size; ++j) {
					board[i][j] = chars[j];
				}
			}
			List<Integer> cols = new ArrayList<Integer>();
			helper(board, cols, size, queens, 0);
			System.out.println(rst);
		}
	}
	private static void helper(char[][] board, List<Integer> cols, int size, int queens, int row) {
		if (queens == 0) {
			rst++;
			return;
		}
		if (row >= size) {
			return;
		}
		for (int i = 0; i < size; ++i) {
			if (board[row][i] == '.') {
				continue;
			}
			if (isValid(cols, i)) {
				cols.add(i);
				helper(board, cols, size, queens - 1, row + 1);
				cols.remove(cols.size() - 1);
			}
		}
		helper(board, cols, size, queens,row + 1);
	}
	private static boolean isValid(List<Integer> cols, int col) {
		for (int i = 0; i < cols.size(); ++i) {
			if (cols.get(i) == col) {
				return false;
			}
		}
		return true;
	}
}