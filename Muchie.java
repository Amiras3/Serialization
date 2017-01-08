package amir;

/**
 * operatii muchie
 * 
 * @author Amir
 *
 */
public class Muchie {
	/**
	 * adauga muchie
	 * 
	 * @param a
	 * @param b
	 */
	public void adauga(Node a, Node b) {
		a.adiacenta(b);
		b.adiacenta(a);
	}

	/**
	 * sterge muchie
	 * 
	 * @param a
	 * @param b
	 */
	public void sterge(Node a, Node b) {
		a.elim(b);
		b.elim(a);
	}
}