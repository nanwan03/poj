import java.io.*;
import java.util.*;
import java.math.*;

class Point {
	double x;
	double y;
	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			Point a = new Point(in.nextDouble(), in.nextDouble());
			Point b = new Point(in.nextDouble(), in.nextDouble());
			Point c = new Point(in.nextDouble(), in.nextDouble());
			double distAB = dist(a, b);
			double distAC = dist(a, c);
			double distBC = dist(b, c);
			double r = distAB * distAC * distBC / Math.sqrt((distAB + distAC + distBC)*
															(distAB + distAC - distBC)*
															(distAB + distBC - distAC)*
															(distAC + distBC - distAB));
			double area = Math.PI * 2 * r;
			System.out.print(String.format("%.2f\n", area));
		}
	}
	private static double dist(Point a, Point b) {
		return Math.sqrt( (a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y) ); 
	}
}