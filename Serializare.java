package amir;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * clasa care implementeaza Serializarea
 * 
 * @author Amir
 */
public class Serializare {
	public int id = 0;

	/**
	 * functia de dfs - afiseaza in adancime graful in formatul cerut
	 * 
	 * @param write
	 * @param start_node
	 * @param nr_taburi
	 * @throws IOException
	 */
	public void dfs(BufferedWriter write, Node start_node, int nr_taburi) throws IOException {
		start_node.id = id;
		id++;
		start_node.folosit = true;
		for (int i = 1; i <= nr_taburi; i++) {
			write.write("\t"); // afiseaza numarul de taburi necesare
		}
		start_node.afiseaza_string_nod(write);
		for (int i = 1; i <= nr_taburi + 1; i++) {
			write.write("\t");
		}
		write.write("<Nume>" + start_node.name + "<\\Nume>");
		write.newLine();
		if (start_node.version == 1) { // daca versiunea este in lista se face
										// recursiv dfs in ce e in lista
			for (int i = 1; i <= nr_taburi + 1; i++) {
				write.write("\t");
			}
			write.write("<LIST>");
			write.newLine();
			for (Node n : start_node.list.l) {
				if (n.folosit == true) {
					for (int i = 1; i <= nr_taburi + 2; i++) {
						write.write("\t");
					}
					n.afiseaza_string_nod_reference(write);
				} else {
					dfs(write, n, nr_taburi + 2);
				}
			}
			for (int i = 1; i <= nr_taburi + 1; i++) {
				write.write("\t");
			}
			write.write("<\\LIST>");
			write.newLine();
		}
		if (start_node.version == 2) {
			for (int i = 1; i <= nr_taburi + 1; i++) {
				write.write("\t");
			}
			write.write("<VECTOR>");
			write.newLine();
			for (Node n : start_node.vect.v) { // daca versiunea este vector se
												// face recursiv dfs in ce este
												// in vector
				if (n.folosit == true) {
					for (int i = 1; i <= nr_taburi + 2; i++) {
						write.write("\t");
					}
					n.afiseaza_string_nod_reference(write);
				} else {
					dfs(write, n, nr_taburi + 2);
				}
			}
			for (int i = 1; i <= nr_taburi + 1; i++) {
				write.write("\t");
			}
			write.write("<\\VECTOR>");
			write.newLine();
		}
		if (start_node.version == 3) {// daca versiunea este in set se face
										// recursiv dfs in ce este in vector
			for (int i = 1; i <= nr_taburi + 1; i++) {
				write.write("\t");
			}
			write.write("<SET>");
			write.newLine();
			for (Node n : start_node.set.s) {
				if (n.folosit == true) {
					for (int i = 1; i <= nr_taburi + 2; i++) {
						write.write("\t");
					}
					n.afiseaza_string_nod_reference(write);
				} else {
					dfs(write, n, nr_taburi + 2);
				}
			}
			for (int i = 1; i <= nr_taburi + 1; i++) {
				write.write("\t");
			}
			write.write("<\\SET>");
			write.newLine();
		}
		for (int i = 1; i <= nr_taburi; i++) {
			write.write("\t");
		}
		write.write("<\\Object>");
		write.newLine();
	}
}