package lector;

import java.io.IOException;


public class FabricaLectorGitHub implements FabricaLector
{
	FachadaGitHub fachadaLector; 
	
	private static FabricaLectorGitHub instancia;
	
	/*Constructor privado*/
	private FabricaLectorGitHub(){}
	
	/*Creacion de instancia y return de la misma*/
	
	public static FabricaLectorGitHub getInstance()
	{
		if (instancia == null)
		{
			instancia = new FabricaLectorGitHub();
		}
		return instancia;
	}
		
	public FachadaLector crearFachadaLector(String usuario, String password) throws IOException 
	{
		fachadaLector = FachadaGitHub.getInstance(usuario, password); 
		return fachadaLector;
	}
	
}