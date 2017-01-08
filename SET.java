package amir;

import java.util.HashSet;
import java.util.Set;

/**
 * clasa care implementeaza SET
 * 
 * @author Amir
 */
public class SET {
	public Set<Node> s;

	public SET() {
		s = new HashSet<>();
	}

	public int size() {
		return s.size();
	}

	public void add(Node n) {
		s.add(n);
	}

	public void sterg(Node n) {
		boolean remove = s.remove(n);
	}

	public void sterge_tot() {
		s.clear();
	}
}