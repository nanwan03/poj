import java.io.*;
import java.util.*;

class SegmentTree {
	int val;
	int lMax;
	int rMax;
	int cMax;
	
	int lMin;
	int rMin;
	int cMin;
	
	SegmentTree(int val) {
		this.val = val;
		this.lMax = val;
		this.rMax = val;
		this.cMax = val;
		
		this.lMin = val;
		this.rMin = val;
		this.cMin = val;
	}
	
	public void update(int val) {
		this.val = val;
		this.lMax = val;
		this.rMax = val;
		this.cMax = val;
		
		this.lMin = val;
		this.rMin = val;
		this.cMin = val;
	}
}
public class Main {
	private static SegmentTree tree[];
	private static final int NUM = 100010;
	private static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		tree = new SegmentTree[NUM << 2];
		for (int i = 0; i < NUM << 2; ++i) {
			tree[i] = new SegmentTree(0);
		}
		int number = in.nextInt();
		build(1, number, 1);
		int query = in.nextInt();
		for (int i = 1; i <= query; ++i) {
			int index = in.nextInt();
			int cur = in.nextInt();
			update(index, cur, 1, number, 1);
			if (tree[1].val == tree[1].cMax) {
				System.out.println(tree[1].val - tree[1].cMin);
			} else {
				System.out.println(Math.max(tree[1].cMax, tree[1].val - tree[1].cMin));
			}
		}
		
	}
	private static void build(int left, int right, int index) {
		if (left == right) {
			tree[index] = new SegmentTree(in.nextInt());
			return;
		}
		int mid = left + (right - left) / 2;
		build(left, mid, index << 1);
		build(mid + 1, right, (index << 1) | 1);
		pushUp(index);
	}
	private static void update(int pos, int cur, int left, int right, int index) {
		if (left == right && left == pos) {
			tree[index].update(cur);
			return;
		}
		int mid = left + (right - left) / 2;
		if (pos <= mid) {
			update(pos, cur, left, mid, index << 1);
		} else {
			update(pos, cur, mid + 1, right, (index << 1) | 1);
		}
		pushUp(index);
	}
	private static void pushUp(int index) {
		tree[index].val = tree[index << 1].val + tree[(index << 1) | 1].val;
		tree[index].lMax = Math.max(tree[index << 1].lMax, tree[index << 1].val + tree[(index << 1) | 1].lMax);
		tree[index].rMax = Math.max(tree[(index << 1) | 1].rMax, tree[(index << 1) | 1].val + tree[index << 1].rMax);
		tree[index].cMax = Math.max(tree[index << 1].cMax, Math.max(tree[(index << 1) | 1].cMax, tree[index << 1].rMax + tree[(index << 1) | 1].lMax));
		
		tree[index].lMin = Math.min(tree[index << 1].lMin, tree[index << 1].val + tree[(index << 1) | 1].lMin);
		tree[index].rMin = Math.min(tree[(index << 1) | 1].rMin, tree[(index << 1) | 1].val + tree[index << 1].rMin);
		tree[index].cMin = Math.min(tree[index << 1].cMin, Math.min(tree[(index << 1) | 1].cMin, tree[index << 1].rMin + tree[(index << 1) | 1].lMin));
	}
}