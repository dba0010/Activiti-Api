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
	 * Lavel para el combobox de los sitios web.
	 */
	private JLabel lblSitiosWeb;
	
	/**
	 * Combobox para seleccionar el sitio web con el que se quiere trabajar.
	 */
	private JComboBox<String> cmbSitiosWeb;
	
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
	 * Area de texto donde se muestra la informacion.
	 */
	private JTextArea txtaInfo;
	
	/**
	 * Fabrica asbstracta.
	 */
	private FabricaLector fabricaLector;
	
	/**
	 * Fachada de rest.
	 */
	private FachadaLector lector;
		
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
		frmFormulario.setBounds(100, 100, 412, 526);
		frmFormulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFormulario.getContentPane().setLayout(new BorderLayout(0, 0));
		
		pnlPrincipal = new JPanel();
		frmFormulario.getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
		pnlPrincipal.setLayout(null);
		
		lblSitiosWeb = new JLabel("Sitio web");
		lblSitiosWeb.setBounds(26, 27, 92, 14);
		pnlPrincipal.add(lblSitiosWeb);
		
		cmbSitiosWeb = new JComboBox<String>();
		cmbSitiosWeb.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				btnBuscarRepositorios.setEnabled(true);
			}
		});
		cmbSitiosWeb.setModel(new DefaultComboBoxModel<String>(new String[] {"GitHub"}));
		cmbSitiosWeb.setBounds(131, 24, 86, 20);
		pnlPrincipal.add(cmbSitiosWeb);
		
		lblUsuarioCon = new JLabel("Usuario");
		lblUsuarioCon.setBounds(26, 55, 46, 14);
		pnlPrincipal.add(lblUsuarioCon);
		
		txtUsuarioCon = new JTextField();
		txtUsuarioCon.setBounds(131, 52, 86, 20);
		pnlPrincipal.add(txtUsuarioCon);
		
		lblPassword = new JLabel("password");
		lblPassword.setBounds(227, 55, 63, 14);
		pnlPrincipal.add(lblPassword);
		
		txtpPassword = new JPasswordField();
		txtpPassword.setBounds(300, 52, 79, 20);
		pnlPrincipal.add(txtpPassword);
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					if(cmbSitiosWeb.getSelectedItem().equals("GitHub"))
					{
						fabricaLector = FabricaLectorGitHub.getInstance();
						lector = fabricaLector.crearFachadaLector(txtUsuarioCon.getText(), String.valueOf(txtpPassword.getPassword()));
						JOptionPane.showMessageDialog(frmFormulario,"Conectado satisfactoriamente como " + txtUsuarioCon.getText() + ".");
					}
				} 
				catch (IOException e) 
				{
					JOptionPane.showMessageDialog(frmFormulario,"Fallo al conectar.");
				}
			}
		});
		btnConectar.setBounds(171, 83, 89, 23);
		pnlPrincipal.add(btnConectar);
		
		lblUsuarioSearch = new JLabel("Usuario a buscar");
		lblUsuarioSearch.setBounds(26, 140, 105, 14);
		pnlPrincipal.add(lblUsuarioSearch);
		
		txtUsuarioSearch = new JTextField();
		txtUsuarioSearch.setBounds(132, 137, 86, 20);
		pnlPrincipal.add(txtUsuarioSearch);
		txtUsuarioSearch.setColumns(10);		
		
		lblRepositorio = new JLabel("Repositorio");
		lblRepositorio.setBounds(26, 173, 67, 14);
		pnlPrincipal.add(lblRepositorio);
		
		cmbRepositorio = new JComboBox<String>();
		cmbRepositorio.setBounds(133, 170, 85, 20);
		pnlPrincipal.add(cmbRepositorio);
		
		btnBuscarRepositorios = new JButton("Buscar repositorios");
		btnBuscarRepositorios.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					lector.obtenerRepositorios(txtUsuarioSearch.getText());
					cmbRepositorio.setModel(new DefaultComboBoxModel<String>(lector.getNombresRepositorio()));
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
		btnBuscarRepositorios.setBounds(228, 136, 151, 23);
		pnlPrincipal.add(btnBuscarRepositorios);
		
		txtaInfo = new JTextArea();
		txtaInfo.setBounds(26, 203, 353, 284);
		pnlPrincipal.add(txtaInfo);
		
		btnObtenerDatos = new JButton("Obtener datos");
		btnObtenerDatos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				txtaInfo.setText("");
				try
				{
					lector.obtenerMetricas(txtUsuarioSearch.getText(), new RepositoryId(txtUsuarioSearch.getText(),cmbRepositorio.getSelectedItem().toString()));
					txtaInfo.setText(lector.getMetricas().toString());
				}
				catch(IOException e)
				{
					JOptionPane.showMessageDialog(frmFormulario,"No se han recibido datos.");
				}
			}
		});
		btnObtenerDatos.setBounds(227, 169, 152, 23);
		pnlPrincipal.add(btnObtenerDatos);
	}	
}
