import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MisereNim {

    // Complete the nimGame function below.
    static String nimGame(int[] pile) {
    	if (Arrays.stream(pile).allMatch(x -> x == 1)) {
    		return pile.length % 2 == 0
    				? "First"
					: "Second";
    	}
    	return nimSum(pile) == 0
    			? "Second"
				: "First";
    }

    static int nimSum(int[] pile) {
    	return Arrays.stream(pile).reduce(0, (x, y) -> x^y);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int g = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int gItr = 0; gItr < g; gItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] pile = new int[n];

            String[] pileItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int pileItem = Integer.parseInt(pileItems[i]);
                pile[i] = pileItem;
            }

            String result = nimGame(pile);
            System.out.println(result);

//            bufferedWriter.write(result);
//            bufferedWriter.newLine();
        }

//        bufferedWriter.close();

        scanner.close();
    }
}
