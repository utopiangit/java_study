import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class LilysHomework {

    // Complete the lilysHomework function below.
    static int lilysHomework(int[] arr) {
    	int[] reversed = new int[arr.length];
    	IntStream.range(0, arr.length).forEach(i -> reversed[i] = arr[arr.length - 1 - i]);
    	int x = solve(arr);
    	int y = solve(reversed);
    	return Math.min(x, y);
    }

    public static int solve(int[] arr) {
    	int[] sorted = new int[arr.length];
        System.arraycopy(arr, 0, sorted, 0, arr.length);
    	Arrays.sort(sorted);
    	Map<Integer, Integer> position = new HashMap<>();
		IntStream
			.range(0, arr.length)
			.forEach(i -> {
				position.put(arr[i], i);
			});

		int counter = 0;
    	for (int i = 0; i < arr.length; ++i) {
    		if (arr[i] != sorted[i]) {
				arr[position.get(sorted[i])] = arr[i];
				position.put(arr[i], position.get(sorted[i]));
    			counter += 1;
    		}
    	}

    	return counter;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = lilysHomework(arr);
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
