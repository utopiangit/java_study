
public class SampleComplex {

	public static void main(String[] args) {
		Complex one = Complex.valueOf(1, 0);
		Complex i = Complex.valueOf(0, 1);
		Complex c = Complex.plus(one, i);
		System.out.println(c.toString());

	}

}
