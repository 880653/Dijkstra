package ilarak;

public class ArrayIlara<E> implements Ilara<E> {

	private static int DEFAULT_SIZE = 5;
	private E[] elems;
	// this.front-1 <= this.rear
	// empty(this) <-> this.rear = this.front-1
	// size(this) = this.rear - this.front + 1
	// ∀ i (front<=i<=rear -> kokapena(i) = i % elems.length)
	private int front, rear, size;

	private int kokapena(int i) {
		return (i % this.elems.length);
	}

	public ArrayIlara() {
		elems = (E[]) new Object[DEFAULT_SIZE];
		front = 0;
		rear = -1;
		size = 0;
	}

	private void resize() {
		int currentSize = this.elems.length;
		E[] biltegiBerri = (E[]) new Object[2*currentSize];
		for (int i=0; i< currentSize; i++)
			biltegiBerri[i] = this.elems[kokapena(front + i)];
		this.elems = biltegiBerri;
		this.front = 0;
		this.rear = this.size - 1;
	}

	@Override
	public boolean add(E elem) throws Exception {
		if (this.size == this.elems.length)
			this.resize();

		this.rear++;
		this.elems[kokapena(rear)] = elem;
		this.size++;
		return true;
	}

	@Override
	public E peek() {
		if (this.rear == this.front-1)
			return null;
		else
			return this.elems[kokapena(front)];
	}

	@Override
	public E poll() {
		if (this.size == 0)
			return null;
		else {
			E aurreko = this.elems[kokapena(front)];
			this.front++;
			this.size--;
			return aurreko;
		}
	}

	@Override
	public boolean empty() {
		return (this.size == 0);
	}

	public String toString () {
		String emaitza = "[";
		for (int i=this.front; i<=this.rear; i++)
			emaitza += " " + this.elems[kokapena(i)];
		emaitza += " ]";
		return emaitza;
	}

}
