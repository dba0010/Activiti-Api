package lector;

import java.io.IOException;


public class FabricaConexionGitHub implements FabricaConexion
{
	FachadaConexionGitHub fachadaConexion; 
	
	private static FabricaConexionGitHub instancia;
	
	/*Constructor privado*/
	private FabricaConexionGitHub(){}
	
	/*Creacion de instancia y return de la misma*/
	
	public static FabricaConexionGitHub getInstance()
	{
		if (instancia == null)
		{
			instancia = new FabricaConexionGitHub();
		}
		return instancia;
	}
		
	public FachadaConexion crearFachadaConexion(String usuario, String password) throws IOException 
	{
		fachadaConexion = FachadaConexionGitHub.getInstance(usuario, password); 
		return fachadaConexion;
	}
	
}