import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = Integer.parseInt(in.nextLine());
		for(int i = 1; i <= testcase; ++i) {
			int petrol = Integer.parseInt(in.nextLine());
			System.out.print("Scenario #"+i+":\n");
			output(petrol);
			System.out.print("\n");
		}
	}
	private static void output(int petrol) {
		int row = petrol * 2 + 1;
		for (int i = 1; i <= row; ++i) {
			System.out.print("slice #"+i+":\n");
			draw(petrol, row, Math.abs(petrol - i + 1));
		}
	}
	private static void draw(int petrol, int row, int floor) {
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < row; ++j) {
				int sum = Math.abs(i - petrol) + Math.abs(j - petrol) + floor;
				if (sum <= petrol) {
					System.out.print(sum);
				} else {
					System.out.print(".");
				}
			}
			System.out.print("\n");
		}
	}
}