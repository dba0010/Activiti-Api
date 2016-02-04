package gui;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import lector.FabricaConexion;
import lector.FabricaConexionGitHub;
import lector.FachadaConexion;

/**
 * Clase panel con componentes para seleccioanr dos informes y realizar su comparacion.
 * @author David Blanco Alonso
 */
public class PanelComparacion extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Label asociado al campo de texto para la ruta del informe 1.
	 */
	private JLabel lblFichero1;
	
	/**
	 * Campo de texto donde introducor la ruta del informe 1.
	 */
	private JTextField txtFichero1;
	
	/**
	 * Boton para seleccionar un fichero como informe 1.
	 */
	private JButton btnFichero1;
	
	/**
	 * Label asociado al campo de texto para la ruta del informe 2.
	 */
	private JLabel lblFichero2;
	
	/**
	 * Campo de texto donde introducor la ruta del informe 2.
	 */
	private JTextField txtFichero2;
	
	/**
	 * Boton para seleccionar un fichero como informe 2.
	 */
	private JButton btnFichero2;
	
	/**
	 * Boton para mostrar la comparacion de los dos ficheros seleccionados.
	 */
	private JButton btnComparar;

	/**
	 * Fabrica asbstracta.
	 */
	protected FabricaConexion fabricaConexion1;
	
	/**
	 * Fachada de rest.
	 */
	protected FachadaConexion conexion1;
	
	/**
	 * Fabrica asbstracta.
	 */
	protected FabricaConexion fabricaConexion2;
	
	/**
	 * Fachada de rest.
	 */
	protected FachadaConexion conexion2;
	
	/**
	 * Panel de resultados de la comparacion.
	 */
	private PanelResultadosComparacion pnlResultados;
	
	/**
	 * Creamos el panel.
	 */
	public PanelComparacion(final Principal aplicacion) 
	{
		setLayout(null);
		this.setBounds(0, 0, 700, 470);
		
		lblFichero1 = new JLabel("1º fichero a comparar");
		lblFichero1.setBounds(22, 178, 148, 14);
		add(lblFichero1);
		
		txtFichero1 = new JTextField();
		txtFichero1.setBounds(180, 175, 340, 20);
		add(txtFichero1);
		txtFichero1.setColumns(10);
		
		btnFichero1 = new JButton("Seleccionar");
		btnFichero1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				seleccionarFichero(aplicacion, txtFichero1);
			}
		});
		btnFichero1.setBounds(530, 174, 148, 23);
		add(btnFichero1);
		
		lblFichero2 = new JLabel("2º fichero a comparar");
		lblFichero2.setBounds(22, 252, 148, 14);
		add(lblFichero2);
		
		txtFichero2 = new JTextField();
		txtFichero2.setColumns(10);
		txtFichero2.setBounds(180, 249, 340, 20);
		add(txtFichero2);
		
		btnFichero2 = new JButton("Seleccionar");
		btnFichero2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				seleccionarFichero(aplicacion, txtFichero2);
			}
		});
		btnFichero2.setBounds(530, 248, 148, 23);
		add(btnFichero2);
		
		btnComparar = new JButton("Comparar");
		btnComparar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				leerFicheros(aplicacion);
			}
		});
		btnComparar.setBounds(276, 308, 148, 23);
		add(btnComparar);
	}
	
	/**
	 * Metodo que permite seleccionar un fichero por medio de un JFileChooser.
	 * @param aplicacion Principal ventana principal en la que se genera el panel.
	 * @param txtDestino JTextField componente en el que mostrar la ruta del fichero seleccionado.
	 */
	private void seleccionarFichero(Principal aplicacion, JTextField txtDestino)
	{
		JFileChooser file = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
		file.setFileFilter(filtro);
		int abrir = file.showDialog(aplicacion.frmFormulario, "Seleccionar");
		if (abrir == JFileChooser.APPROVE_OPTION)
		{
			txtDestino.setText(file.getSelectedFile().getAbsolutePath());
		}
	}
	
	/**
	 * Metodo que realiza la lectura de los ficheros seleccionados creando las conexiones y las metricas y generando el panel resultados.
	 * @param aplicacion Principal en el que se genera la operación.
	 */
	private void leerFicheros(Principal aplicacion)
	{
		if(txtFichero1.getText().equals("") || txtFichero2.getText().equals(""))
		{
			JOptionPane.showMessageDialog(aplicacion.frmFormulario, "Debes introducir dos informes para compararles", "Error", JOptionPane.WARNING_MESSAGE);
		}
		if(txtFichero1.getText().equals(txtFichero2.getText()))
		{
			JOptionPane.showMessageDialog(aplicacion.frmFormulario, "Estas intentando comparar el mismo informe", "Error", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			try 
			{
				FileReader lee1 = new FileReader(txtFichero1.getText());
				FileReader lee2 = new FileReader(txtFichero2.getText());
				BufferedReader archivo1 = new BufferedReader(lee1);
				BufferedReader archivo2 = new BufferedReader(lee2);
				String linea1 = "";
				String linea2 = "";
				if(((linea1 = archivo1.readLine()) != null) && ((linea2 = archivo2.readLine()) != null))
				{
					if(linea1.equals("GitHub") && linea2.equals("GitHub"))
					{
						fabricaConexion1 = FabricaConexionGitHub.getInstance();
						fabricaConexion2 = FabricaConexionGitHub.getInstance();
						conexion1 = fabricaConexion1.crearFachadaConexion();
						conexion2 = fabricaConexion2.crearFachadaConexion();
						conexion1.leerArchivo(archivo1);
						conexion2.leerArchivo(archivo2);
						
						pnlResultados = new PanelResultadosComparacion(compararInformes());
						aplicacion.cargarPanel(pnlResultados);
					}
					else
					{
						JOptionPane.showMessageDialog(aplicacion.frmFormulario, "Estas intentando comparar informes de distinta plataforma", "Error", JOptionPane.WARNING_MESSAGE);
					}
				}
				archivo1.close();
				lee1.close();
				archivo2.close();
				lee2.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo que genera el texto en formato HTML con los resultados de la comparacion.
	 * @return String con los resultados de la comparacion en formato HTML.
	 */
	private String compararInformes()
	{	
		String texto = "<html>\n<head></head>\n<body>\n";
		texto += "<table>";
		texto += "<tr>";
		texto += "<th></th>";
		texto += "<th>" + conexion1.getNombreRepositorio() + "</th>";
		texto += "<th>" + conexion2.getNombreRepositorio() + "</th>";
		texto += "</tr>";
		
		texto += conexion1.comparar(conexion2);
		
		texto += "</table>";
		texto += "</body>\n</html>";
		return texto;
	}
}
