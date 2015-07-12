import java.util.ArrayList;  
import java.util.LinkedList;  
import java.util.List;  
import java.util.Queue;  
import java.util.Scanner;  
      
public class Main {   
    public static final double INF = 1e8; 
    private static final double EPSILON = 1e-8; 
    public static void main(String[] args) {  
        Scanner in = new Scanner(System.in);  
        int testcase = in.nextInt();  
        while (testcase-- > 0) { 
            int row = in.nextInt();  
            int col = in.nextInt();  
            int number = in.nextInt();   
            double[][] cap = new double[row + col +2][row + col + 2];
            int[] dist = new int[row + col + 2];
            List<List<Integer>> map = new ArrayList<List<Integer>>();  
            for(int i = 0; i < row + col +2; ++i) {  
                map.add(new ArrayList<Integer>()); 
            }  
            int start = 0;  
            int end = row + col + 1;  
            for(int i = 1; i <= row; ++i){  
                cap[0][i] = Math.log10(in.nextDouble());  
                map.get(0).add(i);  
                map.get(i).add(0);  
            }  
            for(int i = 1; i <= col; ++i){  
                cap[i + row][end] = Math.log10(in.nextDouble());  
                map.get(i + row).add(end);  
                map.get(end).add(i + row);  
            }  
              
            for(int i = 0; i < number; ++i){  
                int a = in.nextInt();  
                int b = in.nextInt();  
                cap[a][b + row] = INF;  
                map.get(a).add(b + row);  
                map.get(b + row).add(a);  
            }  
            double minCost = 0;
            while(BFS(start, end, map, cap, dist)) {
                minCost += DFS(start, end, INF, map, cap, dist);
            } 
            System.out.printf("%.4f\n",Math.pow(10, minCost));
        }  
    }
    public static double DFS(int start, int end, double flow, List<List<Integer>> map, double[][] cap, int[] dist) {          
		if(start == end) {  
		    return flow;
		}
		double subFlow = 0;   
		for(int i = 0; i < map.get(start).size(); ++i){  
		    int j = map.get(start).get(i);  
		    if(dist[start] + 1 == dist[j] && eps(cap[start][j]) > 0){  
		        double adjustFlow = DFS(j, end, Math.min(flow-subFlow, cap[start][j]), map, cap, dist);  
		        subFlow += adjustFlow;
		        cap[start][j] -= adjustFlow;  
		        cap[j][start] += adjustFlow;  
		    }  
		}  
		  
		return subFlow;  
    }
    public static boolean BFS(int start, int end, List<List<Integer>> map, double[][] cap, int[] dist) {  
        Queue<Integer> queue = new LinkedList<Integer>();  
        boolean visit[] = new boolean[dist.length];  
        queue.offer(start);  
        visit[start] = true;  
        dist[start] = 1;  
        while(!queue.isEmpty()){  
            int v = queue.poll();  
            for(int i = 0; i < map.get(v).size(); ++i){  
                int j = map.get(v).get(i);  
                if(!visit[j] && eps(cap[v][j]) > 0){  
                    visit[j] = true;  
                    dist[j] = dist[v] + 1;  
                    queue.add(j);  
                }  
            }  
        }   
        return visit[end];  
    }
    public static double eps(double d) {  
        return d < EPSILON ? 0 : d;  
    }
}