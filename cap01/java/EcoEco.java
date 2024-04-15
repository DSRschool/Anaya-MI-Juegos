import java.util.Scanner;

public class EcoEco {

	public static void main(String[] args) {
		System.out.println("Juego del \"eco, eco\": dime algo que yo lo repito");
		Scanner s = new Scanner(System.in);
		System.out.print("¿eco? ");
		String frase = s.nextLine();
		System.out.println("¡eco! " + frase);
		s.close();
	}
}
