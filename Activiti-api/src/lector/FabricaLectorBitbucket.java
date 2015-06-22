package lector;


public class FabricaLectorBitbucket implements FabricaLector
{
	FachadaBitbucket fachadaLector = FachadaBitbucket.getInstance();
	
	private static FabricaLectorBitbucket instancia;
	
	/*Constructor privado*/
	private FabricaLectorBitbucket(){}
	
	/*Creacion de instancia y return de la misma*/
	
	public static FabricaLectorBitbucket getInstance()
	{
		if (instancia == null)
		{
			instancia = new FabricaLectorBitbucket();
		}
		return instancia;
	}
	
	
	public FachadaLector crearFachadaLector() 
	{
		return fachadaLector;
	}
	
}