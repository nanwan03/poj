import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = Integer.parseInt(in.nextLine());
		while (testcase-- > 0) {
			int nodeNum = Integer.parseInt(in.nextLine());
			int count = 1;
			int total = 0;
			List<List<String>> words = new ArrayList<List<String>>();
			List<String> ans = new ArrayList<String>();
			int[] parent = new int[26];
			int[] ru = new int[26];
			int[] chu = new int[26];
			boolean[] visited = new boolean[26];
			boolean[][] used = new boolean[26][1005];
			for (int i = 0; i < 26; ++i) {
				parent[i] = i;
				words.add(new ArrayList<String>());
			}
			while (nodeNum-- > 0) {
				char[] chars = in.nextLine().toCharArray();
				int u = chars[0] - 'a';
				int v = chars[chars.length - 1] - 'a';
				if (!visited[u]) {
					visited[u] = true;
					total++;
				}
				if (!visited[v]) {
					visited[v] = true;
					total++;
				}
				ru[v]++;
				chu[u]++;
				words.get(u).add(new String(chars));
				int uRoot = find(u, parent);
				int vRoot = find(v, parent);
				if (uRoot != vRoot) {
					parent[uRoot] = vRoot;
					count++;
				}
			}
			if (count != total) {
				System.out.println("***");
			} else if (!helper(words, ru, chu, ans, used)) {
				System.out.println("***");
			}
		}
	}
	private static boolean helper(List<List<String>> words, int[] ru, int[] chu, List<String> ans, boolean[][] used) {
		int min = 27;
		int odd = 0;
		int start = 0;
		for (int i = 0; i < 26; ++i) {
			if (words.get(i).size() != 0) {
				min = Math.min(min, i);
			}
			if (ru[i] - chu[i] == -1) {
				odd++;
				start = i;
			} else if (chu[i] - ru[i] == -1) {
				odd++;
			} else if (chu[i] != ru[i]) {
				return false;
			}
			if (odd > 2) {
				return false;
			}
		}
		for (int i = 0; i < 26; ++i) {
			Collections.sort(words.get(i));
		}
		if (odd == 0) {
			dfs(min, words, used, ans);
		} else {
			dfs(start, words, used, ans);
		}
		for (int i = ans.size() - 1; i > 0; --i) {
			System.out.print(ans.get(i) + ".");
		}
		System.out.println(ans.get(0));
		return true;
	}
	private static void dfs(int u, List<List<String>> words, boolean[][] used, List<String> ans) {
		for (int i = 0; i < words.get(u).size(); ++i) {
			char[] chars = words.get(u).get(i).toCharArray();
			int v = chars[chars.length - 1] - 'a';
			if (!used[u][i]) {
				used[u][i] = true;
				dfs(v, words, used, ans);
				ans.add(words.get(u).get(i));
			}
		}
	}
	private static int find(int index, int[] parent) {
		if (parent[index] == index) {
			return index;
		} else {
			parent[index] = find(parent[index], parent);
			return parent[index];
		}
	}
}