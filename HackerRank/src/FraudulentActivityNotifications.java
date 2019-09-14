import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FraudulentActivityNotifications {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
    	Monitor monitor = new Monitor(d);
    	Arrays.stream(expenditure).forEach(e -> {
    		monitor.add(e);
    	});

    	return monitor.count();

    }

    public static class Monitor {
    	private final int N = 201;
    	private final int d;
    	private int notification = 0;
    	private int counter = 0;
    	private final int[] storage;
    	private final Queue<Integer> queue;

    	Monitor(int d) {
    		this.d = d;
    		this.storage = new int[N];
    		this.queue = new LinkedList<Integer>();
    	}

    	public void add(int x) {
    		if (counter < d) {
        		counter += 1;
        		queue.add(x);
        		storage[x] += 1;
        		return;
    		}

    		int m = this.medianDoubled();
    		if (m <= x) {
    			notification += 1;
    		}
    		queue.add(x);
    		storage[x] += 1;

    		int remove = queue.poll();
    		storage[remove] -= 1;

    	}

    	private int medianDoubled() {
    		int c = 0;
    		int dd = d / 2;
    		boolean isDEven = d % 2 == 0;
    		for (int i = 0; i < N; ++i) {
    			c += storage[i];
    			if (isDEven) {
    				if (dd < c) {
    					return 2 * i;
    				} else if (dd == c) {
    					int next = i + 1;
    					while (storage[next] == 0) next += 1;
    					return i + next;
    				}
    			}
    			else {
    				if (dd < c) {
    					return 2 * i;
    				}
    			}
    		}
    		return -1;
    	}

    	public int count() {
    		return this.notification;
    	}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);
        System.out.println(result);
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
