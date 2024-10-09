package TDAArbol;

import Exceptions.*;

import java.util.Iterator;
import TDALista.*;
import TDAMapeo.Map;
import TDAMapeo.MapeoConHash;
import Auxiliares.Position;

/**
 * Clase Árbol, implementa la interfaz Tree.
 * @param <E>: tipo de dato con el que trabajará el Árbol.
 * @author Ezequiel Ramírez Beltrán.
 * @author Dmytro Shkolyar.
 */
public class Arbol<E> implements Tree<E> {

	protected TNodo<E> root;
	protected int size;
	
	/**
	 * Constructor de Árbol. 
	 * Inicializa el Árbol con raíz nula, y cantidad de nodos en 0.
	 */
	public Arbol() {
		root = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		E toReturn = nodo.element();
		nodo.setElement(e);
		return(toReturn);
	}

	public Position<E> root() throws EmptyTreeException {
		if(isEmpty()) {
			throw new EmptyTreeException("El arbol esta vacio.");
		}
		return root;
	}

	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> aux = checkPosition(v);
		if(aux == root) {
			throw new BoundaryViolationException("Le esta pidiendo el padre a la raiz del arbol.");
		}
		return aux.padre();
	}

	/**
	 * Convierte la posición recibida a TNodo.
	 * @param v posición del árbol a transformar.
	 * @return nodo del árbol, ubicado en la posición v.
	 * @throws InvalidPositionException si la conversión a nodo produjo un error.
	 */
	private TNodo<E> checkPosition(Position<E> v)throws InvalidPositionException{
		TNodo<E> nodo = null;
		if(v == null) {
			throw new InvalidPositionException("Posición invalida");
		}
		if(size == 0) {
			throw new InvalidPositionException("Árbol Vacío.");
		}
		try {
			nodo = (TNodo<E>) v;
		}
		catch(ClassCastException e){
			throw new InvalidPositionException("Posición inválida.");
		}
		return nodo;
	}
	
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hijo = new TNodo<E>(e,padre);
		padre.Hijos().addLast(hijo);
		size++;
		return hijo;
	}

	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> n = checkPosition(v);
		PositionList<Position<E>> lista = new ListaDoblementeEnlazada<Position<E>>();
		for(TNodo<E> nodo : n.Hijos()) {
			lista.addLast(nodo);
		}
		return lista;
	}

	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> n = checkPosition(v);
		return (!n.Hijos().isEmpty());
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> n = checkPosition(v);
		return (n.Hijos().isEmpty());
	}

	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> n = checkPosition(v);
		return n == root;
	}

	public void createRoot(E e) throws InvalidOperationException {
		if(!isEmpty()) {
			throw new InvalidOperationException("Ya existe una raíz en el árbol.");
		}
		TNodo<E> aux = new TNodo<E>(e, null);
		size++;
		root = aux;
	}
	
	/**
	 * Método que realiza el recorrido del árbol en preorden.
	 * @return ListaDoblementeEnlazada de nodos del árbol, según el recorrido preorden.
	 * @throws EmptyTreeException en caso de que el árbol esté vacío.
	 */
	public PositionList<E> recorrerPreorden() throws EmptyTreeException {
		PositionList<E> l = new ListaDoblementeEnlazada<E>();
		if(isEmpty()) {
			throw new EmptyTreeException("El Árbol está vacío.");
		}
		l.addLast(root.element());
		for(TNodo<E> n : root.Hijos()) {
			recorrerPreordenAux(l, n);
		}
		return l;
	}
	
	/**
	 * Recorre recursivamente el árbol en preorden, y llena una lista doblemente enlazada con las 
	 * posiciones de los rótulos de los nodos del árbol.
	 * @param r el nodo del árbol que se evalúa.
	 * @param l la lista en donde se guardan las posiciones ordenadas.
	 */
	private void recPreorden(TNodo<E> r , PositionList<Position<E>> l ) {
		l.addLast(r);
		for(TNodo<E> n : r.Hijos()) {
			recPreorden(n, l);
		}
	}
	
	/**
	 * Recorre recursivamente el árbol en preorden, y llena una lista doblemente enlazada de los 
	 * rótulos de los nodos del árbol.
	 * @param l la lista donde se guardan los rótulos.
	 * @param r el nodo del árbol que se evalúa.
	 */
	private void recorrerPreordenAux(PositionList<E> l, TNodo<E> r) {
		l.addLast(r.element());
		for(TNodo<E> n : r.Hijos()) {
			recorrerPreordenAux(l, n);
		}
	}
	
	public Iterator<E> iterator() {
		PositionList<E> list = new ListaDoblementeEnlazada<E>();
		for(Position<E> a : positions()) {
			list.addLast(a.element());
		}
		return list.iterator();
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> lista = new ListaDoblementeEnlazada<Position<E>>();
		if(size > 0) {
			recPreorden(root , lista);
		}
		return lista;
	}

	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		if(isEmpty()) {
			throw new InvalidPositionException("El arbol esta vacío.");
		}
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hijo = new TNodo<E>(e, padre);
		padre.Hijos().addFirst(hijo);
		size++;
		return hijo;
	}

	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNodo<E> padre = checkPosition(p);
		if(isExternal(p)) {
			throw new InvalidPositionException("La posición p no tiene hijos.");
		}
		TNodo<E> hermanoDerecho = checkPosition(rb);
		TNodo<E> nodo = new TNodo<E>(e, padre);
		try {
			if(nodo.padre() != hermanoDerecho.padre()) {
				throw new InvalidPositionException("El nuevo nodo , y el nodo derecho, no son hermanos.");
			}
			PositionList<TNodo<E>> lista = padre.Hijos();
			Position<TNodo<E>> pos = lista.first();
			while(pos.element() != hermanoDerecho) {
				pos = lista.next(pos);
			}
			lista.addBefore(pos, nodo);
		}
		catch(EmptyListException | BoundaryViolationException ex) {
			ex.printStackTrace();
		}
		size++;
		return nodo;
	}

	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> padre = checkPosition(p);
		if(isExternal(p)) {
			throw new InvalidPositionException("El nodo padre no tiene hijos");
		}
		TNodo<E> hermanoIzquierdo = checkPosition(lb);
		TNodo<E> nodo = new TNodo<E>(e, padre);
		if(hermanoIzquierdo.padre() != padre) {
			throw new InvalidPositionException("Nodo nuevo y nodo izquierdo, no tienen el mismo padre.");
		}
		PositionList<TNodo<E>> lista = padre.Hijos();
		try {
			Position<TNodo<E>> pos = lista.first();
			while(pos != null) {
				if(pos.element() == lb) {
					lista.addAfter(pos, nodo);
					pos = null;
				}
				if(pos == lista.last() && pos != null) {
					throw new InvalidPositionException("La posición de hijo izquierdo no existe en la lista de hijos de p.");
				}
				else if(pos != lista.last()) {
					pos=lista.next(pos);
				}
			}
		}
		catch(InvalidPositionException | EmptyListException | BoundaryViolationException ex) {
			ex.printStackTrace();
		}
		size++;
		return nodo;
	}

	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		//si la posicion recibida, es de un nodo con hijos
		if(!isExternal(p)) {
			throw new InvalidPositionException("Le esta mandando el método remove external, a un nodo interno.");
		}
		//si la posicion recibida, es la raiz:
		if(nodo == root) {
			root = null;
		}
		//si la posicion recibida no es la raiz
		else {
			TNodo<E> padre = nodo.padre();
			try {
				PositionList<TNodo<E>> lista = padre.Hijos();
				Position<TNodo<E>> pos = lista.first();
				while(pos.element() != nodo) {
					pos = lista.next(pos);
				}
				lista.remove(pos);
			}
			catch(InvalidPositionException | EmptyListException | BoundaryViolationException e) {
				e.printStackTrace();
			}
		}
		size--;
	}

	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		try {
			TNodo<E> nodo = checkPosition(p);
			if(!isInternal(p)) {
				throw new InvalidPositionException("La posición que se quiere eliminar, es de un nodo que no es interno.");
			}
			if(nodo == root){
				if(root.Hijos().size() > 1) {
					throw new InvalidPositionException("La posición que se quiere eliminar es de la raíz, con mas de 1 hijo.");
				}
				TNodo<E> n = root.Hijos().first().element();
				n.setPadre(null);
				root = n;
			}
			//si la posicion a eliminar no es la raiz
			else {
				//recorro la lista de hijos del padre de p, hasta encontrar la posicion de p y guardarla
				PositionList<TNodo<E>> lista = nodo.padre().Hijos();
				Position<TNodo<E>> pos = lista.first();
				while(pos.element() != nodo) {
					pos = lista.next(pos);
				}
				//para cada uno de los hijos de p,los agrego a la lista de hijos de su abuelo, luego de la 
				//posicion pos. A la vez, les seteo como padre, a su abuelo.
				for(TNodo<E> tn : nodo.Hijos()) {
					tn.setPadre(nodo.padre);
					lista.addBefore(pos, tn);
				}
				//seteo al padre de p como nulo,elimino el nodo de la posicion p, dismunuyo en 1 la cantidad
				nodo.setPadre(null);
				lista.remove(pos);
			}
		}
		catch(EmptyListException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		size--;
	}
		
	public void removeNode(Position<E> p) throws InvalidPositionException {
		try {
			TNodo<E> nodo = checkPosition(p);
			if (nodo == root) {
				//si la raiz tiene 1 solo hijo
				if(nodo.Hijos().size() == 1) {
					Position<TNodo<E>> raizN = nodo.Hijos().first();
					raizN.element().setPadre(null);
					root = raizN.element();
					size--;
				} else {
					//si el unico nodo del arbol es la raiz:
					if (size == 1) {
						root = null;
						size--;
					} else {
						//si la raiz tiene mas de 1 hijo
						throw new InvalidPositionException("Quiere eliminar la raíz que tiene mas de 1 hijo.");
					}
				}
			} else {
				//si tenemos que eliminar un nodo que no es raiz
				TNodo<E> padre = nodo.padre();
				PositionList<TNodo<E>> listHijosN = nodo.Hijos();
				PositionList<TNodo<E>> listHijosP = padre.Hijos();
				Position<TNodo<E>> primero = listHijosP.first();
				
				while(primero.element() != p){
					//es lo mismo q (primero.element() != n)
					primero = listHijosP.next(primero);
				}
				//si n tiene getChildren , la recorro y se inserta en orden
				if(!listHijosN.isEmpty()) {
					while(!listHijosN.isEmpty()) {
						Position<TNodo<E>> hijoAInsertar = listHijosN.first();
						listHijosP.addBefore(primero , hijoAInsertar.element());
						TNodo<E> insertado = listHijosP.prev(primero).element();
						insertado.setPadre(padre);
						listHijosN.remove(listHijosN.first());
					}
				}
				//eliminamos a n de la lista de getChildren de p
				listHijosP.remove(primero);
				size--;
			}
		} catch (EmptyListException | BoundaryViolationException e) {
			e.printStackTrace();
		}
	}
	//EJERCICIOS DE ARBOLES
	
	public int sizeSubarbol(Position<E> p) throws InvalidPositionException{
		TNodo<E> raizSub= checkPosition(p);
		return sizeRec(raizSub);
	}
	/*implementa un recorrido en posorden para calcular el tamaño
	 * de un subarbol*/
	private int sizeRec(TNodo<E> n) {
		int sizeSub=0;
		//recorro recursivamente a los hijos de n
		for(TNodo<E> hijo:n.Hijos())
			sizeSub+=sizeRec(hijo);
		//visito a n
		sizeSub++;
		return sizeSub;
	}
	
	public Map<Position<E>, Integer> mapSizeSubarboles( ) throws InvalidPositionException{
		Map<Position<E>, Integer> m= new MapeoConHash<Position<E>, Integer>();
		try {
			for(Position<E> p: positions())
				m.put(p, sizeSubarbol(p));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return m;
	} 
	
	public int podarSubarbol(Position<E> p) throws InvalidPositionException{
		TNodo<E> raizSub= checkPosition(p);
		int cantEliminados=sizeSubarbol(raizSub);
		TNodo<E> padre= raizSub.padre();
		PositionList<TNodo<E>> hermanos= padre.Hijos();
		Iterator<Position<TNodo<E>>> it= hermanos.positions().iterator();
		Position<TNodo<E>> posActual=null;
		boolean encontre=false;
		while(it.hasNext()&& !encontre) {
			posActual=it.next();
			encontre=posActual.element()==raizSub;}
		if(!encontre)	
				throw new InvalidPositionException("Arbol corrupto");
		hermanos.remove(posActual);
		size-=cantEliminados;
		return cantEliminados;
	}
	
	public Tree<E> clonar(){
		Tree<E> nuevo= new Arbol<E>();
		try {
			if(size!=0)
				{nuevo.createRoot(root.element());
				 clonarRec(root,nuevo.root(),nuevo);}
		} catch (InvalidOperationException | EmptyTreeException e) {
			e.printStackTrace();
		}
		return nuevo;
	}

	private void clonarRec(TNodo<E> n, Position<E> posNuevo, Tree<E> nuevo) {

       try {
		for(TNodo<E> hijo: n.Hijos())
		   { Position<E> posHijoNuevo= nuevo.addLastChild(posNuevo, hijo.element());
		     clonarRec(hijo,posHijoNuevo,nuevo);
			       }
	} catch (InvalidPositionException e) {
		e.printStackTrace();
	}
		
	}
}
	
	
	
	
