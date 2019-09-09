import java.math.BigInteger;
import java.util.Scanner;

public class ExtraLongFactorials {

    // Complete the extraLongFactorials function below.
    static void extraLongFactorials(int n) {
    	BigInteger ans = factorial(BigInteger.valueOf(n));
    	System.out.println(ans);

    }

    private static BigInteger factorial(BigInteger n) {
    	if (n.equals(BigInteger.ONE)) {
    		return BigInteger.ONE;
    	} else {
    		return n.multiply(factorial(n.subtract(BigInteger.ONE)));
    	}

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        extraLongFactorials(n);

        scanner.close();
    }
}
