package amir;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * implementeaza nod
 * 
 * @author Amir
 */
public class Node {
	public int id;
	public boolean folosit = false;
	public int version;
	public Vector vect;
	public List list;
	public SET set;
	public String name;

	/**
	 * constructor ;
	 */
	public Node() {

	}

	/**
	 * constructor cu parametrii pentru nume si versiune
	 * 
	 * @param name
	 * @param version
	 */
	public Node(String name, int version) {
		this.name = name;
		this.version = version;
		list = new List();
		vect = new Vector();
		set = new SET();
	}

	/**
	 * adauga n in lista de adiacenta
	 * 
	 * @param n
	 */
	public void adiacenta(Node n) {
		if (version == 1) {
			list.add(n);
		}
		if (version == 2) {
			vect.add(n);
		}
		if (version == 3) {
			set.add(n);
		}
	}

	/**
	 * elimina n din lista de adiacenta
	 * 
	 * @param n
	 */
	public void elim(Node n) {
		if (version == 1) {
			list.sterg(n);
		}
		if (version == 2) {
			vect.sterg(n);
		}
		if (version == 3) {
			set.sterg(n);
		}
	}

	/**
	 * cauta in lista de adiacenta dupa id
	 * 
	 * @param id
	 * @return
	 */
	public int cauta_id(int id) {
		if (version == 1) {
			if (list.l.isEmpty()) {
				return 0;
			}
			for (Node n : list.l) {
				if (n.id == id) {
					return 1;
				}
			}
		}
		if (version == 2) {
			if (vect.v.isEmpty()) {
				return 0;
			}
			for (Node n : vect.v) {
				if (n.id == id) {
					return 1;
				}
			}
		}
		if (version == 3) {
			if (set.s.isEmpty()) {
				return 0;
			}
			for (Node n : set.s) {
				if (n.id == id) {
					return 1;
				}
			}
		}
		return 0;
	}

	/**
	 * afiseaza stringul potrivit pentru fisierul de serializare
	 * 
	 * @param write
	 * @throws IOException
	 */
	public void afiseaza_string_nod(BufferedWriter write) throws IOException {
		write.write("<Object class=");
		if (this instanceof NodeA) {
			write.write("\"" + "NodA" + "\"" + " ");
		}
		if (this instanceof NodeB) {
			write.write("\"" + "NodB" + "\"" + " ");
		}
		if (this instanceof NodeC) {
			write.write("\"" + "NodC" + "\"" + " ");
		}
		write.write("Version=" + "\"" + version + "" + "\"" + " " + "id=" + "\"" + id + "" + "\"" + ">");
		write.newLine();
	}

	/**
	 * afiseaza stringul potrivit pentru fisierul de serializare - cazul
	 * reference
	 * 
	 * @param write
	 * @throws IOException
	 */
	public void afiseaza_string_nod_reference(BufferedWriter write) throws IOException {
		write.write("<Reference class=");
		if (this instanceof NodeA) {
			write.write("\"" + "NodA" + "\"" + " ");
		}
		if (this instanceof NodeB) {
			write.write("\"" + "NodB" + "\"" + " ");
		}
		if (this instanceof NodeC) {
			write.write("\"" + "NodC" + "\"" + " ");
		}
		write.write("Version=" + "\"" + version + "" + "\"" + " " + "id=" + "\"" + id + "" + "\"" + ">");
		write.newLine();
	}

	/**
	 * sterge nodul din listele de adiacenta ale celorlalte noduri
	 */
	public void sterge_nod_liste() {
		if (version == 1) {
			for (Node n : list.l) {
				n.elim(this);
			}
		}
		if (version == 2) {
			for (Node n : vect.v) {
				n.elim(this);
			}
		}
		if (version == 3) {
			for (Node n : set.s) {
				n.elim(this);
			}
		}
	}
}