package TDAConjunto;

import java.util.Vector;

public class ConjuntoArreglo<E> implements Conjunto<E> {
	
	private E[] s;
	
	
	public ConjuntoArreglo (int max) {
		s= (E[]) new Object[max];
	}

	@Override
	public int size() {
		int cant=0;
		for(int i=0;i<s.length;i++)
			if(s[i]!=null)
				cant++;
	    return cant;
	}

	@Override
	public int capacity() {
		
		return s.length;
	}

	@Override
	public boolean isEmpty() {
		
		return size()==0;
	}

	@Override
	public E get(int i) {
		return s[i];
	}

	@Override
	public void put(E elem) {
		boolean encontre=false;
		for(int i=0; i<s.length && !encontre;i++)
			if(s[i]==null)
			{	s[i]=elem;
			    encontre=true;}
		
	}
	
	public boolean pertenece(E elem) {
		boolean encontre=false;
		for(int i=0; i<s.length && !encontre;i++)
			encontre =elem.equals(s[i]);
	     return encontre;
		
	}
	
	public boolean perteneceRec(E elem) {
	     return perteneceRec(elem,s.length);
		
	}
	
/* * CB: si el conjunto est� vac�o, el elemento no se encuentra en el conjunto.
	 * CR: si el conjunto tiene elementos, el elemento se encuentra en el conjunto si y solo si el elemento
	 * es igual al �ltimo elemento del conjunto o si se encuentra en conjunto'. Siendo conjunto' igual
	 * a conjunto sin su �ltimo elemento*/
	
	private boolean perteneceRec(E elem, int n) {
	 boolean pertenece;
	 if(n==0)
		 pertenece=false;
	 else
		 pertenece= elem.equals(s[n-1])||perteneceRec(elem,n-1);
	 return pertenece;	
	}

	@Override
	public Conjunto<E> intersection(Conjunto<E> c) {
	int longMenor=menor(size(),c.size());
	Conjunto<E> intersectado= new ConjuntoArreglo<E>(longMenor);
	Conjunto<E> aRecorrer;
	Conjunto<E> otro;
	if(size()<c.size())
		{aRecorrer=this; otro=c;}
	else
		{aRecorrer=c; otro=this;}

	
	for(int i=0;i<aRecorrer.capacity();i++)
		if(otro.pertenece(aRecorrer.get(i)))
				intersectado.put(aRecorrer.get(i));
	   
	return intersectado;
	}

	private int menor(int a, int b) {
		int men;
		if(a<b)
			men=a;
		else
			men=b;
		return men;
	}
}
