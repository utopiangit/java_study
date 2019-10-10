import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class DijkstraShortestPath {

	static class Scanner {
	    private final InputStream in = System.in;
	    private final byte[] buffer = new byte[1024];
	    private int ptr = 0;
	    private int buflen = 0;

	    private boolean hasNextByte() {
	        if (ptr < buflen) {
	            return true;
	        } else {
	            ptr = 0;
	            try {
	                buflen = in.read(buffer);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            if (buflen <= 0) {
	                return false;
	            }
	        }
	        return true;
	    }

	    private int readByte() {
	        return hasNextByte() ? buffer[ptr++] : -1;
	    }

	    private boolean isPrintableChar(int c) {
	        return 33 <= c && c <= 126;
	    }

	    private void skipUnprintable() {
	        while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
	    }

	    public boolean hasNext() {
	        skipUnprintable();
	        return hasNextByte();
	    }

	    public String next() {
	        if (!hasNext()) throw new NoSuchElementException();
	        StringBuilder sb = new StringBuilder();
	        int b = readByte();
	        while (isPrintableChar(b)) {
	            sb.appendCodePoint(b);
	            b = readByte();
	        }
	        return sb.toString();
	    }

	    public int nextInt() {
	        return (int) nextLong();
	    }

	    public long nextLong() {
	        if (!hasNext()) throw new NoSuchElementException();
	        long n = 0;
	        boolean minus = false;
	        int b = readByte();
	        if (b == '-') {
	            minus = true;
	            b = readByte();
	        }
	        if (b < '0' || '9' < b) {
	            throw new NumberFormatException();
	        }
	        while (true) {
	            if ('0' <= b && b <= '9') {
	                n *= 10;
	                n += b - '0';
	            } else if (b == -1 || !isPrintableChar(b)) {
	                return minus ? -n : n;
	            } else {
	                throw new NumberFormatException();
	            }
	            b = readByte();
	        }
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


	public static class DirectedEdge {
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
		public boolean equals(Object o) {
			if (!(o instanceof DirectedEdge)) return false;
			DirectedEdge de = (DirectedEdge) o;
			return this.from == de.from && this.to == de.to;
		}

		@Override
		public int hashCode() {
			int result = Integer.hashCode(this.from);
			result = 31 * result + Integer.hashCode(this.to);
			return result;
		}
	}

	public static class EdgeWeightedDigraph {
		private final int V;
//		private final HashSet<DirectedEdge>[] adj;
		private final HashMap<Integer, Integer>[] adj;

		public EdgeWeightedDigraph(int v)
		{
			this.V = v;
			adj = (HashMap<Integer, Integer>[])new HashMap[V];
			for (int i = 0; i < V; ++i)
				adj[i] = new HashMap<Integer, Integer>();
		}

		public void addEdge(int from, int to, int weight)
		{
			if (adj[from].containsKey(to)) {
				adj[from].put(to, Math.min(adj[from].get(to), weight));
			} else {
				adj[from].put(to, weight);
			}
		}

		public HashMap<Integer, Integer> adj(int v)
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
	}


	public static class Dijkstra {
		private boolean[] marked;
		private int[] distance;
		private int[] parent;
		private PriorityQueue<Node> pq;

		public Dijkstra(EdgeWeightedDigraph g, int start)
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
				if (!marked[node.Index()])
				{
					int visiting = node.Index();
					marked[visiting] = true;
					HashMap<Integer, Integer> adj = g.adj(visiting);
					for (int to : adj.keySet())
					{
						if (marked[to]) continue;
						if (distance[visiting] + adj.get(to) < distance[to])
						{
							distance[to] = distance[visiting] + adj.get(to);
							parent[to] = visiting;
							pq.add(new Node(to, distance[to]));
						}
					}
				}
			}
			for (int i = 0; i < g.V(); ++i)
			{
				if (!marked[i]) distance[i] = -1;
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


    // Complete the shortestReach function below.
    static int[] shortestReach(int n, int[][] edges, int s) {
    	EdgeWeightedDigraph g = new EdgeWeightedDigraph(n);
    	for (int[] edge : edges) {
    		g.addEdge(edge[0] - 1, edge[1] - 1, edge[2]);
    		g.addEdge(edge[1] - 1, edge[0] - 1, edge[2]);
    	}
    	Dijkstra path = new Dijkstra(g, s - 1);
    	return IntStream.range(0, n).filter(i -> i != s - 1).map(i -> path.distTo(i)).toArray();

    }

//    private static final Scanner scanner = new Scanner(System.in);
    private static final Scanner scanner = new Scanner();

    public static void main(String[] args) throws IOException {

        int t = scanner.nextInt();
        for (int tItr = 0; tItr < t; tItr++) {
        	int n = scanner.nextInt();
    		int m = scanner.nextInt();
        	EdgeWeightedDigraph g = new EdgeWeightedDigraph(n);

    		for (int i = 0; i < m; i++) {
    			int from = scanner.nextInt();
    			int to = scanner.nextInt();
				int weight = scanner.nextInt();
        		g.addEdge(from - 1, to - 1, weight);
        		g.addEdge(to - 1, from - 1, weight);
    		}
        	int s = scanner.nextInt();

        	Dijkstra path = new Dijkstra(g, s - 1);

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < n; ++i) {
            	if (i != s - 1) {
                	builder.append(path.distTo(i));
                	builder.append(" ");
            	}
            }
            System.out.println(builder);

        }

//        scanner.close();
    }
}
