package CajaConHerencia;

public class Perro extends Elemento {
	
	private int edad;
	private String nombre;
	
	public Perro(int e, String n) {
		edad=e;
		nombre=n;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
