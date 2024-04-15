package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui {
	public static void main(String[] args) {
		JFrame marco = new JFrame("Muestrario GUI con Swing");
		marco.setLayout(new FlowLayout());

		JPanel panelBorder = new JPanel();
		panelBorder.setBackground(Color.BLUE);
		panelBorder.setLayout(new BorderLayout());
		panelBorder.add(new JButton("NORTE"), BorderLayout.NORTH);
		panelBorder.add(new JButton("SUR"), BorderLayout.SOUTH);
		panelBorder.add(new JButton("ESTE"), BorderLayout.EAST);
		panelBorder.add(new JButton("OESTE"), BorderLayout.WEST);
		panelBorder.add(new JButton("CENTRO"), BorderLayout.CENTER);
		marco.add(panelBorder);
		
		JPanel panelBoxVertical = new JPanel();
		panelBoxVertical.setLayout(
			new BoxLayout(panelBoxVertical, BoxLayout.PAGE_AXIS));
		panelBoxVertical.setBackground(Color.GREEN);
		panelBoxVertical.add(new JLabel("etiqueta"));
		panelBoxVertical.add(new JTextField("campo de texto"));
		panelBoxVertical.add(new JTextArea("área de texto", 5, 15));
		marco.add(panelBoxVertical);
		
		JPanel panelBoxHorizontal = new JPanel();
		panelBoxHorizontal.setLayout(
			new BoxLayout(panelBoxHorizontal, BoxLayout.LINE_AXIS));
		panelBoxHorizontal.setBackground(Color.CYAN);
		
		ButtonGroup grupo = new ButtonGroup();
		for (int i = 0; i < 5; i++) {
			JRadioButton radio = new JRadioButton("Opción " + i);
			grupo.add(radio);
			panelBoxHorizontal.add(radio);
		}
		marco.add(panelBoxHorizontal);
		
		JPanel panelGrid = new JPanel();
		panelGrid.setBackground(Color.ORANGE);
		panelGrid.setLayout(new GridLayout(2, 3));
		for (int i = 0; i < 6; i++) {
			JCheckBox check = new JCheckBox("Opción " + (char)('A' + i));
			panelGrid.add(check);
		}
		marco.add(panelGrid);
		
		JPanel panelCard = new JPanel();
		panelCard.setBackground(Color.MAGENTA);
		CardLayout layoutTarjetas = new CardLayout();
		panelCard.setLayout(layoutTarjetas);

		ActionListener listenerTarjetas = 
			evento -> layoutTarjetas.next(panelCard);

		JPanel tarjeta1 = new JPanel();
		JButton boton1 = new JButton(
			new ImageIcon(Gui.class.getResource("explosion.png")));
		boton1.addActionListener(listenerTarjetas);
		tarjeta1.add(boton1);
		panelCard.add(tarjeta1);
		
		JPanel tarjeta2 = new JPanel();
		JButton boton2 = new JButton(
			new ImageIcon(Gui.class.getResource("mina.png")));
		boton2.addActionListener(listenerTarjetas);
		tarjeta2.add(boton2);
		panelCard.add(tarjeta2);
		
		JPanel tarjeta3 = new JPanel();
		JButton boton3 = new JButton("FIN");
		boton3.addActionListener(listenerTarjetas);
		tarjeta3.add(boton3);
		panelCard.add(tarjeta3);
		
		marco.add(panelCard);

		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setBounds(50, 100, 300, 500);
		marco.setVisible(true);
	}
}
