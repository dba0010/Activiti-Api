package lector;

import java.io.IOException;
import java.net.MalformedURLException;
import org.eclipse.egit.github.core.RepositoryId;

public interface FachadaLector
{
	public void obtenerRepositorios(String usuario) throws IOException;
	
	public void obtenerIssues(String usuario, RepositoryId repositorio) throws MalformedURLException, IOException;
	
	public void obtenerCommits(RepositoryId repositorio) throws MalformedURLException, IOException;
	
	public void obtenerMetricas(String usuario, RepositoryId repositorio) throws MalformedURLException, IOException;
	
	public FachadaMetricas getMetricas();
	
	public String[] getNombresRepositorio();
}