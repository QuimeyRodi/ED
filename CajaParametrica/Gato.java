package CajaParametrica;

public class Gato {
	private int edad;
	private String nombre;
	
	public Gato(int e, String n) {
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
