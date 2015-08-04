import java.util.*;

import bailian.Node;

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
	private static boolean[] flags = new boolean[362880];
	private static final int MAX = 400000;;
	private static char[] result = new char[MAX];
	private static Node[] queues = new Node[MAX];
	private static int qHead = 0;
	private static int qTail = 1;
	private static char[] moves = {'u', 'd', 'r', 'l'};
	private static int[] factorial = new int[9];
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
		} else {
			if (bfs(getPermutationIndex(input))) {
				int nMoves = 0;
				int nPos = qHead;
				do {
					result[nMoves++] = queues[nPos].move;
					nPos = queues[nPos].father;
				} while (nPos != 0);
				for (int i = nMoves - 1; i >= 0; --i) {
					System.out.print(result[i]);
				}
				System.out.println("");
			} else {
				System.out.println("unsolvable");
			}
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
	private static boolean bfs(int statusIndex) {
		Arrays.fill(flags, false);
		qHead = 0;
		qTail = 1;
		queues[qHead] = new Node(statusIndex, -1, '0');
		while (qHead != qTail) {
			int curStatus = queues[qHead].status;
			if (curStatus == goalStatus) {
				return true;
			}
			for (int i = 0; i < 4; ++i) {
				int newStatus = getNewStatus(curStatus, moves[i]);
				if (newStatus == -1) {
					continue;
				}
				if (flags[newStatus]) {
					continue;
				}
				flags[newStatus] = true;
				queues[qTail++] = new Node(newStatus, qHead, moves[i]);
			}
			++qHead;
		}
		return false;
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