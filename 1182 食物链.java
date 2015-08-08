import java.util.*;
import java.io.*;

public class Main {
	private static int[] parent;
	private static int[] relation; //每个节点对应的 r[]值记录他与根节点的关系：0：同类， 1：被父亲节点吃， 2： 吃父亲节点
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = read.readLine().split("\\s+");
        int num = Integer.parseInt(strs[0]);
        int testcase = Integer.parseInt(strs[1]);
        parent = new int[num + 1];
        relation = new int[num + 1];
        for (int i = 1; i < parent.length; ++i) {
            parent[i] = i;
        }
        int rst = 0;
        while (testcase-- > 0) {
            strs = read.readLine().split("\\s+");
            int type = Integer.parseInt(strs[0]);
            int a = Integer.parseInt(strs[1]);
            int b = Integer.parseInt(strs[2]);
            if (a > num || b > num) {
                rst++;
                continue;
            }
            if (a == b && type == 2) {
                rst++;
                continue;
            }
            int fa = find(a);
            int fb = find(b);
            if (fa == fb) {
                if (type == 1) {
                    if (relation[a] != relation[b]) {
                    	rst++;
                    }
                } else {
                    if ((relation[a] + 1) % 3 != relation[b] % 3) {
                    	rst++;
                    }
                }
            } else
                union(a, b, type);
        }
        read.close();
        System.out.println(rst);
    }
	private static int find(int animal) {
		if (animal == parent[animal])
            return parent[animal];
        else {
            int p = parent[animal];
            parent[animal] = find(parent[animal]);
            relation[animal] = (relation[animal] + relation[p]) % 3;
        }
        return parent[animal];
	}
	private static void union(int x, int y, int type) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            if (relation[x] < relation[y]) {
                parent[fx] = fy;
                if (type == 2) {
                	relation[fx] = (relation[y] - relation[x] - 1 + 3) % 3;
                } else {
                	relation[fx] = (relation[y] - relation[x]) % 3;
                }
            } else {
                parent[fy] = fx;
                if (type == 2) {
                	relation[fy] = (relation[x] - relation[y] + 1) % 3;
                } else {
                	relation[fy] = (relation[x] - relation[y]) % 3;
                }
            }
        }
    }
}