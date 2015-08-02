import java.util.*;

class Road {
	int dest;
	int length;
	int cost;
	Road(int dest, int length, int cost) {
		this.dest = dest;
		this.length = length;
		this.cost = cost;
	}
}
public class Main {
	private static final int MAX = 0x3f3f3f3f;
	private static int rst = MAX;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int totalMoney = in.nextInt();
		int cityNumber = in.nextInt();
		int roadNumber = in.nextInt();
		boolean[] visited = new boolean[cityNumber + 1];
		int[][] minLength = new int[cityNumber + 1][totalMoney + 1];
		List<List<Road>> map = new ArrayList<List<Road>>();
		for (int i = 0; i <= cityNumber; ++i) {
			map.add(new ArrayList<Road>());
		}
		for (int i = 0; i < roadNumber; ++i) {
			int start = in.nextInt();
			Road r = new Road(in.nextInt(), in.nextInt(), in.nextInt());
			if (r.dest != start) {
				map.get(start).add(r);
			}
		}
		for (int i = 0; i <= cityNumber; ++i) {
			for (int j = 0; j <= totalMoney; ++j) {
				minLength[i][j] = MAX;
			}
		}
		visited[1] = true;
		helper(map, visited, minLength, 0, 0, 1, totalMoney, cityNumber);
		rst = (rst == MAX) ? -1 : rst;
		System.out.println(rst);
	}
	private static void helper(List<List<Road>> map, boolean[] visited, int[][] minLength, int curLen, int curCost, int curLoc, int totalMoney, int dest) {
		if (curLoc == dest) {
			rst = Math.min(curLen, rst);
			return;
		}
		for (int i = 0; i < map.get(curLoc).size(); ++i) {
			int nextLoc = map.get(curLoc).get(i).dest;
			if (!visited[nextLoc]) {
				int cost = curCost + map.get(curLoc).get(i).cost;
				if (cost > totalMoney) {
					continue;
				}
				if (curLen + map.get(curLoc).get(i).length >= rst ||
						curLen + map.get(curLoc).get(i).length >= minLength[nextLoc][cost]) {
					continue;
				}
				curLen += map.get(curLoc).get(i).length;
				curCost += map.get(curLoc).get(i).cost;
				minLength[nextLoc][cost] = curLen;
				visited[nextLoc] = true;
				helper(map, visited, minLength, curLen, curCost, nextLoc, totalMoney, dest);
				visited[nextLoc] = false;
				curLen -= map.get(curLoc).get(i).length;
				curCost -= map.get(curLoc).get(i).cost;
			}
		}
	}
}