package TDAPila;

import Exceptions.EmptyStackException;

public class PilaConArreglo<E> implements Stack<E> {
	
	private E[] arreglo;
	private int tope; //tope apunta al primer lugar disponible
	
	public PilaConArreglo() {
		tope=0;
		arreglo=(E[]) new Object[50];	
	}

	@Override
	public int size() {
		return tope;
	}

	@Override
	public boolean isEmpty() {
		return tope==0;
	}

	@Override
	public E top() throws EmptyStackException {
		if(tope==0)
				throw new EmptyStackException("No se puede consultar el tope de una pila vacía");
		return arreglo[tope-1];
	}

	@Override
	public void push(E element) {
		/*if(tope==arreglo.length)//no tengo mas lugar
			agrandarArreglo();*/
		arreglo[tope]=element;
		tope++;
		if(tope==arreglo.length)//no tengo mas lugar
			agrandarArreglo();
		
	}

	private void agrandarArreglo() {
		E[] arregloNuevo=(E[]) new Object[arreglo.length*2]; //creo un nuevo arreglo con el doble del tamaño	
		for(int i=0;i<tope;i++)
			arregloNuevo[i]=arreglo[i];
		arreglo=arregloNuevo;
	}

	@Override
	public E pop() throws EmptyStackException {
		if(tope==0)
			throw new EmptyStackException("No se puede desapilar de una pila vacía");
		E toRet= arreglo[tope-1];
		arreglo[tope-1]=null;
		tope--;
		return toRet;
	}

}
