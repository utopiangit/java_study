
public class Complex {
	private final double re;
	private final double im;

	private Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}

	public static Complex valueOf(
			double real,
			double imaginary) {
		return new Complex(real, imaginary);
	}

	public static Complex plus(Complex a, Complex b) {
		return valueOf(a.re + b.re, a.im + b.im);
	}

	public static Complex minus(Complex a, Complex b) {
		return valueOf(a.re - b.re, a.im - b.im);
	}

	public static Complex prod(Complex a, Complex b) {
		return valueOf(a.re * b.re - a.im * b.im, a.im * b.re + a.re * b.im);
	}

	@Override
	public int hashCode() {
		return 31 * Double.hashCode(re) + Double.hashCode(im);
	}

	@Override
	public String toString() {
		return "(" + re + " + " + im + "i)";
	}

}
