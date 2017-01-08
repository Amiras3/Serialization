package amir;

import java.util.LinkedList;

/**
 * implementare lista
 * 
 * @author Amir
 */

public class List {
	public LinkedList<Node> l;

	/**
	 * constructor lista
	 */
	public List() {
		l = new LinkedList<>();
	}

	/**
	 * lungime lista
	 * 
	 * @return
	 */
	public int size() {
		return l.size();
	}

	/**
	 * adauga in lista
	 * 
	 * @param n
	 */
	public void add(Node n) {
		l.add(n);
	}

	/**
	 * sterge din lista
	 * 
	 * @param n
	 */
	public void sterg(Node n) {
		boolean remove = l.remove(n);
	}

	/**
	 * sterge tot din lista
	 */
	public void sterge_tot() {
		l.clear();
	}
}