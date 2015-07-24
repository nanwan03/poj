import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = Integer.parseInt(in.nextLine());
		for (int i = 1; i <= testcase; ++i) {
			double R = in.nextDouble();
			double n = in.nextDouble();
			double r = R * (1.0 - 1.0 / (1.0 + Math.sin(Math.PI / n)));
			System.out.print("Scenario #" + i + ":\n");
			System.out.print(String.format("%.3f\n\n", r));
		}
	}
}