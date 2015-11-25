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
	private JLabel lblUsuario;
	
	/**
	 * Campo de texto para introducir el usuraio.
	 */
	private JTextField txtUsuario;
	
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
		frmFormulario.setBounds(100, 100, 385, 430);
		frmFormulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFormulario.getContentPane().setLayout(new BorderLayout(0, 0));
		
		pnlPrincipal = new JPanel();
		frmFormulario.getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
		pnlPrincipal.setLayout(null);
		
		lblSitiosWeb = new JLabel("Sitio web");
		lblSitiosWeb.setBounds(26, 27, 46, 14);
		pnlPrincipal.add(lblSitiosWeb);
		
		cmbSitiosWeb = new JComboBox<String>();
		cmbSitiosWeb.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				btnBuscarRepositorios.setEnabled(true);
			}
		});
		cmbSitiosWeb.setModel(new DefaultComboBoxModel<String>(new String[] {"GitHub", "Bitbucket"}));
		cmbSitiosWeb.setBounds(102, 24, 86, 20);
		pnlPrincipal.add(cmbSitiosWeb);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(26, 55, 46, 14);
		pnlPrincipal.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(102, 52, 86, 20);
		pnlPrincipal.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblRepositorio = new JLabel("Repositorio");
		lblRepositorio.setBounds(26, 83, 67, 14);
		pnlPrincipal.add(lblRepositorio);
		
		cmbRepositorio = new JComboBox<String>();
		cmbRepositorio.setBounds(102, 80, 85, 20);
		pnlPrincipal.add(cmbRepositorio);
		
		btnBuscarRepositorios = new JButton("Buscar repositorios");
		btnBuscarRepositorios.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(cmbSitiosWeb.getSelectedItem().equals("GitHub"))
				{
					fabricaLector = FabricaLectorGitHub.getInstance();
					lector = fabricaLector.crearFachadaLector();
				}
				else if(cmbSitiosWeb.getSelectedItem().equals("Bitbucket"))
				{
					fabricaLector = FabricaLectorBitbucket.getInstance();
					lector = fabricaLector.crearFachadaLector();
				}
				try
				{
					lector.obtenerRepositorios(txtUsuario.getText());
					cmbRepositorio.setModel(new DefaultComboBoxModel<String>(lector.getNombres()));
					btnObtenerDatos.setEnabled(true);
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
		btnBuscarRepositorios.setBounds(198, 51, 151, 23);
		pnlPrincipal.add(btnBuscarRepositorios);
		
		txtaInfo = new JTextArea();
		txtaInfo.setBounds(26, 122, 323, 259);
		pnlPrincipal.add(txtaInfo);
		
		btnObtenerDatos = new JButton("Obtener datos");
		btnObtenerDatos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					lector.obtenerIssues(txtUsuario.getText(), cmbRepositorio.getSelectedItem().toString());
					lector.obtenerCommits(txtUsuario.getText(), cmbRepositorio.getSelectedItem().toString());
					lector.obtenerMetricas();
					txtaInfo.setText(lector.getMetricas().toString());
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
		btnObtenerDatos.setBounds(197, 79, 152, 23);
		pnlPrincipal.add(btnObtenerDatos);
	}
}
