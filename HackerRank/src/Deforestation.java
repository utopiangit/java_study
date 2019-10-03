import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Deforestation {
	public static class Tree {
		private final int n;
		private final HashSet<Integer>[] adj;

		public Tree (int n) {
			this.n = n;
			this.adj = (HashSet<Integer>[])new HashSet[n];
			for (int i = 0; i < n; ++i) adj[i] = new HashSet<>();
		}

		public int nodeNumber() { return n; }

		public void addEdge(int from, int to) {
			adj[from].add(to);
		}

		public HashSet<Integer> adj(int i) {
			return this.adj[i];
		}
	}

	public static class Hackenbush {
		Tree tree;
		boolean[] marked;

		public Hackenbush(Tree tree) {
			this.tree = tree;
			this.marked = new boolean[tree.nodeNumber()];
			Arrays.fill(marked, false);
		}

		public int nimSum(int from) {
			marked[from] = true;
			HashSet<Integer> edges = tree.adj(from);
			edges.removeIf(i -> marked[i]);
			if (edges.size() == 0) return 0;
			if (edges.size() == 1) {
				int to = edges.iterator().next();
				if (marked[to]) return 1;
				else return 1 + nimSum(to);
			}
			int ret = 0;
			for (int e : edges) {
				if (!marked[e]) ret ^= 1 + nimSum(e);
			}
			return ret;
		}
	}

    /*
     * Complete the deforestation function below.
     */
    static String deforestation(int n, int[][] tree) {
    	Tree tr = new Tree(n);
    	Arrays.stream(tree).forEach(e -> tr.addEdge(e[0] - 1, e[1] - 1));
    	Arrays.stream(tree).forEach(e -> tr.addEdge(e[1] - 1, e[0] - 1));
    	Hackenbush s = new Hackenbush(tr);
    	if (s.nimSum(0) == 0) return "Bob";
    	else return "Alice";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

            int[][] tree = new int[n-1][2];

            for (int treeRowItr = 0; treeRowItr < n-1; treeRowItr++) {
                String[] treeRowItems = scanner.nextLine().split(" ");
//                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

                for (int treeColumnItr = 0; treeColumnItr < 2; treeColumnItr++) {
                    int treeItem = Integer.parseInt(treeRowItems[treeColumnItr]);
                    tree[treeRowItr][treeColumnItr] = treeItem;
                }
            }

            String result = deforestation(n, tree);
            System.out.println(result);
        }

        scanner.close();
    }
}
