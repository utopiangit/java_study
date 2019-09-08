import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
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

public class SynchronousShopping {

	public static void main(String[] args) {
		Scanner sc = new Scanner();
	    int n = sc.nextInt();
	    int m = sc.nextInt();
	    int k = sc.nextInt();

	    int fish[][] = new int[n][];
	    for (int i = 0; i < n; ++i)
	    {
	    	int ti = sc.nextInt();
	    	fish[i] = new int[ti];
	    	for (int j = 0; j < ti; ++j)
	    	{
	    		fish[i][j] = sc.nextInt();
	    	}
	    }

	    EdgeWeightedDigraph g = new EdgeWeightedDigraph(n, fish);

	    for (int i = 0; i < m; ++i)
	    {
	    	int s = sc.nextInt() - 1;
	    	int t = sc.nextInt() - 1;
	    	int w = sc.nextInt();
	    	g.addEdge(new DirectedEdge(s, t, w));
	    	g.addEdge(new DirectedEdge(t, s, w));
	    }
	    Dijkstra path = new Dijkstra(g, 0, k, fish[0]);
	    int minimum2CatsPath = Integer.MAX_VALUE;
	    int subMax = (int)Math.pow(2, k);
	    for (int i = 0; i < subMax; ++i) {
	    	for (int j = 0; j < subMax; ++j) {
		    	if ((i | j) == subMax-1) {
			    	int t = Math.max(path.distTo(n - 1, i), path.distTo(n - 1, j));
	    	    	minimum2CatsPath = Math.min(minimum2CatsPath, t);
		    	}
	    	}
	    }

	    System.out.println(minimum2CatsPath);

	}

	public static class DirectedEdge {
		private final int from;
		private final int to;
		private final double weight;

		public DirectedEdge(int from, int to, double weight)
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

		public double weight()
		{
			return weight;
		}

	}

	public static class EdgeWeightedDigraph {
		private final int V;
		private final HashSet<DirectedEdge>[] adj;
		private final int[][] fish;

		public EdgeWeightedDigraph(int v, int[][] fish)
		{
			this.V = v;
			this.fish = fish;
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

		public int[] fish(int i) {
			return this.fish[i];
		}

		public int V()
		{
			return V;
		}

//		public int E()
//		{
//			return Arrays.stream(adj)
//					.mapToInt(h -> h.size())
//					.sum();
//		}
//
//		@SuppressWarnings("unchecked")
//		public Iterable<DirectedEdge> edges()
//		{
//			return (Iterable<DirectedEdge>)Arrays.stream(adj)
//					.flatMap(e -> e.stream())
//					.iterator();
//
//		}
	}


	public static class Node implements Comparable<Node> {
		private final int index;
		private final int subIndex;
		private final int distance;

		public Node(int i, int j, int dist)
		{
			this.index = i;
			this.subIndex = j;
			this.distance = dist;
		}

		public int Index()
		{
			return this.index;
		}

		public int SubIndex()
		{
			return this.subIndex;
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

	public static class Fishes {
		public static int addFish(int index, int[] kindOfFish) {
//			return index | Arrays.stream(kindOfFish).map(iFish -> (int)Math.pow(2, iFish - 1)).sum();
			int tens = 0;
			for (int i = 0; i < kindOfFish.length; ++i) {
				tens += (int)Math.pow(2, kindOfFish[i] - 1);
			}
			return index | tens;
		}
	}

	public static class Dijkstra {
		private boolean[][] marked;
		private int[][] distance;
		private int[][] parent;
		private PriorityQueue<Node> pq;

		public Dijkstra(EdgeWeightedDigraph g, int start, int k, int[] initialFishes)
		{
			int nSubIndex = (int) Math.pow(2, k);
			int subStart = Fishes.addFish(0, initialFishes);
			distance = new int[g.V()][nSubIndex];
			parent = new int[g.V()][nSubIndex];
			marked = new boolean[g.V()][nSubIndex];
			pq = new PriorityQueue<Node>();
			for (int i = 0; i < g.V(); ++i)
				for (int j = 0; j < nSubIndex; ++j)
					distance[i][j] = Integer.MAX_VALUE;
			distance[start][subStart] = 0;
			parent[start][subStart] = start;
			pq.add(new Node(start, subStart, 0));
			while (!pq.isEmpty())
			{
				Node node = pq.poll();
				if (!marked[node.Index()][node.SubIndex()])
				{
					int visiting = node.Index();
					int currentFish = node.SubIndex();
					marked[visiting][currentFish] = true;
					for (DirectedEdge edge : g.adj(visiting))
					{
						int newFishIndex = Fishes.addFish(currentFish, g.fish(edge.to()));
						if (marked[edge.to()][newFishIndex]) continue;
						if (distance[visiting][currentFish] + edge.weight() < distance[edge.to()][newFishIndex])
						{
							distance[edge.to()][newFishIndex] = distance[visiting][currentFish] + (int)edge.weight();
							parent[edge.to()][newFishIndex] = visiting;
							pq.add(new Node(edge.to(), newFishIndex, distance[edge.to()][newFishIndex]));
						}
					}
				}
			}
		}

		public int distTo(int iNode, int iSubIndex)
		{
			return distance[iNode][iSubIndex];
		}

//		public Iterable<Integer> pathTo(int v)
//		{
//			LinkedList<Integer> path = new LinkedList<Integer>();
//			int bc = v;
//			while (!(parent[bc] == bc))
//			{
//				path.add(bc);
//				bc = parent[bc];
//			}
//			path.add(bc);
//			return path;
//		}
	}
}


