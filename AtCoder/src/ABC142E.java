
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;


class Scanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;

    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        } else {
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }

    private int readByte() {
        return hasNextByte() ? buffer[ptr++] : -1;
    }

    private boolean isPrintableChar(int c) {
        return 33 <= c && c <= 126;
    }

    private void skipUnprintable() {
        while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
    }

    public boolean hasNext() {
        skipUnprintable();
        return hasNextByte();
    }

    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while (isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    public int nextInt() {
        return (int) nextLong();
    }

    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while (true) {
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            } else if (b == -1 || !isPrintableChar(b)) {
                return minus ? -n : n;
            } else {
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
}

public class ABC142E {
//public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner();
	    int n = sc.nextInt();
	    int m = sc.nextInt();
	    int[] a = new int[m];
	    int[][] c = new int[m][];
	    for (int i = 0; i < m; ++i) {
	    	a[i] = sc.nextInt();
	    	int bm = sc.nextInt();
	    	c[i] = new int[bm];
	    	for (int j = 0; j < bm; ++j) {
	    		c[i][j] = sc.nextInt();
	    	}
	    }
	    long ans = solve2(n, a, c);
	    System.out.println(ans);
	}

	public static long solve(int n, int[] a, int[][] c) {
		int mx = (int) Math.pow(2, n);
		int INITIAL = 1000000000;
		int[][] dp = new int[mx][2];
		Arrays.stream(dp).forEach(e -> Arrays.fill(e, INITIAL));
		dp[0][0] = 0;
		for (int i = 0; i < a.length; ++i) {
			int keys = toIndex(c[i]);
			for (int j = 0; j < mx; ++j) {
				dp[j | keys][(i + 1) % 2] = Math.min(dp[j | keys][(i + 1) % 2],
						Math.min(dp[j | keys][i % 2], dp[j][i % 2] + a[i]));
			}
		}
		if (dp[mx - 1][a.length % 2] == INITIAL) return -1;
		else return dp[mx - 1][a.length % 2];
	}

	public static long solve2(int n, int[] a, int[][] c) {
		int mx = (int) Math.pow(2, n);
		int INITIAL = 1000000000;
		int[][] dp = new int[2][mx];
		Arrays.stream(dp).forEach(e -> Arrays.fill(e, INITIAL));
		dp[0][0] = 0;
		for (int i = 0; i < a.length; ++i) {
			int keys = toIndex(c[i]);
			dp[(i + 1) % 2] = Arrays.copyOf(dp[(i) % 2], mx);
			for (int j = 0; j < mx; ++j) {
				dp[(i + 1) % 2][j | keys] = Math.min(dp[(i + 1) % 2][j | keys],
						Math.min(dp[i % 2][j | keys], dp[i % 2][j] + a[i]));
			}
		}
		if (dp[a.length % 2][mx - 1] == INITIAL) return -1;
		else return dp[a.length % 2][mx - 1];
	}


	public static int toIndex(int[] c) {
		return Arrays.stream(c).map(x -> (int)Math.pow(2, (x - 1))).sum();
	}
}
