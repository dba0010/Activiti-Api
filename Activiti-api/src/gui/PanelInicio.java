package gui;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import lector.FabricaConexionGitHub;

public class PanelInicio extends JPanel 
{
	private static final long serialVersionUID = 1L;

	/**
	 * Boton para seleccionar GitHub como la plataforma con la que trabajar.
	 */
	private JButton btnGithub;
		
	/**
	 * Create the panel.
	 */
	public PanelInicio(final Principal aplicacion) 
	{
		this.setLayout(null);
		this.setBounds(0, 0, 409, 500);
		
		btnGithub = new JButton("GitHub");
		btnGithub.setToolTipText("Comenzar a trabajar con la plataforma GitHub");
		btnGithub.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				aplicacion.fabricaConexion = FabricaConexionGitHub.getInstance();
				
				aplicacion.cargarPanel(aplicacion.pnlConexion);
			}
		});
		btnGithub.setBounds(112, 128, 190, 84);
		this.add(btnGithub);
	}
}
