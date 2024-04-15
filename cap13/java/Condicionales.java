import java.util.Scanner;

public class Condicionales {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Dame un número entero: ");
		int num = s.nextInt();
		
		// una rama
		if (num == 0) {
			System.out.println("Es cero");
		}
		
		// dos ramas
		if (num % 2 == 0) {
			System.out.println("Es par");
		} else {
			System.out.println("Es impar");
		}
		
		// varias ramas
		if (num < -100) {
			System.out.println("Es muy negativo");
		} else if (num < 0) {
			System.out.println("Es negativo");
		} else if (num == 0) {
			System.out.println("Ya te dije que es cero");
		} else if (num > 100) {
			System.out.println("Es muy positivo");
		} else { // entre 1 y 99
			System.out.println("Es positivo");
		}
		
		// ternario
		System.out.println("En ternario, es " 
				+ ((num % 2 == 0) ? "par" : "impar"));

		// sentencia switch
		String figura;
		switch (num) {
		case 4:
			figura = "cuadrilátero";
			break;
		case 5:
			figura = "pentágono";
			break;
		case 6:
			figura = "hexágono";
			break;
		default:
			figura = "no lo sé";
		}
		System.out.println("figura = " + figura);
		
		// la nueva expresión switch
		String nuevaFigura = switch (num) {
			case 4 -> "cuadrilátero";
			case 5 -> "pentágono";
			case 6 -> "hexágono";
			default -> "no lo sé";
		};
		System.out.println("nuevaFigura = " + nuevaFigura);
		
		// cierre del scánner
		s.close();
	}
}
