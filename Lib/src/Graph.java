import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import EvenTree.Graph;

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


//
//	例：子ノードの数が奇数のノードの個数を探す
//
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
