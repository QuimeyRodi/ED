package TDADiccionario;

import java.util.Iterator;

import Auxiliares.Entrada;
import Auxiliares.Entry;
import Auxiliares.Position;
import Exceptions.InvalidEntryException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

public class DiccionarioHashAbierto<K,V> implements Dictionary<K,V>{
	protected PositionList<Entrada<K,V>> [] arregloBucket;
	protected int n;
	protected int N;
	
	public DiccionarioHashAbierto(){
		n=0;
		N=11;
		arregloBucket= new PositionList[N];
		for(int i=0; i<N; i++){
			arregloBucket[i]=new ListaDoblementeEnlazada<Entrada<K,V>>();
		}
	}
	
	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return n==0;
	}

	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);
		Entry<K, V> toReturn=null;
		boolean encontre=false;
			Iterator<Entrada<K,V>> it=arregloBucket[hashThisKey(key)].iterator();
			while(it.hasNext() && !encontre){
				Entrada<K,V> e=it.next();
				if(e.getKey().equals(key)){
					toReturn=e;
					encontre=true;
				}
			}			
		return toReturn;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		PositionList<Entry<K, V>> toReturn= new ListaDoblementeEnlazada<Entry<K, V>>();
		for(Entrada<K,V> e:arregloBucket[hashThisKey(key)])
		{if(e.getKey().equals(key))
			toReturn.addLast(e);}
		return toReturn;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		Entrada<K,V> nueva= new Entrada<K,V>(key,value);
		arregloBucket[hashThisKey(key)].addLast(nueva);
		n++;
		if(!(n/N < 0.5)) //Factor de carga GT.
			reHash();
		return nueva;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if(e==null) 
			throw new InvalidEntryException("entrada nula");
		Entry<K, V> toRet=null;
		boolean encontre=false;
		try {
			Iterator<Position<Entrada<K,V>>> it=arregloBucket[hashThisKey(e.getKey())].positions().iterator() ;
			while(it.hasNext()&&!encontre) {
				Position<Entrada<K,V>> p= it.next();
				if(p.element().getKey().equals(e.getKey())) {
					encontre=true;
					arregloBucket[hashThisKey(e.getKey())].remove(p);
					toRet=p.element();
				}
			}
		} catch (InvalidKeyException|InvalidPositionException e1) {
			throw new InvalidEntryException("diccionario corrupto");
		} 
		if(toRet==null)
			throw new InvalidEntryException("La entrada no se encuentra en el diccionario");
		else
			n--;
		return toRet;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> eList=new ListaDoblementeEnlazada<Entry<K,V>>();
		for(int i=0; i<N; i++){
			Iterator<Entrada<K,V>> it=arregloBucket[i].iterator();
			while(it.hasNext()){
				eList.addLast(it.next());
			}
		}
		return eList;
	}
	
	//-----------------------------------------METODOS AUXILIARES------------------------------------------------------
	
		/**
		 *	Valida la clave utilizada 
		 * @param k clave a validar
		 * @throws InvalidKeyException si no es una clave v�lida.
		 */
		private void checkKey(K k) throws InvalidKeyException{
			if (k==null){
				throw new InvalidKeyException ("Error en Mapeo: Clave nula");
			}
		}
		
		/**
		 * Calcula funci�n hash a partir de la clave k dada.
		 * @param k clave.
		 * @return posici�n del "bucket" donde se ubicar� la entrada con clave k.
		 * @throws InvalidKeyException
		 */
		private int hashThisKey(K k) throws InvalidKeyException{
			checkKey(k); //por si acaso es nula.
			return Math.abs(k.hashCode() % N); //por si acaso pasan un negativo.
		}
		
		/**
		 * Calcula el siguiente n�mero primo de un n�mero p recibido.
		 * @param p tama�o actual del arreglo de bucket.
		 * @return n�mero primo que servir� para determinar el nuevo tama�o del arreglo de bucket.
		 */
		private static int nextPrimo(int p){
		    int toReturn=p+1;		
		    while (!esPrimo(toReturn)) {
		        toReturn++;
		        }
		    return toReturn;
		}

		/**
		 * Determina si un numero es primo.
		 * @param p n�mero a determinar si es o no primo.
		 * @return <code>true</code> si p es un n�mero primo, <code>false</code> caso contrario. 
		 */
		private static boolean esPrimo(int p){
			boolean toReturn=false;
			boolean divide=false;
			int maxDivisor = (int) Math.ceil(Math.sqrt(p));
			for (int divisor = 2; divisor <= maxDivisor && !divide ; divisor++) {
	            if (p % divisor == 0) {
	                divide = true;
	            	}
				}		
	       if(divide)
				toReturn=false;
	       		
				else 
					toReturn=true;
			return toReturn;
		}
		
		/**
		 * Incrementa el tama�o del arreglo de bucket al siguiente numero primo del tama�o actual.
		 */
		private void reHash(){		
			Iterable<Entry<K,V>> entradas=entries();
			N=nextPrimo(N);
			n=0;
			arregloBucket= new PositionList[N];
			for(int i=0;i<N;i++){
				arregloBucket[i]=new ListaDoblementeEnlazada<Entrada<K,V>>();
			}
			try{
				for(Entry<K,V> e: entradas){
					this.insert(e.getKey(), e.getValue());
				}
			}catch(InvalidKeyException error){
				System.out.println("Error en reHash: "+ error.getMessage());
			}
		}
}
