import java.util.Scanner;

public class AhorcadoV1 {

	private static final String PALABRA = "PAPA"; // "ABRACADABRA"

	private static final char COMODIN = '_';

	private static final String TITULO = "=== El Ahorcado ===";
	private static final String DIME = "Dime una letra";
	private static final String ENHORABUENA = 
			"Enhorabuena, has resuelto la palabra buscada: ";

	public static void main(String[] args) {
		System.out.println(TITULO);
		String objetivo = PALABRA;
		char[] enJuego = generarBase(objetivo);
		System.out.println(enJuego);
		Scanner s = new Scanner(System.in);
		boolean adivinado;
		do {
			System.out.println(DIME);
			char jugada = s.next().charAt(0); // tomamos la 1ª letra
			for (int i = 0; i < objetivo.length(); i++) {
				char letra = objetivo.charAt(i);
				if (letra == jugada) {
					enJuego[i] = jugada;
				}
			}
			adivinado = true;
			for (int i = 0; i < objetivo.length(); i++) {
				adivinado &= enJuego[i] != COMODIN;
			}
			System.out.println(enJuego);
		} while (!adivinado);
		
		System.out.println(ENHORABUENA + objetivo);
		s.close();
	}
	
	/**
	 * Genera un array de caracteres rellenos con el carácter COMODIN
	 * de la misma longitud que la palabra recibida.
	 * @param palabra de la que tomar la longitud
	 * @return array de COMODIN de la misma longitud que palabra
	 */
	private static char[] generarBase(String palabra) {
		char[] base = new char[palabra.length()];
		for (int i = 0; i < base.length; i++) {
			base[i] = COMODIN;
		}
		return base;
	}
}
