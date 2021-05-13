package dijsktra;

import ilarak.Ilara;
import maps.Errepidea;

public class MugakoIlara implements Ilara<Errepidea> {

	private static class Nodo {
		Errepidea info;
		Nodo next;
		Nodo previous;

		public Nodo(Errepidea elem) {
			this.info = elem;
		}
	}

	private Nodo front, rear;

	public MugakoIlara() {
		this.front = null;
		this.rear = null;
	}

	// Ilara hutsa dagoen edo ez
	@Override
	public boolean empty() {
		// front = null <-> rear = null
		return this.front == null;
	}

	// Lehenengo balioa itzultzen du, borratu gabe
	@Override
	public Errepidea peek() {
		if (this.front == null)
			return null;
		else
			return this.front.info;
	}

	// Lehenengo balioa itzultzen eta borratzen du
	@Override
	public Errepidea poll() {
		if (this.front == null)
			return null;
		else {
			Errepidea aurreko = this.front.info;
			this.front = this.front.next;
			//
			this.front.previous = null;
			return aurreko;
		}
	}

	@Override
	public boolean add(Errepidea elem) {
		Nodo newNode = new Nodo(elem);
		Nodo korri = this.rear;
		if (this.front == null) {
			this.front = newNode;
			this.rear = newNode;
		} else {
			this.rear.next = newNode;
			this.rear = newNode;
			this.rear.previous = korri;
		}
		return true;
	}

	public String toString() {

		String emaitza = "Front-etik rear-era: [";
		Nodo korri = this.front;
		while (korri != null) {
			emaitza += korri.info;
			korri = korri.next;
		}

		emaitza += "]\nRear-etik front-era: ";

		emaitza += "[";
		korri = this.rear;
		while (korri != null) {
			emaitza += korri.info;
			korri = korri.previous;
		}
		emaitza += "]";
		return emaitza;

	}

	public void addSorted(Errepidea elem) {
		
		Nodo newNode = new Nodo(elem);
		Nodo korri = this.rear;
		Nodo korri2 = this.front;

		if (korri2 == null || korri.info.luzera < elem.luzera) {
			add(elem);

		} else if (this.front.info.luzera > newNode.info.luzera) {
			this.front.previous = newNode;
			this.front = newNode;
			this.front.next = korri2;

		} else {

			while ((korri.previous != null) && (newNode.info.luzera < korri.previous.info.luzera)) {
				korri = korri.previous;
			}

			newNode.previous = korri.previous;
			newNode.previous.next = newNode;
			newNode.next = korri;
			newNode.next.previous = newNode;

		}
	}
}
