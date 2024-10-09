package TDAPila;
import Exceptions.*;

public class PilaEnlazada<E> implements Stack<E> {

	//atributos de instancia
	protected Nodo<E> tope;//referencia al tope de la pila
	protected int tamanio;

	public PilaEnlazada() {
		tope = null;
		tamanio = 0;
	}
	
	public int size() {
		return tamanio;
	}
	
	public boolean isEmpty() {
		return tamanio == 0;
	}
	
	public E top() throws EmptyStackException {
		if(tamanio == 0) {
			throw new EmptyStackException("No se puede consultar el tope de una pila vacia");
		}
		return tope.getElemento();
	}
	
	public void push(E element) {
		Nodo<E> nuevo = new Nodo<E>(element, tope);
		tope = nuevo;
		tamanio++;
	}

	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("No se puede consultar el tope de una pila vacia");
		}
		else {
			E toRet = tope.getElemento();
			tope = tope.getSiguiente();
			tamanio--;
			return toRet;
		}
	}

}