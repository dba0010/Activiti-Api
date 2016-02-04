package lector;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.*;

/**
 * Fachada para trabajar con GitHub.
 * @author David Blanco Alonso
 */
public class FachadaConexionGitHub implements FachadaConexion
{
	/**
	 * instancia de la fachada.
	 */
	private static FachadaConexionGitHub instancia;
	
	/**
	 * Creacion de instancia y return de la misma
	 */
	public static FachadaConexionGitHub getInstance()
	{
		instancia = new FachadaConexionGitHub();
		
		return instancia;
	}

	/**
	 * Creacion de instancia y return de la misma
	 */
	public static FachadaConexionGitHub getInstance(String usuario, String password) throws IOException
	{
		instancia = new FachadaConexionGitHub(usuario, password);
		
		return instancia;
	}
	
	/**
	 * GitHubClient cliente autenticado o no autenticado.
	 */
	private GitHubClient cliente;
	
	/**
	 * Commits obtenidos de la plataforma.
	 */
	private List<RepositoryCommit> commits;
	
	/**
	 * Issues obtenidas de la plataforma.
	 */
	private List<Issue> issues;
	
	/**
	 * Fachada que contiene las metricas calculadas para el repositorio.
	 */
	private FachadaMetricas metricas;
	
	/**
	 * String nombre del reposiorio del que se ha realizado el calculo de métricas.
	 */
	private String nombreRepositorio;
	
	/**
	 * Nombres de los repositorios pertenecientes a un usuario.
	 */
	private String[] nombresRepositorio;
	
	/**
	 * Repositorio de trabajo.
	 */
	private Repository repositorio;
	
	/**
	 * Lista de los repositorios pertenecioentes a un usuario.
	 */
	private List<Repository> repositorios;
	
	/**
	 * CommitService servicio para trabajar conn los commits de GitHub.
	 */
	private CommitService servicioCommits;
		
	/**
	 * IssueService servicio para trabajar con las issues de GitHub.
	 */ 
	private IssueService servicioIssues;
	
	/**
	 * RepositoryService servicio para trabajar con los repositorios de GitHub.
	 */
	private RepositoryService servicioRepositorios;
	
	/**
	 * Constructor privado
	 */
	private FachadaConexionGitHub()
	{
		cliente = new GitHubClient();
        this.servicioRepositorios = new RepositoryService(this.cliente);
	}
	
	/**
	 * Constructor privado
	 */
	private FachadaConexionGitHub(String usuario, String password) throws IOException
	{
		cliente = new GitHubClient();
        cliente.setCredentials(usuario, password);
        this.servicioRepositorios = new RepositoryService(this.cliente);
		this.repositorios = this.servicioRepositorios.getRepositories(usuario);
	}
	
	/**
	 * Realiza la comparacion de esta conexion y sus resultados con los resultados de otra conexion.
	 * @param conexion2 FachadaConexion conexión con la que realizar la comparación.
	 * @return String resultado de la comparacion(contien filas de tabla HTML).
	 */
	public String comparar(FachadaConexion comparacion)
	{
		return metricas.comparar(comparacion);
	}

	/**
	 * Genera un informe con los resultados obtenidos en las metricas.
	 * @return Char[] texto del informe.
	 */
	public char[] generarArchivo()
	{
		String resultado = "GitHub\n" + this.nombreRepositorio + "\n" + this.metricas.generarArchivo();
		return resultado.toCharArray();
	}
	
	/**
	 * Devuelve la Fachada metricas que contiene los resultados de las metricas calculadas.
	 * @return FachadaMetricas metricas calculadas.
	 */
	public FachadaMetricas getMetricas()
	{
		return metricas;
	}
	
	/**
	 * Devuelve el nombre del repositorio del que se ha realizado el calculo de las métricas.
	 * @return String nombre del repositorio.
	 */
	public String getNombreRepositorio()
	{
		return this.nombreRepositorio;
	}
		
	/**
	 * Metodo que devuelve los repositorios que pertenecen al usuario indicado.
	 * @param usuario String usuario del que obtner los repositorios. 
	 * @return String[] array que contien todos los respositorios de ese usuario.
	 * @throws IOException
	 */
	public String[] getNombresRepositorio(String usuario) throws IOException 
	{
		this.obtenerRepositorios(usuario);
		
		return this.nombresRepositorio;
	}
	
	/**
	 * Devulve las peticiones restantes para el cliente existente.
	 * @return int numero de peticiones.
	 */
	public int getPeticionesRestantes()
	{
		return this.cliente.getRemainingRequests();
	}
	
	/**
	 * Metodo que devuelve los resultados de las metricas en formato texto y gráfico.
	 * @return Object[] resultados en formato texto y ChartPanel.
	 */
	public Object[] getResultados()
	{		
		return this.metricas.getResultados();
	}
	
	/**
	 * Realiza la lectura de un informe.
	 * @param archivo BufferedReader archivo del informe.
	 */
	public void leerArchivo(BufferedReader archivo)
	{
		String linea = "";
		try 
		{
			if((linea = archivo.readLine()) != null)
			{
				this.nombreRepositorio = linea;
				this.metricas = new FachadaMetricasGitHub(archivo);
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtiene la información de los commits para un repositorio dado.
	 * @param repositorio RepositoryID repositorio del que obtener los commits.
	 * @throws IOException
	 */
	private void obtenerCommits(RepositoryId repositorio) throws IOException 
	{
		this.servicioCommits = new CommitService(this.cliente);
		
		this.commits = this.servicioCommits.getCommits(repositorio);
	}
	
	/**
	 * Obtiene la información de las issues para un repositorio dado.
	 * @param repositorio RepositoryID repositorio del que obtener las issues.
	 * @throws IOException
	 */
	private void obtenerIssues(RepositoryId repositorio) throws IOException
	{
		this.servicioIssues = new IssueService(this.cliente);
		
		Map<String,String> filtro = new HashMap<String,String>();
		filtro.put("state", "all");
       	
		this.issues = this.servicioIssues.getIssues(repositorio, filtro);
	}
	
	/**
	 * Metodo por el que se realiza el calculo de las metricas, despues de solicitar la información del repositorio indicado.
	 * @param usuario String usuario propietario del repositorio.
	 * @param repositorio String repositorio seleccionado.
	 * @throws IOException
	 */
	public void obtenerMetricas(String usuario, String repositorio) throws IOException
	{
		this.metricas = null;
		RepositoryId idRepositorio = new RepositoryId(usuario,repositorio); 
		
		this.obtenerRepositorio(usuario, idRepositorio);
		this.obtenerIssues(idRepositorio);
		this.obtenerCommits(idRepositorio);	
		
		this.nombreRepositorio = idRepositorio.getOwner() + "_" + idRepositorio.getName();
				
		metricas = new FachadaMetricasGitHub(this.repositorio, this.issues, this.commits);
	}
	
	/**
	 * Obtiene los datos de un repositorio dando su usuario y su id.
	 * @param usuario String Usuario al que pertenece el repositorio.
	 * @param repositorio RepositoryID id del repositorio buscado.
	 * @throws IOException
	 */
	private void obtenerRepositorio(String usuario, RepositoryId repositorio) throws IOException
	{
		this.repositorio = this.servicioRepositorios.getRepository(usuario, repositorio.getName());
	}
	
	/**
	 * Obtiene los repositorios pertenecientes a un usuario.
	 * @param usuario String usuario del que buscar los repositorios.
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	private void obtenerRepositorios(String usuario) throws IOException, IllegalArgumentException
	{
		this.servicioRepositorios = new RepositoryService(this.cliente);
		
		this.repositorios = this.servicioRepositorios.getRepositories(usuario);
				
		this.nombresRepositorio = new String[this.repositorios.size()];
		int contador = 0;
		for(Repository x : this.repositorios)
		{
			this.nombresRepositorio[contador] = x.getName();
			contador++;
		}
	}
}
