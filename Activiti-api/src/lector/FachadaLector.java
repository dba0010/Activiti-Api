package lector;
import java.util.ArrayList;


public interface FachadaLector
{
	public <T> ArrayList<T> obtenerRepositorios(String usuario);
	
	public <T> ArrayList<T> obtenerIssues(String usuario, String repositorio);
}