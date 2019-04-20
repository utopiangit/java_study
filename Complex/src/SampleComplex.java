
public class SampleComplex {

	public static void main(String[] args) {
		Complex one = Complex.valueOf(1, 0);
		Complex i = Complex.valueOf(0, 1);
		//Complex c = Complex.plus(one, i);
		System.out.println(Complex.plus(one, i).toString());

		Complex a = Complex.valueOf(1, 1);
		Complex b = Complex.valueOf(2, -1);
		System.out.println(Complex.minus(a, b).toString());
		System.out.println(Complex.prod(a, b).toString());


	}

}
