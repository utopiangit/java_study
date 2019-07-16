import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.LongStream;

public class ABC133_D {
	static long mod = 1000000007;
    static boolean[] marked;
	static HashSet<Integer>[] adj;
	static long k;
	static int n;

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    n = sc.nextInt();
	    k = sc.nextInt();
	    sc.nextLine();

	    marked = new boolean[n];
	    adj = new HashSet[n];
	    for (int i = 0; i < n; ++i) {
	    	adj[i] = new HashSet<Integer>();
	    }
	    for (int i = 0; i < n - 1; ++i) {
		    int a = sc.nextInt() - 1;
		    int b = sc.nextInt() - 1;
		    adj[a].add(b);
		    adj[b].add(a);
	    }

	    long count = dfs(0, -1);
	    System.out.println(k * count % mod);

	}

	public static long dfs(int i, int from)
	{
		long num_col;
		if (from == -1) num_col = k - 1;
		else num_col = k - 2;

		marked[i] = true;
		if (k < adj[i].size()) return 0;
		long count = 1;
		for (int j : adj[i])
		{
			if (!marked[j])
			{
				count = (dfs(j, i) * count) % mod;
			}
		}
		if (adj[i].size() == 1 && from != -1) return count;

		int num_children = 0;
		if (from == -1) num_children = adj[i].size();
		else num_children = adj[i].size() - 1;
		return (count * permutation(num_col, num_children)) % mod;
	}

	public static long factorial(int n)
	{
		if (n <= 1) return 1;
		return n * factorial(n-1) % mod;
	}

	public static long permutation(long n, int m)
	{
		if (n < m) return 1;
		if (m <= 0) return 1;
		return LongStream.rangeClosed(n-m+1, n).reduce(1, (l, r) -> (l * r) % mod);
	}

}
