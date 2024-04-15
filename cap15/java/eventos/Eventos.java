package eventos;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Eventos extends JFrame {
	private JButton boton;
	private JTextField campo;
	private JTextArea espia;
	private JLabel mensaje;
	private JLabel contador;
	private int contadorEventos = 0;

	public Eventos() {
		super("Eventos");
		setLayout(new BorderLayout());

		boton = new JButton();
		boton.setText("Botón de los eventos");
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarEvento(e, "ActionPerformed en el botón");				
			}
		});
		add(boton, BorderLayout.WEST);

		campo = new JTextField();
		campo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				mostrarEvento(e, "Has tecleado " + e.getKeyChar() 
					+ " (" + e.getKeyCode() + ")");
			}

			@Override
			public void keyReleased(KeyEvent e) {
				mostrarEvento(e, "Has soltado " + e.getKeyChar() 
					+ " (" + e.getKeyCode() + ")");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				mostrarEvento(e, "Has pulsado " + e.getKeyChar() 
					+ " (" + e.getKeyCode() + ")");
			}
		});
		add(campo, BorderLayout.NORTH);

		espia = new JTextArea("Datos del evento");
		espia.setEditable(false);
		espia.setLineWrap(true);
		espia.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				mostrarEvento(e, "El foco ya no está en el espía");
			}

			@Override
			public void focusGained(FocusEvent e) {
				mostrarEvento(e, "El foco está en el cuadro espía");
			}
		});
		add(espia, BorderLayout.CENTER);

		mensaje = new JLabel("Zona de mensajes");
		mensaje.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				mostrarEvento(e, "Has soltado el ratón");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				mostrarEvento(e, "Has presionado el ratón");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mostrarEvento(e, "El ratón ha salido");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mostrarEvento(e, "El ratón ha entrado");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarEvento(e, "Has hecho clic con el ratón");
			}

		});
		add(mensaje, BorderLayout.SOUTH);
		
		contador = new JLabel("" + contadorEventos);
		add(contador, BorderLayout.EAST);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 500, 150);
		setVisible(true);
	}

	private void mostrarEvento(AWTEvent e, String textoMensaje) {
		contador.setText("" + ++contadorEventos);
		espia.setText(e.paramString());
		mensaje.setText(textoMensaje);
	}

	public static void main(String[] args) {
		new Eventos();
	}
}
