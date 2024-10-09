package Ejercicios;

import Exceptions.EmptyStackException;
import TDAPila.*;


public class EjerciciosPyCCatedra {
	
	/*mismo problema que en la clase anterior pero esta vez lo 
	resolvemos usando la pila que recien implementamos*/
	public static<E> void main (String[] args) {
		
		/*Escriba un método que reciba un arreglo de enteros y retorne una pila
		 * con los números pares del arreglo. Utilice la pila provista la
		 * catedra y luego muestre su contenido*/
		//creo el arreglo
		Integer[] arreglo= new Integer[10];
		//lo lleno con los números del 1 al 10
		for(int i=0;i<arreglo.length;i++)
			arreglo[i]=i+1;
		//llamo al método auxiliar para que me retorne una pila solo con los pares
		Stack<Integer> p= pilaDePares(arreglo);
		//muestro el contenido de p
		
		try {
			while(!p.isEmpty())
				System.out.println(p.pop());
			
		     } catch (EmptyStackException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
		
		
		//solo para que vean como se ve cuando ocurre una excepcion
		try { 
			Stack<Integer> nuevaP= new PilaConArreglo<Integer>();
			System.out.println(p.pop());
			} catch (EmptyStackException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				}
		
		System.out.println("La ejecución sigue?");
		System.out.println("SI :) ");
	
	}

	

	private static Stack<Integer> pilaDePares(Integer[] arreglo) {
		 Stack<Integer> nuevaPila= new PilaConArreglo<Integer>();
		 
		 for(int i=0;i<arreglo.length;i++)
				if (arreglo[i]%2==0)
					nuevaPila.push(arreglo[i]);
		 
		return nuevaPila;
	}

}
