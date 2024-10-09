package TDAMapeo;

import java.util.Iterator;

import Auxiliares.Entrada;
import Auxiliares.Entry;
import Auxiliares.Position;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

public class MapeoConLista<K,V> implements Map<K,V> {

	private PositionList<Entrada<K,V>> pl;
	
	public MapeoConLista() {
		pl= new ListaDoblementeEnlazada<Entrada<K,V>>();
	}
	@Override
	public int size() {
		return pl.size();
	}

	@Override
	public boolean isEmpty() {
		return pl.isEmpty();
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		if(key==null)
				throw new InvalidKeyException("clave nula");
		Iterator<Entrada<K,V>> it= pl.iterator();
		V toRet=null;
		boolean encontre=false;
		while(it.hasNext()&&!encontre)
			{Entrada<K,V> actual=it.next();
			 if(actual.getKey().equals(key))
			 	{encontre=true;
			 	toRet=actual.getValue();
			 }
			}
		return toRet;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("clave nula");
		Iterator<Entrada<K,V>> it= pl.iterator();
		V toRet=null;
		boolean encontre=false;
		while(it.hasNext()&&!encontre)
		{Entrada<K,V> actual=it.next();
		 if(actual.getKey().equals(key))
		 	{encontre=true;
		 	 toRet=actual.getValue();
		 	 actual.setValue(value);
		 }
		}
		if(!encontre)
			pl.addLast(new Entrada<K,V>(key,value));
	return toRet;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("clave nula");
		Iterator<Position<Entrada<K,V>>> it= pl.positions().iterator();
		V toRet=null;
		try {
			boolean encontre=false;
			while(it.hasNext()&&!encontre) {
				Position<Entrada<K,V>> p= it.next();
				if(p.element().getKey().equals(key))
					{pl.remove(p);
					 toRet=p.element().getValue();}
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		
		return toRet;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> nueva= new ListaDoblementeEnlazada<K>();
		for(Entrada<K,V> e: pl)
			nueva.addLast(e.getKey());
		return nueva;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> nueva= new ListaDoblementeEnlazada<V>();
		for(Entrada<K,V> e: pl)
			nueva.addLast(e.getValue());
		return nueva;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> nueva= new ListaDoblementeEnlazada<Entry<K, V>>();
		for(Entrada<K,V> e: pl)
			nueva.addLast(e);
		return nueva;
	}

}
