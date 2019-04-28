import java.math.BigInteger;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Mersenne {

	public static void main(String[] args) {
/*		primes()
			.map(p -> BigInteger.valueOf(2).pow(p.intValueExact()).subtract(BigInteger.ONE))
			.filter(mersenne -> mersenne.isProbablePrime(50))
			.limit(20)
			.forEach(System.out::println);
*/
		System.out.println(pi(10000000));
	}

	static Stream<BigInteger> primes() {
		return Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime);
	}

	static long pi(long n) {
		return LongStream.rangeClosed(2, n)
				.parallel()
				.mapToObj(BigInteger::valueOf)
				.filter(i -> i.isProbablePrime(50))
				.count();
	}


}
