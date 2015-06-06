package lector;


public class FabricaLectorGitHub implements FabricaLector
{
	FachadaGitHub fachadaLector = FachadaGitHub.getInstance();
	
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
	
	
	public FachadaLector crearFachadaLector() 
	{
		return fachadaLector;
	}
	
}