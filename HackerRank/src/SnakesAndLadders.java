import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SnakesAndLadders {

	public static class Digraph {
		private int V;
		private HashSet<Integer>[] adj;

		public Digraph(int V)
		{
			this.V = V;
			initializeAdj(V);
		}

		void initializeAdj(int V) {
			this.adj = new HashSet[V];
			for (int v = 0; v < V; ++v)
				adj[v] = new HashSet<Integer>();
		}

		void addEdge(int v, int w)
		{
			adj[v].add(w);
		}

		public HashSet<Integer> adj(int v)
		{
			return this.adj[v];
		}

		public int V()
		{
			return this.V;
		}

		public int E()
		{
			return Arrays.stream(adj)
				.mapToInt(e -> e.size())
				.sum();
		}

		public Digraph reverse()
		{
			Digraph g = new Digraph(this.V());
			for (int v = 0; v < this.V(); ++v)
			{
				for (int w : this.adj(v))
				{
					g.addEdge(w, v);
				}
			}
			return g;
		}
		void show()
		{
			for (int v = 0; v < this.V; ++v)
				for (int w : this.adj(v))
					System.out.println(v + "-" + w);
		}

	}


    // Complete the quickestWayUp function below.
    static int quickestWayUp(int[][] ladders, int[][] snakes) {
    	int N = 100;
    	Digraph g = new Digraph(N);
    	for (int[] ladder : ladders) g.addEdge(ladder[0] - 1, ladder[1] - 1);
    	for (int[] snake : snakes) g.addEdge(snake[0] - 1, snake[1] - 1);

    	int[] dist = new int[N];
    	Arrays.fill(dist, -1);
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(0);
    	dist[0] = 0;
		while (!q.isEmpty())
		{
			int v = q.poll();
			for (int dice : dices()) {
				int visiting = Math.min(N - 1, v + dice);
				if (!g.adj(visiting).isEmpty()) {
					visiting = g.adj(visiting).iterator().next();
				}

				if (dist[visiting] == -1)
				{
					q.add(visiting);
					dist[visiting] = dist[v] + 1;
				}
				if (visiting == N - 1) break;
			}
		}
		return dist[N - 1];
    }

    private static int [] dices() {
    	return new int[] {1,2,3,4,5,6};
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] ladders = new int[n][2];

            for (int i = 0; i < n; i++) {
                String[] laddersRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int laddersItem = Integer.parseInt(laddersRowItems[j]);
                    ladders[i][j] = laddersItem;
                }
            }

            int m = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] snakes = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] snakesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int snakesItem = Integer.parseInt(snakesRowItems[j]);
                    snakes[i][j] = snakesItem;
                }
            }

            int result = quickestWayUp(ladders, snakes);
            System.out.println(result);

        }

        scanner.close();
    }
}
