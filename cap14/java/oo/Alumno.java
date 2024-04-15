package oo;

public class Alumno extends Persona {

	private String numMatricula;
	
	public Alumno(String nombre, String apellidos, String numMatricula) {
		super(nombre, apellidos);
		this.numMatricula = numMatricula;
	}
	
	public void entregarTarea() {
		System.out.println("Tarea entregada por " + numMatricula);
	}
}
