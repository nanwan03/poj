import java.util.*;

class Node {
	Node[] nodes;
	int num;
	boolean isEnd;
	Node() {
		nodes = new Node[26];
		isEnd = false;
		num = 0;
	}
	public void insert(String str) {
		Node node = this;
		for (char c : str.toCharArray()) {
			if (node.nodes[c - 'a'] == null) {
				node.nodes[c - 'a'] = new Node();
			}
			node = node.nodes[c - 'a'];
			node.num++;
		}
		node.isEnd = true;
	}
	public String search(String str) {
		Node node = this;
		StringBuilder sb = new StringBuilder();
		for (char c : str.toCharArray()) {
			node = node.nodes[c - 'a'];
			sb.append(c);
			if (node.num == 1) {
				break;
			}
		}
		return sb.toString();
	}
}
public class Main {
    public static void main(String[] args) {   
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList<String>();
        Node root = new Node();
        while (in.hasNext()) {
        	String str = in.nextLine();
        	list.add(str);
        	root.insert(str);
        }
        for (String str : list) {
        	String rst = root.search(str);
        	System.out.println(str + " " + rst);
        }
    }
}