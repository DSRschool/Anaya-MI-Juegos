package oo;

public class PruebaPersona {
	
	public static void main(String[] args) {
		Persona jefa = new Persona("Paz", "Cruces");
		System.out.println(jefa.firmarConNombreCompleto());
		System.out.println(jefa.firmarConIniciales());
		
		Persona presi = new Persona("Luis", "Planas");
		System.out.println(presi.firmarConNombreCompleto());
		System.out.println(presi.firmarConIniciales());
	}
}
