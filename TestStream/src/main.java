import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int[] as = {1, 3, 5, 9};
		Arrays.stream(as).map(e -> e*2)
						 .forEach(System.out::println);

		List<Integer> list = Arrays.asList(9,3,1,7,5,3,1);
		Collections.sort(list, Integer::compare);
		System.out.println(list);

		int prod = list.stream().filter(e -> e < 5).reduce((x, y) -> x * y).get();
		System.out.println(prod);

		System.out.println(factorial(5));

	}

	public static int factorial(int a)
	{
		return IntStream.range(1, a + 1).reduce((x, y) -> x * y).getAsInt();
	}

}
