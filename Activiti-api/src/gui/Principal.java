package gui;
import lector.*; 
import datos.*;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;


public class Principal {

	private JFrame frame;
	private JTextField txtUsuario;

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
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(28, 22, 46, 14);
		panel.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusLost(FocusEvent arg0) 
			{
				FabricaLector fabricaLector = FabricaLectorGitHub.getInstance();
				FachadaLector lector = fabricaLector.crearFachadaLector();
				
				ArrayList<RepositorioGitHub> repos = lector.obtenerRepositorios(txtUsuario.getText());
				System.out.println("focus perdido ");
				for(RepositorioGitHub x : repos)
		    	{
		    		System.out.println(x.toString());
		    	}
			}
		});
		txtUsuario.setBounds(104, 19, 86, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(105, 62, 85, 20);
		panel.add(comboBox);
	}
}
