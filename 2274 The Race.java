/*
 * Only solved first question which is inverted sequence number
 */
import java.io.*;
import java.util.*;

class Ship {
	int loc;
	int speed;
	Ship(int loc, int speed) {
		this.loc = loc;
		this.speed = speed;
	}
}

class ISNSegmentTreeNode {
	int left;
	int right;
	int isn;
	ISNSegmentTreeNode(int left, int right) {
		this.left = left;
		this.right = right;
		this.isn = 0;
	}
}

public class Solution {
	private static final int MOD = 1000000;
	private static ISNSegmentTreeNode[] isnSegmentTree = new ISNSegmentTreeNode[4 * 110];
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		int shipNumber = Integer.parseInt(in.nextLine());
		Ship[] ships = new Ship[shipNumber + 1];
		buildISNSegmentTree(1, 100, 1);
		int isn = 0;
		for (int i = 1; i <= shipNumber; ++i) {
			String[] strs = in.nextLine().split("\\s+");
			int loc = Integer.parseInt(strs[0]);
			int speed = Integer.parseInt(strs[1]);
			ships[i] = new Ship(loc, speed);
			isn = (isn + queryISN(ships[i].speed, 100, 1)) % MOD;
			addISNNode(ships[i].speed, ships[i].speed, 1);
		}
		System.out.println(isn);
	}

	private static void buildISNSegmentTree(int left, int right, int index) {
		isnSegmentTree[index] = new ISNSegmentTreeNode(left, right);
		if (left != right) {
			int mid = left + (right - left) / 2;
			buildISNSegmentTree(left, mid, 2 * index);
			buildISNSegmentTree(mid + 1, right, 2 * index + 1);
		}
	}
	
	private static int queryISN(int left, int right, int index) {
		if (left <= isnSegmentTree[index].left && isnSegmentTree[index].right <= right) {
			return isnSegmentTree[index].isn % MOD;
		} else {
			int mid = isnSegmentTree[index].left + (isnSegmentTree[index].right - isnSegmentTree[index].left) / 2;
			if (right <= mid) {
				return queryISN(left, right, 2 * index) % MOD;
			} else if (left > mid) {
				return queryISN(left, right, 2 * index + 1) % MOD;
			} else {
				return (queryISN(left, mid, 2 * index) + queryISN(mid + 1, right, 2 * index + 1)) % MOD;
			}
		}
	}
	
	private static void addISNNode(int left, int right, int index) {
		if (left <= isnSegmentTree[index].left && isnSegmentTree[index].right <= right) {
			isnSegmentTree[index].isn++;
		} else {
			int mid = isnSegmentTree[index].left + (isnSegmentTree[index].right - isnSegmentTree[index].left) / 2;
			if (right <= mid) {
				addISNNode(left, right, 2 * index);
			} else {
				addISNNode(left, right, 2 * index + 1);
			}
			isnSegmentTree[index].isn = (isnSegmentTree[2 * index].isn + isnSegmentTree[2 * index + 1].isn) % MOD;
		}
	}
}