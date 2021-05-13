package maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ListUndiMapaProbatu {
	public static String printableTime (long nanos) {
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
		Scanner input = new Scanner(new File("inputs/mapaTxiki.txt"));
		ListUndiMapa m = new ListUndiMapa(input);

		System.out.println(m.toString());
		System.out.println(" ");
		
		
		System.out.println("4. erpinaren ondokoak");
		System.out.println(m.adjacentsOf(4));
		System.out.println(" ");
		
		
		m.addEdge(4, 12, 2);
		m.addEdge(4, 5, 2);
		
		
		System.out.println("4. erpinaren ondokoak, konexioak gehitu ondoren");
		System.out.println(m.adjacentsOf(4));
		System.out.println(" ");
		

		System.out.println("Grafoak bal al du ziklorik? : " + m.connected());
		System.out.println(" ");	

		
		m.addEdge(3, 12, 4);
		m.addEdge(10, 8, 3);
		m.addEdge(7, 5, 7);
		m.addEdge(5, 2, 1);
		m.addEdge(2, 1, 9);
		
		
		System.out.println("Konexioak gehitu ondoren, grafoak bal al du ziklorik? " + m.connected());
		System.out.println(" ");
		
		
		Scanner inp = new Scanner(new File("inputs/mapaTxiki.txt"));
		ListUndiMapa ziklo = new ListUndiMapa(inp);
		
		System.out.println("Grafo txikiak ba al du ziklorik? " + ziklo.connected());
		System.out.println(" ");
	
//		long orain = System.nanoTime();
//		System.out.println("Grafoak bal al du ziklorik? : " + g.ziklorik());
//		String denboPrint = printableTime(System.nanoTime()-orain);
//		System.out.println("Iraundutako denbora " + denboPrint + " behar du");
		

	}
}
