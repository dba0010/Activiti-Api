package lector;

import java.io.IOException;
import java.net.MalformedURLException;
import org.eclipse.egit.github.core.RepositoryId;

public interface FachadaConexion
{	
	public FachadaMetricas getMetricas(String usuario, RepositoryId repositorio) throws MalformedURLException, IOException;
		
	public String[] getNombresRepositorio(String usuario) throws IOException;
}