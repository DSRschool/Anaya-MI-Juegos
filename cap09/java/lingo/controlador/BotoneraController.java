package lingo.controlador;

import static lingo.modelo.Lingo.RANDOM;
import static lingo.vista.Textos.MAS_PISTAS;
import static lingo.vista.Textos.MENS_AYUDA;
import static lingo.vista.Textos.NUEVA;
import static lingo.vista.Textos.PISTA_ALEATORIA;
import static lingo.vista.Textos.SALIR;
import static lingo.vista.Textos.SOLUCION;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lingo.modelo.Letra;
import lingo.modelo.Lingo;
import lingo.modelo.NoDiccionarioException;
import lingo.modelo.NoMasPistasException;
import lingo.modelo.Palabra;
import lingo.vista.LingoGrafico;

public class BotoneraController implements ActionListener {
	
	private Lingo modelo;
	private LingoGrafico vista;
	
	public BotoneraController(Lingo modelo, LingoGrafico vista) {
		this.modelo = modelo;
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case NUEVA:
			try {
				tratarBotonNueva();
			} catch (NoDiccionarioException nde) {
				vista.error(nde);
			}
			break;
		case MAS_PISTAS:
			darPista();
			break;
		case SOLUCION:
			vista.resolver();
			break;
		case SALIR:
			System.exit(0);
		case PISTA_ALEATORIA:
			modelo.alternarAleatoria();
			break;
		}
	}

	private void tratarBotonNueva() throws NoDiccionarioException {
		Palabra objetivo = modelo.nuevaPalabra();
		vista.reiniciar();
		int pista;
		if (modelo.getConfiguracion().isAleatoria()) {
			pista = RANDOM.nextInt(modelo.getLongitudPalabra());
		} else {
			pista = 0;
		}
		vista.rellenarCasilla(pista, objetivo.getLetra(pista));
		objetivo.letraColocada(pista);
		vista.actualizarEstado(MENS_AYUDA);
		vista.repintarPuntuacion();
	}

	private void darPista() {
		try {
			Palabra objetivo = modelo.getObjetivo();
			int posicionPista = objetivo.darPista();
			Letra letra = objetivo.getLetra(posicionPista);
			vista.rellenarCasilla(posicionPista, letra);
			modelo.otraPista();
		} catch (NoMasPistasException e) {
			vista.noMasPistas();
		}
	}
}
