import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = in.nextInt();
		while (testcase-- > 0) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			int size = in.nextInt();
			for (int i = 1; i < size; ++i) {
				int parent = in.nextInt();
				int child = in.nextInt();
				map.put(child, parent);
			}
			int u = in.nextInt();
			int v = in.nextInt();
			solve(map, u, v);
		}
	}
	private static void solve(Map<Integer, Integer> map, int u, int v) {
		int distU = 0;
		int distV = 0;
		int startU = u;
		int startV = v;
		while (map.containsKey(u)) {
			distU++;
			u = map.get(u);
		}
		u = startU;
		while (map.containsKey(v)) {
			distV++;
			v = map.get(v);
		}
		v = startV;
		int diff = Math.abs(distU - distV);
		while (diff-- > 0) {
			if (distU > distV) {
				u = map.get(u);
			} else {
				v = map.get(v);
			}
		}
		while (u != v && map.containsKey(u) && map.containsKey(v)) {
			u = map.get(u);
			v = map.get(v);
		}
		System.out.println(u);
	}
}