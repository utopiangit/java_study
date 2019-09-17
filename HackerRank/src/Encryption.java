import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Encryption {

    // Complete the encryption function below.
    static String encryption(String s) {
//    	s = s.replaceAll("\\s+","");
    	int l = s.length();
    	double rootL = Math.sqrt(l);
    	int floorL = (int)Math.floor(rootL);
    	int ceilL = (int) Math.ceil(rootL);
    	if (floorL * ceilL < l) floorL += 1;
    	char[][] encrypted = new char [floorL][ceilL];
    	for (int i = 0; i < l; ++i) {
    		encrypted[i / ceilL][i % ceilL] = s.charAt(i);
    	}

    	encrypted = transpose(encrypted);
    	return Arrays
    			.stream(encrypted)
    			.map(String::valueOf)
    			.map(str -> str.trim())
    			.reduce((x, y) -> x + " " + y)
    			.get();
    }

    static char[][] transpose(char[][] input) {
    	int r = input.length;
    	int c = input[0].length;
    	char[][] ret = new char[c][r];
    	for (int i = 0; i < r; ++i) {
    		for (int j = 0; j < c; ++j) {
    			ret[j][i] = input[i][j];
    		}
    	}
    	return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = encryption(s);
        System.out.println(result);
//        bufferedWriter.write(result);
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
