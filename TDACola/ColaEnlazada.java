package TDACola;

import Exceptions.EmptyQueueException;

public class ColaEnlazada<E> implements Queue<E> {

	private Nodo<E> front;
	private Nodo<E> rear;
	private int size;
	
	public ColaEnlazada() {
		rear=null;
		front=null;
		size=0;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	@Override
	public E front() throws EmptyQueueException {
		// TODO Auto-generated method stub
		if(size==0)
			throw new EmptyQueueException("No se puede consultar el frente de una cola vacia");
		return front.getElemento();
	}

	@Override
	public void enqueue(E element) {
		// TODO Auto-generated method stub
		Nodo<E> nuevo= new Nodo<E>(element);
		if(size==0) {
			front=nuevo;
			rear=nuevo;}
		else {
			rear.setSiguiente(nuevo);
			rear=nuevo;}
		size++;
		
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		// TODO Auto-generated method 
		if(size==0)
			throw new EmptyQueueException("No se puede desencolar de una cola vacia");
		E toRet=front.getElemento();
		front=front.getSiguiente();
		size--;	
		return toRet;
	}

}
