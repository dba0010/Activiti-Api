package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class PanelRepositorio extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Label para el campo de texto del usuario.
	 */
	private JLabel lblUsuario;
	
	/**
	 * Campo de texto donde se indica el usuario del que buscar los repositorios.
	 */
	private JTextField txtUsuario;

	/**
	 * Label para el combobox que muestra los repositorios del usuario.
	 */
	private JLabel lblRepositorio;
	
	/**
	 * ComboBox que muestra los repositorios del usuario especificado.
	 */
	private JComboBox<String> cmbRepositorios;
	
	/**
	 * Boton para calcular las metricas y continuar al panel que muestra los resultados de las metricas.
	 */
	private JButton btnMostrarMetricas;
	
	/**
	 * Create the panel.
	 */
	public PanelRepositorio(final Principal aplicacion) 
	{
		setLayout(null);
		this.setBounds(0, 0, 409, 500);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(133, 165, 148, 14);
		add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("Usuario del que buscar los repositorios de los que es propietario.");
		
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(133, 190, 148, 20);
		add(txtUsuario);
		
		lblRepositorio = new JLabel("Repositorio:");
		lblRepositorio.setBounds(133, 221, 148, 14);
		add(lblRepositorio);
		
		cmbRepositorios = new JComboBox<String>();
		cmbRepositorios.setToolTipText("Repositorios de los que es propietario el usuario introducido.");
		cmbRepositorios.setBounds(133, 246, 148, 20);
		add(cmbRepositorios);
		
		txtUsuario.addFocusListener(new FocusAdapter() 
		{
			public void focusLost(FocusEvent arg0) 
			{
				try 
				{
					cmbRepositorios.setModel(new DefaultComboBoxModel<String>(aplicacion.conexion.getNombresRepositorio(txtUsuario.getText())));
					btnMostrarMetricas.setEnabled(true);
				}
				catch (IOException e) 
				{
					JOptionPane.showMessageDialog(aplicacion.frmFormulario,"Debes introducir un nombre de ususario valido.");
				}
				catch (IllegalArgumentException e) 
				{
				}
			}
		});
		
		btnMostrarMetricas = new JButton("Mostrar Metricas");
		btnMostrarMetricas.setToolTipText("Calcular los resultados de las metricas y mostrarlos.");
		btnMostrarMetricas.setEnabled(false);
		btnMostrarMetricas.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					aplicacion.conexion.obtenerMetricas(txtUsuario.getText(), cmbRepositorios.getSelectedItem().toString());
					aplicacion.pnlResultados = new PanelResultados(aplicacion);
					
					aplicacion.cargarPanel(aplicacion.pnlResultados);
				}
				catch (IOException e1) 
				{
					JOptionPane.showMessageDialog(aplicacion.frmFormulario,"No se han recibido datos.\nO estos son erroneos.\nSi no estas conectado igual has alcanzado el limite de peticiones.");
				}
			}
		});
		btnMostrarMetricas.setBounds(133, 277, 148, 23);
		add(btnMostrarMetricas);
	}
}
