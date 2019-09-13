import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class AlmostSorted {

    // Complete the almostSorted function below.
    static void almostSorted(int[] arr) {
    	if (arr.length == 2) {
    		System.out.println("yes");
    		if (arr[0] > arr[1]) {
        		System.out.println(String.format("swap %d %d", 1, 2));
    		}
    		return;
    	} else if(isSorted(arr)) {
    		System.out.println("yes");
    		return;
    	}

    	Optional<int[]> swap = isSwappable(arr);
    	if (swap.isPresent()) {
    		System.out.println("yes");
    		System.out.println(String.format("swap %d %d", swap.get()[0], swap.get()[1]));
    		return;
    	}
    	Optional<int[]> reverse = isReversable(arr);
    	if (reverse.isPresent()) {
    		System.out.println("yes");
    		System.out.println(String.format("reverse %d %d", reverse.get()[0], reverse.get()[1]));
    		return;
    	}

		System.out.println("no");
    }

    static boolean isSorted(int[] arr) {
    	int[] copied = new int[arr.length];
        System.arraycopy(arr, 0, copied, 0, arr.length);
        Arrays.sort(copied);
    	for (int i = 0; i < arr.length; ++i) {
    		if (arr[i] != copied[i]) {
    			return false;
    		}
    	}
    	return true;
    }

    static Optional<int[]> isSwappable(int[] arr) {
    	int i1 = -1;
    	for (int i = 0; i < arr.length - 1; ++i) {
    		if (arr[i] > arr[i + 1]) {
    			if (i > 1 && arr[i - 1] > arr[i + 1]) {
    				return Optional.empty();
    			}
    			i1 = i;
    			break;
    		}
    	}
    	int i2 = i1 + 1;
    	for (int j = i1 + 1; j < arr.length - 1; ++j ) {
    		if (arr[j] > arr[j + 1]) {
    			i2 = j + 1;
    			break;
    		}
    	}
    	int[] swapped = new int[arr.length];
    	System.arraycopy(arr, 0, swapped, 0, arr.length);
    	int tmp = swapped[i2];
    	swapped[i2] = swapped[i1];
    	swapped[i1] = tmp;
    	if (isSorted(swapped)) {
    		int[] ans = {i1 + 1, i2 + 1};
    		return Optional.of(ans);
    	} else {
    		return Optional.empty();
    	}
    }

    static Optional<int[]> isReversable(int[] arr) {
    	int i1 = -1;
    	for (int i = 0; i < arr.length - 1; ++i) {
    		if (arr[i] > arr[i + 1]) {
    			i1 = i;
    			break;
    		}
    	}
    	int i2 = arr.length - 1;
    	for (int j = i1 + 1; j < arr.length - 1; ++j ) {
    		if (arr[j] < arr[j + 1]) {
    			if (arr[i1] > arr[j + 1]) {
    				return Optional.empty();
    			}
    			i2 = j;
    			break;
    		}
    	}

    	int[] reversed = new int[arr.length];
    	System.arraycopy(arr, 0, reversed, 0, arr.length);
    	for (int i = 0; i <= i2 - i1; ++i) {
    		reversed[i1 + i] = arr[i2 - i];
    	}
    	if (isSorted(reversed)) {
    		int[] ans = {i1 + 1, i2 + 1};
    		return Optional.of(ans);
    	} else {
    		return Optional.empty();
    	}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        almostSorted(arr);

        scanner.close();
    }
}
