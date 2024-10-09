package Ejercicios;


//Estos import son necesarios porque estamos usando la Pila y la Cola que provee Java.
 //Interface "cola de Java" 
import java.util.Stack; //Clase "Pila de Java" (no tiene interface)
import java.util.LinkedList; //Clase que implementa a la interface Queue de Java
import java.util.Queue;

public class Ejercicios {

	public static void main (String[] args) {
		
		/*Escriba un método que reciba un arreglo de enteros y retorne una pila
		 * con los números pares del arreglo. Utilice la pila provista por Java
		 * y luego muestre su contenido*/
		//creo el arreglo
		Integer[] arreglo= new Integer[10];
		//lo lleno con los números del 1 al 10
		for(int i=0;i<arreglo.length;i++)
			arreglo[i]=i+1;
		//llamo al método auxiliar para que me retorne una pila solo con los pares
		Stack<Integer> p= pilaDePares(arreglo);
		//muestro el contenido de p
		while(!p.isEmpty())
			System.out.println(p.pop());
		//como queda p luego de mostrar su contenido?
		
		/*Escriba un método que reciba un arreglo de enteros y retorne una cola
		 * con los números impares del arreglo. Utilice la cola provista por Java
		 * y luego muestre su contenido. Tenga cuenta que la cola que provee Java tiene
		 * una interfece que se llama Queue y LinkedList es una de las clases que implementa
		 * esa interface*/
		//utilizaremos el mismo arreglo con el mismo contenido
		
		Queue<Integer> c= colaDeImpares(arreglo);
	
	
		
		//Complete con código para mostrar el contenido de la cola c.
	}

	

	private static Stack<Integer> pilaDePares(Integer[] arreglo) {
		 Stack<Integer> nuevaPila= new Stack<Integer>();
		 
		 for(int i=0;i<arreglo.length;i++)
				if (arreglo[i]%2==0)
					nuevaPila.push(arreglo[i]);
		 
		return nuevaPila;
	}
	
	private static Queue<Integer> colaDeImpares(Integer[] arreglo) {
		/*Observe que tal como mencionamos en la primer clase cuando somos clientes del
		 * TDA el tipo estatico siempre debe ser el de la interface. En el caso del método
		 * anterior Java no provee una interface para la pila pero si para la cola. LinkedList
		 * es una de las tantas clases que implementan la interface Queue de Java*/
		Queue<Integer> nuevaCola= new LinkedList<Integer>();
		 
		 for(int i=0;i<arreglo.length;i++)
				if (arreglo[i]%2!=0)
					nuevaCola.add(arreglo[i]);// en esta interface el metodo para encolar se llama add
		 
		return nuevaCola;
	}
	

}
