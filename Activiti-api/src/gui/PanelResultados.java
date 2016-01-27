package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartPanel;

public class PanelResultados extends JPanel 
{
	private static final long serialVersionUID = 1L;

	/**
	 * Panel de pestañas para mostrar los resultados y los graficos resultantes.
	 */
	private JTabbedPane tabpnlPrincipal;
	
	/**
	 * JScrollPanel para que el texto tenga barras de scroll si es necesario.
	 */
	private JScrollPane scrpnlAuxiliar;
	
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
		this.setBounds(0, 0, 700, 470);
		
		tabpnlPrincipal = new JTabbedPane(JTabbedPane.TOP);
		tabpnlPrincipal.setBounds(0, 0, 695, 450);
		add(tabpnlPrincipal);
		
		scrpnlAuxiliar = new JScrollPane();
		
		txtpnlResultados = new JTextPane();
		txtpnlResultados.setEditable(false);
				
		Object[] resultados = aplicacion.conexion.getResultados();
		for(Object x : resultados)
		{
			switch (x.getClass().getName())
			{
				case "java.lang.String": 
					txtpnlResultados.setText((String)x);
					scrpnlAuxiliar.setViewportView(txtpnlResultados);
					tabpnlPrincipal.addTab("Resultados", null, scrpnlAuxiliar, "Resultados obtenidos en formato texto.");
					break;
				case "org.jfree.chart.ChartPanel":
					JScrollPane scrPnlGRafico = new JScrollPane();
					scrPnlGRafico.setViewportView((ChartPanel)x);
					tabpnlPrincipal.addTab(((ChartPanel)x).getChart().getTitle().getText(), null, scrPnlGRafico, "Uno de los graficos de los resultados.");
					break;
			}
		}
	}
}
