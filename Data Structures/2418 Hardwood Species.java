import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		int rst = 0;
		String name;
		while ((name = cin.readLine()) != null) {
			rst++;
			if (map.containsKey(name)) {
				map.put(name, map.get(name) + 1);
			} else {
				map.put(name,  1);
			}
		}
		Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> entry = it.next();
			System.out.printf("%s %.4f\n", entry.getKey(), entry.getValue() * 100.0 / rst);
		}
	}
}