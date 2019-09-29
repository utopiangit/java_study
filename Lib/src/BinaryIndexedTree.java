
public class BinaryIndexedTree {
	private final long[] bit;
	private final int n;

	public BinaryIndexedTree(int n) {
		this.n = n;
		this.bit = new long[n + 1];
	}

	public void add(int i, int v) {
		while (i <= n) {
			bit[i] += v;
			i += i & -i;
		}
	}

	public long sum(int i) {
		long s = 0;
		while (i > 0) {
			s += bit[i];
			i -= i & -i;
		}
		return s;
	}

	public long sum(int from, int to) {
		if (from > 0) return sum(to) - sum(from - 1);
		return sum(to);
	}

}
