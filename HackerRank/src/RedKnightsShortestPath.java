import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Stream;

public class RedKnightsShortestPath {

    // Complete the printShortestPath function below.
    static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
        // Print the distance along with the sequence of moves.
    	int[][] dist = new int[n][n];

    	for (int[] d : dist) Arrays.fill(d, Integer.MAX_VALUE);
    	Queue<Integer[]> queue = new LinkedList<>();
    	Integer[][][] from = new Integer[n][n][2];
    	String[][] way = new String[n][n];
    	dist[i_start][j_start] = 0;
    	way[i_start][j_start] = "start";
    	queue.add(new Integer[] {i_start, j_start});
    	while(!queue.isEmpty()) {
    		Integer[] visiting = queue.poll();
    		move(n, visiting[0], visiting[1]).forEach(entry -> {
    			Integer[] next = entry.to;
    			if (dist[next[0]][next[1]] == Integer.MAX_VALUE) {
    				dist[next[0]][next[1]] = dist[visiting[0]][visiting[1]] + 1;
    				queue.add(next);
    				from[next[0]][next[1]] = visiting;
    				way[next[0]][next[1]] = entry.way();
    			}
    		});

    		if (dist[i_end][j_end] < Integer.MAX_VALUE) {
    			System.out.println(dist[i_end][j_end]);
    			List<String> path = new LinkedList<>();
    			Integer[] pos = new Integer[] {i_end, j_end};
    			while (way[pos[0]][pos[1]] != "start") {
    				path.add(way[pos[0]][pos[1]]);
    				pos = from[pos[0]][pos[1]];
    			}
    			Collections.reverse(path);
    			path.stream().forEach(p -> {
    				System.out.print(p);
    				System.out.print(" ");
    			});
    			return;
//    			return dist[n-1][n-1];
    		}
    	}

    	System.out.println("Impossible");
		return;

    }

//    static Stream<Entry<String, Integer[]>> move(int n, int x, int y) {
//    	Map<String, Integer[]> candidates = new HashMap<>();
//    	candidates.put("UL", new Integer[] {x - 2, y - 1});
//    	candidates.put("UR", new Integer[] {x - 2, y + 1});
//    	candidates.put("R",  new Integer[] {x, y + 2});
//    	candidates.put("LR", new Integer[] {x + 2, y + 1});
//    	candidates.put("LL", new Integer[] {x + 2, y - 1});
//    	candidates.put("L",  new Integer[] {x, y - 2});
//
//    	return candidates.entrySet()
//    			.stream()
//    			.filter(entry -> {
//    				Integer[] pos = entry.getValue();
//    				return 0 <= pos[0] && pos[0] < n && 0 <= pos[1] && pos[1] < n;
//    			});
//    }

  static Stream<Move> move(int n, int x, int y) {
	  List<Move> candidates = new LinkedList<>();
	  candidates.add(new Move(new Integer[] {x - 2, y - 1}, "UL"));
	  candidates.add(new Move(new Integer[] {x - 2, y + 1}, "UR"));
	  candidates.add(new Move(new Integer[] {x, y + 2}, "R"));
	  candidates.add(new Move(new Integer[] {x + 2, y + 1}, "LR"));
	  candidates.add(new Move(new Integer[] {x + 2, y - 1}, "LL"));
	  candidates.add(new Move(new Integer[] {x, y - 2}, "L"));

	return candidates
				.stream()
				.filter(entry -> entry.isValid(n));
}


    static class Move {
    	private final Integer[] to;
    	private final String way;
    	Move(Integer[] to, String way) {
//    		this.from = from;
    		this.to = to;
    		this.way = way;
    	}
    	boolean isValid(int n) {
    		return 0 <= to[0] && to[0] < n && 0 <= to[1] && to[1] < n;
    	}

    	String way() {
    		return way;
    	}

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] i_startJ_start = scanner.nextLine().split(" ");

        int i_start = Integer.parseInt(i_startJ_start[0]);

        int j_start = Integer.parseInt(i_startJ_start[1]);

        int i_end = Integer.parseInt(i_startJ_start[2]);

        int j_end = Integer.parseInt(i_startJ_start[3]);

        printShortestPath(n, i_start, j_start, i_end, j_end);

        scanner.close();
    }
}
