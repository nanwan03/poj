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
			 boards[0] = new Board(origX, origX, origY);
			 for (int i = 1; i <= boardNumber; ++i) {
				 boards[i] = new Board(in.nextInt(), in.nextInt(), in.nextInt());
			 }
			 Arrays.sort(boards, new BoardComparator());
			 for (int i = boardNumber; i >= 0; --i) {
				 int j;
				 for (j = i + 1; j <= boardNumber; ++j) {
					 if (boards[i].left <= boards[j].right && boards[i].left >= boards[j].left) {
						 break;
					 }
				 }
				 if (j > boardNumber) {
					 if (boards[i].height > maxHeight) {
						 leftMinTime[i] = 0x3f3f3f3f;
					 } else {
						 leftMinTime[i] = boards[i].height;
					 }
				 } else {
					 int difH = boards[i].height - boards[j].height;
					 if (difH > maxHeight) {
						 leftMinTime[i] = 0x3f3f3f3f;
					 } else {
						 leftMinTime[i] = difH + Math.min(leftMinTime[j] + boards[i].left - boards[j].left,
								 							rightMinTime[j] + boards[j].right - boards[i].left);
					 }
				 }
				 for (j = i + 1; j <= boardNumber; ++j) {
					 if (boards[i].right <= boards[j].right && boards[i].right >= boards[j].left) {
						 break;
					 }
				 }
				 if (j > boardNumber) {
					 if (boards[i].height > maxHeight) {
						 rightMinTime[i] = 0x3f3f3f3f;
					 } else {
						 rightMinTime[i] = boards[i].height;
					 }
				 } else {
					 int difH = boards[i].height - boards[j].height;
					 if (difH > maxHeight) {
						 rightMinTime[i] = 0x3f3f3f3f;
					 } else {
						 rightMinTime[i] = difH + Math.min(leftMinTime[j] + boards[i].right - boards[j].left,
								 							rightMinTime[j] + boards[j].right - boards[i].right);
					 }
				 }
			 }
			 System.out.println(Math.min(leftMinTime[0], rightMinTime[0]));
		 }
	 }
}