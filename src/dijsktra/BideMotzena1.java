package dijsktra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import maps.ListUndiMapa;

public class BideMotzena1 {

	public ListUndiMapa mapa;
	private boolean[] bukatutakoa;
	private float[] disMin;

	// bidea inprimitu ahal izateko sortu dudan array-a, bertan, nodo bakoitzaren
	// aurrekoa gordetzen da
	private int[] pre;

	public BideMotzena1(Scanner iturria) throws FileNotFoundException {

		mapa = new ListUndiMapa(iturria);

		bukatutakoa = new boolean[mapa.numVertices()];

		disMin = new float[mapa.numVertices()];
		Arrays.fill(disMin, Float.POSITIVE_INFINITY);

		pre = new int[mapa.numVertices()];

	}

	public ListUndiMapa getMapa() {
		return mapa;
	}

	public void setMapa(ListUndiMapa mapa) {
		this.mapa = mapa;
	}

	public boolean[] getBukatutakoa() {
		return bukatutakoa;
	}

	public void setBukatutakoa(boolean[] bukatutakoa) {
		this.bukatutakoa = bukatutakoa;
	}

	public float[] getDisMin() {
		return disMin;
	}

	public void setDisMin(float[] disMin) {
		this.disMin = disMin;
	}

	public int[] getPre() {
		return pre;
	}

	public void setPre(int[] pre) {
		pre = pre;
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
			int aurrekoa = jatorria;
			int nora = 0;
			float luzera = 0;

			finkatu(jatorria, 0, -1);

			// helburuko nodoa bukatu gabe dagoen bitartean: jarraitu!
			while (!bukatutakoa[helburua]) {

				float min = Float.POSITIVE_INFINITY;
				int lag = -1;

				// mugako nodoetara dagoen distantzia sartzen du disMin array-ean + distantzia
				// minimoa kalkulatzen du
				for (int i = 0; i < bukatutakoa.length; i++) {

					if (bukatutakoa[i]) {

						for (int j = 0; j < mapa.adjacentsOf(i).size(); j++) {

							if (!bukatutakoa[mapa.adjacentsOf(i).get(j).getNora()]) {

								nora = mapa.adjacentsOf(i).get(j).getNora();
								luzera = mapa.adjacentsOf(i).get(j).getLuzera() + disMin[i];

								disMin[nora] = luzera;

								if (min > luzera) {

									min = luzera;
									lag = nora;

									// bidea inprimitzeko beharko dugu
									aurrekoa = i;
								}
							}
						}
					}
				}

				// distantzia txikienera dagoen nodoa finkatzen da
				finkatu(lag, min, aurrekoa);

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

	public void egoera(BideMotzena1 bm) {

		for (int i = 0; i < bm.getMapa().numVertices(); i++) {

			System.out.println(i + ". erpina");
			System.out.println("Bukatuta? " + bm.getBukatutakoa()[i]);
			System.out.println("Distantzia minimoa: " + bm.getDisMin()[i]);
			System.out.println("");
		}
	}

	public void egoera2(BideMotzena1 bm) {

		for (int i = 0; i < bm.getMapa().numVertices(); i++) {

			System.out.println(i + ".ren aurrekoa: " + bm.getPre()[i] + " distantzia: " + bm.getDisMin()[i]);
			System.out.println("");
		}
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
			BideMotzena1 bidea = new BideMotzena1(input);

			System.out.println("Hau da landuko dugun mapa\n");
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

			// bidea.egoera2(bidea);

			System.out.println("");

			// Iraundutako denbora
			String denboPrint = bidea.printableTime(System.nanoTime() - orain);
			System.out.println("Iraundutako denbora: " + denboPrint);

			// egoera2(bidea);

		} catch (FileNotFoundException e) {
			System.out.println("Fitxategi hori ez da existitzen");
		}
	}
}
