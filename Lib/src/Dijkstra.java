import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	public static void main(String[] args) {
	    @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt();
//	    sc.nextLine();

	    EdgeWeightedDigraph g = new EdgeWeightedDigraph(n);
	    for (int i = 0; i < n; ++i)
	    {
	    	int u = sc.nextInt();
	    	int k = sc.nextInt();
	    	for (int j = 0; j < k; ++j)
	    	{
	    		int v = sc.nextInt();
		    	int c = sc.nextInt();
		    	g.addEdge(new DirectedEdge(u, v, c));
	    	}
	    }

	    ShortestPaths path = new ShortestPaths(g, 0);
	    for (int i = 0; i < n; ++i)
	    {
	    	System.out.println(i + " " + path.distTo(i));
	    }

	    for (int i = 0; i < n; ++i)
	    {
	    	System.out.println(path.pathTo(i));
	    }


	}

	public static class DirectedEdge implements Comparable<DirectedEdge> {
		private final int from;
		private final int to;
		private final int weight;

		public DirectedEdge(int from, int to, int weight)
		{
			this.from = from;
			this.to= to;
			this.weight = weight;
		}

		public int from()
		{
			return from;
		}

		public int to()
		{
			return to;
		}

		public int weight()
		{
			return weight;
		}

		@Override
		public int compareTo(DirectedEdge that) {
			return this.weight < that.weight
					? -1
					: this.weight > that.weight
						? +1
						: 0;
		}

	}

	public static class EdgeWeightedDigraph {
		private final int V;
		private final HashSet<DirectedEdge>[] adj;

		@SuppressWarnings("unchecked")
		public EdgeWeightedDigraph(int v)
		{
			this.V = v;
			adj = (HashSet<DirectedEdge>[])new HashSet[V];
			for (int i = 0; i < V; ++i)
				adj[i] = new HashSet<DirectedEdge>();
		}

		public void addEdge(DirectedEdge e)
		{
			int v = e.from();
			adj[v].add(e);
		}

		public Iterable<DirectedEdge> adj(int v)
		{
			return adj[v];
		}

		public int V()
		{
			return V;
		}

		public int E()
		{
			return Arrays.stream(adj)
					.mapToInt(h -> h.size())
					.sum();
		}

		@SuppressWarnings("unchecked")
		public Iterable<DirectedEdge> edges()
		{
			return (Iterable<DirectedEdge>)Arrays.stream(adj)
					.flatMap(e -> e.stream())
					.iterator();

		}
	}

	public static class Node implements Comparable<Node> {
		private final int index;
		private final int distance;

		public Node(int i, int dist)
		{
			this.index = i;
			this.distance = dist;
		}

		public int Index()
		{
			return this.index;
		}

		@Override
		public int compareTo(Node that) {
			return this.distance < that.distance
					? -1
					: this.distance > that.distance
						? +1
						: 0;
		}

	}

	public static class ShortestPaths {
		private boolean[] marked;
		private int[] distance;
		private int[] parent;
		private PriorityQueue<Node> pq;

		public ShortestPaths(EdgeWeightedDigraph g, int start)
		{
			distance = new int[g.V()];
			parent = new int[g.V()];
			marked = new boolean[g.V()];
			pq = new PriorityQueue<Node>();
			for (int i = 0; i < g.V(); ++i)
			{
				distance[i] = Integer.MAX_VALUE;
			}
			distance[start] = 0;
			parent[start] = start;
			pq.add(new Node(start, 0));
			while (!pq.isEmpty())
			{
				Node node = pq.poll();
				if (!marked[node.index])
				{
					int visiting = node.index;
					marked[visiting] = true;
					for (DirectedEdge edge : g.adj(visiting))
					{
						if (marked[edge.to]) continue;
						if (distance[visiting] + edge.weight < distance[edge.to])
						{
							distance[edge.to] = distance[visiting] + edge.weight;
							parent[edge.to] = visiting;
							pq.add(new Node(edge.to, distance[edge.to]));
						}
					}
				}
			}

		}

		public int distTo(int v)
		{
			return distance[v];
		}

		public Iterable<Integer> pathTo(int v)
		{
			LinkedList<Integer> path = new LinkedList<Integer>();
			int bc = v;
			while (!(parent[bc] == bc))
			{
				path.add(bc);
				bc = parent[bc];
			}
			path.add(bc);
			return path;
		}
	}

}
