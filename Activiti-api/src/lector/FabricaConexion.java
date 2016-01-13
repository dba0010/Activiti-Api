package lector;

import java.io.IOException;

public interface FabricaConexion
{	
	public FachadaConexion crearFachadaConexion(String usuario, String password) throws IOException;
}