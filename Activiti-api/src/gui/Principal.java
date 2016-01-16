package gui;

import lector.*; 

import java.awt.EventQueue;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal 
{
	/**
	 * Frame principal.
	 */
	protected JFrame frmFormulario;
	
	/**
	 * Panel donde se muestran los botones.
	 */
	protected JPanel pnlBotones;
	
	/**
	 * Panel con botones para seleccionar la plataforma con la que trabajar.
	 */
	protected PanelInicio pnlInicio;
	
	/**
	 * Panel que se encarga de realizar la conexion con la plataforma seleccionada.
	 */
	protected PanelConexion pnlConexion;
	
	/**
	 * Panel donde seleccionar el repositorio sobre el que obener los datos.
	 */
	protected PanelRepositorio pnlRepositorio;
	
	/**
	 * Panel donde se muestran los resultados de las metricas.
	 */
	protected PanelResultados pnlResultados;

	/**
	 * Boton para guardar los resultados en un archivo.
	 */
	private JButton btnGuardar;
	
	/**
	 * Boton para mostrar la ventana de ayuda.
	 */
	private JButton btnAyuda;
	
	/**
	 * Boton para volver al panel anterior.
	 */
	protected JButton btnAtras;
	
	/**
	 * Panel anterior al que nos encontramos.
	 */
	protected JPanel anterior;
	
	/**
	 * Elemento que guarda la informacion del archivo de ayuda.
	 */
	private HelpSet helpset;
	
	/**
	 * Elemento encargado de mostrar la ayuda.
	 */
	private HelpBroker helpbroker;
	
	/**
	 * Fabrica asbstracta.
	 */
	protected FabricaConexion fabricaConexion;
	
	/**
	 * Fachada de rest.
	 */
	protected FachadaConexion conexion;
	
	/**
	 * Metodo get para la Fabrica encargada de crear la conexion.
	 * @return FabricaConexion Fabrica encargada de crear la conexion.
	 */
	public FabricaConexion getFabricaConexion() 
	{
		return fabricaConexion;
	}

	/**
	 * Metodo get para la Fachada encargada de trabajar con la plataforma elegida.
	 * @return FachadaConexion Fachada encargada de realizar las operaciones con la plataforma elegida.
	 */
	public FachadaConexion getConexion() 
	{
		return conexion;
	}	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Principal window = new Principal();
					window.frmFormulario.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() 
	{
		initialize();
		cargarAyuda();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frmFormulario = new JFrame();
		frmFormulario.setResizable(false);
		frmFormulario.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/imagenes/Ubu.png")));
		frmFormulario.setTitle("Activiti-Api");
		frmFormulario.setBounds(100, 100, 414, 526);
		frmFormulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFormulario.getContentPane().setLayout(new BorderLayout(0, 0));
		
		pnlBotones = new JPanel();
		pnlBotones.setLayout(new GridLayout(0, 3, 0, 0));
		
		btnGuardar = new JButton("Guardar Resultados");
		btnGuardar.setEnabled(false);
		btnGuardar.setToolTipText("<html><p width=\"500\">Guardar los resultados en un archivo.<br><br>(En desarrollo)</p></html>");
		btnGuardar.setVisible(false);
		btnGuardar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		btnGuardar.setBounds(10, 466, 148, 23);
		pnlBotones.add(btnGuardar);
		
		btnAyuda = new JButton("Ayuda");
		btnAyuda.setToolTipText("<html><p width=\"500\">Mostrar ventana de ayuda (F1)</p></html>");
		btnAyuda.setBounds(10, 466, 148, 23);
		pnlBotones.add(btnAyuda);
		
		btnAtras = new JButton("Atras");
		btnAtras.setToolTipText("Retroceder al panel anterior.");
		btnAtras.setVisible(false);
		btnAtras.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				cargarPanel(anterior);
			}
		});
		pnlBotones.add(btnAtras);
		
		frmFormulario.getContentPane().add(pnlBotones, BorderLayout.SOUTH);
				
		pnlInicio = new PanelInicio(this);
		frmFormulario.getContentPane().add(pnlInicio);
		
		pnlConexion = new PanelConexion(this);
		pnlRepositorio = new PanelRepositorio(this);
	}	
	
	/**
	 * Boton que carga un nuevo panel en el formulario.
	 * @param panel JPanel a cargar en el formulario.
	 */
	protected void cargarPanel(JPanel panel)
	{
		switch (panel.getClass().getName())
		{
			case "gui.PanelInicio":
				btnAtras.setVisible(false);
				btnGuardar.setVisible(false);
				frmFormulario.setTitle("Activiti-Api");
				break;
			case "gui.PanelConexion": 
				btnAtras.setVisible(true);
				btnGuardar.setVisible(false);
				frmFormulario.setTitle("Realizar conexion");
				
				//añadimos la ayuda a los botones
				//Al pulsar sobre el boton del menu ayuda se muestra la ayuda
				helpbroker.enableHelpOnButton(btnAyuda, "Conexion", helpset);
				//Al presionar F1 sobre la ventana se muestra la ayuda
				helpbroker.enableHelpKey(frmFormulario.getContentPane(), "Conexion", helpset);
				
				anterior = pnlInicio;
				break;
			case "gui.PanelRepositorio": 
				btnAtras.setVisible(true);
				btnGuardar.setVisible(false);
				frmFormulario.setTitle("Seleccionar repositorio");
				
				//añadimos la ayuda a los botones
				//Al pulsar sobre el boton del menu ayuda se muestra la ayuda
				helpbroker.enableHelpOnButton(btnAyuda, "Repositorios", helpset);
				//Al presionar F1 sobre la ventana se muestra la ayuda
				helpbroker.enableHelpKey(frmFormulario.getContentPane(), "Repositorios", helpset);
				
				anterior = pnlConexion;
				break;
			case "gui.PanelResultados": 
				btnAtras.setVisible(true);
				btnGuardar.setVisible(true);
				frmFormulario.setTitle("Resultados");

				//añadimos la ayuda a los botones
				//Al pulsar sobre el boton del menu ayuda se muestra la ayuda
				helpbroker.enableHelpOnButton(btnAyuda, "Resultados", helpset);
				//Al presionar F1 sobre la ventana se muestra la ayuda
				helpbroker.enableHelpKey(frmFormulario.getContentPane(), "Resultados", helpset);
				
				anterior = pnlRepositorio;
				break;
		}
		
		frmFormulario.getContentPane().remove(1);
		frmFormulario.getContentPane().add(panel);
		frmFormulario.getContentPane().repaint();
	}
	

	private void cargarAyuda() 
	{
		File archivo = new File("src/gui/ayuda/ayuda.hs");
		URL hsURL;
		try 
		{
			hsURL = archivo.toURI().toURL();
			//Leemos el HelpSet de COnfiguracion
			helpset = new HelpSet(null,hsURL);
			helpbroker = helpset.createHelpBroker();
			
			//añadimos la ayuda a los botones
			//Al pulsar sobre el boton del menu ayuda se muestra la ayuda
			helpbroker.enableHelpOnButton(btnAyuda, "Principal", helpset);
			//Al presionar F1 sobre la ventana se muestra la ayuda
			helpbroker.enableHelpKey(frmFormulario.getContentPane(), "Principal", helpset);
		}
		catch (Exception ex) 
		{
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
