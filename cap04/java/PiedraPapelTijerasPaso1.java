public class PiedraPapelTijerasPaso1 {
	// Declaración de constantes
	private static final String[] JUEGO = {"P", "L", "T"}; 
	private static final String SALIR = "S";

	private static final int EMPATE = 0;
	private static final int GANAS = 1;
	private static final int PIERDES = 2;
	private static final int ERROR_NO_ENCONTRADA = -1;
	
	private static final String BIENVENIDA = 
			"=== Vamos a jugar al Piedra, Papel, Tijeras ===";
	private static final String DESPEDIDA = "¡Gracias por jugar!";
	private static final String PEDIR_JUGADA = "¿Cuál es tu jugada? "
			+ "[P]iedra, pape[L], [T]ijeras o [S]alir";
	private static final String MSJ_GANAS = "¡Has ganado!";
	private static final String MSJ_PIERDES = "¡Has perdido!";
	private static final String MSJ_EMPATE = "¡Hemos empatado!";
	private static final String MSJ_ERROR = "No te he entendido.";
}