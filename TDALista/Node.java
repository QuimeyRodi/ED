package TDALista;

import Auxiliares.Position;

public class Node<E> implements Position<E> {
	
	protected E element;
	protected Node<E> next;
	
	public Node(){
		this(null,null);
	}
	public Node(E e, Node<E> n){
		element=e;
		next=n;
	}
	public E element(){
		return element;
	}
	public Node<E> getSiguiente(){
		return next;
	}
	public void setElement(E newElem){
		element=newElem;
	}
	public void setSiguiente(Node<E> newNext){
		next=newNext;
	}

}
