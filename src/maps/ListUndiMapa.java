package maps;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ListUndiMapa extends ListMapa implements UndiMapa {

	public ListUndiMapa(int numVertices) {
		super(numVertices);
	}

	public ListUndiMapa(Scanner input) {
		super(input);
	}

	public void addEdge(int v, int w, float luzera) {
		
		egiaztatuDistantzia(luzera);
		egiaztatuErpina(v);
		egiaztatuErpina(w);
		
		if (v==w)
			throw new NoSuchElementException(String.format("Ezin da definitu"
					+ " jatorri eta helburu berdinak duen arkua (%d)", v));
		if (!getErrepideLists()[v].contains(w)) {
			Errepidea a = new Errepidea(v, luzera);
			Errepidea b = new Errepidea(w, luzera);
			getErrepideLists()[v].add(b);
			getErrepideLists()[w].add(a);
			this.M++;
		}
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(N + " erpin, " + M + " ertz\n");
		for (int v = 0; v < N; v++) {
			s.append(v + ": ");
			for (Errepidea w : getErrepideLists()[v]) {
				s.append(w + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}

	@Override
	public boolean connected() {
		boolean[] markatua = new boolean[this.N];
		connected(0, markatua);
		for (int i=0; i<N; i++) {
			if (!markatua[i])
				return false;
		}
		return false;
	}
	
	


	private void connected(int i, boolean[] markatua) {
		markatua[i] = true;
		//for (int w : adjacentsOf(i))
		for (int w = 0; i<adjacentsOf(i).size(); i++)
			if (!markatua[w])
				connected(w, markatua);
	}
	
}
