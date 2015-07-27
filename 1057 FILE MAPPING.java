import java.io.*;

class Node implements Comparable< Node>{
	String content;
	Node parent;
	Node fleftmost;
	Node frightsib;
	Node dleftmost;
	Node drightsib;
	Node drightmost;
	Node(String c,Node n){
		content = c;
		parent = n;
	}
	@Override
	public int compareTo(Node o) {
		return this.content.compareTo(o.content);
	}
}

public class Main {
	 public static void main(String[] args) throws Exception{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 String content = "";
		 Node root=new Node("ROOT",null);
		 Node curr=root;
		 int count=1;
		 while(true) {
			 content = br.readLine();
			 if (content.charAt(0) == 'f') {
		 		Node node = new Node(content,curr);
		 		if(curr.fleftmost == null) {
		 			curr.fleftmost = node;
		 		} else {
		 			if(node.compareTo(curr.fleftmost)< 0) {
		 				node.frightsib = curr.fleftmost;
		 				curr.fleftmost = node;
		 			} else {
		 				Node tempNode = curr.fleftmost;
		 				while(tempNode.frightsib != null && node.compareTo(tempNode.frightsib) > 0){
		 					tempNode = tempNode.frightsib;
		 				}
		 				node.frightsib = tempNode.frightsib;
		 				tempNode.frightsib = node;
		 			}
		 		}
			 } else if (content.charAt(0) == 'd') {
			 	Node node = new Node(content,curr);
			 	if(curr.dleftmost == null) {
			 		curr.dleftmost = curr.drightmost = node;
			 	} else {
			 		curr.drightmost.drightsib = node;
			 		curr.drightmost = curr.drightmost.drightsib;
			 	}
			 	curr = curr.drightmost;
			 } else if (content.charAt(0) == ']') {
				 curr = curr.parent;
			 } else if (content.charAt(0) == '*') {
				 print(root,count++);
				 curr=root=new Node("ROOT",null);
			 } else {
				 System.exit(0);
			 }
		 }
	 }
	 
	 private static void print(Node root, int count) {
	   System.out.println("DATA SET "+count+":");
	   helper(root,0);
	   System.out.println();
	 }

	private static void helper(Node root, int depth) {
	   for(int i = 0; i < depth; ++i) {
		   System.out.print("|     ");
		}
	    System.out.println(root.content);
		Node node = root.dleftmost;
		while(node != null) {
			helper(node, depth + 1);
			node = node.drightsib;
		}
		node = root.fleftmost;
		while(node != null) {
			helper(node, depth);
			node = node.frightsib;
		}
	 }
}