package TDACola;

import Exceptions.EmptyQueueException;

public class ColaConArreglo<E> implements Queue<E> {

	private E[] arreglo;
	private int front;
	private int rear;
	
	public ColaConArreglo(){
		front=0;
		rear=0;
		arreglo= (E[]) new Object[20];
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return rear-front;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return rear==front;
	}

	@Override
	public E front() throws EmptyQueueException {
		// TODO Auto-generated method stub
		if(isEmpty())
				throw new EmptyQueueException("No se puede consultar el frente de una cola vacia");
		return arreglo[front];
	}

	@Override
	public void enqueue(E element) {
		arreglo[rear]=element;
		rear++;
		if(rear==arreglo.length)
			agrandarArreglo();
		
	}

	private void agrandarArreglo() {
		E[] nuevo= (E[])new Object[arreglo.length*2];
		int indiceNuevo=0;
		for(int i=front;i<rear;i++) {
			nuevo[indiceNuevo]=arreglo[i];
			indiceNuevo++;}
		front=0;
		rear=indiceNuevo;
		arreglo=nuevo;
	}
	
	@Override
	public E dequeue() throws EmptyQueueException {
		// TODO Auto-generated method stub
		if(isEmpty())
			throw new EmptyQueueException("No se puede desencolar de una cola vacia");
		E toRet=arreglo[front];
		arreglo[front]=null;
		front++;
		return toRet;
	}

}
