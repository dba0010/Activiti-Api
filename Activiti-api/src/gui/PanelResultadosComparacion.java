package gui;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.JScrollPane;

/**
 * Panel que muestra los resultados de comparar dos informes.
 * @author David Blanco Alonso
 */
public class PanelResultadosComparacion extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Panel para tener barras de scroll si fuese necesario.
	 */
	private JScrollPane scrollPane;
	
	/**
	 * Panel para mostrar la informacion de la comparacion.
	 */
	private JEditorPane pnlTexto;
	
	/**
	 * Creamos el panel.
	 */
	public PanelResultadosComparacion(String texto) 
	{
		setLayout(null);
		this.setBounds(0, 0, 700, 470);
				
		HTMLEditorKit kit = new HTMLEditorKit();
		StyleSheet styleSheet = kit.getStyleSheet();
		styleSheet.addRule("table {font-weight: bold;margin: 0 auto;text-align: center}");
		styleSheet.addRule("table td {background-color: #C0C0C0;}");
		styleSheet.addRule("table .rojo {background-color: #ff4646;}");
		styleSheet.addRule("table .verde {background-color: #62ff79;}");
		
		pnlTexto = new JEditorPane();
		pnlTexto.setEditable(false);
		pnlTexto.setEditorKit(kit);
		pnlTexto.setBounds(0, 0, 680, 450);
		Document doc = kit.createDefaultDocument();
		pnlTexto.setDocument(doc);
		pnlTexto.setText(texto);
		
		scrollPane = new JScrollPane(pnlTexto);
		scrollPane.setBounds(10, 10, 680, 438);
		add(scrollPane);
	}
}
