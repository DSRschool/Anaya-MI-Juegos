import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class AhorcadoV3 {

	private static final String[] PALABRAS = { 
			"PELOTA", "CAMISA", "PESADO", "AMIGAS", "ZAPATO", 
			"TOMATE", "JUGADA", "ALMEJA", "PETATE", "LUCERO",
			"AMIGA", "BUCEO", "CASPA", "DEDAL", "ELITE", "FUEGO" };
	private static final int NUM_FALLOS = 5;

	private static final char COMODIN = '_';

	private static final String TITULO = "=== El Ahorcado ===";
	private static final String DIME = "Dime una letra";
	private static final String ENHORABUENA = 
			"Enhorabuena, has resuelto la palabra buscada: ";
	private static final String PERDIDO = "¡Oooh! Ya no te quedan "
			+ "intentos para dar con la palabra buscada: ";

	private static final String ERROR_MUCHAS = 
			"No vale, solo una letra";
	private static final String INTENTOS = "Intentos: ";
	private static final String INTENTOS_GASTADOS = "*";
	private static final String INTENTOS_RESTANTES = "·";

	public static void main(String[] args) {
		System.out.println(TITULO);
		String objetivo = escogerObjetivo();
		char[] enJuego = generarBase(objetivo);
		System.out.println(String.valueOf(enJuego)
				+ "\t" + INTENTOS + intentos(0));
		
		try (Scanner s = new Scanner(System.in)) {
			int fallos = 0;
			boolean adivinado = false;
			do {
				boolean acierto = false;
				System.out.println(DIME);
				String entrada = s.next();
				if (entrada.length() > 1) {
					System.err.println(ERROR_MUCHAS);
					continue;
				}
				char jugada = entrada.toUpperCase().charAt(0); // tomamos la primera letra
				for (int i = 0, lon = objetivo.length(); i < lon; i++) {
					char letra = objetivo.charAt(i);
					if (letra == jugada) {
						acierto = true;
						enJuego[i] = jugada;
					}
				}
				if (!acierto) {
					fallos++;
				}
				adivinado = true;
				for (int i = 0; i < objetivo.length(); i++) {
					adivinado &= enJuego[i] != COMODIN;
				}
				System.out.println(String.valueOf(enJuego) 
						+ "\t" + INTENTOS + intentos(fallos));
			} while (!adivinado && fallos < NUM_FALLOS);

			System.out.println(
					(adivinado ? ENHORABUENA : PERDIDO) + objetivo);
		}
	}

	private static String escogerObjetivo() {
		Random r = new Random();
		return PALABRAS[r.nextInt(PALABRAS.length)];
	}

	/**
	 * Genera un array de caracteres rellenos con el carácter COMODIN
	 * de la misma longitud que la palabra recibida.
	 * @param palabra de la que tomar la longitud
	 * @return array de COMODIN de la misma longitud que palabra
	 */
	private static char[] generarBase(String palabra) {
		char[] base = new char[palabra.length()];
		Arrays.fill(base, COMODIN);
		return base;
	}
	
	private static String intentos(int fallos) {
		String intentos = "";
		for (int i = 0; i < fallos; i++) {
			intentos += INTENTOS_GASTADOS;
		}
		for (int i = 0; i < NUM_FALLOS - fallos; i++) {
			intentos += INTENTOS_RESTANTES;
		}
		return intentos;
	}
}
