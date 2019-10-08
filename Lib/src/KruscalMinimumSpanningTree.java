import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class KruscalMinimumSpanningTree {
	public static class UnionFind {
		private int[] par;

		public UnionFind(int n)
		{
			par = new int[n];
			init(n);
		}

		private void init(int n)
		{
			for (int i = 0; i < n; ++i)
			{
				par[i] = i;
			}
		}

		int root(int x)
		{
			if (par[x] == x)
			{
				return x;
			} else
			{
				return par[x] = root(par[x]);
			}
		}

		public boolean same(int x, int y)
		{
			return root(x) == root(y);
		}

		public void unite(int x, int y)
		{
			x = root(x);
			y = root(y);
			if (x == y) return;
			par[x] = y;
		}

		public IntStream parent() {
			return Arrays.stream(par).map(p -> root(p));
		}

	}

	public static class Edge implements Comparable<Edge> {
		private int v;
		private final int w;
		private final int weight;

		Edge(int v, int w, int weight)
		{
			this.v = v;
			this.w = w;
			this.weight = weight;
		}

		int either()
		{
			return this.v;
		}

		int other(int v)
		{
			return v == this.v
					? this.w
					: this.w;
		}

		int weight()
		{
			return this.weight;
		}

		@Override
		public int compareTo(Edge that) {
			return this.weight < that.weight
					? -1
					: this.weight > that.weight
						? +1
						: 0;
		}

	}

	public static class EdgeWeightedGraph
	{
	   public final int V;
	   private final HashSet<Edge>[] adj;
	   private PriorityQueue<Edge> edges;
	   public EdgeWeightedGraph(int V)

	   {
	      this.V = V;
	      adj = (HashSet<Edge>[]) new HashSet[V];
	      for (int v = 0; v < V; v++)
	         adj[v] = new HashSet<Edge>();
	      edges = new PriorityQueue<Edge>();
	}
	   public void addEdge(Edge e)
	   {
	      int v = e.either(), w = e.other(v);
	      adj[v].add(e);
	      adj[w].add(e);
	      edges.add(e);
	   }
	   public Iterable<Edge> adj(int v)
	   {
		   return adj[v];
	   }

	   public PriorityQueue<Edge> edges()
	   {
		   return this.edges;
	   }
	}

	public static class KruskalMST {
		private Queue<Edge> mst = new LinkedList<Edge>();

		public KruskalMST(EdgeWeightedGraph g)
		{
			PriorityQueue<Edge> pq = g.edges();
			UnionFind uf = new UnionFind(g.V);
			while(!pq.isEmpty() && mst.size() < (g.V - 1))
			{
				Edge e = pq.poll();
				int v = e.either();
				int w = e.other(v);
				if (!uf.same(v, w))
				{
					uf.unite(v, w);
					mst.add(e);
				}
			}
		}

		public Iterable<Edge> edges()
		{
			return mst;
		}

		public int overallWeight() {
			return mst.stream().mapToInt(e -> e.weight()).sum();
		}

	}


    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
    	EdgeWeightedGraph g = new EdgeWeightedGraph(gNodes);
    	Iterator<Integer> iterFrom = gFrom.iterator();
    	Iterator<Integer> iterTo = gTo.iterator();
    	Iterator<Integer> iterWeight = gWeight.iterator();
    	while(iterFrom.hasNext()) {
    		g.addEdge(new Edge(iterFrom.next() - 1, iterTo.next() - 1, iterWeight.next()));
    	}
    	KruskalMST mst = new KruskalMST(g);
    	return mst.overallWeight();
    }

}