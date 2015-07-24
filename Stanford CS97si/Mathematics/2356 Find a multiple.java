import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = Integer.parseInt(in.nextLine());
		int[] input = new int[num + 1];
		int[] sum = new int[num + 1];
		input[0] = 0;
		for (int i = 1; i <= num; ++i) {
			input[i] = in.nextInt();
			sum[i] = (sum[i - 1] + input[i] % num) % num;
		}
		solve(input, sum);
	}
	private static void solve(int[] input, int[] sum) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 1; i < input.length; ++i) {
			if (sum[i] == 0) {
				System.out.println(i);
				for (int j = 1; j <= i; ++j) {
					System.out.println(input[j]);
				}
				return;
			} else {
				if(map.containsKey(sum[i])) {
					int index = map.get(sum[i]);
					System.out.println(i - index);
					for (int j = index + 1; j <= i; ++j) {
						System.out.println(input[j]);
					}
					return;
				}
				map.put(sum[i], i);
			}
		}
	}