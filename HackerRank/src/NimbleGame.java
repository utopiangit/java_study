import java.io.IOException;
import java.util.Scanner;

public class NimbleGame {

    // Complete the nimbleGame function below.
    static String nimbleGame(int[] s) {
    	int ret = 0;
    	for (int i = 0; i < s.length; ++i) {
    		if (s[i] % 2 != 0) ret ^= i;
    	}
    	if (ret == 0) return "Second";
    	return "First";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] s = new int[n];

            String[] sItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int sItem = Integer.parseInt(sItems[i]);
                s[i] = sItem;
            }

            String result = nimbleGame(s);

            System.out.println(result);
        }


        scanner.close();
    }
}
