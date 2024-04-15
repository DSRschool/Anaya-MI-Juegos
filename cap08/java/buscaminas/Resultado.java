package buscaminas;

public enum Resultado {
	BOMBA("¡Bomba! ¡Has perdido!"),
	FIN("Ya hemos terminado"),
	GANADA("¡Bien! ¡Has ganado!"),
	ANOTADO("¡Anotado!"),
	BIEN("¡Bien!"),
	ERROR("Quizá deberías revisar las marcas");

	private String mensaje;
	
	Resultado(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
