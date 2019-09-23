import java.io.IOException;
import java.util.Scanner;

public class ACMICPCTeam {

    // Complete the acmTeam function below.
    static int[] acmTeam(String[] topic) {
    	int n = topic.length;
    	int m = topic[0].length();
    	int[] ans = new int[m + 1];
    	for (int i = 0; i < n - 1; ++i) {
    		for (int j = i + 1; j < n; ++j) {
    			ans[knownTopic(topic[i], topic[j])] += 1;
    		}
    	}

    	for (int i = m; i >= 0; --i) {
    		if (ans[i] != 0) {
    			return new int[] {i, ans[i]};
    		}
    	}
    	return new int[] {};
    }

    static int knownTopic(String x, String y) {
    	int n = 0;
    	for (int i = 0; i < x.length(); ++i) {
    		if (x.charAt(i) == '1' ||  y.charAt(i) == '1') n += 1;
    	}
    	return n;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        String[] topic = new String[n];

        for (int i = 0; i < n; i++) {
            String topicItem = scanner.nextLine();
            topic[i] = topicItem;
        }

        int[] result = acmTeam(topic);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);

        }

        scanner.close();
    }
}
