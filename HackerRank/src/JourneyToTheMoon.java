import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class JourneyToTheMoon {

	public static class UnionFind {
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



    // Complete the journeyToMoon function below.
    static long journeyToMoon(int n, int[][] astronaut) {
    	UnionFind uf = new UnionFind(n);
    	for (int[] astro : astronaut) {
    		uf.unite(astro[0], astro[1]);
    	}
    	Map<Integer, Integer> map = new HashMap<>();
    	uf.parent().distinct().forEach( p -> {
    		map.put(p, 0);
    	});
    	uf.parent().forEach( p -> {
    		int current = map.get(p);
    		map.put(p, current + 1);
    	});
    	int m = map.size();
    	Integer[] keys = map.keySet().toArray(new Integer[m]);
    	int[] sums = new int[m];
    	for (int i = 0; i < m - 1; ++i) {
    		sums[i + 1] = sums[i] + map.get(keys[i]);
    	}
    	long ret = 0;
    	for (int i = m - 1; i >= 0; --i) {
    		ret += map.get(keys[i]) * sums[i];
    	}
    	return ret;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] np = scanner.nextLine().split(" ");

        int n = Integer.parseInt(np[0]);

        int p = Integer.parseInt(np[1]);

        int[][] astronaut = new int[p][2];

        for (int i = 0; i < p; i++) {
            String[] astronautRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int astronautItem = Integer.parseInt(astronautRowItems[j]);
                astronaut[i][j] = astronautItem;
            }
        }

        long result = journeyToMoon(n, astronaut);
        System.out.println(result);

        scanner.close();
    }
}
