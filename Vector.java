package amir;

import java.util.ArrayList;

/**
 * clasa care implementeaza Vector
 * 
 * @author Amir
 */
public class Vector {
	public ArrayList<Node> v;

	public Vector() {
		v = new ArrayList<>();
	}

	public int size() {
		return v.size();
	}

	public void add(Node n) {
		v.add(n);
	}

	public void sterg(Node n) {
		boolean remove = v.remove(n);
	}

	public void sterge_tot() {
		v.clear();
	}
}
