import java.io.*;
import java.util.*;
class StringComparator implements Comparator<String> {
	public int compare(String a, String b) {
		return getUnsortedness(a) - getUnsortedness(b);
	}
	public int getUnsortedness(String str) {
		char[] chars = str.toCharArray();
		int rst = 0;
		for (int i = 0; i < chars.length; ++i) {
			for (int j = i + 1; j < chars.length; ++j) {
				if (chars[j] < chars[i]) {
					rst++;
				}
			}
		}
		return rst;
	}
}
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String pars = in.nextLine();
		String[] strs = pars.split(" ");
		int length = Integer.parseInt(strs[0]);
		int testcase = Integer.parseInt(strs[1]);
		List<String> inputs = new ArrayList<String>();
		while (testcase-- > 0) {
			inputs.add(in.nextLine());
		}
		Collections.sort(inputs, new StringComparator());
		for (String str : inputs) {
			System.out.print(str+"\n");
		}
	}
}