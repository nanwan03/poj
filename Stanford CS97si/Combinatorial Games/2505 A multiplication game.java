import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		while(in.hasNext()) {
			double m = in.nextDouble();
			while (m > 18) {
				m /= 18;
			}
			System.out.println(m > 9 ? "Ollie wins." : "Stan wins.");
		}
	}
}