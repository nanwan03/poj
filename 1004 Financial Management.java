import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		double sum = 0.0;
		for (int i = 0; i < 12; i++) {
			sum += in.nextDouble();
		}
		double ave = sum / 12.0;
		System.out.print(String.format("$%.2f\n",ave));
	}
}