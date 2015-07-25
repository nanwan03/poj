import java.util.*;
import java.io.*;

class Node {
	int[] num = new int[6];
	Node next;
	Node() {
		Arrays.fill(num, -1);
		next = null;
	}
}
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(in.readLine());
		int[] edges = new int[6];
		boolean isFound = false;
		Node[] nodes = new Node[100001];
		for (int i = 0; i < nodes.length; ++i) {
			nodes[i] = new Node();
		}
		for (int i = 0; i < number; ++i) {
			int sum = 0;
			String[] strs = in.readLine().split("\\s+");
			for (int j = 0; j < 6; ++j) {
				edges[j] = Integer.parseInt(strs[j]);
				sum += edges[j];
			}
			if (!isFound) {
				Arrays.sort(edges);
				Node pNode = nodes[sum % 100000];
				while (pNode.next != null && !isFound) {
					pNode = pNode.next;
					for (int j = 0; j < 6; ++j) {
						if (pNode.num[j] != edges[j]) {
							break;
						} else if (j == 5) {
							isFound = true;
						}
					}
				}
				Node newNode = new Node();
				for (int j = 0; j < 6; ++j) {
					newNode.num[j] = edges[j];
				}
				pNode.next = newNode;
			}
		}
		if (isFound)
	    {
	        System.out.println("Twin snowflakes found.");
	    }
	    else
	    {
	        System.out.println("No two snowflakes are alike.");
	    }
	}
}