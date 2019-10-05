import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;
public class BfsShortestPath {

	public static class Graph {
		private int V;
		private HashSet<Integer>[] adj;

		public Graph(int V)
		{
			this.V = V;
			initializeAdj(V);
		}

		private void initializeAdj(int V) {
			this.adj = new HashSet[V];
			for (int v = 0; v < V; ++v)
				adj[v] = new HashSet<Integer>();
		}

		void addEdge(int v, int w)
		{
			adj[v].add(w);
			adj[w].add(v);
		}

		public Iterable<Integer> adj(int v)
		{
			return this.adj[v];
		}

		public int V()
		{
			return this.V;
		}

		public int E()
		{
			// Streamで書いた方がいいかも
			int e = 0;
			for (HashSet<Integer> v : adj)
			{
				e += v.size();
			}
			return e;
		}

		void show()
		{
			for (int v = 0; v < this.V; ++v)
				for (int w : this.adj(v))
					System.out.println(v + "-" + w);
		}
	}


	public static class BreadthFirstPaths {
		private boolean[] marked;
		private int[] edgeTo;
		private final int s;

		public BreadthFirstPaths(Graph g, int s)
		{
			this.marked = new boolean[g.V()];
			this.edgeTo = new int[g.V()];
			this.s = s;
			bfs(g, s);
		}

		private void bfs(Graph g, int s)
		{
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(s);
			marked[s] = true;
			while (!q.isEmpty())
			{
				int v = q.poll();
				for (int w : g.adj(v))
				{
					if (!marked[w])
					{
						q.add(w);
						marked[w] = true;
						edgeTo[w] = v;
					}
				}
			}
		}

		public boolean hasPathTo(int v)
		{
			return marked[v];
		}

		public Iterable<Integer> pathTo(int v)
		{
		   if (!hasPathTo(v)) return null;
		   Stack<Integer> path = new Stack<Integer>();
		   for (int x = v; x != this.s; x = edgeTo[x])
		      path.push(x);
		   path.push(this.s);
		   return path;
		}

		public int distanceTo(int v) {
			if (!marked[v]) return -1;
			int ret = 0;
			for (int i : pathTo(v)) ++ret;
			return (ret - 1) * 6;
		}

	}

    // Complete the bfs function below.
	static int[] bfs(int n, int m, int[][] edges, int s) {
		Graph graph = new Graph(n);
		for (int[] e : edges) graph.addEdge(e[0] - 1, e[1] - 1);
		BreadthFirstPaths bfs = new BreadthFirstPaths(graph, s - 1);

		return IntStream.range(0, n).filter(i -> i != s - 1).map(i -> bfs.distanceTo(i)).toArray();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

            for (int i = 0; i < result.length; i++) {
            	System.out.print(result[i]);

                if (i != result.length - 1) {
                	System.out.print(" ");
                }
            }
            System.out.println("");
        }

        scanner.close();
    }
}
