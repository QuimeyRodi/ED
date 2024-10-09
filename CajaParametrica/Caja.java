package CajaParametrica;

public interface Caja<E> {

	public void insertar(E elem);
	public void eliminar(E elem);
	public boolean buscar(E elem);
	public E recuperarAlguno();
}
/*OBSERVEN QUE ESTA INTERFACE NO DEFINE LA ESPECIFICACIÓN DE LAS OPERACIONES
 * INSERTAR: ¿QUE PASA SI ELEM YA ESTA EN LA CAJA?, ¿PUEDE LA CAJA LLENARSE?
 * ELIMINAR: ¿QUE PASA SI ELEM ESTA REPEDIDO?*
 * RECUPERAR ALGUNO: ¿QUÉ RETORNA SI LA CAJA ESTA VACIA?/
 * EN LAS INTERFACES QUE VAMOS A UTILIZAR PARA CREAR NUESTRAS ESTRUCTURAS DE DATOS VAN A 
 * ENCONTRAR ESPECIFICACIONES MUY DETALLADAS DE CADA UNA DE LAS OPERACIONES.
 */