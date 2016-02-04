package lector;

import java.io.IOException;

/**
 * Fabrica abstracta encargada de generar FachadasConexionGitHub.
 * @author David Blanco Alonso
 */
public class FabricaConexionGitHub implements FabricaConexion
{
	/**
	 * Instancia de la fabrica.
	 */
	private static FabricaConexionGitHub instancia; 
	
	/**
	 * Creacion de instancia y return de la misma
	 */	
	public static FabricaConexionGitHub getInstance()
	{
		if (instancia == null)
		{
			instancia = new FabricaConexionGitHub();
		}
		return instancia;
	}
	
	/**
	 * Fachada generada.
	 */
	FachadaConexion fachadaConexion;
	
	/**
	 * Constructor privado
	 */
	private FabricaConexionGitHub(){}
		
	/**
	 * Metodo para crear una fachada no autenticada.
	 */
	public FachadaConexion crearFachadaConexion()
	{
		fachadaConexion = FachadaConexionGitHub.getInstance(); 
		return fachadaConexion;
	}
	
	/**
	 * Metodo para generar una fachada autenticada.
	 */
	public FachadaConexion crearFachadaConexion(String usuario, String password) throws IOException 
	{
		fachadaConexion = FachadaConexionGitHub.getInstance(usuario, password); 
		return fachadaConexion;
	}
}