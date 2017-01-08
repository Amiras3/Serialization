package amir;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * functia in care se afla mainul
 * 
 * @author Amir
 */
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 * @throws java.io.FileNotFoundException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader read;
		read = new BufferedReader(new FileReader("amir.in"));
		Node[] noduri = new Node[10000];
		int size_noduri = 0;
		ArOp op = new ArOp();
		int version1, version2, version3;
		String line;
		String[] cuvinte;
		line = read.readLine();
		cuvinte = line.split(" ");
		version1 = Integer.parseInt(cuvinte[1]);
		version2 = Integer.parseInt(cuvinte[2]);
		version3 = Integer.parseInt(cuvinte[3]);
		while ((line = read.readLine()) != null) {
			cuvinte = line.split(" ");
			if ("Add".equals(cuvinte[0])) {// adauga nod
				String type = cuvinte[1];
				String name = cuvinte[2];
				Node a;
				if ("NodA".equals(type)) {
					a = new NodeA(name, version1);
					noduri[size_noduri++] = a;
				}
				if ("NodB".equals(type)) {
					a = new NodeB(name, version2);
					noduri[size_noduri++] = a;
				}
				if ("NodC".equals(type)) {
					a = new NodeC(name, version3);
					noduri[size_noduri++] = a;
				}
				for (int i = 3; i < cuvinte.length; i++) {
					int indice = op.cauta(noduri, size_noduri, cuvinte[i]);
					noduri[size_noduri - 1].adiacenta(noduri[indice]);
					noduri[indice].adiacenta(noduri[size_noduri - 1]);
				}
			}
			if ("Del".equals(cuvinte[0])) {// sterge nod
				String name = cuvinte[2];
				int indice = op.cauta(noduri, size_noduri, name);
				noduri[indice].sterge_nod_liste();
				op.sterge(noduri, size_noduri, indice);
				size_noduri--;
			}
			if ("AddM".equals(cuvinte[0])) {// adauga muchie
				String name1 = cuvinte[1];
				String name2 = cuvinte[2];
				int index1 = 0, index2 = 0;
				index1 = op.cauta(noduri, size_noduri, name1);
				index2 = op.cauta(noduri, size_noduri, name2);
				Muchie m = new Muchie();
				m.adauga(noduri[index1], noduri[index2]);
			}
			if ("DelM".equals(cuvinte[0])) {// sterge muchie
				String name1 = cuvinte[1];
				String name2 = cuvinte[2];
				int index1 = 0, index2 = 0;
				index1 = op.cauta(noduri, size_noduri, name1);
				index2 = op.cauta(noduri, size_noduri, name2);
				Muchie m = new Muchie();
				m.sterge(noduri[index1], noduri[index2]);
			}
			if ("Settings".equals(cuvinte[0])) {// pune setings
				version1 = Integer.parseInt(cuvinte[1]);
				version2 = Integer.parseInt(cuvinte[2]);
				version3 = Integer.parseInt(cuvinte[3]);
			}
			if ("Serialize".equals(cuvinte[0])) {// serializeaza
				String name = cuvinte[1];
				String output_file = cuvinte[2];
				BufferedWriter br = new BufferedWriter(new FileWriter(output_file));
				int index = op.cauta(noduri, size_noduri, name);
				Serializare s = new Serializare();
				s.dfs(br, noduri[index], 0);
				for (int i = 0; i < size_noduri; i++) {
					noduri[i].folosit = false;
				}
				br.close();
			}
			if ("Deserialize".equals(cuvinte[0])) {// deserializeaza
				String input_file = cuvinte[1];
				String output_file = "Deserialize_" + input_file + "_Cast.log";
				BufferedReader br = new BufferedReader(new FileReader(input_file));
				BufferedWriter bw = new BufferedWriter(new FileWriter(output_file));
				Deserializare d = new Deserializare();
				noduri = d.construieste(br, bw, version1, version2, version3);
				size_noduri = d.numar_noduri ;
				br.close();
				bw.close();
			}
		}
		read.close();
	}
}