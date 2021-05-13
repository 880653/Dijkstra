package dijsktra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import maps.Errepidea;
import maps.ListUndiMapa;

public class BideMotzena2 {

	public ListUndiMapa mapa;
	private boolean[] bukatutakoa;
	private MugakoIlara mugakoak;

	// bidea inprimitu ahal izateko sortu dudan array-a, bertan, nodo bakoitzaren
	// aurrekoa gordetzen da
	private int[] pre;

	private float[] disMin;

	public BideMotzena2(Scanner iturria) throws FileNotFoundException {

		mapa = new ListUndiMapa(iturria);

		bukatutakoa = new boolean[mapa.numVertices()];

		disMin = new float[mapa.numVertices()];
		Arrays.fill(disMin, Float.POSITIVE_INFINITY);

		pre = new int[mapa.numVertices()];

		mugakoak = new MugakoIlara();

	}

	public ListUndiMapa getMapa() {
		return mapa;
	}

	public boolean[] getBukatutakoa() {
		return bukatutakoa;
	}

	public float[] getDisMin() {
		return disMin;
	}

	public float dijkstra(int jatorria, int helburua) throws ArrayIndexOutOfBoundsException {

		if (jatorria == helburua) {
			System.out.println("Jatorria eta helburua berdinak dira");
			return 0;
		} else if (jatorria < 0 || jatorria >= mapa.numVertices()) {
			System.out.println("Jatorriko erpina ez da existitzen");
			return -1;
		} else if (helburua < 0 || helburua >= mapa.numVertices()) {
			System.out.println("Helburuko erpina ez da existitzen");
			return -1;
		}

		try {

			int nora = 0;
			float luzera = 0;
			float min;
			int lag;
			Errepidea err;

			finkatu(jatorria, 0, -1);

			// jatorriaren ondokoak mugako ilaran sartu
			for (Errepidea k : mapa.adjacentsOf(jatorria)) {

				mugakoak.addSorted(k);

				pre[k.getNora()] = jatorria;

				disMin[k.getNora()] = k.getLuzera();

			}

			// helburuko nodoa bukatu gabe dagoen bitartean: jarraitu!
			while (!bukatutakoa[helburua]) {

				lag = mugakoak.peek().nora;

				if (!bukatutakoa[lag]) {

					min = mugakoak.peek().luzera;

					// distantzia txikienera dagoen nodoa finkatzen da
					finkatu(lag, min, pre[lag]);

					// finkatu berri dugunaren ondokoak mugako ilaran sartu
					for (Errepidea k : mapa.adjacentsOf(lag)) {

						if (!bukatutakoa[k.getNora()]) {

							nora = k.getNora();
							luzera = k.getLuzera() + disMin[lag];

							err = new Errepidea(nora, luzera);
							mugakoak.addSorted(err);
							if (disMin[nora] > luzera) {

								disMin[nora] = luzera;
								pre[nora] = lag;
							}
						}
					}
				} 
				else {
					mugakoak.poll();
				}
			}

			if (disMin[helburua] == Float.POSITIVE_INFINITY) {
				System.out.println("Ez dago bide posiblerik");
				return -1;

			} else {

				// bidea inprimatzen da
				bideaInprimitu(helburua, jatorria);
				return disMin[helburua];
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Ez dago bide posiblerik");
			return -1;
		}
	}

	public boolean bukatuDa() {

		for (int i = 0; i < bukatutakoa.length; i++) {
			if (!bukatutakoa[i]) {
				return false;
			}
		}
		return true;
	}

	public void finkatu(int lag, float min, int aurrekoa) {

		bukatutakoa[lag] = true;
		disMin[lag] = min;
		pre[lag] = aurrekoa;

		mugakoak.poll();

	}

	public void bideaInprimitu(int helburua, int jatorria) {

		String emaitza = helburua + "";
		int lag = pre[helburua];

		while (lag != jatorria) {
			emaitza = lag + " - " + emaitza;
			lag = pre[lag];
		}

		emaitza = "Bidea: " + jatorria + " - " + emaitza;

		System.out.println(emaitza);

	}

	public void egoera(BideMotzena2 bm) {

		for (int i = 0; i < bm.getMapa().numVertices(); i++) {

			System.out.println(i + ". erpina");
			System.out.println("Bukatuta? " + bm.getBukatutakoa()[i]);
			System.out.println("Distantzia minimoa: " + bm.getDisMin()[i]);
			System.out.println("");
		}
	}

	public void egoera2(BideMotzena2 bm) {

		for (int i = 0; i < bm.getMapa().numVertices(); i++) {

			System.out.println(i + ".ren aurrekoa: " + bm.getPre()[i] + " distantzia: " + bm.getDisMin()[i]);
			System.out.println("");
		}
	}

	public int[] getPre() {
		return pre;
	}

	public void setPre(int[] pre) {
		this.pre = pre;
	}

	public String printableTime(long nanos) {
		String result;
		if (nanos > 1000_000_0000L)
			result = String.format("%.2f seg", (double) nanos / 1000_000_000.);
		else if (nanos > 1000_000L)
			result = String.format("%.2f ms", (double) nanos / 1000_000.);
		else if (nanos > 1000L)
			result = String.format("%.2f μs", (double) nanos / 1000.);
		else
			result = String.format("%d ns", nanos);
		return result;
	}

	public static void main(String[] args) throws FileNotFoundException {

		try {

			Scanner input = new Scanner(new File("files/inputs/mapaAdibide.txt"));
			BideMotzena2 bidea = new BideMotzena2(input);

			System.out.println("Hau da landuko dugun mapa, mugako ilararekin\n");
			// System.out.println(bidea.getMapa().toString());

			// egoera(bidea);

			float dis;

			// jatorria, gure kasuan: 0
			int j = 0;

			// hasierako denbora
			long orain = System.nanoTime();

			// helburua(k), gure kasuan: mapako nodo guztiak
			for (int i = 0; i < bidea.getMapa().numVertices(); i++) {

				System.out.println(j + ". erpinetik " + i + ". erpinera dagoen distantzia minimoa");

				dis = bidea.dijkstra(j, i);

				if (dis != -1) {
					System.out.println(dis);
				}

				System.out.println("");
			}

			// Iraundutako denbora
			String denboPrint = bidea.printableTime(System.nanoTime() - orain);
			System.out.println("Iraundutako denbora: " + denboPrint);

		} catch (FileNotFoundException e) {
			System.out.println("Fitxategi hori ez da existitzen");
		}
	}
}
