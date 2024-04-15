package oo;

import java.time.LocalDate;

public class PruebaPersonaOculta {
	
	public static void main(String[] args) {
		LocalDate edadHoy17 = LocalDate.now().minusYears(17);
		LocalDate edadHoy20YMedio = 
				LocalDate.now().minusYears(20).minusMonths(6);
		
		PersonaOculta p1 = new PersonaOculta(
				"Paula", "Gala", edadHoy17, "rojo");
		System.out.println(p1.getNombre() + " es mayor de edad: " 
				+ p1.esAdulto());
		if (p1.cumpleHoy()) {
			System.out.println("Hoy hay que felicitar a " 
					+ p1.getNombre() + " " + p1.getApellidos());
		}
		
		PersonaOculta p2 = new PersonaOculta(
				"Pablo", "Prego", edadHoy20YMedio, "azul");
		System.out.println(p2.getNombre() + " es mayor de edad: " 
				+ p2.esAdulto());
		if (p2.cumpleHoy()) {
			System.out.println("Hoy hay que felicitar a " 
					+ p2.getNombre() + " " + p2.getApellidos());
		}

		// Colores
		System.out.println("Colores Favoritos: " 
				+ p1.getNombre() + " " + p1.getColorFavorito() + " " 
				+ p2.getNombre() + " " + p2.getColorFavorito());
		// Ahora a Pablo le gusta el color favorito de Paula
		p2.setColorFavorito(p1.getColorFavorito());
		System.out.println("Colores Favoritos: " 
				+ p1.getNombre() + " " + p1.getColorFavorito() + " " 
				+ p2.getNombre() + " " + p2.getColorFavorito());

	}
}