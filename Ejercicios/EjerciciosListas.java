package Ejercicios;

import Auxiliares.Position;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;
import TDALista.*;

public class EjerciciosListas {
	
	public static void main(String[] args) {
		
		PositionList<Character> l= new ListaDoblementeEnlazada<Character>();
		l.addLast('a');
		l.addLast('a');
		l.addLast('b');
		l.addLast('a');
		l.addLast('c');
		System.out.println("Lista antes de modificar:");
		mostrarLista(l);
		modificar(l,'a','z');
		System.out.println("Lista despues de modificar:");
		mostrarLista(l);
		
		
	}

	private static<E> void mostrarLista(PositionList<E> l) {
		//recorro la lista para mostrar sus elementos
		Position<E> cursor;
		try {
			if(!l.isEmpty())
			{cursor= l.first();
			 while(cursor!=null)
			 {System.out.println(cursor.element()); //VISITA:hago algo con el elemento de la posicion actual
			  if(cursor==l.last())//AVANZAR
				  cursor=null;
			  else
				  cursor=l.next(cursor);
			 }
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
	}

	private static<E> void modificar(PositionList<E> l, E e1, E e2) {
		//recorro la lista para cambiar todas las apariciones de e1 por e2
		try {
			Position<E> cursor;
			if(!l.isEmpty())
			{cursor= l.first();
			 while(cursor!=null)
			 {if(cursor.element().equals(e1)) //VISITA:hago algo con el elemento de la posicion actual
				 l.set(cursor, e2);
			  if(cursor==l.last()) //AVANZAR
				  cursor=null;
			  else
				  cursor=l.next(cursor);
			 }
			}
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
