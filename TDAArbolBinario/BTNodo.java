/**
 * 
 */
package TDAArbolBinario;

import Auxiliares.Position;

/**
 *
 */
public class BTNodo<E> implements Position<E>{
	private E element;
	private BTNodo<E> left;
	private BTNodo<E> right;
	private BTNodo<E> parent;
	
	public BTNodo(E elem,  BTNodo<E> p,  BTNodo<E> der,  BTNodo<E> izq) {
		element=elem;
		left=izq;
		right=der;
		parent=p;
	}
	
	public BTNodo(E elem,  BTNodo<E> p) {
		element=elem;

		parent=p;
	}
	
	public void setElement(E elem) {
		element=elem;
	}
	
	public void setLeft( BTNodo<E> izq) {
		left=izq;
	}
	
	public void setRight( BTNodo<E> der) {
		right=der;
	}
	
	public void setParent( BTNodo<E> p) {
		parent=p;
	}
	
	public E element() {
		return element;
	}
	
	public  BTNodo<E> getRight(){
		return right;
	}
	
	public  BTNodo<E> getLeft(){
		return left;
	}
	
	public  BTNodo<E> getParent(){
		return parent;
	}
}
