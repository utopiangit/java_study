
public class TestThread {

	public static void main(String[] args) {
		TestJoin();
		TestRunnable();
	}

	public static void TestJoin() {
		System.out.println("Test of thread");
		MyThread t1 = new MyThread("thread01");
		MyThread t2 = new MyThread("thread02");

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {

		}

		System.out.println("finish main thread");
	}

	public static void TestRunnable() {
		Thread t1 = new Thread(new MyRunnable());
		t1.start();
		System.out.println("finish main thread");
	}


}
