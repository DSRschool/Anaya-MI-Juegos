import java.util.Scanner;

public class Iterativas {

	public static void main(String[] args) {
		System.out.println("// for each");
		for (String s : args) {
			System.out.print(s + " ");
		}
		System.out.println();
		
		System.out.println("// for");
		for (int i = 0; i < 5; i ++) {
			System.out.print(i + ".");
		}
		System.out.println();
		
		System.out.println("// while");
		int i = 0;
		while (i < args.length && !"x".equals(args[i])) {
			System.out.print(args[i] + " ");
			i++;
		}
		System.out.println();

		System.out.println("// do while");
		Scanner s = new Scanner(System.in);
		String entrada;
		String salida = "";
		do {
			System.out.println("Dime algo, x para terminar");
			entrada = s.next();
			salida += entrada;
		} while (!"x".equals(entrada));
		System.out.println("FIN: " + salida);
		s.close();
		
		System.out.println("// rectángulo 3 x 8");
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 8; y++) {
				System.out.print("X");
			}
			System.out.println();
		}
	}
}
