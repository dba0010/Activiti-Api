package lector;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;


public interface FachadaLector
{
	public void obtenerRepositorios(String usuario) throws IOException;
	
	public void obtenerIssues(String usuario, String repositorio) throws MalformedURLException, IOException;
	
	public void obtenerCommits(String usuario, String repositorio) throws MalformedURLException, IOException;
	
	public String[] getNombres();
	
	public double getPorcentajeIssuesCerradas();
	
	public Date getUltimaModificacion();
	
	public long getTiempoMedioCierre();

	public String getTexto();
}