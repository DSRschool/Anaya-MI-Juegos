package oo;

public class Profesor extends Empleado {

	private String numColegiado;
	
	public Profesor(String nombre, String apellidos, int salarioAnual,
			String numColegiado) {
		super(nombre, apellidos, salarioAnual);
		this.numColegiado = numColegiado;
	}
	
	public void calificarTarea() {
		System.out.println("Tarea calificada por " + numColegiado);
	}
}
