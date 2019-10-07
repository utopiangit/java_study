import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TheFullCountingSort {


    // Complete the countSort function below.

	// This function cannot solve the test case 5(time limit exceeded).
	// So I put it in main.
    static void countSort(List<List<String>> arr) {
    	List<StringBuilder> builders = new ArrayList<>(100);
    	IntStream.range(0, 100).forEach(e -> builders.add(new StringBuilder()));
    	int n = arr.size();
    	int count = 0;
    	for (List<String> line : arr) {
    		count += 1;
    		int x = Integer.parseInt(line.get(0));
			builders.get(x).append(count <= n / 2
					? "-"
					: line.get(1));
			builders.get(x).append(" ");
    	}
    	builders.stream().forEach(System.out::print);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

//        List<List<String>> arr = new ArrayList<>();

    	List<StringBuilder> builders = new ArrayList<>(100);
    	IntStream.range(0, 100).forEach(e -> builders.add(new StringBuilder()));

    	for (int i = 0; i < n; ++i) {
    		String[] line = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
    		int x = Integer.parseInt(line[0]);
			builders.get(x).append(i < n / 2
					? "-"
					: line[1]);
			builders.get(x).append(" ");

    	}
    	builders.stream().forEach(System.out::print);

//        IntStream.range(0, n).forEach(i -> {
//            try {
//                arr.add(
//                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//                        .collect(Collectors.toList())
//                );
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        });

//        countSort(arr);

        bufferedReader.close();

    }
}
