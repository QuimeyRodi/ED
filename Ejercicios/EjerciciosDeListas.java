package Ejercicios;

import java.util.Iterator;

import Auxiliares.Position;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

public class EjerciciosDeListas {
	
	public static void main(String[] args) {
		
		//completar con casos de prueba
	}

//Ejercicio 1
	public static Iterable<Character> soloVocales (PositionList<Character> pl, int n){
		PositionList<Character> nueva = new ListaDoblementeEnlazada<Character>();
		Iterator<Character> it= pl.iterator();
		int cont=0;
		while(it.hasNext()&&cont<n)
			{char c= it.next();
			 if(esVocal(c))
			  {nueva.addLast(c);
			   cont++;}
			  }
		return nueva;
	}

	private static boolean esVocal(char c) {
		return c=='a'||c=='e'||c=='i'||c=='o'||c=='u';
	}


//Ejercicio 2
Iterable<Position<Character>> nPrimerasPos(PositionList<Character> pl, Character c, int n){
	
	PositionList<Position<Character>> toRet= new ListaDoblementeEnlazada<Position<Character>>();
	Iterator<Position<Character>> it= pl.positions().iterator();
	int cont=0;
	while(it.hasNext()&&cont<n)
	{Position<Character> p= it.next();
	 if(p.element()==c)
	  {toRet.addLast(p);
	   cont++;}
	  }
	return toRet;
}
}
