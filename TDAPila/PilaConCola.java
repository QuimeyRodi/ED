package TDAPila;

import Exceptions.EmptyQueueException;
import Exceptions.EmptyStackException;
import TDACola.*;

public class PilaConCola<E> implements Stack<E> {
	
	private Queue<E> cola;

	public PilaConCola() {
		cola= new ColaConArreglo<E>();
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return cola.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return cola.isEmpty();
	}

	@Override
	public E top() throws EmptyStackException {
		// TODO Auto-generated method stub
		if(cola.isEmpty())
			throw new EmptyStackException("No se puede consultar el tope de una pila vacia");
		E toRet=null;
		Queue<E> aux= new ColaConArreglo<E>();
		try {
			while(!cola.isEmpty()) {
				toRet=cola.dequeue();
				aux.enqueue(toRet);
			}
			cola=aux;
		} catch (EmptyQueueException e) {
			throw new EmptyStackException("No se puede consultar el tope de una pila vacia");
		}
		return toRet;
	}

	@Override
	public void push(E element) {
		cola.enqueue(element);
		
	}

	@Override
	public E pop() throws EmptyStackException {
		// TODO Auto-generated method stub
				if(cola.isEmpty())
					throw new EmptyStackException("No se puede desapilar de una pila vacia");
				E toRet=null;
				Queue<E> aux= new ColaConArreglo<E>();
				try {
					while(!cola.isEmpty()) {
						toRet=cola.dequeue();
						if(cola.size()!=0) // el último no lo quiero encolar
						 aux.enqueue(toRet);
					}
					cola=aux;
				} catch (EmptyQueueException e) {
					throw new EmptyStackException("No se puede desapilar el tope de una pila vacia");
				}
				return toRet;
	}

}
