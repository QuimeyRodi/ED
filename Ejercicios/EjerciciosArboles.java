package Ejercicios;

import Auxiliares.Position;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDAArbol.Arbol;
import TDAArbol.TNodo;
import TDAArbol.Tree;
import TDAArbolBinario.ArbolBinario;
import TDAArbolBinario.BTNodo;
import TDAArbolBinario.BinaryTree;

public class EjerciciosArboles {
	
	public static<E> Tree<E> clonar(Tree<E> t){
		Tree<E> nuevo= new Arbol<E>();
		try {
			if(t.size()!=0)
				{nuevo.createRoot(t.root().element());
				 clonarRec(t.root(),t,nuevo.root(),nuevo);}
		} catch (InvalidOperationException | EmptyTreeException e) {
			e.printStackTrace();
		}
		return nuevo;
	}

	private static<E> void clonarRec(Position<E> posT,Tree<E> t,Position<E> posNuevo, Tree<E> nuevo) {

       try {
		for(Position<E> hijo: t.children(posT))
		   { Position<E> posHijoNuevo= nuevo.addLastChild(posNuevo, hijo.element());
		     clonarRec(hijo,t,posHijoNuevo,nuevo);
			       }
	} catch (InvalidPositionException e) {
		e.printStackTrace();
	}}
		
	
	//ejercicio del problema 1
	public static<E> int sizeSubarbol(Position<E> p, Tree<E> t) {
		return sizeRec(p,t);
	}
	/*implementa un recorrido en posorden para calcular el tamaño
	 * de un subarbol*/
	private static<E> int sizeRec(Position<E> p, Tree<E> t) {
	int sizeSub=0;
	//recorro recursivamente a los hijos de n
	try {
		for(Position<E> hijo:t.children(p))
			sizeSub+=sizeRec(hijo,t);
	} catch (InvalidPositionException e) {
		e.printStackTrace();
	}
	//visito a n
	sizeSub++;
	return sizeSub;
}
	
	//ejercicio del problema 5
	
		public static<E> BinaryTree<E> clonar(BinaryTree<E> t){
			BinaryTree<E> nuevo= new ArbolBinario<E>();
			try {
				if(t.size()!=0)
				{
					nuevo.createRoot(t.root().element());
					clonarRec(t.root(),t,nuevo.root(),nuevo);
				}
			} catch (InvalidOperationException | EmptyTreeException e) {
				e.printStackTrace();
			}
			return nuevo;
		}

		private static<E> void clonarRec(Position<E> posT, BinaryTree<E> t, Position<E> posNuevo, BinaryTree<E> nuevo) {
		

				try {
					if(t.hasLeft(posT)) {
						Position<E> posHI=nuevo.addLeft(posNuevo, t.left(posT).element());
						clonarRec(t.left(posT),t,posHI,nuevo);
					}
					if(t.hasRight(posT)) {
						Position<E> posHD=nuevo.addRight(posNuevo, t.right(posT).element());
						clonarRec(t.right(posT),t,posHD,nuevo);
					}
				} catch (InvalidPositionException | InvalidOperationException | BoundaryViolationException e) {
					e.printStackTrace();
				}
			
			
		}
		
	
}
