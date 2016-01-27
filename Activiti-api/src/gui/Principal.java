package gui;

import lector.*; 

import java.awt.EventQueue;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JMenuBar;

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
	 * Panel donde seleccionar que dos ficheros de repositorio vamos a comparar.
	 */
	protected PanelComparacion pnlComparacion;

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
	 * Barra de menu para las distintas opciones que podemos realizar.
	 */
	private JMenuBar menuBar;
	
	/**
	 * Menu para buscar un repositorio en una plataforma.
	 */
	private JMenuItem mnBuscarRepositorio;
	
	/**
	 * Menu para seleccionar un fichero de resultados guardado anteriormente.
	 */
	private JMenuItem mnCargarArchivo;
	
	/**
	 * Menu para comparar dos repositorios desde dos ficheros.
	 */
	private JMenuItem mnCompararArchivos;
	
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
		frmFormulario.setBounds(100, 100, 700, 530);
		frmFormulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFormulario.getContentPane().setLayout(new BorderLayout(0, 0));
		
		pnlBotones = new JPanel();
		pnlBotones.setLayout(new GridLayout(0, 3, 0, 0));
		
		btnGuardar = new JButton("Guardar Resultados");
		btnGuardar.setToolTipText("<html><p width=\"500\">Guardar los resultados en un archivo.<br><br>(En desarrollo)</p></html>");
		btnGuardar.setVisible(false);
		btnGuardar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				guardarResultados();
			}
		});
		pnlBotones.add(btnGuardar);
		
		btnAyuda = new JButton("Ayuda");
		btnAyuda.setToolTipText("<html><p width=\"500\">Mostrar ventana de ayuda (F1)</p></html>");
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
		
		menuBar = new JMenuBar();
		frmFormulario.setJMenuBar(menuBar);
				
		mnBuscarRepositorio = new JMenuItem("Buscar Repositorio");
		mnBuscarRepositorio.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				cargarPanel(pnlInicio);
			}
		});
		menuBar.add(mnBuscarRepositorio);
		
		mnCargarArchivo = new JMenuItem("Cargar Informe");
		mnCargarArchivo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				cargarArchivo();
			}
		});
		menuBar.add(mnCargarArchivo);
				
		mnCompararArchivos = new JMenuItem("Comparar Informes");
		mnCompararArchivos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				cargarPanel(pnlComparacion);
			}
		});
		menuBar.add(mnCompararArchivos);
		
		pnlComparacion = new PanelComparacion(this);
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
				
				//añadimos la ayuda a los botones
				//Al pulsar sobre el boton del menu ayuda se muestra la ayuda
				helpbroker.enableHelpOnButton(btnAyuda, "Inicio", helpset);
				//Al presionar F1 sobre la ventana se muestra la ayuda
				helpbroker.enableHelpKey(frmFormulario.getContentPane(), "Inicio", helpset);
				
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
				frmFormulario.setTitle("Resultados - " + conexion.getNombreRepositorio());

				//añadimos la ayuda a los botones
				//Al pulsar sobre el boton del menu ayuda se muestra la ayuda
				helpbroker.enableHelpOnButton(btnAyuda, "Resultados", helpset);
				//Al presionar F1 sobre la ventana se muestra la ayuda
				helpbroker.enableHelpKey(frmFormulario.getContentPane(), "Resultados", helpset);
				
				anterior = pnlRepositorio;
				break;
			case "gui.PanelComparacion":
				btnAtras.setVisible(false);
				btnGuardar.setVisible(false);
				frmFormulario.setTitle("Comparar informes");
				
				//añadimos la ayuda a los botones
				//Al pulsar sobre el boton del menu ayuda se muestra la ayuda
				helpbroker.enableHelpOnButton(btnAyuda, "Comparar", helpset);
				//Al presionar F1 sobre la ventana se muestra la ayuda
				helpbroker.enableHelpKey(frmFormulario.getContentPane(), "Comparacion", helpset);
				
				anterior = pnlInicio;
				break;
			case "gui.PanelResultadosComparacion":
				btnAtras.setVisible(true);
				btnGuardar.setVisible(false);
				frmFormulario.setTitle("Resultados comparacion");
				
				//añadimos la ayuda a los botones
				//Al pulsar sobre el boton del menu ayuda se muestra la ayuda
				helpbroker.enableHelpOnButton(btnAyuda, "CompararResultados", helpset);
				//Al presionar F1 sobre la ventana se muestra la ayuda
				helpbroker.enableHelpKey(frmFormulario.getContentPane(), "CompararResultados", helpset);
				
				anterior = pnlComparacion;
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
	
	
	public void cargarArchivo()
	{
		try
		{
			JFileChooser file = new JFileChooser();
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
			file.setFileFilter(filtro);
			int abrir = file.showDialog(frmFormulario, "Cargar");
			File abre = null;
			if (abrir == JFileChooser.APPROVE_OPTION)
			{
				abre = file.getSelectedFile();
			}			
			if(abre!=null)
			{     
				FileReader lee =new FileReader(abre);
				BufferedReader archivo = new BufferedReader(lee);
				String linea = "";
				if( (linea = archivo.readLine()) != null)
				{
					if(linea.equals("GitHub"))
					{
						this.fabricaConexion = FabricaConexionGitHub.getInstance();
						this.conexion = fabricaConexion.crearFachadaConexion();
						conexion.leerArchivo(archivo);
					}
				}
				archivo.close();
				lee.close();
				
				pnlResultados = new PanelResultados(this);
				cargarPanel(pnlResultados);
				btnAtras.setVisible(false);
				btnGuardar.setVisible(false);
				frmFormulario.getContentPane().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}    
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(this.frmFormulario, ex+ "" + "\nNo se ha encontrado el informe", "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	public void guardarResultados()
	{
		try
		{
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
			fileChooser.setFileFilter(filtro);
			int abrir = fileChooser.showDialog(frmFormulario, "Guardar");
			File guarda = null;
			if (abrir == JFileChooser.APPROVE_OPTION)
			{
				guarda = fileChooser.getSelectedFile();
			}
			if(guarda !=null)
			{
				if(guarda.exists())
				{
					int respuesta = JOptionPane.showConfirmDialog(frmFormulario, "¿Deseas Sobreescribir el informe?", "Confirmar guardado", JOptionPane.YES_NO_OPTION);
					
					if(respuesta == JOptionPane.YES_OPTION)
					{
						FileWriter save = new FileWriter(guarda);
						save.write(conexion.generarArchivo());
						save.close();
						
						if(!(guarda.getAbsolutePath().endsWith(".txt")))
						{
		                    File temp = new File(guarda.getAbsolutePath()+".txt");
		                    guarda.renameTo(temp);
		                }
						JOptionPane.showMessageDialog(this.frmFormulario, "El informe se ha guardado Exitosamente", "Información",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(this.frmFormulario, "Error al guardar el informe", "Advertencia",JOptionPane.WARNING_MESSAGE);
		}
	}
}
