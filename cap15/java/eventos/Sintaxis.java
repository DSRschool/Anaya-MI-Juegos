package eventos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Sintaxis extends JFrame {
	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;

	public Sintaxis() {
		super("Sintaxis");
		setLayout(new GridLayout());

		boton1 = new JButton();
		boton1.setText("Botón 1");
		boton1.addActionListener(new RatonListener());
		add(boton1);
		
		boton2 = new JButton();
		boton2.setText("Botón 2");
		boton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("actionPerformed en clase anónima");
			}
		});
		add(boton2);

		boton3 = new JButton();
		boton3.setText("Botón 3");
		boton3.addActionListener(new ListenerInterno());
		add(boton3);
		
		boton4 = new JButton();
		boton4.setText("Botón 4");
		boton4.addActionListener(event -> 
			System.out.println("actionPerformed en lambda"));
		add(boton4);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 300, 100);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Sintaxis();
	}
	
	class ListenerInterno implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("actionPerformed en clase interna");
		}
	}
}
