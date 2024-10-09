package TDALista;

import java.util.Iterator;

import Auxiliares.Position;
import Exceptions.*;

public class ListaSimpEnlazada <E> implements PositionList <E> {

	protected Node<E> head;
	protected int size;
	
	public ListaSimpEnlazada(){
		head=null;
		size=0;
	}
	
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size==0;
	}
	
	public Position<E> first() throws EmptyListException{
		if(isEmpty()){
			throw new EmptyListException("Lista vacia");
		}
		return head;
	}

	
	public Position<E> last() throws EmptyListException {
		if(isEmpty()){
			throw new EmptyListException("Lista vacia");
		}
		Node<E> aux = head;
		while(aux.getSiguiente()!=null){
			aux=aux.getSiguiente();
		}
		return aux;
	}

	
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Node<E> n = checkPosition(p);
		if(n.getSiguiente() == null){
			throw new BoundaryViolationException("limite");
		}
		return n.getSiguiente();
	}

	
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Node<E> n= checkPosition(p);
		if(p == head){
			throw new BoundaryViolationException("Limite");
		}
		
		Node<E> aux= head;
		while(aux!= null && aux.getSiguiente() != n  ){
			aux=aux.getSiguiente();
		}
		if(aux==null ){
			throw new InvalidPositionException("Posicion Invalida");
		}
		return aux;
	}

	
	public void addFirst(E element) {
		Node<E> nuevo = new Node<E>(element,head);
		head=nuevo;
		size++;
	}

	
	public void addLast(E element) {
		if(isEmpty()){
			addFirst(element);
		}
		else{
			Node<E> p = head;
			while(p.getSiguiente()!=null){
				p=p.getSiguiente();
			}
			p.setSiguiente(new Node<E> (element,null));
			size++;
		}
	}

	
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Node<E> n = checkPosition(p);
		Node<E> nuevo=new Node<E>(element,n.getSiguiente());
		n.setSiguiente(nuevo);
		size++;
	}

	
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Node<E> n=checkPosition(p);
		if(n == head){
			Node<E> nuevo= new Node<E>(element,n);
			head=nuevo;
			size++;
		}
		else{
			Node<E> cursor=head;
			while(cursor.getSiguiente()!=n && cursor!=null) {
				cursor=cursor.getSiguiente();
			}
			if(cursor==null)
				throw new InvalidPositionException("Posicion invalida ");
			
			Node<E> nuevo= new Node<E>(element, n);
			cursor.setSiguiente(nuevo);
			size++;
			
			
		}
	}

	
	public E remove(Position<E> p) throws InvalidPositionException {
		Node<E>n=checkPosition(p);
		E aux=n.element();
		if(n == head){
			head=head.getSiguiente();
		}
		else {
		Node<E> cursor=head;
		while(cursor!=null&&cursor.getSiguiente()!=n) {
			cursor=cursor.getSiguiente();
		}
		if(cursor==null)
			throw new InvalidPositionException("Posicion invalida ");
		cursor.setSiguiente(n.getSiguiente());}
		
		size--;
		
		return aux;
	}

	
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if(isEmpty()){
			throw new InvalidPositionException(" ");
		}
		else{
			Node<E>n=checkPosition(p);
			E aux=n.element();
			n.setElement(element);
			return aux;
		}
		
	}

	
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new ElementIterator(this);
	}

	
	public Iterable<Position<E>> positions() {
	 PositionList<Position<E>> nueva = new ListaSimpEnlazada<Position<E>>();
	 if(!isEmpty()) {
		Node<E> cursor=head;
		while(cursor!=null) {
			nueva.addLast(cursor);
			cursor=cursor.getSiguiente();
		}
		}
		return nueva;
	}
	
	private Node<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if(p==null || isEmpty() ){
			throw new InvalidPositionException ("Posicion invalida");
				}
		try{ Node<E> n = (Node<E>)p;
			return n;
		}catch (ClassCastException e) { throw new ClassCastException(" ");}
	}
	
}
 