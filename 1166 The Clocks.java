import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] clock = new int[9];
		for (int i = 0; i < 9; ++i) {
			clock[i] = in.nextInt();
		}
		int[] move = new int[9];
		int[] rst = new int[9];
		for (move[8] = 0; move[8] <= 3; ++move[8]) {
			for (move[7] = 0; move[7] <= 3; ++move[7]) {
				for (move[6] = 0; move[6] <= 3; ++move[6]) {
					for (move[5] = 0; move[5] <= 3; ++move[5]) {
						for (move[4] = 0; move[4] <= 3; ++move[4]) {
							for (move[3] = 0; move[3] <= 3; ++move[3]) {
								for (move[2] = 0; move[2] <= 3; ++move[2]) {
									for (move[1] = 0; move[1] <= 3; ++move[1]) {
										for (move[0] = 0; move[0] <= 3; ++move[0]) {
											rst[0]=(clock[0]+move[0]+move[1]+move[3])%4;  
	                                        rst[1]=(clock[1]+move[0]+move[1]+move[2]+move[4])%4; 
	                                        rst[2]=(clock[2]+move[1]+move[2]+move[5])%4; 
	                                        rst[3]=(clock[3]+move[0]+move[3]+move[4]+move[6])%4; 
	                                        rst[4]=(clock[4]+move[0]+move[2]+move[4]+move[6]+move[8])%4;
	                                        rst[5]=(clock[5]+move[2]+move[4]+move[5]+move[8])%4;
	                                        rst[6]=(clock[6]+move[3]+move[6]+move[7])%4;
	                                        rst[7]=(clock[7]+move[4]+move[6]+move[7]+move[8])%4;
	                                        rst[8]=(clock[8]+move[5]+move[7]+move[8])%4;
	                                        if (rst[0] + rst[1] + rst[2] + rst[3] + rst[4] + rst[5] + rst[6] + rst[7] + rst[8] == 0) {
	                                        	for(int i=0;i<move[0];++i)System.out.print("1 ");  
	                                            for(int i=0;i<move[1];++i)System.out.print("2 ");  
	                                            for(int i=0;i<move[2];++i)System.out.print("3 ");  
	                                            for(int i=0;i<move[3];++i)System.out.print("4 ");  
	                                            for(int i=0;i<move[4];++i)System.out.print("5 ");  
	                                            for(int i=0;i<move[5];++i)System.out.print("6 ");  
	                                            for(int i=0;i<move[6];++i)System.out.print("7 ");  
	                                            for(int i=0;i<move[7];++i)System.out.print("8 ");  
	                                            for(int i=0;i<move[8];++i)System.out.print("9 ");  
	                                        }
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}