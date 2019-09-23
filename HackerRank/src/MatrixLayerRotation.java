import static java.util.stream.Collectors.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MatrixLayerRotation {

    // Complete the matrixRotation function below.
    static void matrixRotation(List<List<Integer>> matrix, int r) {
    	int n = matrix.size();
    	int m = matrix.get(0).size();
    	int[][] arr = new int[n][m];

    	int i = 0;
    	for (List<Integer> l : matrix) {
    		for (int e : l) {
        		arr[i / m][i % m] = e;
				i += 1;
    		}
    	}

    	int[][] rotated = new int[n][m];
    	int layers = Math.min(n, m) / 2;
    	for (int layer = 0; layer < layers; ++layer) {
    		int loop = 2 * (n + m - 2 - 4 * layer);
    		for (int l = 0; l < loop; ++l) {
    			int[] index = indexAt(l, n, m, layer);
    			rotated[index[0]][index[1]] = elemLapAt((l + r) % loop, arr, n, m, layer);
    		}
    	}

    	Arrays.stream(rotated).forEach(row -> {
    		Arrays.stream(row).forEach(elem -> {
    			System.out.print(elem);
    			System.out.print(" ");
    		});
    		System.out.println("");
    	});
    }

    static int[] indexAt(int i, int n, int m, int layer) {
    	if (i < m - 2 * layer) {
    		return new int[] {layer, i + layer};
    	} else if (i < n + m - 1 - 4 * layer) {
    		return new int[] {i - m + 1 + 3 * layer, m - 1 - layer};
    	} else if (i < n + 2 * m - 2 - 6 * layer) {
    		return new int[] {n - 1 - layer, n + 2 * m - i - 3 - 5 * layer};
    	} else {
    		return new int[] {2 * (n + m - 2) - 7 * layer - i, layer};
    	}
    }

    static int elemLapAt(int i, int[][] matrix, int n, int m, int layer) {
    	int[] index = indexAt(i, n, m, layer);
    	return matrix[index[0]][index[1]];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
