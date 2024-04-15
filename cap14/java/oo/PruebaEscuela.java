package oo;

public class PruebaEscuela {
	
	public static void main(String[] args) {
		Alumno alumno1 = new Alumno("Alex", "Montes", "BC3254");
		System.out.println(alumno1.firmarConNombreCompleto());
		alumno1.entregarTarea();
		
		Profesor profe1 = new Profesor("Juan", "Grau", 44330, "YW8769");
		System.out.println(profe1.firmarConNombreCompleto());
		profe1.cobrar();
		profe1.calificarTarea();
		
		PAS pas1 = new PAS("Dani", "Santos", 33440);
		System.out.println(pas1.firmarConNombreCompleto());
		pas1.cobrar();
		pas1.generarInforme();
	}
}
