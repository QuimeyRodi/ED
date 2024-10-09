package CajaParametrica;

public class CajaConArreglo<E> implements Caja<E>{
	
	private E[] arreglo;
	
	public CajaConArreglo(){
		arreglo= (E[])new Object[10];
	}

	@Override
	public void insertar(E elem) {
		boolean pudeInsertar=false;
		for(int i=0; i< arreglo.length && !pudeInsertar; i++) {
			if(arreglo[i]==null) {
				arreglo[i]=elem;
				pudeInsertar=true;
			}
				
		}
		
	 if(!pudeInsertar)
		 	System.out.println("No se pudo insertar, espacio insuficiente");
		
	}

	@Override
	public void eliminar(E elem) {
		boolean elimine=false;
		for(int i=0; i< arreglo.length && !elimine; i++) {
			if(elem.equals(arreglo[i])) {
				arreglo[i]=null;
				elimine=true;
			}
				
		}
				
	}

	@Override
	public boolean buscar(E elem) {
		boolean encontre=false;
		for(int i=0; i< arreglo.length && !encontre; i++) 
			encontre= elem.equals(arreglo[i]);
		return encontre;							
		
	}
	
	public E recuperarAlguno() {
		boolean encontre=false;
		E toRet=null;
		for(int i=0; i< arreglo.length && !encontre; i++) 
		 if(arreglo[i]!=null) {
			encontre= true;
			toRet=arreglo[i];}
		
		return toRet;			
		
	}

}
