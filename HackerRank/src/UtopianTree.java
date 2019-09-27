import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class UtopianTree {

    // Complete the utopianTree function below.
    static int utopianTree(int n) {
    	Tree tree = new Tree(1);
    	IntStream.range(0, n).forEach(i -> tree.grow());
    	return tree.height();

    }

    private static class Tree {
    	private int height;
    	private boolean isSpring;

    	Tree(int initialHeight) {
    		this.height = initialHeight;
    		this.isSpring = true;
    	}

    	void grow() {
    		if (isSpring) {
    			this.height *= 2;
    		} else {
    			this.height += 1;
    		}
    		this.isSpring = !this.isSpring;
    	}

    	int height() {
    		return this.height;
    	}

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int result = utopianTree(n);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
