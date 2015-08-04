import java.util.*;

class Node {
	char[][] graph = new char[3][3];
	int x;
	int y;
	int n;
}

class Path {
	int pre;
	int dir;
}

public class Main {
	private static final int N = 362882;
	private static final int[] frc = new int[]{ 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };
	private static int[] hash1 = new int[N];
	private static int[] hash2 = new int[N];
	private static int[][] dir = new int[][] {{1,0},{-1,0},{0,-1},{0,1}};
	private static char[] f1 = new char[] {'d', 'u', 'l', 'r'};
	private static char[] f2 = new char[] {'u', 'd', 'r', 'l'};
	private static Node goal = new Node();
	private static Node source = new Node();
	private static Path[] path1 = new Path[N / 2];
	private static Path[] path2 = new Path[N / 2];
	private static int n1 = 0;
	private static int n2 = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				goal.graph[i][j] = (char)(i * 3 + j + 1 +'0');
			}
		}
		goal.x = 2;
		goal.y = 2;
		String[] strs = in.nextLine().split("\\s+");
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				source.graph[i][j] = strs[i * 3 + j].charAt(0);
				if (source.graph[i][j] == 'x') {
						source.graph[i][j] = '9';
						source.x = i;
						source.y = j;
				}
			}
		}
		if (hash(goal.graph) == hash(source.graph)) {
			System.out.println("unsolvable");
		} else if (bfs()){
			int m = 0;
			int[] d = new int[1000];
			while (n2 > 0) {
				d[m++] = path2[n2].dir;
				n2 = path2[n2].pre;
			}
			for (int i = m - 1; i >= 0; --i) {
				System.out.print(f1[d[i]]);
			}
			while (n1 > 0) {
				System.out.print(f2[path1[n1].dir]);
				n1 = path1[n1].pre;
			}
			System.out.println("");
		} else {
			System.out.println("unsolvable");
		}
	}
	private static boolean judge(int x, int y) {
		return (x >= 0 && x < 3 && y >= 0 && y < 3);
	}
	private static int hash(char[][] graph) {
		int v = 0;  
	    for (int i = 0; i < 9; ++i)  
	    {  
	        int a = graph[i / 3][i % 3]-'0';  
	        int cnt = 0;  
	        for (int j = i + 1; j < 9; ++j)  
	        {  
	            int b = graph[j / 3][j % 3]-'0'; 
	            if (b < a) {
	            	cnt++;  
	            }
	        } 
	        v += cnt * frc[a-1];  
	    }  
	    return v;  
	}
	private static boolean bfs() {
		Queue<Node> p = new LinkedList<Node>();
		Queue<Node> q = new LinkedList<Node>();
		source.n = 0;
		goal.n = 0;
		p.offer(goal);
		q.offer(source);
		hash1[hash(goal.graph)] = 1;
		hash2[hash(source.graph)] = 1;
		while (!p.isEmpty() && !q.isEmpty()) {
			if (!p.isEmpty()) {
				Node top = p.poll();
				for (int i = 0; i < 4; ++i) {
					Node cur = deepCopy(top);
					cur.x = top.x + dir[i][0];
					cur.y = top.y + dir[i][1];
					if (judge(cur.x, cur.y)) {
						swap(cur.graph, top.x, top.y, cur.x, cur.y);
						int v = hash(cur.graph);
						if (hash2[v] != 0) {
							path1[++n1] = new Path();
							path1[n1].pre = cur.n;
							path1[n1].dir = i;
							n2 = hash2[v] - 1;
							return true;
						}
						if (hash1[v] == 0) {
							cur.n = ++n1;
							path1[n1] = new Path();
							path1[n1].pre = top.n;  
	                        path1[n1].dir = i;  
	                        hash1[v] = n1+1;  
	                        p.offer(cur);
						}
					}
				}
			}
			if (!q.isEmpty()) {
				Node top = q.poll();
	            for (int i = 0; i < 4; ++i) {  
	                Node cur = deepCopy(top);  
	                cur.x = top.x + dir[i][0];
	                cur.y = top.y + dir[i][1];  
	                if (judge(cur.x,cur.y)) {  
	                    swap(cur.graph, top.x, top.y, cur.x, cur.y);  
	                    int v = hash(cur.graph);  
	                    if(hash1[v] != 0)  
	                    {  
	                    	path2[++n2] = new Path();
	                        path2[n2].pre = cur.n;  
	                        path2[n2].dir = i;  
	                        n1 = hash1[v]-1;  
	                        return true;  
	                    }  
	                    if(hash2[v] == 0)
	                    {  
	                        cur.n = ++n2;
	                        path2[n2] = new Path();
	                        path2[n2].pre = top.n;  
	                        path2[n2].dir = i;  
	                        hash2[v] = n2+1;  
	                        q.offer(cur);
	                    }  
	                }  
	            }  
			}
		}
		return false;
	}
	private static Node deepCopy(Node node) {
		Node cur = new Node();
		cur.x = node.x;
		cur.y = node.y;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				cur.graph[i][j] = node.graph[i][j];
			}
		}
		cur.n = node.n;
		return cur;
	}
	private static void swap(char[][] graph, int x, int y, int newX, int newY) {
		char c = graph[x][y];
		graph[x][y] = graph[newX][newY];
		graph[newX][newY] = c;
	}
}