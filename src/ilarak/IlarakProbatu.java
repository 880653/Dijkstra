package ilarak;

import java.util.Scanner;

public class IlarakProbatu {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Ilara<Integer> gureIlara = new ArrayIlara<Integer>();
		int agindua;
		Integer balioa;
		System.out.println("Ilarak egiaztatzeko programa. Ilara huts berria sortu da.\n");
		boolean amaitu = false;
		
		do {
			System.out.println("Aukera ezazu burutu nahi duzun eragiketa:\n"
					+ "\t0. Amaitu saioa\n\t1. Ilaratu elementu bat (add)\n"
					+ "\t2. Desilaratu elementu bat (poll)\n\t3. Ilarako lehenengoa idatzi (peek)\n"
					+ "\t4. Esan ilara hutsa dagoen ala ez (empty)");

			agindua = input.nextInt();
			try {
				switch (agindua) {
					case 0: amaitu = true;
							System.out.println("Agur ba\n");
							break;
					case 1: System.out.println("Mesedez idatzi ilaratu beharreko balio osoa\n");
							balioa = input.nextInt();
							boolean denaOndo = gureIlara.add(balioa);
							break;
					case 2: balioa = gureIlara.poll();
							if (balioa == null)
								System.out.println("Ilara hutsik dago");
							else
								System.out.println(balioa + " desilaratu da\n");
							break;
					case 3:	 balioa = gureIlara.peek();
							if (balioa == null)
								System.out.println("Ilara hutsik dago");
							else
								System.out.println("Lehenengo elementua " + balioa + " da\n");
							break;
					case 4: System.out.println("Ilara hutsik al dago?: " + gureIlara.empty() + "\n");
							break;
					default: System.out.println("Aukera baliogabea\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!amaitu)
				System.out.println("Ilara ondoko egoeran dago:\n" + gureIlara + "\n\n");
			
		} while (!amaitu);
		input.close();
	}
}