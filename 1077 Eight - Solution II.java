import Node;

import java.util.Arrays;
import java.util.Scanner;

class Node {
	int status;
	int father;
	char move;
	Node(int status, int father, char move) {
		this.status = status;
		this.father = father;
		this.move = move;
	}
}
public class Main {
	private static int goalStatus = 46233;
	private static boolean[][] flags = new boolean[2][362880];
	private static final int MAX = 400000;;
	private static char[] result = new char[MAX];
	private static Node[][] queues = new Node[2][MAX];
	private static int[] qHead = new int[2];
	private static int[] qTail = new int[2];
	private static char[] moves = {'l', 'r', 'u', 'd'};
	private static int[] factorial = new int[9];
	private static int[] startIndex = new int[2];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		factorial[0] = factorial[1] = 1;
		for(int i = 2; i < 9; ++i) {
			factorial[i] = i * factorial[i - 1];
		}
		//goalStatus = getPermutationIndex("123456780".toCharArray());
		String[] strs = in.nextLine().split("\\s+");
		char[] input = new char[9];
		for (int i = 0; i < strs.length; ++i) {
			if (strs[i].equals("x")) {
				input[i] = '0';
			} else {
				input[i] = strs[i].charAt(0);
			}
		}
		if (!isSolveable(input)) {
			System.out.println("unsolvable");
		}
		if (dbfs(getPermutationIndex(input))) {
			int nMoves = 0;
			int nPos = startIndex[0];
			do {
				result[nMoves++] = queues[0][nPos].move;
				nPos = queues[0][nPos].father;
			} while (nPos != 0);
			for (int i = nMoves - 1; i >= 0; --i) {
				System.out.print(result[i]);
			}
			nMoves = 0;
			nPos = startIndex[1];
			do {
				System.out.print(queues[1][nPos].move);
				nPos = queues[1][nPos].father;
			} while (nPos != 0);
			System.out.println("");
		} else {
			System.out.println("unsolvable");
		}
	}
	private static boolean isSolveable(char[] input) {
		int sumOri = 0;
		for (int i = 0; i < 9; ++i) {
			if (input[i] == '0') {
				continue;
			}
			for (int j = 0; j < i; ++j) {
				if (input[j] != '0' && input[j] < input[i]) {
					sumOri++;
				}
			}
		}
		return (sumOri & 1) == 0;
	}
	private static int getPermutationIndex(char[] input) {
		int num = 0;
		boolean[] used = new boolean[9];
		for (int i = 0; i < 9; ++i) {
			int n = 0;
			for (int j = 0; j < (input[i] - '0'); ++j) {
				if (!used[j]) {
					++n;
				}
			}
			num += n * factorial[8 - i];
			used[(input[i] - '0')] = true;
		}
		return num;
	}
	private static char[] getPermutationByIndex(int index) {
		char[] output = new char[9];
		boolean[] used = new boolean[9];
		for (int i = 0; i < 9; ++i) {
			int j = 0;
			for (j = 0; j < 9; ++j) {
				if (!used[j]) {
					if (factorial[8 - i] >= index + 1) {
						break;
					} else {
						index -= factorial[8 - i];
					}
				}
			}
			used[j] = true;
			output[i] = (char)(j + '0');
		}
		return output;
	}
	private static boolean dbfs(int statusIndex) {
		Arrays.fill(flags[0], false);
		Arrays.fill(flags[1], false);
		qHead[0] = qHead[1] = 0;
		qTail[0] = qTail[1] = 1;
		queues[0][qHead[0]] = new Node(statusIndex, -1, '0');
		queues[1][qHead[1]] = new Node(goalStatus, -1, '0');
		while (qHead[0] != qTail[0] && qHead[1] != qTail[1]) {
			int expandIndex = (qTail[0] - qHead[0]) < (qTail[1] - qHead[1]) ? 0 : 1;
			boolean flag = expand(expandIndex);
			if (flag) {
				return true;
			}
		}
		while (qHead[0] != qTail[0]) {
			boolean flag = expand(0);
			if (flag) {
				return true;
			}
		}
		while (qHead[1] != qTail[1]) {
			boolean flag = expand(1);
			if (flag) {
				return true;
			}
		}
		return false;
	}
	private static boolean expand(int expandIndex) {
		int curStatus = queues[expandIndex][qHead[expandIndex]].status;
		if (flags[1 - expandIndex][curStatus]) {
			startIndex[expandIndex] = qHead[expandIndex];
			for (int i = 0; i < qTail[1 - expandIndex]; ++i) {
				if (queues[1 - expandIndex][i].status == curStatus) {
					startIndex[1 - expandIndex] = i;
					break;
				}
			}
			return true;
		}
		for (int i = 0; i < 4; ++i) {
			int newStatus = getNewStatus(curStatus, moves[i]);
			if (newStatus == -1) {
				continue;
			}
			if (flags[expandIndex][newStatus]) {
				continue;
			}
			flags[expandIndex][newStatus] = true;
			queues[expandIndex][qTail[expandIndex]++] = new Node(newStatus, qHead[expandIndex], (expandIndex == 1) ? reverseMove(moves[i]) : moves[i]);
		}
		++qHead[expandIndex];
		return false;
	}
	private static char reverseMove(char c) {
		if (c == 'u') {
			return 'd';
		} else if (c == 'd') {
			return 'u';
		} else if (c == 'l') {
			return 'r';
		} else {
			return '1';
		}
	}
	private static int getNewStatus(int curStatus, char move) {
		char[] chars = getPermutationByIndex(curStatus);
		int zeroPos = 0;
		for (int i = 0; i < 9; ++i) {
			if(chars[i] == '0') {
				zeroPos = i;
				break;
			}
		}
		if (move == 'u') {
			if (zeroPos - 3 < 0) {
				return -1;
			} else {
				chars[zeroPos] = chars[zeroPos - 3];
				chars[zeroPos - 3] = '0';
			}
		} else if (move == 'd'){
			if (zeroPos + 3 > 8) {
				return -1;
			} else {
				chars[zeroPos] = chars[zeroPos + 3];
				chars[zeroPos + 3] = '0';
			}
		} else if (move == 'l') {
			if (zeroPos % 3 == 0) {
				return -1;
			} else {
				chars[zeroPos] = chars[zeroPos - 1];
				chars[zeroPos - 1] = '0';
			}
		} else {
			if (zeroPos % 3 == 2) {
				return -1;
			} else {
				chars[zeroPos] = chars[zeroPos + 1];
				chars[zeroPos + 1] = '0';
			}
		}
		return getPermutationIndex(chars);
	}
}