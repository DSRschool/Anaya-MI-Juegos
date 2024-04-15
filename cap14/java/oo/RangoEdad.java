package oo;

public enum RangoEdad {
	INFANTIL(0, 6),
	PRIMARIA(6, 12),
	SECUNDARIA(12, 16),
	BACHILLERATO(16, 18);

	private int ini;
	private int fin;
	
	RangoEdad(int ini, int fin) {
		this.ini = ini;
		this.fin = fin;
	}
	
	public boolean enRango(int edad) {
		return ini <= edad && edad >= fin;
	}
}
