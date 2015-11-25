package lector;
import java.io.IOException;
import java.net.MalformedURLException;

public interface FachadaLector
{
	public void obtenerRepositorios(String usuario) throws IOException;
	
	public void obtenerIssues(String usuario, String repositorio) throws MalformedURLException, IOException;
	
	public void obtenerCommits(String usuario, String repositorio) throws MalformedURLException, IOException;
	
	public void obtenerMetricas();
	
	public MetricasGitHub<?> getMetricas();
	
	public String[] getNombres();
}