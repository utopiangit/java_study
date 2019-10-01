import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class IceCreamParlor {

    // Complete the icecreamParlor function below.
    static int[] icecreamParlor(int m, int[] arr) {
    	int[] mark = new int[10000];
    	Arrays.fill(mark, -1);
    	for ( int i = 0; i < arr.length; ++i) {
    		if (mark[arr[i]] != -1) return new int[] {mark[arr[i]] + 1, i + 1};
    		if (m - arr[i] >= 0) {
    			mark[m - arr[i]] = i;
    		}
    	}
    	return new int[] {};
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int m = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            int[] result = icecreamParlor(m, arr);

            for (int i = 0; i < result.length; i++) {
//                bufferedWriter.write(String.valueOf(result[i]));
            	System.out.print(result[i]);

                if (i != result.length - 1) {
//                    bufferedWriter.write(" ");
                	System.out.print(" ");
                }
            }

//            bufferedWriter.newLine();
            System.out.println("");
        }

//        bufferedWriter.close();

        scanner.close();
    }
}
