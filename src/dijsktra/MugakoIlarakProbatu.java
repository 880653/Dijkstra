package dijsktra;

import java.util.Scanner;

import maps.Errepidea;

public class MugakoIlarakProbatu {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		MugakoIlara gureIlara = new MugakoIlara();
		int agindua, nora;
		float luzera;
		Errepidea balioa;
		System.out.println("Mugako ilarak egiaztatzeko programa. Mugako ilara huts berria sortu da.\n");
		boolean amaitu = false;

		do {
			System.out.println("Aukera ezazu burutu nahi duzun eragiketa:\n" + "\t0. Amaitu saioa\n"
					+ "\t1. Ilaratu elementu bat (add)\n" + "\t2. Ilaratu elementu bat ordenean (addSorted)\n"
					+ "\t3. Desilaratu elementu bat (poll)\n" + "\t4. Mugako ilarako lehenengoa idatzi (peek)\n"
					+ "\t5. Esan mugako ilara hutsa dagoen ala ez (empty)");

			agindua = input.nextInt();
			try {
				switch (agindua) {
				case 0:
					amaitu = true;
					System.out.println("Agur ba\n");
					break;
				case 1:
					System.out.println("Mesedez idatzi sartu beharreko errepidearen helburua");
					nora = input.nextInt();
					System.out.println("Eta orain errepidearen luzera\n");
					luzera = input.nextFloat();
					balioa = new Errepidea(nora, luzera);
					boolean denaOndo = gureIlara.add(balioa);
					break;
				case 2:
					System.out.println("Mesedez idatzi ordenean sartu beharreko errepidearen helburua");
					nora = input.nextInt();
					System.out.println("Eta orain errepidearen luzera\n");
					luzera = input.nextFloat();
					balioa = new Errepidea(nora, luzera);
					gureIlara.addSorted(balioa);
					break;
				case 3:
					balioa = gureIlara.poll();
					if (balioa == null)
						System.out.println("Ilara hutsik dago");
					else
						System.out.println(balioa + " desilaratu da\n");
					break;
				case 4:
					balioa = gureIlara.peek();
					if (balioa == null)
						System.out.println("Ilara hutsik dago");
					else
						System.out.println("Lehenengo elementua " + balioa.toString() + " da\n");
					break;
				case 5:
					System.out.println("Ilara hutsik al dago?: " + gureIlara.empty() + "\n");
					break;
				default:
					System.out.println("Aukera baliogabea\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!amaitu)
				System.out.println("Mugako ilara horrela dago:\n" + gureIlara.toString() + "\n\n");

		} while (!amaitu);
		input.close();
	}
}