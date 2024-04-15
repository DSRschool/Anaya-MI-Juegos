package eventos;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Adaptador extends JFrame {
	private JButton boton1;
	private JButton boton2;

	public Adaptador() {
		super("Adaptador");
		setLayout(new GridLayout());

		boton1 = new JButton();
		boton1.setText("Botón 1");
		boton1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("mouseReleased");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("mousePressed");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("mouseExited");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("mouseEntered");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("mouseClicked");
			}
		});
		add(boton1);

		boton2 = new JButton();
		boton2.setText("Botón 2");
		boton2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("mouseClicked en MouseAdapter");
			}
		});
		add(boton2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 300, 100);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Adaptador();
	}
}
