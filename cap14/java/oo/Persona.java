package oo;

public class Persona {
	private String nombre;
	private String apellidos;

	public Persona(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public String firmarConNombreCompleto() {
		return nombre + " " + apellidos;
	}

	public String firmarConIniciales() {
		return nombre.charAt(0) + "" + apellidos.charAt(0);
	}
}
