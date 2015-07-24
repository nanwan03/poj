import java.util.*;
import java.io.*;

public class Main {
	private static int root[];
	private static boolean isSameGroupToRoot[];
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(in.readLine());
		while (testcase-- > 0) {
			String[] strs = in.readLine().split("\\s+");
			int number = Integer.parseInt(strs[0]);
			int lines = Integer.parseInt(strs[1]);
			root = new int[number + 1];
			isSameGroupToRoot = new boolean[number + 1];
			Arrays.fill(isSameGroupToRoot, true);
			for (int i = 0; i <= number; ++i) {
				root[i] = i;
			}
			for (int i = 0; i < lines; ++i) {
				strs = in.readLine().split("\\s+");
				int a = Integer.parseInt(strs[1]);
				int b = Integer.parseInt(strs[2]);
				if (strs[0].equals("A")) {
					if (find(a) == find(b)) {
						if (isSameGroupToRoot[a] != isSameGroupToRoot[b]) {
							System.out.println("In different gangs.");
						} else {
							System.out.println("In the same gang.");
						}
					} else {
						System.out.println("Not sure yet.");
					}
				} else {
					union(a, b);
				}
			}
		}
	}
	private static int find(int node) {
		if (node == root[node]) {
			return node;
		}
		int tmp = root[node];
		root[node] = find(root[node]);
		isSameGroupToRoot[node] = !(isSameGroupToRoot[tmp] ^ isSameGroupToRoot[node]); //根据子节点与父亲节点的关系和父节点与爷爷节点的关系，推导子节点与爷爷节点的关系 如果 a 和 b 的关系是 r1, b 和 c 的关系是 r2, 那么 a 和 c 的关系就是 (r1+r2)%2
		return root[node];
	}
	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB) {
			return;
		}
		root[rootA] = rootB;
		isSameGroupToRoot[rootA] = isSameGroupToRoot[a] ^ isSameGroupToRoot[b]; //联合时，使得 p[fx] = fy; 同时也要寻找 fx 与 fy 的关系。关系为：（r[x]+r[y]+1）%2. fx 与 x 的关系是 r[x], x 与 y 的关系是 1 （因为确定是不同类，才联合的）, y 与 fy 关系是 r[y],模 2 是因为只有两种关系
	}
}