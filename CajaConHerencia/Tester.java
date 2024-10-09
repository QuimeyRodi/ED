package CajaConHerencia;

public class Tester {

	public static void main(String[] args) {
		
		crearCajaNoHomogenea();
		crearCajaHomogenea();
		

	}
	
	public static void crearCajaNoHomogenea() {
		//creo la Caja donde quiero guardar mis perros y gatos
		//OBSERVEN que el tipo estático de la variable miCajita es del tipo de la interface, por qué?
				Caja miCajita= new CajaConArreglo();
				//Creo mis perros y mis gatos
				Perro p1= new Perro(10,"Tobi");
				Perro p2= new Perro(10,"Bobi");
				Gato g1= new Gato(1,"Michi");
				Gato g2= new Gato(10,"Palta");
				//Los guardo en la Caja
				miCajita.insertar(p1);
				miCajita.insertar(p2);
				miCajita.insertar(g1);
				miCajita.insertar(g2);
				
				Elemento recuperado= miCajita.recuperarAlguno();
				//cómo hago si quiero mostrar la edad y el nombre del animal?
				//como se si es un gato o un perro
				//completar con mas casos de prueba
				//completar con mas casos de prueba y agregar salidas por consola adecuadas
	}
	
	public static void crearCajaHomogenea() { //solo perros
		//creo la Caja donde quiero guardar mis perros y gatos
		//OBSERVEN que el tipo estático de la variable miCajita es del tipo de la interface, por qué?
				Caja miCajita= new CajaConArreglo();
				//Creo mis perros
				Perro p1= new Perro(10,"Tobi");
				Perro p2= new Perro(10,"Bobi");
				
				//Los guardo en la Caja
				miCajita.insertar(p1);
				miCajita.insertar(p2);
				
				
				Elemento recuperado= miCajita.recuperarAlguno();
				//cómo hago si quiero mostrar la edad y el nombre del animal?
				//completar con mas casos de prueba y agregar salidas por consola adecuadas
	}
	
	//qué pasa si ahora quiero hacer una caja de números enteros?

}
