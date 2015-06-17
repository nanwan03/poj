import java.io.*;
import java.util.*;
import java.math.*;

class TrieNode {
	TreeMap<Character, TrieNode> map = new TreeMap<Character, TrieNode>();
	int count = 0;
	TrieNode() {
		
	}
	public TrieNode next(char c) {
		return map.get(c);
	}
	public boolean contains(char c) {
		return map.containsKey(c);
	}
	public void insert(char c) {
		if (!contains(c)) {
			map.put(c, new TrieNode());
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
		String name;
		int total = 0;
		TrieNode trie = new TrieNode();
		while ((name = cin.readLine()) != null) {
			insert(trie, name.toCharArray());
			total++;
		}
		dfs(trie, total, new StringBuilder());
	}
	private static void insert(TrieNode root, char[] chars) {
		for (char c : chars) {
			root.insert(c);
			root = root.next(c);
		}
		root.count++;
	}
	private static void dfs(TrieNode root, int total, StringBuilder str) {
		if (root == null) {
			return;
		}
		if (root.count != 0) {
			System.out.printf("%s %.4f\n", str.toString(), root.count * 100.0 / total);
		}
		Iterator<Map.Entry<Character, TrieNode>> it = root.map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Character, TrieNode> entry = it.next();
			str.append(entry.getKey());
			dfs(entry.getValue(), total, str);
			str.deleteCharAt(str.length() - 1);
		}
	}
}