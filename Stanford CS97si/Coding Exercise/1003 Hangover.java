import java.io.*;
import java.util.*;
public class Main {
	private static final double EPSILON = 2.7755575615628914E-17;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			String rst = in.nextLine();
			if (rst.equals("0.00")) {
				break;
			}
			double targets = Double.parseDouble(rst);
			int num = 0;
			while (targets > EPSILON) {
				num++;
				double hangover = 1.0 / ((double)num + 1.0);
				targets -= hangover;
			}
			System.out.print(num+" card(s)\n");
		}
	}
}