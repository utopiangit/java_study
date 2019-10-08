import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS {
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

}
