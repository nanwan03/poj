import java.util.*; 

class Node{  
    int x;  
    int y;  
    public Node(int x, int y) {    
        this.x = x;  
        this.y = y;  
    }  
}

public class Main {   
    public static void main(String[] args) {  
        Scanner in = new Scanner(System.in);  
        while(true){  
            int row = in.nextInt();  
            int col = in.nextInt();  
            if(row == 0 && col == 0) {
            	System.exit(0);
            }
            char map[][] = new char[row][col];  
            int number = 0;
            for(int i = 0; i < row; ++i){  
                map[i] = in.next().toCharArray();  
                for(int j = 0; j < col; ++j){  
                    if(map[i][j]=='H'){  
                        number++;      
                    }  
                }  
            } 
            Node houses[] = new Node[number];  
            Node men[] = new Node[number];
            int hNum = 0;  
            int mNum = 0;  
            for(int i = 0; i < row; ++i){  
                for(int j = 0; j < col; ++j){  
                    if(map[i][j] == 'H')  
                        houses[hNum++] = new Node(i,j);  
                    if(map[i][j] == 'm')  
                        men[mNum++] = new Node(i,j);  
                }  
            }  
            int[][] cost = new int[(number + 1) * 2][(number + 1) * 2];  
            int[][] cap = new int[(number + 1) * 2][(number + 1) * 2];  
            int[][] flow = new int[(number + 1) * 2][(number + 1) * 2];  
            int start = 0;
            int end = 2 * number + 1;  
            for(int i = 1; i <= number; ++i){  
                cap[start][i] = 1;  
                cap[i + number][end] = 1;  
            }  
            for(int i = 0; i < number; ++i){  
                for(int j = 0; j < number; ++j){  
                    cap[i + 1][j + number + 1] = 1;  
                    cost[i + 1][j + number + 1] = Math.abs(men[i].x - houses[j].x) + Math.abs(men[i].y - houses[j].y);  
                    cost[j + number + 1][i + 1] = -cost[i + 1][j + number + 1];  
                }  
            }      
            minCost(number, start, end, cost, cap, flow);  
            int minCostFlow = 0;  
            for(int i = 1; i <= number; ++i){  
                for(int j = number + 1; j <= 2 * number; ++j)  
                    minCostFlow += cost[i][j] * flow[i][j];  
            }
            System.out.println(minCostFlow);  
        }  
    }  
  
    public static void minCost(int number, int start, int end, int[][] cost, int[][] cap, int[][] flow) {      
        int dist[] = new int[(number + 1) * 2];  
        int pre[] = new int[(number + 1) * 2];  
        boolean visit[] = new boolean[(number + 1) * 2];  
        Queue<Integer> queue = new LinkedList<Integer>();  
        while(true) {  
            Arrays.fill(dist, 0x3f3f3f3f);
            Arrays.fill(pre, -1);
            queue.add(start);  
            visit[start] = true;  
            dist[start] = 0;  
            while(!queue.isEmpty()){  
                int v = queue.poll();  
                visit[v] = false;  
                for(int i = 1; i < 2 * number + 2; ++i){  
                    if(flow[v][i] < cap[v][i] && dist[i] > dist[v] + cost[v][i]) {  
                        dist[i] = dist[v] + cost[v][i];  
                        pre[i] = v;  
                        if(!visit[i]){  
                            queue.add(i);  
                            visit[i] = true;  
                        }  
                    }  
                }  
            }  
            if(pre[end]==-1)  
                break;  
            int minFlow = 0x3f3f3f3f;  
            for(int u = end; pre[u] != -1; u = pre[u])  
                minFlow = Math.min(minFlow, cap[pre[u]][u] - flow[pre[u]][u]);  
            for(int u = end; pre[u] != -1; u = pre[u]){  
                flow[pre[u]][u] += minFlow;  
                flow[u][pre[u]] -= minFlow;  
            }
        }
    }
}