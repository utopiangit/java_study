import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Stream;

public class KnightL {

    // Complete the knightlOnAChessboard function below.
    static int[][] knightlOnAChessboard(int n) {
    	int[][] ans = new int[n - 1][n - 1];
    	for (int i = 0; i < n - 1; ++i) {
    		for (int j = i; j < n - 1; ++j) {
    			int ansij = solve(n, i + 1, j + 1);
    			ans[i][j] = ansij;
    			ans[j][i] = ansij;
    		}
    	}

    	return ans;
    }

    static int solve(int n, int a, int b) {
    	int[][] dist = new int[n][n];
    	for (int[] d : dist) Arrays.fill(d, Integer.MAX_VALUE);
    	Queue<Integer[]> queue = new LinkedList<>();
    	dist[0][0] = 0;
    	queue.add(new Integer[] {0, 0});
    	while(!queue.isEmpty()) {
    		Integer[] visiting = queue.poll();
    		move(n, visiting[0], visiting[1], a, b).forEach(next -> {
    			if (dist[next[0]][next[1]] == Integer.MAX_VALUE) {
    				dist[next[0]][next[1]] = dist[visiting[0]][visiting[1]] + 1;
    				queue.add(next);
    			}
    		});

    		if (dist[n-1][n-1] < Integer.MAX_VALUE) {
    			return dist[n-1][n-1];
    		}
    	}

		return -1;
    }

    static Stream<Integer[]> move(int n, int x, int y, int a, int b) {
    	Integer[][] candidates = {
    			{x + a, y + b},
    			{x + a, y - b},
    			{x - a, y + b},
    			{x - a, y - b},
    			{x + b, y + a},
    			{x + b, y - a},
    			{x - b, y + a},
    			{x - b, y - a}
    	};

    	return Arrays.stream(candidates)
    			.filter(pos -> 0 <= pos[0] && pos[0] < n && 0 <= pos[1] && pos[1] < n);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] result = knightlOnAChessboard(n);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
//                bufferedWriter.write(String.valueOf(result[i][j]));
            	System.out.print(result[i][j]);

                if (j != result[i].length - 1) {
//                    bufferedWriter.write(" ");
                	System.out.print(" ");
                }
            }

            if (i != result.length - 1) {
//                bufferedWriter.write("\n");
            	System.out.print("\n");
            }
        }

//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
