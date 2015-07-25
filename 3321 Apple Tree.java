import java.io.*;
import java.util.*;

class Fork {
	int index;
	boolean grown;
	Fork next;
	Fork(int index) {
		this.index = index;
		this.grown = true;
		next = null;
	}
}

class Fenwick {
	int size;
	int[] value;
	int cur;
	Fork[] forks;
	int[][] pis;
	Fenwick(int size, Fork[] forks) {
		this.size = size;
		this.value = new int[size + 1];
		this.forks = forks;
		this.pis = new int[size + 1][2];
		Arrays.fill(value, 0);
	}
	private static int lowBit(int index) {
		return index & (index ^ (index - 1));
	}
	public void add(int val, int index) {
		while (index <= size) {
			value[index] += val;
			index += lowBit(index);
		}
	}
	public int getSum(int index) {
		int sum = 0;
		while (index > 0) {
			sum += value[index];
			index -= lowBit(index);
		}
		return sum;
	}
	public void init() {
		this.cur = 0;
		dfs(1);
		for (int i = 1; i <= size; ++i) {
			add(1, i);
		}
	}
	public void dfs(int index) {
		pis[index][0] = cur;
		cur++;
		Fork f = forks[index].next;
		while (f != null) {
			dfs(f.index);
			f = f.next;
		}
		pis[index][1] = cur;
	}
}
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(cin.readLine());
		Fork[] forks = new Fork[number + 1];
		for (int i = 1; i <= number; ++i) {
			forks[i] = new Fork(i);
		}
		for (int i = 1; i <= number - 1; ++i) {
			String[] strs = cin.readLine().split("\\s+");
			int f1 = Integer.parseInt(strs[0]);
			int f2 = Integer.parseInt(strs[1]);
			Fork tempF = new Fork(f2);
			tempF.next = forks[f1].next;
			forks[f1].next = tempF;
		}
		Fenwick tree = new Fenwick(number, forks);
		tree.init();
		number = Integer.parseInt(cin.readLine());
		for (int i = 0; i < number; ++i) {
			String[] strs = cin.readLine().split("\\s+");
			int index = Integer.parseInt(strs[1]);
			if (strs[0].equals("C")) {
				tree.add(tree.forks[index].grown ? -1 : 1, tree.pis[index][0] + 1);
				tree.forks[index].grown ^= true;
			} else {
				int temp1 = tree.getSum(tree.pis[index][0]);
				int temp2 = tree.getSum(tree.pis[index][1]);
				System.out.println(temp2 - temp1);
			}
		}
	}
}