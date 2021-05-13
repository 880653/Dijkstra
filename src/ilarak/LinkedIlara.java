package ilarak;

public class LinkedIlara<E> implements Ilara<E>{
	
	private static class Nodo<E> {
		E info;
		Nodo<E> next;
		
		public Nodo (E elem) {
			this.info = elem;
		}
	}
	
	private Nodo<E> front, rear;
	
	public LinkedIlara() {
		this.front = null;
		this.rear = null;
	}

	@Override
	public boolean empty() {
		// front = null <-> rear = null
		return this.front == null;
	}

	@Override
	public E peek() {
		if (this.front == null)
			return null;
		else
			return this.front.info;
	}

	@Override
	public E poll() {
		if (this.front == null)
			return null;
		else {
			E aurreko = this.front.info;
			this.front = this.front.next;
			return aurreko;
		}
	}

	@Override
	public boolean add(E elem) {
		Nodo<E> newNode = new Nodo<E>(elem);
		if (this.front == null) {
			this.front = newNode;
			this.rear = newNode;
		}
		else {
			this.rear.next = newNode;
			this.rear = newNode;
		}
		return true;
	}
	
	public String toString () {
		String emaitza = "[";
		Nodo<E> korri = this.front;
		while (korri != null) {
			emaitza += " " + korri.info;
			korri = korri.next;
		}
		emaitza += " ]";
		return emaitza;
	}

}
