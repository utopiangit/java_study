package Prices;
import java.util.Scanner;

public class Prices {

	public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in))
        {
	        int n = sc.nextInt();
	        int[] prices = new int[n];
	        for (int i = 0; i < n; ++i)
	        {
	        	prices[i] = sc.nextInt();
	        }
	        System.out.println(maximum_profit(n, prices));
        }

	}

	public static int maximum_profit(int n, int[] prices)
	{
		int[][] dp = new int[n + 1][2];
		dp[0][0] = 0;
		dp[0][1] = -inf();
		for (int i = 1; i <= n; ++i)
		{
			dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i - 1]);
			dp[i][1] = Math.max(dp[i-1][0] - prices[i - 1], dp[i-1][1]);
		}
		return dp[n][0];
	}

	public static int inf()
	{
		return 2 << 20;
	}

}
