import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EvenTree {

	public static class Graph{
		private int V;
		private HashSet<Integer>[] adj;

		public Graph(int V)
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
			return Stream.of(adj).mapToInt(v -> v.size()).sum();
		}

		//何回も呼ぶならメモ化
		public int numberOfChild(int parent, int visiting) {
			int ret = 0;
			for (int w : adj[visiting]) {
				ret += w != parent
						? 1 + numberOfChild(visiting, w)
						: 0;
			}
			return ret;
		}
	}

    // Complete the evenForest function below.
    static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {
    	Graph g = new Graph(t_nodes);
    	Iterator<Integer> iterFrom = t_from.iterator();
    	Iterator<Integer> iterTo = t_to.iterator();
    	while (iterFrom.hasNext()) g.addEdge(iterFrom.next() - 1, iterTo.next() - 1);

    	final int root = 1;
//    	return dfs(g, root, root) - 1;
    	return stackDfs(g, root);
    }

    public static int dfs(Graph g, int from, int visiting) {
    	int ret = 0;
    	for (int v : g.adj(visiting)) {
    		if (v != from) ret += dfs(g, visiting, v);
    	}
    	return g.numberOfChild(from, visiting) % 2 == 1
    			? ret + 1
				: ret;
    }

    public static int stackDfs(Graph g, int root) {
    	Stack<Integer> stack = new Stack<>();
    	boolean[] marked = new boolean[g.V()];

    	stack.add(root);
    	marked[root] = true;
    	int numberOfEven = 0;
    	while(!stack.isEmpty()) {
    		int visiting = stack.pop();
			Iterable<Integer> adj = g.adj(visiting);
			for (int v : adj) {
				if (!marked[v]) {
					stack.add(v);
					marked[v] = true;
					numberOfEven += g.numberOfChild(visiting, v) % 2 == 1
							? 1
							: 0;
				}
			}
    	}
    	return numberOfEven;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] tNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int tNodes = Integer.parseInt(tNodesEdges[0]);
        int tEdges = Integer.parseInt(tNodesEdges[1]);

        List<Integer> tFrom = new ArrayList<>();
        List<Integer> tTo = new ArrayList<>();

        IntStream.range(0, tEdges).forEach(i -> {
            try {
                String[] tFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                tFrom.add(Integer.parseInt(tFromTo[0]));
                tTo.add(Integer.parseInt(tFromTo[1]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int res = evenForest(tNodes, tEdges, tFrom, tTo);
        System.out.println(res);

        bufferedReader.close();
    }
}
