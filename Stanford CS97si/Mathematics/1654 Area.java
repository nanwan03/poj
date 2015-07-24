import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		int move[][]={{0,0},{1,-1},{1,0},{1,1},{0,-1},{0,0},{0,1},{-1,-1},{-1,0},{-1,1}};
		Scanner in = new Scanner(System.in);
		int testcase = Integer.parseInt(in.nextLine());
		while (testcase-- > 0) {
			String str = in.next();
			long x = 0;
			long y = 0;
			long newX = 0;
			long newY = 0;
			long area = 0;
			for (int i = 0; i < str.length(); ++i) {
				int dir = str.charAt(i) - '0';
				newX = x + move[dir][0];
				newY = y + move[dir][1];
				area += getArea(x, y, newX, newY);
				x = newX;
				y = newY;
			}
			if (area < 0) {
				area = -area;
			}
			if ((area & 1) == 1) {
				System.out.printf("%d.5\n", area / 2);
			} else {
				System.out.printf("%d\n", area / 2);
			}
		}
	}
	private static long getArea(long x, long y, long newX, long newY) {
		return x * newY - newX * y;
	}
}