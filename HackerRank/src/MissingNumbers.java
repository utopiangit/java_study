import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MissingNumbers {
	private final static int N = 10000;
    // Complete the missingNumbers function below.
    static Integer[] missingNumbers(int[] arr, int[] brr) {
    	int[] counts = new int[N];
    	for (int i = 0; i < brr.length; ++i) {
    		counts[brr[i] - 1] += 1;
    	}
    	for (int i = 0; i < arr.length; ++i) {
    		counts[arr[i] - 1] -= 1;
    	}

    	List<Integer> ret = new ArrayList<>();
    	for (int i = 0; i < N; ++i) {
    		if (counts[i] > 0) ret.add(i + 1);
    	}
    	return ret.toArray(new Integer[ret.size()]);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] brr = new int[m];

        String[] brrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int brrItem = Integer.parseInt(brrItems[i]);
            brr[i] = brrItem;
        }

        Integer[] result = missingNumbers(arr, brr);

        for (int i = 0; i < result.length; i++) {
//            bufferedWriter.write(String.valueOf(result[i]));
        	System.out.print(result[i]);

            if (i != result.length - 1) {
//                bufferedWriter.write(" ");
            	System.out.print(" ");
            }
        }
        scanner.close();
    }
}
