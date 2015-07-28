import java.util.*;

class Board {
	int left;
	int right;
	int height;
	Board(int left, int right, int height) {
		this.left = left;
		this.right = right;
		this.height = height;
	}
}

class BoardComparator implements Comparator<Board> {
	public int compare(Board a, Board b) {
		return b.height - a.height;
	}
}

public class Main {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 int testcase = in.nextInt();
		 while (testcase-- > 0) {
			 int boardNumber = in.nextInt();
			 int origX = in.nextInt();
			 int origY = in.nextInt();
			 int maxHeight = in.nextInt();
			 Board[] boards = new Board[boardNumber + 1];
			 int[] leftMinTime = new int[boardNumber + 1];
			 int[] rightMinTime = new int[boardNumber + 1];
			 Arrays.fill(leftMinTime, -1);
			 Arrays.fill(rightMinTime, -1);
			 boards[0] = new Board(origX, origX, origY);
			 for (int i = 1; i <= boardNumber; ++i) {
				 boards[i] = new Board(in.nextInt(), in.nextInt(), in.nextInt());
			 }
			 Arrays.sort(boards, new BoardComparator());
			 System.out.println(helper(boards, leftMinTime, rightMinTime, maxHeight, 0, true));
		 }
	 }
	 private static int helper(Board[] boards, int[] leftMinTime, int[] rightMinTime, int maxHeight, int boardIndex, boolean left) {
		 int height = boards[boardIndex].height;
		 int x = left ? boards[boardIndex].left : boards[boardIndex].right;
		 int i = boardIndex + 1;
		 for (i = boardIndex + 1; i < boards.length; ++i) {
			 if (boards[i].left <= x && boards[i].right >= x) {
				 break;
			 }
		 }
		 if (i >= boards.length) {
			 if (height > maxHeight) {
				 return 0x3f3f3f3f;
			 } else {
				 return height;
			 }
		 } else {
			 int divH = height - boards[i].height;
			 if (divH > maxHeight) {
				 return 0x3f3f3f3f;
			 } else {
				 int leftTime = divH + x - boards[i].left;
				 int rightTime = divH + boards[i].right - x;
				 if (leftMinTime[i] == -1) {
					 leftMinTime[i] = helper(boards, leftMinTime, rightMinTime, maxHeight, i, true);
				 }
				 if (rightMinTime[i] == -1) {
					 rightMinTime[i] = helper(boards, leftMinTime, rightMinTime, maxHeight, i, false);
				 }
				 leftTime += leftMinTime[i];
				 rightTime += rightMinTime[i];
				 return Math.min(leftTime, rightTime);
			 }
		 }
	 }
}