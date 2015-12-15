package lector;

import java.io.IOException;


public interface FabricaLector
{	
	public FachadaLector crearFachadaLector(String usuario, String password) throws IOException;
}