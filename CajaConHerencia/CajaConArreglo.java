package CajaConHerencia;

public class CajaConArreglo implements Caja{
	
	private Elemento[] arreglo;
	
	public CajaConArreglo(){
		arreglo= new Elemento[10];
	}

	@Override
	public void insertar(Elemento elem) {
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
	public void eliminar(Elemento elem) {
		boolean elimine=false;
		for(int i=0; i< arreglo.length && !elimine; i++) {
			if(elem.equals(arreglo[i])) {
				arreglo[i]=null;
				elimine=true;
			}
				
		}
				
	}

	@Override
	public boolean buscar(Elemento elem) {
		boolean encontre=false;
		for(int i=0; i< arreglo.length && !encontre; i++) 
			encontre= elem.equals(arreglo[i]);
		return encontre;							
		
	}
	
	public Elemento recuperarAlguno() {
		boolean encontre=false;
		Elemento toRet=null;
		for(int i=0; i< arreglo.length && !encontre; i++) 
		 if(arreglo[i]!=null) {
			encontre= true;
			toRet=arreglo[i];}
		
		return toRet;			
		
	}

}
