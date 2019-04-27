import java.math.BigInteger;
import java.util.stream.Stream;

public class Mersenne {

	public static void main(String[] args) {
		primes()
			.map(p -> BigInteger.valueOf(2).pow(p.intValueExact()).subtract(BigInteger.ONE))
			.filter(mersenne -> mersenne.isProbablePrime(50))
			.limit(20)
			.forEach(System.out::println);
	}

	static Stream<BigInteger> primes() {
		return Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime);
	}


}
