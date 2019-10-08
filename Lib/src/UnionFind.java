import java.util.Arrays;
import java.util.stream.IntStream;

public class UnionFind {
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
