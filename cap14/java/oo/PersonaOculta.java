package oo;

import java.time.LocalDate;
import java.time.Period;

public class PersonaOculta {
	private String nombre;
	private String apellidos;
	private LocalDate fechaNac;
	private String colorFavorito;
	
	public PersonaOculta(String nombre, String apellidos, 
			LocalDate fechaNac, String colorFavorito) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
		this.colorFavorito = colorFavorito;
		System.out.println("--- " + nombre + " " + apellidos 
				+ " nació el " + fechaNac);
	}

	public boolean esAdulto() {
		return edad().getYears() >= 18;
	}
	
	public boolean cumpleHoy() {
		return edad().getMonths() == 0 && edad().getDays() == 0;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public String getColorFavorito() {
		return colorFavorito;
	}
	
	public void setColorFavorito(String colorFavorito) {
		this.colorFavorito = colorFavorito;
	}
	
	private Period edad() {
		return Period.between(fechaNac, LocalDate.now());
	}
}