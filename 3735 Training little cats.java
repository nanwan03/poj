import java.util.Arrays;   
import java.util.Scanner;
class Matrix {
	long[][] matrix = new long[110][110];
	private void clear() {
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix[0].length; ++j) {
				matrix[i][j] = 0;
			}
		}
	}
	public void init(int size) {
		clear();
		for (int i = 0; i <= size; ++i) {
			matrix[i][i] = 1;
		}
	}
	public void swap(int col1, int col2, int size) {
		for (int i = 0; i <= size; ++i) {
			long temp = matrix[i][col1];
			matrix[i][col1] = matrix[i][col2];
			matrix[i][col2] = temp;
		}
	}
	public void pow(long repeat, int size) {
		if (repeat == 1) {
			return;
		}
		Matrix rst = new Matrix();
		rst.init(size);
		while (repeat != 0) {
			if ((repeat & 1) == 1) {
				rst.mul(this, size);
			}
			repeat >>= 1;
			this.mul(this, size);
		}
		this.matrix = rst.matrix;
	}
	public void mul(Matrix matrix, int size) {
		Matrix temp = new Matrix();
		for (int i = 0; i <= size; ++i) {
			for (int k = 0; k <= size; ++k) {
				if (this.matrix[i][k] != 0) {
					for (int j = 0; j <= size; ++j) {
						temp.matrix[i][j] += (this.matrix[i][k] * matrix.matrix[k][j]);
					}
				}
			}
		}
		this.matrix = temp.matrix;
	}
}
public class Main {
	private static Matrix cat = new Matrix();
    public static void main(String[] args) {   
        Scanner in = new Scanner(System.in);   
        while(true) {
        	String[] strs = in.nextLine().split("\\s+");
        	int size = Integer.parseInt(strs[0]);
        	long repeat = Long.parseLong(strs[1]);
        	int operation = Integer.parseInt(strs[2]);
        	if (size == 0 && repeat == 0 && operation == 0) {
        		System.exit(0);
        	}
        	cat.init(size);
        	for (int i = 0; i < operation; ++i) {
        		strs = in.nextLine().split("\\s+");
        		char type = strs[0].charAt(0);
        		if (type == 'g') {
        			int num = Integer.parseInt(strs[1]);
        			cat.matrix[0][num]++;
        		} else if (type == 'e') {
        			int num = Integer.parseInt(strs[1]);
        			for (int j = 0; j <= size; ++j) {
        				cat.matrix[j][num] = 0;
        			}
        		} else {
        			int col1 = Integer.parseInt(strs[1]);
        			int col2 = Integer.parseInt(strs[2]);
        			if (col1 == col2) {
        				continue;
        			}
        			cat.swap(col1, col2, size);
        		}
        	}
        	if (repeat == 0) {
        		for (int i = 1; i <= size; ++i) {
        			if (i == 1) {
        				System.out.print("0");
        			} else {
        				System.out.print(" 0");
        			}
        		}
        		System.out.println("");
        	} else {
        		cat.pow(repeat, size);
        		for (int i = 1; i <= size; ++i) {
        			if (i == 1) {
        				System.out.print(cat.matrix[0][i]);
        			} else {
        				System.out.print(" " + cat.matrix[0][i]);
        			}
        		}
        		System.out.println("");
        	}
        }
    }
}