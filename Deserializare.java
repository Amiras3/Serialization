package amir;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * clasa care implementeaza Deserializarea
 * 
 * @author Amir
 */
public class Deserializare {
	public int numar_noduri ;
	/**
	 * intoarce primul index care nu e tab
	 * 
	 * @param s
	 * @return
	 */
	public int elim_taburi(String s) {
		int index = 0;
		while (s.charAt(index) == '\t') {
			index++;
		}
		return index;
	}

	/**
	 * verifica daca stringul respectiv are asociat un object
	 * 
	 * @param s
	 * @param index
	 * @return
	 */
	public boolean is_object(String s, int index) {
		if (s.charAt(index + 1) == 'O') {
			return true;
		}
		return false;
	}

	/**
	 * verifica daca stringul respectiv are asociat un reference object
	 * 
	 * @param s
	 * @param index
	 * @return
	 */
	public boolean is_reference(String s, int index) {
		if (s.charAt(index + 1) == 'R') {
			return true;
		}
		return false;
	}

	/**
	 * verifica daca se deschide o lista
	 * 
	 * @param s
	 * @param index
	 * @return
	 */
	public boolean is_lista(String s, int index) {
		if (s.charAt(index + 1) == 'L' || s.charAt(index + 1) == 'V' || s.charAt(index + 1) == 'S') {
			return true;
		}
		return false;
	}

	/**
	 * verifica daca se inchide o lista
	 * 
	 * @param s
	 * @param index
	 * @return
	 */
	public boolean is_end_lista(String s, int index) {
		if (s.charAt(index + 2) == 'L' || s.charAt(index + 2) == 'V' || s.charAt(index + 2) == 'S') {
			return true;
		}
		return false;
	}

	/**
	 * returneaza tipul nodului
	 * 
	 * @param s
	 * @param index
	 * @return
	 */
	public int get_type(String s, int index) {
		int i = index;
		while (s.charAt(i) != 'd') {
			i++;
		}
		i++;
		if (s.charAt(i) == 'A') {
			return 1;
		}
		if (s.charAt(i) == 'B') {
			return 2;
		}
		if (s.charAt(i) == 'C') {
			return 3;
		}
		return -1;
	}

	/**
	 * returneaza versiunea nodului
	 * 
	 * @param s
	 * @param index
	 * @return
	 */
	public int get_version(String s, int index) {
		int i = index;
		while (s.charAt(i) != 'n') {
			i++;
		}
		i += 3;
		return (s.charAt(i) - '0');
	}

	/**
	 * returneaza id-ul nodului
	 * 
	 * @param s
	 * @param index
	 * @return
	 */
	public int get_id(String s, int index) {
		int i = index, j = index;
		while (s.charAt(i) != 'd') {
			i++;
		}
		i++;
		while (s.charAt(i) != 'd') {
			i++;
		}
		i += 3;
		j = i;
		while (s.charAt(j) != '"') {
			j++;
		}
		return Integer.parseInt(s.substring(i, j));
	}

	/**
	 * returneaza numele nodului
	 * 
	 * @param s
	 * @param index
	 * @return
	 */
	public String get_name(String s, int index) {
		int i = index, j = index + 1;
		while (s.charAt(i) != '>') {
			i++;
		}
		while (s.charAt(j) != '<') {
			j++;
		}
		return s.substring(i + 1, j);
	}

	/**
	 * construieste graful prezent in fisier
	 * 
	 * @param r
	 * @param w
	 * @param versionA
	 * @param versionB
	 * @param versionC
	 * @return
	 * @throws IOException
	 */
	public Node[] construieste(BufferedReader r, BufferedWriter w, int versionA, int versionB, int versionC)
			throws IOException {
		ArOp op = new ArOp();
		Node[] noduri = new Node[1000]; // retin noul graf
		Node[] adancimi = new Node[1000]; // retirn in ad..[i] ultimul nod gasit
											// la adancimea i
		String line, name;
		int type = 0, lversion = 0, cversion = 0, id = 0, adancime = 0, size_noduri = 0;
		while ((line = r.readLine()) != null) {
			int i = elim_taburi(line);
			if (is_object(line, i)) {
				type = get_type(line, i);
				lversion = get_version(line, i);
				id = get_id(line, i);
				line = r.readLine();
				int i1 = elim_taburi(line);
				name = get_name(line, i1);
				Node a = null; // urmeaza initializarea lui a si castul
				if (type == 1) {
					cversion = versionA;
					a = new NodeA(name, lversion);
					a.id = id;
				}
				if (type == 2) {
					cversion = versionB;
					a = new NodeB(name, lversion);
					a.id = id;
				}
				if (type == 3) {
					cversion = versionC;
					a = new NodeC(name, lversion);
					a.id = id;
				}
				if (lversion < cversion) {
					w.write("OK cast" + " ");
					a.version = cversion;

				}
				if (cversion < lversion) {
					w.write("FAIL cast" + " ");
				}
				if (cversion != lversion) {
					if (type == 1) {
						w.write("NodA " + name + " from Version=\"" + lversion + "\"" + " to Version=\"" + cversion
								+ "\"");
						w.newLine();
					}
					if (type == 2) {
						w.write("NodB " + name + " from Version=\"" + lversion + "\"" + " to Version=\"" + cversion
								+ "\"");
						w.newLine();
					}
					if (type == 3) {
						w.write("NodC " + name + " from Version=\"" + lversion + "\"" + " to Version=\"" + cversion
								+ "\"");
						w.newLine();
					}
				}
				noduri[size_noduri++] = a; // adaugal pe in in vectorul de
											// noduri
				if (adancime != 0) {
					Node c = adancimi[adancime - 1]; // c este parintele lui a
					if (c.cauta_id(a.id) == 0) {
						c.adiacenta(a);
					}
					if (a.cauta_id(c.id) == 0) {
						a.adiacenta(c);
					}
				}
				adancimi[adancime] = a;
			}
			if (is_reference(line, i)) {
				id = get_id(line, i);
				int index = op.cauta_id(noduri, size_noduri, id);
				Node c = noduri[index];
				Node d = adancimi[adancime - 1]; // d este parintele lui c
				if (c.cauta_id(d.id) == 0) {
					c.adiacenta(d);
				}
				if (d.cauta_id(c.id) == 0) {
					d.adiacenta(c);
				}
				adancimi[adancime] = c;

			}
			if (is_lista(line, i)) {
				adancime++;
			}
			if (is_end_lista(line, i)) {
				adancime--;
			}

		}
		numar_noduri = size_noduri ;
		return noduri;
	}
}
