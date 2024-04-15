package oo;

public class Empleado extends Persona {

	private int salarioAnual;
	
	public Empleado(String nombre, String apellidos, int salarioAnual) {
		super(nombre, apellidos);
		this.salarioAnual = salarioAnual;
	}
	
	public void cobrar() {
		System.out.println("Nómina: " + salarioAnual / 12);
	}
}
