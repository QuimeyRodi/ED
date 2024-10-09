package CajaParametrica;

public class Tester {

	public static void main(String[] args) {
		
		crearCajaNoHomogenea();
		crearCajaHomogeneaDePerros();
		crearCajaHomogeneaDeEnteros();
		crearCajaDeLoqueSea();	

	}
	
	public static void crearCajaNoHomogenea() {
		//NO SE PUEDE, LA GENERICIDAD PARAMETRICA GARANTIZA LA HOMOGENEIDAD DE LOS DATOS
	}
	
	public static void crearCajaHomogeneaDePerros() { //solo perros
		//creo la Caja donde quiero guardar mis perros y gatos
		//OBSERVEN que el tipo estático de la variable miCajita es del tipo de la interface, por qué?
				Caja<Perro> miCajita= new CajaConArreglo<Perro>();
				//Creo mis perros
				Perro p1= new Perro(10,"Tobi");
				Perro p2= new Perro(10,"Bobi");
				
				//Los guardo en la Caja
				miCajita.insertar(p1);
				miCajita.insertar(p2);
				
				
				Perro recuperado= miCajita.recuperarAlguno();
				//en este caso el tipo estático es Perro por lo tanto puedeo acceder a su edad y su nombre
				//completar con mas casos de prueba y agregar salidas por consola adecuadas
	}
	
	public static void crearCajaHomogeneaDeEnteros() {
		Caja<Integer> miCajita= new CajaConArreglo<Integer>();
		//Observen que Integer es la clase WRAPPER de int
		
		//guardo enteros en la caja
		miCajita.insertar(123);
		miCajita.insertar(222);
		//observen que por parámetro manejamos los enteros como tipos elementales, la conversión
		//Integer - int es automática
		
		//completar con mas casos de prueba y agregar salidas por consola adecuadas		
		
	}
	
	public static<E> void crearCajaDeLoqueSea() {
		/*Obseven que luego del static se agrega el parámetro E, cuando vamos a trabajar
		con parámetros sin instanciar desde un método estático hay que especificarlo de esta manera*/
		
		Caja<E> miCajita= new CajaConArreglo<E>();
		
		/*Pero qué puedo insertar ahora? La respuesta es que en este punto necesitamos si o si
		 * ya tener la istancia del parámetro E para poder insertar elementos, o bien 
		 * recibir por parámetro otra estructura de tipo E y pasar elementos de una a otra (esto último
		 * lo iremos viendo a medida que avance la materia)*/
	}

}
