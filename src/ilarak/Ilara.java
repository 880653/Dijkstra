package ilarak;

public interface Ilara<E> {

	// Inserts the specified element into this queue if it is possible to do so
	// immediately without violating capacity restrictions, returning true upon
	// success and throwing an IllegalStateException if no space is currently available.
	public boolean add(E elem) throws Exception;

	// Retrieves, but does not remove, the head of this queue, or returns null if this
	// queue is empty.
	public E peek();

	// Retrieves and removes the head of this queue, or returns null if this queue is empty.
	public E poll();
	
	// Tests if this queue is empty.
	public boolean empty();
}
