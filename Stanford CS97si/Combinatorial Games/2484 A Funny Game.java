import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		while(true) {
			int n = in.nextInt();
			if (n == 0) {
				System.exit(0);
			}
			if (n > 2) {
				System.out.println("Bob");
				
			} else {
				System.out.println("Alice");
			}
		}
	}
}