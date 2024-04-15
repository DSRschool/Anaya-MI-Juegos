public class Tablero3EnRaya {

	private static final String X = "X";
	private static final String O = "O";
	private static final String VACIA = " ";

	private static final String N = System.lineSeparator();

	// Tableros de ejemplo
	private static final String[][] TABLERO_VACIO = { 
			{ VACIA, VACIA, VACIA }, 
			{ VACIA, VACIA, VACIA },
			{ VACIA, VACIA, VACIA } 
		};
	private static final String[][] TABLERO_LLENO = { 
			{ VACIA, O, VACIA }, 
			{ X, O, X }, 
			{ X, O, VACIA } 
		};

	public static final void pintarTablero(String[][] tablero) {
		String texto = "";
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				texto += VACIA + tablero[i][j] + VACIA;
				if (j != tablero[i].length - 1) {
					texto += "|";
				} else {
					texto += N;
				}
			} // for j
			if (i != tablero.length - 1) {
				for (int j = 0; j < tablero[i].length; j++) {
					texto += "---";
					if (j != tablero[i].length - 1) {
						texto += "+";
					} else {
						texto += N;
					}
				}
			} else {
				texto += N;
			}
		} // for is
		System.out.println(texto);
	}

	public static void main(String[] args) {
		System.out.println("Tablero vacío:");
		pintarTablero(TABLERO_VACIO);
		System.out.println("Tablero lleno:");
		pintarTablero(TABLERO_LLENO);
	}
}
