import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InsertionSortAdvancedAnalysis {

	public static class BinaryIndexedTree {
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

    // Complete the insertionSort function below.
    static long insertionSort(int[] arr) {
    	int[] sorted = Arrays.copyOf(arr, arr.length);
    	Arrays.sort(sorted);
    	Map<Integer, Integer> map = new HashMap<>();
    	for (int i = 0; i < arr.length; ++i) map.put(sorted[i], i + 1);
    	BinaryIndexedTree tree = new BinaryIndexedTree(arr.length);
    	long ans = 0;
    	for (int j = 0; j < arr.length; ++j) {
    		int order = map.get(arr[j]);
    		ans += j - tree.sum(order);
    		tree.add(order, 1);
    	}
    	return ans;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");


        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.nextLine();

            int[] arr = new int[n];

//            String l1 = scanner.nextLine();
//            String l2 = scanner.nextLine();
            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = insertionSort(arr);
            System.out.println(result);
//
//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
        }

//        bufferedWriter.close();

        scanner.close();
    }
}
