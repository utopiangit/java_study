import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;


public class RoadsAndLibraries {

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



    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
    	UnionFind uf = new UnionFind(n);
    	for (int[] city : cities) uf.unite(city[0] - 1, city[1] - 1);
    	Map<Integer, Integer> map = new HashMap<>();
    	uf.parent().distinct().forEach( p -> {
    		map.put(p, 0);
    	});
    	uf.parent().forEach( p -> {
    		int current = map.get(p);
    		map.put(p, current + 1);
    	});
    	long ret = 0;
    	for (int key : map.keySet()) {
    		int mi = map.get(key);
    		ret += Math.min(c_lib + (mi - 1) * c_road, mi * c_lib);
    	}
    	return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);
            System.out.println(result);
        }

        scanner.close();
    }
}
