
public class MainPlanet {

	public static void main(String[] args) {
		double earthWeight = 10.0;
		double mass = earthWeight / Planet.EARTH.surfaceGravity();
		for (Planet p : Planet.values())
			System.out.printf("Weight on %s is %f%n", p, p.surfaceWeight(mass));

	}

	public enum Planet {
		MERCURY(3.3e+23, 2.4e+6),
		VENUS(4.9e+24, 6.1e+6),
		EARTH(6.0e+24, 6.4e+6),
		MARS(6.4e+23, 3.4e+6),
		JUPITER(1.9e+27, 7.1e+7);


		private final double mass;
		private final double radius;
		private final double surfaceGravity;

		private static final double G = 6.673E-11;

		Planet(double mass, double radius) {
			this.mass = mass;
			this.radius = radius;
			this.surfaceGravity = G * mass / (radius * radius);
		}

		public double mass() { return mass; }
		public double radius() { return radius; }
		public double surfaceGravity() { return surfaceGravity; }
		public double surfaceWeight(double mass) {
			return mass * surfaceGravity;
		}
	}

}
