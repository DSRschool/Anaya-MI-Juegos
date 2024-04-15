package lingo.vista;

import static lingo.vista.Textos.MENS_ENTRADA_NOVALIDA;
import static lingo.vista.Textos.MENS_FELIZ;
import static lingo.vista.Textos.MENS_NOMASPISTAS;
import static lingo.vista.Textos.MENS_SOLUCION;
import static lingo.vista.Textos.MENS_TERMINADA;
import static lingo.vista.Textos.TITULO;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lingo.controlador.BotoneraController;
import lingo.controlador.EntradaController;
import lingo.controlador.MenuController;
import lingo.modelo.Letra;
import lingo.modelo.Lingo;

public class LingoGrafico extends JFrame {

	private Lingo modelo;
	
	private JPanel pTablero;
	private Logotipo logotipo;
	private Botonera botonera;
	private Marcador marcador;
	private Entrada entrada;
	private Panel panel;
	private BarraEstado barraEstado;

	public LingoGrafico(Lingo modelo) {
		super(TITULO);
		this.modelo = modelo;

		setLayout(new BorderLayout());
		pTablero = new JPanel(new BorderLayout());

		logotipo = new Logotipo();
		pTablero.add(logotipo, BorderLayout.NORTH);
		
		botonera = new Botonera(new BotoneraController(modelo, this));
		pTablero.add(botonera, BorderLayout.EAST);
		
		marcador = new Marcador(modelo.getPuntuacion());
		pTablero.add(marcador, BorderLayout.WEST);
		
		entrada = new Entrada(new EntradaController(modelo, this));
		pTablero.add(entrada, BorderLayout.SOUTH);
		
		panel = new Panel(modelo);
		pTablero.add(panel, BorderLayout.CENTER);

		setJMenuBar(new Menu(modelo, new MenuController(modelo)));
		add(pTablero, BorderLayout.CENTER);
		
		barraEstado = new BarraEstado();
		add(barraEstado, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 500, 350);
		setVisible(true);
	}

	public void reiniciar() {
		if (modelo.getConfiguracion().getNumeroLineas() 
				!= panel.getTamanyo()) {
			pTablero.remove(panel);
			panel = new Panel(modelo);
			pTablero.add(panel, BorderLayout.CENTER);
		} else {
			panel.limpiar();
		}
		botonera.reiniciar();
		entrada.abrir();
	}
	
	public void rellenarCasilla(int posicion, Letra letra) {
		panel.rellenarCasilla(
				modelo.getEstado().getLineaActual(), posicion, letra);
	}
	
	public void actualizarEstado(String mensaje) {
		barraEstado.actualizarEstado(mensaje);
	}

	public void repintarPuntuacion() {
		marcador.repintarPuntuacion();
	}
	
	public void pintarJugada() {
		panel.pintarPista();
		entrada.limpiar();
		entrada.abrir();
	}

	public void prepararLineaSiguiente() {
		if (modelo.getEstado().isTerminada()) {
			barraEstado.actualizarEstado(String.format(
					MENS_TERMINADA, modelo.getObjetivo().getPalabra()));
			entrada.bloquear();
			botonera.noMasPistas();
		} else {
			panel.prepararLineaSiguiente();
		}
	}
	
	public void solucionar() {
		barraEstado.actualizarEstado(MENS_FELIZ);
		entrada.bloquear();
		botonera.resolver();
	}
	
	public void entradaNoValida(String palabraNoValida) {
		entrada.limpiar();
		entrada.abrir();
		barraEstado.actualizarEstado(
				String.format(MENS_ENTRADA_NOVALIDA, 
						palabraNoValida, modelo.getLongitudPalabra()));
	}
	
	public void noMasPistas() {
		botonera.noMasPistas();
		barraEstado.actualizarEstado(MENS_NOMASPISTAS);
	}
	
	public void resolver() {
		botonera.resolver();
		entrada.bloquear();
		barraEstado.actualizarEstado(String.format(MENS_SOLUCION, 
				modelo.getObjetivo().getPalabra()));
	}

	public void error(Exception e) {
		barraEstado.actualizarEstado(e.getMessage());
		botonera.inhabilitar();
	}
}
