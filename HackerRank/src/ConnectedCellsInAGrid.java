import java.io.IOException;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class ConnectedCellsInAGrid {

    // Complete the connectedCell function below.
    static int connectedCell(int[][] matrix) {

    	int n = matrix.length;
    	int m = matrix[0].length;

    	boolean isAllZero = true;
    	for (int i = 0; i < n; ++i) {
    		for (int j = 0; j < m; ++j) {
    			if (matrix[i][j] == 1) isAllZero = false;
    		}
    	}
    	if (isAllZero) return 0;


    	UnionFind uf = new UnionFind(n*m);
    	for (int i = 0; i < n; ++i) {
    		for (int j = 0; j < m; ++j) {
    			if (matrix[i][j] == 1) {
    				if (i + 1 < n && matrix[i + 1][j] == 1) {
    					uf.unite(m, i, j, i + 1, j);
    				}

    				if (j + 1 < m && matrix[i][j + 1] == 1) {
    					uf.unite(m, i, j, i, j + 1);
    				}

    				if (i + 1 < n && j + 1 < m && matrix[i + 1][j + 1] == 1) {
    					uf.unite(m, i, j, i + 1, j + 1);
    				}

    				if (0 < i - 1 && j + 1 < m && matrix[i - 1][j + 1] == 1) {
    					uf.unite(m, i, j, i - 1, j + 1);
    				}

    			}
    		}
    	}
    	int[] count = new int[n*m];
    	Arrays.stream(uf.par()).forEach(p -> count[p] += 1);
    	OptionalInt a = Arrays.stream(count).max();
    	return a.getAsInt();
    }

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

    	public void unite(int n, int ax, int ay, int bx, int by)
    	{
    		int a = ax * n + ay;
    		int b = bx * n + by;
    		unite(a, b);
    	}


    	public int[] par() {
    		return Arrays.stream(par).map(e -> this.root(e)).toArray();
    	}
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] matrixRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int matrixItem = Integer.parseInt(matrixRowItems[j]);
                matrix[i][j] = matrixItem;
            }
        }

        int result = connectedCell(matrix);
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

//        bufferedWriter.close();

        scanner.close();
    }
}
