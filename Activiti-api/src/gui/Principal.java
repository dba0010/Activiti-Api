package gui;
import lector.*; 
import datos.*;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;


public class Principal {

	/**
	 * Frame principal.
	 */
	private JFrame frame;
	
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
	 * CAmpo de texto para introducir el usuraio.
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
	private JButton btnBuscarIssues;
	
	/**
	 * Area de texto donde se muestran las issues leidas.
	 */
	private JTextArea txtaIssues;
	
	/**
	 * Fabrica asbstracta.
	 */
	private FabricaLector fabricaLector;
	
	/**
	 * Fachada de rest.
	 */
	private FachadaLector lector;
	
	/**
	 * ArayList con los repositorios.
	 */
	ArrayList<?> repositorios;
	
	/**
	 * ArayList con las issues.
	 */
	ArrayList<?> issues;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		pnlPrincipal = new JPanel();
		frame.getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
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
					repositorios = new ArrayList<RepositorioGitHub>();
					issues = new ArrayList<IssueGitHub>();
				}
				else if(cmbSitiosWeb.getSelectedItem().equals("Bitbucket"))
				{
					fabricaLector = FabricaLectorBitbucket.getInstance();
					lector = fabricaLector.crearFachadaLector();
					repositorios = new ArrayList<RepositorioBitbucket>();
					issues = new ArrayList<IssueBitbucket>();
				}
				repositorios = lector.obtenerRepositorios(txtUsuario.getText());
				String[] nombres = new String[repositorios.size()];
				int contador = 0;
				for(Object x : repositorios)
		    	{
					nombres[contador] = ((Repositorio) x).getName();
					contador++;
		    	}
				cmbRepositorio.setModel(new DefaultComboBoxModel<String>(nombres));
				btnBuscarIssues.setEnabled(true);
			}
		});
		btnBuscarRepositorios.setBounds(198, 51, 128, 23);
		pnlPrincipal.add(btnBuscarRepositorios);
		
		btnBuscarIssues = new JButton("Obtener Issues");
		btnBuscarIssues.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				issues = lector.obtenerIssues(txtUsuario.getText(), cmbRepositorio.getSelectedItem().toString());
				String texto = "";
				for(Object x : issues)
		    	{
					texto += x.toString();
		    	}
				txtaIssues.setText(texto);
			}
		});
		btnBuscarIssues.setBounds(197, 79, 128, 23);
		pnlPrincipal.add(btnBuscarIssues);
		
		txtaIssues = new JTextArea();
		txtaIssues.setBounds(26, 108, 300, 143);
		pnlPrincipal.add(txtaIssues);
	}
}
