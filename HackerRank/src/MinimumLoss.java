import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class MinimumLoss {

    // Complete the minimumLoss function below.
    static long minimumLoss(long[] price) {
    	TreeSet<Long> set = new TreeSet<>();
    	long ret = Integer.MAX_VALUE;
    	for (int i = 0; i < price.length; ++i) {
    		if (set.higher(price[i]) != null)
    			ret = Math.min(set.higher(price[i]) - price[i], ret);
    		set.add(price[i]);
    	}
    	return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] price = new long[n];

        String[] priceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long priceItem = Long.parseLong(priceItems[i]);
            price[i] = priceItem;
        }

        long result = minimumLoss(price);
        System.out.println(result);
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
