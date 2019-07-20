import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

class Scanner {
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

public class ALDS1_12_C {

	public static void main(String[] args) {
//	    @SuppressWarnings("resource")
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner();
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
		private int[] d;
		private PriorityQueue<Node> pq;

		public ShortestPaths(EdgeWeightedDigraph g, int s)
		{
			d = new int[g.V()];
			marked = new boolean[g.V()];
			pq = new PriorityQueue<Node>();
			for (int i = 0; i < g.V(); ++i)
			{
				d[i] = Integer.MAX_VALUE;
			}
			d[s] = 0;
			pq.add(new Node(s, 0));
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
						d[edge.to] = Math.min(d[edge.to], d[visiting] + edge.weight);
						pq.add(new Node(edge.to, d[edge.to]));
					}
				}
			}

		}

		public int distTo(int v)
		{
			return d[v];
		}

		public Iterable<DirectedEdge> pathTo(int v)
		{
			return new LinkedList<DirectedEdge>();
		}
	}

}

