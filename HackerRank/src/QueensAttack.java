import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class QueensAttack {

    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
    	Board board = new Board(n);
    	Arrays.stream(obstacles).forEach(e -> board.addObstacle(e));
    	int[][] directions = {
    			{1, 1},
    			{0, 1},
    			{-1, 1},
    			{-1, 0},
    			{-1, -1},
    			{0, -1},
    			{1, -1},
    			{1, 0}
			};

    	int sum = 0;
    	for (int[] d : directions) {
    		int[] pos = move(new int[] {r_q - 1, c_q - 1}, d);
    		while(board.canPut(pos)) {
    			sum += 1;
    			pos = move(pos, d);
    		}
    	};
    	return sum;
    }

    static class Board {
    	private final long size;
    	private Set<Long> obstacles;
    	public Board(int size) {
    		this.size = size;
    		this.obstacles = new HashSet<Long>();
    	}
    	public void addObstacle(int[] obstacle) {
    		this.obstacles.add((obstacle[0] - 1) * size + obstacle[1] - 1);
    	}
    	public boolean canPut(int[] pos) {
        	if (0 > pos[0] || pos[0] >= size || 0 > pos[1] || pos[1] >= size) return false;
        	return !obstacles.contains(pos[0] * size + pos[1]);
    	}

    }

    static int[] move(int[] pos, int[] direction) {
    	return new int[] { pos[0] + direction[0], pos[1] + direction[1] };
    }

    static boolean canReach(int n, int[] pos, Set<Integer> obstacles) {
    	if (0 > pos[0] || pos[0] >= n || 0 > pos[1] || pos[1] >= n) return false;
    	return !obstacles.contains(pos[0] * n + pos[1]);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);
        System.out.println(result);

        scanner.close();
    }
}
