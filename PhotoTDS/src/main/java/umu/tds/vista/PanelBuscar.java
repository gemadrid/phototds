package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelBuscar extends JPanel {
	
	//Ventana principal
	private VentanaPrincipal ventana;
	
	//TextField para introducir la búsqueda
	private JTextField textBuscar;

	/**
	 * Create the panel.
	 */
	public PanelBuscar(VentanaPrincipal v) {
		ventana = v;
		crearPanelBuscar();
	}
	
	private void crearPanelBuscar() {
		setBackground(Colores.FONDO);
		setLayout(new BorderLayout(0, 0));
		
		//Panel de búsqueda
		JPanel panelBuscarNorte = new JPanel();
		panelBuscarNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		panelBuscarNorte.setBackground(Colores.FONDO);
		add(panelBuscarNorte, BorderLayout.NORTH);
		
		//Texto Buscar
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Poppins", Font.BOLD, 25));
		panelBuscarNorte.add(lblBuscar);
		
		//Área buscar
		textBuscar = new JTextField();
		textBuscar.setFont(new Font("Poppins", Font.PLAIN, 20));
		textBuscar.setBackground(Colores.AREA_TEXTO);
		textBuscar.setBorder(null);
		textBuscar.setColumns(25);
		panelBuscarNorte.add(textBuscar);
		
		addManejadorTextBuscar();
		
		//TODO Panel de resultados de búsqueda (separados por usuarios / hashtags)
	}
	
	//Manejador del campo de búsqueda
	private void addManejadorTextBuscar() {
		textBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaBusquedaUsuario busquedaUsuario = new VentanaBusquedaUsuario(ventana, textBuscar.getText());
				busquedaUsuario.setLocationRelativeTo(ventana);
				busquedaUsuario.setVisible(true);
			}
		});
	}

}
