package oo;

public class PAS extends Empleado {

	public PAS(String nombre, String apellidos, int salarioAnual) {
		super(nombre, apellidos, salarioAnual);
	}
	
	public void generarInforme() {
		System.out.println("Informe generado");
	}
	
	@Override
	public String firmarConNombreCompleto() {
		return super.firmarConNombreCompleto() + " VALIDADO";
	}
}
