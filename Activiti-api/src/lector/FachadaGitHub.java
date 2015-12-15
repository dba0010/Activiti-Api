package lector;


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

public class FachadaGitHub implements FachadaLector
{
	private static FachadaGitHub instancia;
	
	private GitHubClient cliente;
	
	private RepositoryService servicioRepositorios;
	
	private IssueService servicioIssues;
	
	private CommitService servicioCommits;
	
	private List<Repository> repositorios;
	
	private List<Issue> issues;
	
	private List<RepositoryCommit> commits;
	
	private String[] nombresRepositorio;
	
	MetricasGitHub metricas;
		
	/*Constructor privado*/
	private FachadaGitHub(String usuario, String password) throws IOException
	{
		cliente = new GitHubClient();
        cliente.setCredentials(usuario, password);
        this.servicioRepositorios = new RepositoryService(this.cliente);
		this.repositorios = this.servicioRepositorios.getRepositories(usuario);
	}
	
	/*Creacion de instancia y return de la misma*/
	public static FachadaGitHub getInstance(String usuario, String password) throws IOException
	{
		instancia = new FachadaGitHub(usuario, password);
		
		return instancia;
	}
	
	public void obtenerRepositorios(String usuario) throws IOException
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

	public void obtenerIssues(String usuario, RepositoryId repositorio) throws IOException
	{
		this.servicioIssues = new IssueService(this.cliente);
		
		Map<String,String> filtro = new HashMap<String,String>();
		filtro.put("state", "all");
       	
		this.issues = this.servicioIssues.getIssues(repositorio, filtro);
	}
	
	public void obtenerCommits(RepositoryId repositorio) throws IOException 
	{
		this.servicioCommits = new CommitService(this.cliente);
		
		this.commits = this.servicioCommits.getCommits(repositorio);
		
		RepositoryCommit commitAux = null;
		for(int i = 0; i < this.commits.size(); i++)
		{
			commitAux = this.commits.get(i);
			this.commits.remove(i);
			this.commits.add(i, this.servicioCommits.getCommit(repositorio, commitAux.getSha()));
		}
	}
		
	public void obtenerMetricas(String usuario, RepositoryId repositorio) throws IOException
	{
		this.metricas = null;
		
		this.obtenerIssues(usuario, repositorio);
		this.obtenerCommits(repositorio);		
				
		metricas = new MetricasGitHub(issues, commits);
	}
	
	public MetricasGitHub getMetricas()
	{
		return this.metricas;
	}
	
	public String[] getNombresRepositorio() 
	{
		return this.nombresRepositorio;
	}
	
	class InfoCommit
	{
		String sha;
		String url;
		
		public String getSha() 
		{
			return sha;
		}
		public String getUrl() 
		{
			return url;
		}
		
		public String toString()
		{
			return "Info-commit:" + 
					"\n\tid: " + this.sha + 
					"\n\turl: " + this.url;
		}
	}

}
