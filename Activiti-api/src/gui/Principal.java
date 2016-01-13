package gui;

import lector.*; 

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JPasswordField;

import org.eclipse.egit.github.core.RepositoryId;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;


public class Principal {

	/**
	 * Frame principal.
	 */
	private JFrame frmFormulario;
	
	/**
	 * Panel pricipal.
	 */
	private JPanel pnlPrincipal;
	
	/**
	 * Panel con los campos para realizar la conexion a GitHUB.
	 */
	private JPanel panel_conexion;

	/**
	 * Panel con los campos de informacion del repositorio sobre el que se va a buscar informacion.
	 */
	private JPanel panel_repositorio;
	
	/**
	 * Label del campo de texto de usuario.
	 */
	private JLabel lblUsuarioCon;
	
	/**
	 * Campo de texto para introducir el usuraio.
	 */
	private JTextField txtUsuarioCon;
	
	/**
	 * Label del campo password.
	 */
	private JLabel lblPassword;
	
	/**
	 * Campo de texto para la contreseña del usuario.
	 */
	private JPasswordField txtpPassword;
	
	/**
	 * Boton para crear el cliente con el que va a trabajar la aplicacion.
	 */
	private JButton btnConectar;
	
	/**
	 * Label del usuario sobre el que se va a buscar informacion.
	 */
	private JLabel lblUsuarioSearch;
	
	/**
	 * Campo de texto donde se introduce el usuario sobre el que se va a buscar la informacion.
	 */
	private JTextField txtUsuarioSearch;
	
	/**
	 * Boton para buscar los repositorios de ese usuario en ese sitio web.
	 */
	private JButton btnBuscarRepositorios;

	/**
	 * Label del combobox para seleccionar con que repositorio de ese usuario deseas trabajar.
	 */
	private JLabel lblRepositorio;
	
	/**
	 * Combobox para seleccionar con que repositorio del usuario se pretende trabajar.
	 */
	private JComboBox<String> cmbRepositorio;
	
	/**
	 * Boton para buscar las issues.
	 */
	private JButton btnObtenerDatos;
	
	/**
	 * Panel para activar barras de scroll al textarea.
	 */
	private JScrollPane scroll;
	
	/**
	 * Area de texto donde se muestra la informacion.
	 */
	private JTextArea txtaInfo;
	
	/**
	 * Fabrica asbstracta.
	 */
	private FabricaConexion fabricaConexion;
	
	/**
	 * Fachada de rest.
	 */
	private FachadaConexion conexion;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmFormulario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frmFormulario = new JFrame();
		frmFormulario.setResizable(false);
		frmFormulario.setBounds(100, 100, 414, 526);
		frmFormulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFormulario.getContentPane().setLayout(new BorderLayout(0, 0));
		
		pnlPrincipal = new JPanel();
		frmFormulario.getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
		pnlPrincipal.setLayout(null);
		
		txtaInfo = new JTextArea();
		txtaInfo.setLineWrap(true);
		txtaInfo.setEditable(false);
		
		scroll = new JScrollPane(txtaInfo);
		scroll.setViewportBorder(new TitledBorder(null, "Estadisticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scroll.setBounds(10, 197, 386, 290);
		pnlPrincipal.add(scroll);
		
		panel_conexion = new JPanel();
		panel_conexion.setBorder(new TitledBorder(null, "Conexion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_conexion.setBounds(10, 11, 386, 82);
		pnlPrincipal.add(panel_conexion);
		panel_conexion.setLayout(null);
		
		lblUsuarioCon = new JLabel("Usuario");
		lblUsuarioCon.setBounds(10, 24, 46, 14);
		panel_conexion.add(lblUsuarioCon);
		
		txtUsuarioCon = new JTextField();
		txtUsuarioCon.setBounds(66, 21, 86, 20);
		panel_conexion.add(txtUsuarioCon);
		
		lblPassword = new JLabel("password");
		lblPassword.setBounds(224, 24, 63, 14);
		panel_conexion.add(lblPassword);
		
		txtpPassword = new JPasswordField();
		txtpPassword.setBounds(297, 21, 79, 20);
		panel_conexion.add(txtpPassword);
		
		btnConectar = new JButton("Conectar");
		btnConectar.setBounds(148, 49, 89, 23);
		panel_conexion.add(btnConectar);
		
		panel_repositorio = new JPanel();
		panel_repositorio.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion del repositorio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_repositorio.setBounds(10, 104, 386, 82);
		pnlPrincipal.add(panel_repositorio);
		panel_repositorio.setLayout(null);
		
		lblUsuarioSearch = new JLabel("Usuario propietario:");
		lblUsuarioSearch.setBounds(10, 22, 115, 14);
		panel_repositorio.add(lblUsuarioSearch);
		
		txtUsuarioSearch = new JTextField();
		txtUsuarioSearch.setBounds(129, 19, 96, 20);
		panel_repositorio.add(txtUsuarioSearch);
		txtUsuarioSearch.setColumns(10);		
		
		btnBuscarRepositorios = new JButton("Buscar repositorios");
		btnBuscarRepositorios.setBounds(235, 18, 141, 23);
		panel_repositorio.add(btnBuscarRepositorios);
		
		lblRepositorio = new JLabel("Repositorio");
		lblRepositorio.setBounds(10, 52, 67, 14);
		panel_repositorio.add(lblRepositorio);
		
		cmbRepositorio = new JComboBox<String>();
		cmbRepositorio.setBounds(129, 49, 96, 20);
		panel_repositorio.add(cmbRepositorio);
		
		btnObtenerDatos = new JButton("Obtener datos");
		btnObtenerDatos.setBounds(235, 48, 141, 23);
		panel_repositorio.add(btnObtenerDatos);
		btnObtenerDatos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				txtaInfo.setText("");
				try
				{
					txtaInfo.setText(conexion.getMetricas(txtUsuarioSearch.getText(), new RepositoryId(txtUsuarioSearch.getText(),cmbRepositorio.getSelectedItem().toString())).toString());
				}
				catch(IOException e)
				{
					JOptionPane.showMessageDialog(frmFormulario,"No se han recibido datos.");
				}
			}
		});
		btnBuscarRepositorios.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					cmbRepositorio.setModel(new DefaultComboBoxModel<String>(conexion.getNombresRepositorio(txtUsuarioSearch.getText())));
					btnObtenerDatos.setEnabled(true);
					txtaInfo.setText("");
				}
				catch(IOException e)
				{
					JOptionPane.showMessageDialog(frmFormulario,"Debes introducir un nombre de ususario valido.");
				}
				catch(NullPointerException e)
				{
					JOptionPane.showMessageDialog(frmFormulario,"Debes introducir un nombre de ususario valido.");
				}
			}
		});
		btnConectar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					fabricaConexion = FabricaConexionGitHub.getInstance();
					conexion = fabricaConexion.crearFachadaConexion(txtUsuarioCon.getText(), String.valueOf(txtpPassword.getPassword()));
					JOptionPane.showMessageDialog(frmFormulario,"Conectado satisfactoriamente como " + txtUsuarioCon.getText() + ".");
				} 
				catch (IOException e) 
				{
					JOptionPane.showMessageDialog(frmFormulario,"Fallo al conectar.");
				}
			}
		});
		
		
	}	
}
