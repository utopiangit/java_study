import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

class DoubleArrayList<E> {
	ArrayList<E> left;
	ArrayList<E> right;
	public DoubleArrayList() {
		left = new ArrayList<E>();
		right = new ArrayList<E>();
	}

	public void addFirst(E x) {
		left.add(x);
	}

	public void addLast(E x) {
		right.add(x);
	}

	public void pollFirst() {
		if (left.size() == 0)
		{
			right.remove(0);
		} else {
			left.remove(left.size()-1);
		}
	}

	public void pollLast() {
		if (right.size() == 0)
		{
			left.remove(0);
		} else {
			right.remove(right.size() - 1);
		}
	}

	public E get(int i) {
		int n = left.size();
		if (n <= i) {
			return right.get(i - n);
		} else {
			return left.get(n - i - 1);
		}

	}

}

public class ITP2_1_B {

	public static void main(String[] args) {
		Scanner sc = new Scanner();
		int q = sc.nextInt();
//		LinkedList<Integer> deq = new LinkedList<Integer>();
		DoubleArrayList<Integer> deq = new DoubleArrayList<Integer>();
	    for (int i = 0; i < q; ++i)
	    {
	    	int query = sc.nextInt();
	    	switch (query) {
	    	case 0:
	    		push(sc, deq);
	    		break;
	    	case 1:
	    		randomAccess(sc, deq);
	    		break;
	    	case 2:
	    		pop(sc, deq);
	    		break;
	    	}
	    }
	}

	static void push(Scanner sc, DoubleArrayList<Integer> deq)
	{
		int d = sc.nextInt();
		int x = sc.nextInt();
		if (d == 0)
		{
			deq.addFirst(x);
		} else
		{
			deq.addLast(x);
		}
	}

	static void randomAccess(Scanner sc, DoubleArrayList<Integer> deq)
	{
		int p = sc.nextInt();
		System.out.println(deq.get(p));
	}

	static void pop(Scanner sc, DoubleArrayList<Integer> deq)
	{
		int d = sc.nextInt();
		if (d == 0)
		{
			deq.pollFirst();
		} else
		{
			deq.pollLast();
		}
	}

}
