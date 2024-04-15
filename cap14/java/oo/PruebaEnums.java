package oo;

public class PruebaEnums {
	public static void main(String[] args) {
		// prueba Nivel
		System.out.println("PRUEBA DE NIVEL");
		Nivel nivel = Nivel.FACIL;
		
		System.out.println("Quiero el nivel " + nivel);
		
		if (nivel == Nivel.IMPOSIBLE) {
			System.out.println("El nivel es demasiado alto.");
		} else {
			System.out.println("Tú puedes.");
		}
		
		System.out.println("Los niveles posibles son:");
		for (Nivel n : Nivel.values()) {
			System.out.println(n);
		}
		System.out.println();
		
		// prueba RangoEdad
		System.out.println("PRUEBA DE RANGO DE EDAD");
		int[] edades = {3, 5, 7, 12, 17};
		for (int edad : edades) {
			for (RangoEdad rango : RangoEdad.values()) {
				System.out.println("La edad " + edad + " "
						+ (rango.enRango(edad) ? "sí" : "no") 
						+ " está en rango para " + rango);
			}
		}
	}
}
