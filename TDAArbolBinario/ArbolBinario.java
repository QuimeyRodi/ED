/**
 * 
 */
package TDAArbolBinario;
import java.util.Iterator;

import Exceptions.*;
import TDAArbol.TNodo;
import Auxiliares.Position;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;
/**
 * @author JanoHernan
 *
 */
public class ArbolBinario<E> implements BinaryTree<E>{
	protected BTNodo<E> raiz;
	protected int size;
	
	public ArbolBinario() {
		raiz=null;
		size=0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return raiz==null;
	}
	
	public boolean hasLeft(Position<E> v) throws InvalidPositionException{
		BTNodo<E> nodo = checkPosition(v);
		return nodo.getLeft()!=null;
	}
	
	public boolean hasRight(Position<E> v) throws InvalidPositionException{
		BTNodo<E> nodo = checkPosition(v);
		return nodo.getRight()!=null;
	}
	
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
		BTNodo<E> nodo = checkPosition(v);
		if(nodo.getLeft()==null) {
			throw new BoundaryViolationException("El nodo ingresado no tiene hijo derecho");
		}
		return nodo.getLeft();
	}
	
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
		BTNodo<E> nodo = checkPosition(v);
		if(nodo.getRight()==null) {
			throw new BoundaryViolationException("El nodo ingresado no tiene hijo derecho");
		}
		return nodo.getRight();
	}
	
	
	public void createRoot(E elem) throws InvalidOperationException{
		if(raiz!=null) {
			throw new InvalidOperationException("Se esta itentando crear una raiz cuando ya hay una");
		}
		BTNodo<E> nuevo = new BTNodo<E>(elem, null, null, null);
		raiz=nuevo;
		size++;
		
	}
	
	public Position<E> addLeft(Position<E> nodo, E elem) throws InvalidOperationException, InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("El arbol esta vacio");
		}else {
			BTNodo<E> padre = checkPosition(nodo);
			if(padre.getLeft()!=null) {
				throw new InvalidOperationException("El nodo ingresado ya tiene hijo izquierdo");
			}
			BTNodo<E> nuevo = new BTNodo<E>(elem,padre,null,null);
			padre.setLeft(nuevo);
			size++;
			return nuevo;
		}
	}
	
	public Position<E> addRight(Position<E> nodo, E elem) throws InvalidOperationException, InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("El arbol esta vacio");
		}else {
			BTNodo<E> padre = checkPosition(nodo);
			if(padre.getRight()!=null) {
				throw new InvalidOperationException("El nodo ingresado ya tiene hijo izquierdo");
			}
			BTNodo<E> nuevo = new BTNodo<E>(elem,padre,null,null);
			padre.setRight(nuevo);
			size++;
			return nuevo;
		}
	}
	
	public E replace(Position<E> nodo, E elem) throws InvalidPositionException{
		BTNodo<E> n = checkPosition(nodo);
		E devolver = n.element();
		n.setElement(elem);
		return devolver;
	}
	
	public Position<E> parent(Position<E> p) throws InvalidPositionException, BoundaryViolationException{
		BTNodo<E> nodo = checkPosition(p);
		if(nodo==raiz) {
			throw new BoundaryViolationException("El nodo ingresado no tiene padre porque es la raiz");
		}
		return nodo.getParent();
	}
	
	public Iterable<Position<E>> children(Position<E> p) throws InvalidPositionException{
		
		BTNodo<E> nodo = checkPosition(p);
		PositionList<Position<E>> devolver = new ListaDoblementeEnlazada<Position<E>>();
		
		if(nodo.getLeft()!=null) {
			devolver.addLast(nodo.getLeft());
		}
		if(nodo.getRight()!=null) {
			devolver.addLast(nodo.getRight());
		}
		
		return devolver;
	}
	
	public Iterator<E> iterator(){
		PositionList<E> list = new ListaDoblementeEnlazada<E>();
		
		for(Position<E> nodo : positions()) {
			list.addLast(nodo.element());
		}
		
		return list.iterator();
	}
	
	public Iterable<Position<E>> positions(){
		//Implementado con preorden
		PositionList<Position<E>> list = new ListaDoblementeEnlazada<Position<E>>();
		if(!isEmpty()) {
			preOrden(raiz,list);
		}
		return list;
	}
	
	private void preOrden(BTNodo<E> nodo, PositionList<Position<E>> list) {
		list.addLast(nodo);
		if(nodo.getLeft()!=null) {
			preOrden(nodo.getLeft(),list);
		}
		if(nodo.getRight()!=null) {
			preOrden(nodo.getRight(),list);
		}
	}
	
	
	private E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException{
		BTNodo<E> nodo = checkPosition(v);
		if(hasLeft(nodo) && hasRight(nodo)) {
			throw new InvalidOperationException("Se esta intentando eliminar un nodo con dos hijos");
		}
		BTNodo<E> hijo;
		if(hasRight(nodo)) {
			hijo = nodo.getRight();
		}else {
			hijo = nodo.getLeft();
		}
		
		if(hijo!=null) {
			hijo.setParent(nodo.getParent());
		}
		
		if(nodo==raiz) {
			raiz=hijo;
		}else {
			BTNodo<E> padre = nodo.getParent();
			if(nodo==padre.getRight()) {
				padre.setRight(hijo);
			}else {
				padre.setLeft(hijo);
			}
		}
		
		size--;
		E devolver = nodo.element();
		nodo.setElement(null);
		nodo.setParent(null);
		nodo.setLeft(null);
		nodo.setRight(null);
		return devolver;
	}
	
	/*ANALIZAR POR QUE ESTA SOLUCION VIOLA EL ENCAPSULAMIENTO, NO ES CORRECTA!*/
	/*public void attach(Position<E> p, BinaryTree<E> tree1, BinaryTree<E> tree2) throws InvalidPositionException {
		BTNodo<E> nodo = checkPosition(p);
		if(isInternal(nodo)) {
			throw new InvalidPositionException("El nodo ingresado no es una hoja");
		}
		ArbolBinario<E> t1 = (ArbolBinario<E>) tree1;
		ArbolBinario<E> t2 = (ArbolBinario<E>) tree2;
		size += t1.size() + t2.size();
		
		
		if(!t1.isEmpty()) {
			t1.raiz.setParent(nodo);
			nodo.setLeft(t1.raiz);
			t1.raiz = null;
			t1.size=0;
		}
		if(!t2.isEmpty()) {
			t2.raiz.setParent(nodo);
			nodo.setRight(t2.raiz);
			t2.raiz = null;
			t2.size=0;
		}
	}*/
	
	public void attach(Position<E> p, BinaryTree<E> t1, BinaryTree<E> t2) throws InvalidPositionException {
		BTNodo<E> raiz_local = checkPosition(p);
		if (raiz_local.getLeft() != null || raiz_local.getRight() != null)
			throw new InvalidPositionException("La posicion no corresponde a un nodo hoja");
		try {
		//Clonación de T1 como subárbol izquierdo
		if (!t1.isEmpty()) {
			Position<E> raiz_t1 = t1.root();
			BTNodo<E> hi_raiz_local = new BTNodo<E>(raiz_t1.element(), raiz_local);
			raiz_local.setLeft(hi_raiz_local);
			clonar(raiz_local.getLeft(), raiz_t1, t1);
		}
		//Clonación de T2 como subárbol derecho
		if (!t2.isEmpty()) {
			Position<E> raiz_t2 = t2.root();
			BTNodo<E> hd_raiz_local = new BTNodo<E>(raiz_t2.element(), raiz_local);
			raiz_local.setRight(hd_raiz_local);
			clonar(raiz_local.getRight(), raiz_t2, t2);
		}
		size += t1.size() + t2.size();
		}catch(EmptyTreeException e) { raiz_local.setLeft(null); raiz_local.setRight(null); }
	}

	protected void clonar(BTNodo<E> padre_local, Position<E> padre_t, BinaryTree<E> t) {
	try {
		//Si existe hijo izquierdo en T de padre_t, se clona este y el subárbol a partir del hijo izquierdo de padre_t.
		if (t.hasLeft(padre_t)) {
			Position<E> hi_padre_t = t.left(padre_t);
			BTNodo<E> hi_padre_local = new BTNodo<E>(hi_padre_t.element(), padre_local);
			padre_local.setLeft(hi_padre_local);
			clonar(hi_padre_local, hi_padre_t, t);
		}
		//Si existe hijo derecho en T de padre_t, se clona este y el subárbol a partir del hijo derecho de padre_t.
		if (t.hasRight(padre_t)) {
			Position<E> hd_padre_t = t.right(padre_t);
			BTNodo<E> hd_padre_local = new BTNodo<E>(hd_padre_t.element(), padre_local);
			padre_local.setRight(hd_padre_local);
			clonar(hd_padre_local, hd_padre_t, t);
		}
		}catch(InvalidPositionException | BoundaryViolationException e) {
		padre_local.setLeft(null);
		padre_local.setRight(null);
	}
	}
	
	
	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		Position<E> nodo = checkPosition(p);
		return hasLeft(nodo) || hasRight(nodo);
	}
	
	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		return !isInternal(p);
	}
	
	public boolean isRoot(Position<E> p) throws InvalidPositionException{
		Position<E> nodo = checkPosition(p);
		return raiz==nodo;
	}
	
	public Position<E> root() throws EmptyTreeException{
		if(isEmpty()) {
			throw new EmptyTreeException("El arbol esta vacio");
		}
		return raiz;
	}
	
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException{
		try {
			return addLeft(p,e);
		}catch(InvalidOperationException exc) {
			throw new InvalidPositionException(exc.getMessage());
		}
	}
	
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException{
		try {
			return addRight(p,e);
		}catch(InvalidOperationException exc) {
			throw new InvalidPositionException(exc.getMessage());
		}
	}
	
	public Position<E> addBefore(Position<E> p, Position<E> hd, E e) throws InvalidPositionException{
		try {
			BTNodo<E> padre = checkPosition(p);
			BTNodo<E> hijoDer = checkPosition(hd);
			if(padre.getRight()!=hijoDer) {
				throw new InvalidPositionException("El hijo ingresado no corresponde al padre ingresado");
			}
			return addLeft(p,e);
		}catch(InvalidOperationException exc) {
			throw new InvalidPositionException(exc.getMessage());
		}
	}
	
	public Position<E> addAfter(Position<E> p, Position<E> hi, E e) throws InvalidPositionException{
		try {
			BTNodo<E> padre = checkPosition(p);
			BTNodo<E> hijoIzq = checkPosition(hi);
			if(padre.getLeft()!=hijoIzq) {
				throw new InvalidPositionException("El hijo ingresado no corresponde al padre ingresado");
			}
			return addRight(p,e);
		}catch(InvalidOperationException exc) {
			throw new InvalidPositionException(exc.getMessage());
		}
	}
	
	public void removeExternalNode(Position<E> p) throws InvalidPositionException{
		try {
			if(!isExternal(p)) {
				throw new InvalidPositionException("El nodo no es un nodo externo");
			}
			remove(p);
		}catch(InvalidOperationException e) {
			throw new InvalidPositionException(e.getMessage());
		}
		
	}
	
	public void removeInternalNode(Position<E> p) throws InvalidPositionException{
		try {
			if(!isInternal(p)) {
				throw new InvalidPositionException("El nodo no es un nodo externo");
			}
			remove(p);
		}catch(InvalidOperationException e) {
			throw new InvalidPositionException(e.getMessage());
		}
	}
	
	public void removeNode(Position<E> p) throws InvalidPositionException{
		try {
			remove(p);
		}catch(InvalidOperationException e) {
			throw new InvalidPositionException(e.getMessage());
		}
	}
	
	
	//FIN METRODOS HEREDADOS DE TREE
	private BTNodo<E> checkPosition(Position<E> v) throws InvalidPositionException{
		try {
			BTNodo<E> nodo = (BTNodo<E>) v;
			if(nodo==null) {
				throw new InvalidPositionException("La posicion ingresada es nula");
			}else if(nodo.element()==null) {
				throw new InvalidPositionException("La posicion ingresada fue eliminada previamente");
			}else {
				return nodo;
			}
		}catch(ClassCastException e) {
			throw new InvalidPositionException("La posicion ingresada no pertenece al arbol");
		}
	}
	
	//EJERCICIOS DE AB
	public int sizeSubarbol(Position<E> p) throws InvalidPositionException{
		BTNodo<E> raizSub= checkPosition(p);
		return sizeRec(raizSub);
	}
	/*implementa un recorrido en posorden para calcular el tamaño
	 * de un subarbol*/
	private int sizeRec(BTNodo<E> n) {
		int sizeSub=0;
		//recorro recursivamente a los hijos de n
		if(n.getLeft()!=null)
			sizeSub+=sizeRec(n.getLeft());
		if(n.getRight()!=null)
			sizeSub+=sizeRec(n.getRight());
		//visito a n
		sizeSub++;
		return sizeSub;
	}
	
	//ejercicio del problema 3
	public int podarSubarbol(Position<E> p) throws InvalidPositionException{
		BTNodo<E> raizSub= checkPosition(p);
		int cantEliminados=sizeSubarbol(raizSub);
		BTNodo<E> padre= raizSub.getParent();
		if(padre.getLeft()==raizSub)
			padre.setLeft(null);
		else
			if(padre.getRight()==raizSub)
				padre.setRight(null);
			else
				throw new InvalidPositionException("Arbol corrupto");
		
		size-=cantEliminados;
		return cantEliminados;
	}
	
	//ejercicio del problema 4
	
	public BinaryTree<E> clonar(){
		BinaryTree<E> nuevo= new ArbolBinario<E>();
		try {
			if(raiz!=null)
			{
				nuevo.createRoot(raiz.element());
				clonarRec(raiz,nuevo.root(),nuevo);
			}
		} catch (InvalidOperationException | EmptyTreeException e) {
			e.printStackTrace();
		}
		return nuevo;
	}

	private void clonarRec(BTNodo<E> n, Position<E> p, BinaryTree<E> nuevo) {
	
		try {
			if(n.getLeft()!=null) {
				Position<E> posHI=nuevo.addLeft(p, n.getLeft().element());
				clonarRec(n.getLeft(),posHI,nuevo);
			}
			if(n.getRight()!=null) {
				Position<E> posHD=nuevo.addRight(p, n.getLeft().element());
				clonarRec(n.getRight(),posHD,nuevo);
			}
		} catch (InvalidOperationException | InvalidPositionException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
