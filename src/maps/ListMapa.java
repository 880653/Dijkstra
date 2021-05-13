package maps;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class ListMapa implements Mapa {

	protected final int N;
	protected int M;
	private List<Errepidea>[] errepideLists;


	public ListMapa(int numVertices) {
		if (numVertices < 0) throw new IllegalArgumentException("Erpin-kopurua "
				+ "ezin daiteke negatiboa izan: " + numVertices);
		this.N = numVertices;
		this.M = 0;
		setErrepideLists((List<Errepidea>[]) new LinkedList[numVertices]);
		for (int v = 0; v < numVertices; v++) {
			getErrepideLists()[v] = new LinkedList<Errepidea>();
		}
	}

	/**
	 * Mapa hasieratzen da emandako input-iturritik
	 * Formatoa ondokoa da:
	 *
	 * N
	 * M
	 * v11 v12 l1
	 * v21 v22 l2
	 * ...
	 * vM1 vM2 lM
	 */

	public ListMapa(Scanner input) {
		try {
			N = input.nextInt();
			if (N < 0) throw new IllegalArgumentException("Erpin-kopurua "
					+ "ezin daiteke negatiboa izan: " + N);

			setErrepideLists((List<Errepidea>[]) new LinkedList[N]);
			for (int v = 0; v < N; v++) {
				getErrepideLists()[v] = new LinkedList<Errepidea>();
			}


			int numEdges = input.nextInt();
			if (numEdges < 0) throw new IllegalArgumentException("Number of edges in "
					+ "a Graph must be nonnegative");

			for (int i = 0; i < numEdges; i++) {
				
				int v = input.nextInt();
				int w = input.nextInt();
				float x = input.nextFloat();
				

				addEdge(v, w, x);

			}
			M = numEdges;
		}
		catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Grafoa eraikitzeko sarrerak "
					+ "ez dauka behar bezalako formatua", e);
		}
	}
	
	@Override
	public int numVertices() {
		return N;
	}

	
	@Override
	public int numEdges() {
		return M;
	}

	void egiaztatuDistantzia(float d) {
		if(d <= 0) {
			throw new IllegalArgumentException(d + " distantziak zenbaki positiboa izan behar du");
		}
	}
	
	// Egiaztatu 0 <= v < V}
	void egiaztatuErpina(int v) {
		if (v < 0 || v >= N)
			throw new IllegalArgumentException(v + " erpinak ez dauka zenbaki zuzena, "
					+ "0 eta " + (N-1) + " ez baitago.");
	}

	@Override
	abstract public void addEdge(int v, int w, float distantzia);

	@Override
	public List<Errepidea> adjacentsOf (int v) {
		egiaztatuErpina(v);
		return getErrepideLists()[v];
	}

	@Override
	public int degree(int v) {
		egiaztatuErpina(v);
		return getErrepideLists()[v].size();
	}

	@Override
	public int inDegree(int v) {
		egiaztatuErpina(v);
		int emaitza = 0;
		for (List<Errepidea> lista : getErrepideLists())
			if (lista.contains(v))
				emaitza++;
		return emaitza;
	}
	


	@Override
	public String toString() {
		return "ListMapa [N=" + N + ", M=" + M + ", errepideLists=" + Arrays.toString(getErrepideLists())
				+ ", numVertices()=" + numVertices() + ", numEdges()=" + numEdges() + "]\n";
	}

	public List<Errepidea>[] getErrepideLists() {
		return errepideLists;
	}

	public void setErrepideLists(List<Errepidea>[] errepideLists) {
		this.errepideLists = errepideLists;
	}

	//abstract public String toString();
	
	
}