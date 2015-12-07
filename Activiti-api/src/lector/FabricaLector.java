package lector;

import java.io.IOException;


public interface FabricaLector
{
	public FachadaLector crearFachadaLector(String usuario) throws IOException;
	
	public FachadaLector crearFachadaLector(String usuario, String repositorio) throws IOException;
}