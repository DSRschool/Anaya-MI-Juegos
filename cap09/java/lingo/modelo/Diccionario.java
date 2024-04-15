package lingo.modelo;

import static lingo.vista.Textos.ERROR_DICCIONARIO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Diccionario {
	private static List<String> diccionario;
	private static int longitudDiccionario;
	private static int longitudPalabra;

	public static void cargarDiccionario(int longitudPalabra) 
			throws NoDiccionarioException {
		try (InputStream is = Diccionario.class.getResourceAsStream(
					"dicc" + longitudPalabra + ".dic");
				Scanner entrada = new Scanner(is)) {
			diccionario = new ArrayList<>();
			while (entrada.hasNextLine()) {
				diccionario.add(entrada.nextLine());
			}
			longitudDiccionario = diccionario.size();
			Diccionario.longitudPalabra = longitudPalabra;
		} catch (IOException | NullPointerException e) {
			throw new NoDiccionarioException(
				String.format(ERROR_DICCIONARIO, longitudPalabra));
		}
	}

	public static String obtenerPalabra() {
		return diccionario.get(
				Lingo.RANDOM.nextInt(longitudDiccionario));
	}
	
	public static boolean palabraValida(String palabra) {
		return diccionario.contains(palabra);
	}

	public static int getLongitudPalabra() {
		return longitudPalabra;
	}
}
