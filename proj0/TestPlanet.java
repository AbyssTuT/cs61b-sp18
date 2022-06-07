/**
 *  Tests Planet
 */
public class TestPlanet {
    /**
     *  Tests planet: creates two planets and prints out the pairwise force between them.
     */
    public static void main(String[] args) {
        Planet a = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet b = new Planet(2.0, 2.0, 3.0, 4.0, 8.0, "jupiter.gif");
		System.out.println(a.calcForceExertedBy(b));
    }
	
}
