import java.io.IOException;
import java.util.Scanner;

public class BiggerIsGreater {

	// Complete the biggerIsGreater function below.
    static String biggerIsGreater(String w) {
    	char[] cs = w.toCharArray();
        int mark = -1;
        for (int i = cs.length - 1; i > 0; i--) {
            if (cs[i] > cs[i - 1]) {
                mark = i - 1;
                break;
            }
        }

        if (mark == -1) {
            return "no answer";
        }

        int idx = cs.length-1;
        for (int i = cs.length-1; i >= mark+1; i--) {
            if (cs[i] > cs[mark]) {
                idx = i;
                break;
            }
        }

        swap(cs, mark, idx);

        reverse(cs, mark + 1, cs.length - 1);

        return String.valueOf(cs);
    }

    private static void swap(char[] nums, int i, int j) {
        char t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    private static void reverse(char[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int TItr = 0; TItr < T; TItr++) {
            String w = scanner.nextLine();

            String result = biggerIsGreater(w);
            System.out.println(result);

//            bufferedWriter.write(result);
//            bufferedWriter.newLine();
        }

//        bufferedWriter.close();

        scanner.close();
    }
}
