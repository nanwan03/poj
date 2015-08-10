import java.util.*;

class Point {
	int x;
	int y;
	int level;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.level = 0;
	}
	Point(int x, int y, int level) {
		this.x = x;
		this.y = y;
		this.level = level;
	}
}
public class Main {
	private static int[][] dir = new int[][]{{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2}};
	private static int[][] map = new int[305][305];
	private static int[][] levels = new int[305][305];
	private static Queue<Point> queue1 = new LinkedList<Point>();
	private static Queue<Point> queue2 = new LinkedList<Point>();
	private static int size = 0;
    public static void main(String[] args) {   
        Scanner in = new Scanner(System.in);   
        int testcase = in.nextInt();
        while (testcase-- > 0) {
        	init();
        	size = in.nextInt();
        	Point start = new Point(in.nextInt(), in.nextInt());
        	Point end = new Point(in.nextInt(), in.nextInt());
        	int rst = dbfs(start, end);
        	System.out.println(rst);
        }
    }
    private static void init() {
    	for (int i = 0; i < levels.length; ++i) {
    		Arrays.fill(levels[i], 0);
    		Arrays.fill(map[i], 0);
    		queue1.clear();
    		queue2.clear();
    	}
    }
    private static int dbfs(Point start, Point end) {
    	if (start.x == end.x && start.y == end.y) {
    		return 0;
    	}
    	map[start.x][start.y] = 1;
    	map[end.x][end.y] = 2;
    	queue1.offer(start);
    	queue2.offer(end);
    	while (!queue1.isEmpty() || !queue2.isEmpty()) {
    		if (queue1.size() != 0 && queue1.size() < queue2.size()) {
    			Point head = queue1.poll();
    			int level = head.level + 1;
    			for (int i = 0; i < 8; ++i) {
    				int dx = head.x + dir[i][0];
    				int dy = head.y + dir[i][1];
    				if (dx >= 0 && dx < size && dy >= 0 && dy < size && map[dx][dy] != 1) {
    					if (map[dx][dy] == 2) {
    						return (level + levels[dx][dy]);
    					}
    					map[dx][dy] = 1;
    					levels[dx][dy] = level;
    					queue1.offer(new Point(dx, dy, level));
    				}
    			}
    		} else {
	    		if (!queue2.isEmpty()) {
	    			Point tail = queue2.poll();
	    			int level = tail.level + 1;
	    			for (int i = 0; i < 8; ++i) {
	    				int dx = tail.x + dir[i][0];
	    				int dy = tail.y + dir[i][1];
	    				if (dx >= 0 && dx < size && dy >= 0 && dy < size && map[dx][dy] != 2) {
	    					if (map[dx][dy] == 1) {
	    						return (level + levels[dx][dy]);
	    					}
	    					map[dx][dy] = 2;
	    					levels[dx][dy] = level;
	    					queue2.offer(new Point(dx, dy, level));
	    				}
	    			}
	    		}
    		}
    	}
    	return 0;
    }
}