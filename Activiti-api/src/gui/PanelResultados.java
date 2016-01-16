package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class PanelResultados extends JPanel 
{
	private static final long serialVersionUID = 1L;

	/**
	 * JScrollPanel para que el texto tenga barras de scroll si es necesario.
	 */
	private JScrollPane scrollPane;
	
	/**
	 * JTextPanel para mostra los resultados de las metricas.
	 */
	private JTextPane txtpnlResultados;
	
	
	/**
	 * Create the panel.
	 */
	public PanelResultados(final Principal aplicacion) 
	{
		setLayout(null);
		this.setBounds(0, 0, 409, 500);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 409, 455);
		add(scrollPane, BorderLayout.CENTER);
		
		txtpnlResultados = new JTextPane();
		txtpnlResultados.setEditable(false);
		scrollPane.setViewportView(txtpnlResultados);
		txtpnlResultados.setText(aplicacion.conexion.getStringResultados());
	}
}
