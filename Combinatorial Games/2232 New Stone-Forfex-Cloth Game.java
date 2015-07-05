import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		while(in.hasNext()) {
			int number = Integer.parseInt(in.nextLine());
			int fNum = 0;
			int sNum = 0;
			int cNum = 0;
			String[] strs = in.nextLine().split("\\s+");
			for (String s : strs) {
				if (s.equals("C")) {
					cNum++;
				} else if(s.equalsIgnoreCase("S")) {
					sNum++;
				} else {
					fNum++;
				}
			}
			if (cNum == number || sNum == number || fNum == number) {
				System.out.println(number);
			} else if (cNum != 0 && sNum != 0 && fNum != 0) {
				System.out.println(number);
			} else {
				if (sNum == 0) {
					System.out.println(fNum);
				} else if (fNum == 0) {
					System.out.println(cNum);
				} else {
					System.out.println(sNum);
				}
			}
		}
	}
}