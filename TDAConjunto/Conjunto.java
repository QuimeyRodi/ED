package TDAConjunto;


public interface Conjunto<E> {

	/**
	 * Consulta la cantidad de elementos del conjunto.
	 * @return Cantidad de elementos del conjunto.
	 */
	public int size();
	
	/**
	 * Consulta la capacidad del conjunto.
	 * @return Capacidad m�xima del conjunto.
	 */
	public int capacity();

	/**
	 * Consulta si el conjunto est� vac�o.
	 * @return Verdadero si el conjunto est� vac�o, falso en caso contrario.
	 */
	public boolean isEmpty();

	/**
	 * Retorna el i-esimo elemento del conjunto. Requiere que i sea una posici�n v�lida
	 * @return Retorna el i-esimo elemento del conjunto..
	 */
	
	public E get(int i);
	
	/**
	 * Agrega un elemento al conjunto. Requiere que el conjunto no est� lleno
	 *
	 */
	public void put (E elem);
	
		/**
	 * Verifica si el elemento elem pertenece a la colecci�n.
	 * @return Retorna verdadero si elem pertenece a la coleccion.
	 *
	 */
	public boolean pertenece(E elem);
	
	/**
	 * Calcula la intersecci�n entre el conjunto que recibe el mensaje y el pasado por par�metro.
	 * @return Retorna el conjunto resultante de la intersecci�n.
	 *
	 */
	public Conjunto<E> intersection(Conjunto<E> c);
	
}
