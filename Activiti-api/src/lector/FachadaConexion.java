package lector;

import java.io.IOException;

public interface FachadaConexion
{	
	public void obtenerMetricas(String usuario,String repositorio) throws IOException;
	
	public Object[] getResultados();
		
	public String[] getNombresRepositorio(String usuario) throws IOException;
}