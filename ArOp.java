package amir;

import java.util.ArrayList;

/**
 * operatii cu vectori
 * 
 * @author Amir
 */

public class ArOp {
	/**
	 * cauta nod in vector dupa nume
	 * 
	 * @param a
	 * @param size
	 * @param name
	 * @return
	 */
	public int cauta(Node[] a, int size, String name) {
		for (int i = 0; i < size; i++) {
			if (!name.equals(a[i].name)) {
			} else {
				return i;
			}
		}
		return -1;
	}

	/**
	 * cauta nod in vector dupa id
	 * 
	 * @param a
	 * @param size
	 * @param id
	 * @return
	 */
	public int cauta_id(Node[] a, int size, int id) {
		for (int i = 0; i < size; i++) {
			if (a[i].id == id) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * sterge nodul de pe pozitia index
	 * 
	 * @param a
	 * @param size
	 * @param index
	 */
	public void sterge(Node[] a, int size, int index) {
		a[index] = a[size - 1];
		a[size - 1] = null;
	}
}