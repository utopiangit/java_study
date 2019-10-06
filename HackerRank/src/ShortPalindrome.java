import java.io.IOException;
import java.util.Scanner;

public class ShortPalindrome {
	private final static int mod = 1000 * 1000 * 1000 + 7;
    // Complete the shortPalindrome function below.
    static int shortPalindrome(String s) {
        int[] arr1 = new int[26]; //  char i がループの途中までで何回出てきたか
        int[][] arr2 = new int[26][26]; // char i とchar j がこの並びで何回出てたか
        int[] arr3 = new int[26]; // arr[i]はchar i char j char jがこの並びで出てきた回数 -> char i が出てきたときに条件を満たす回文が何個できるかを表す
        int ans = 0;
        for (int i = 0; i < s.length();i++){
            int index = (int)(s.charAt(i)-'a');
            ans += (arr3[index] % mod);
            ans = ans % mod;
            for (int j = 0; j < 26; j++){
                arr3[j] = (arr3[j] + arr2[j][index]) % mod;
            }
            for (int j = 0; j < 26;j++){
                arr2[j][index] = (arr2[j][index] + arr1[j]) % mod;
            }

            arr1[index] = (arr1[index] + 1) % mod;
        }
        return ans;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String s = scanner.nextLine();

        int result = shortPalindrome(s);

        System.out.println(result);
        scanner.close();
    }
}
