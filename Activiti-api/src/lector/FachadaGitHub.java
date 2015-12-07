package lector;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import datos.*;

public class FachadaGitHub implements FachadaLector
{
	private static FachadaGitHub instancia;
	
	private static String usuario= "";
	
	private static String repositorio = "";
	
	private ArrayList<RepositorioGitHub> repositorios = new ArrayList<RepositorioGitHub>();
	
	private static ArrayList<IssueGitHub> issues = new ArrayList<IssueGitHub>();
	
	private static ArrayList<CommitGitHub> commits = new ArrayList<CommitGitHub>();
	
	private String[] nombresRepositorio;
	
	MetricasGitHub<?> metricas;
		
	/*Constructor privado*/
	private FachadaGitHub(String usuario) throws IOException
	{
		FachadaGitHub.usuario = usuario;
		this.obtenerRepositorios(usuario);
	}
	
	private FachadaGitHub(String usuario, String repositorio) throws IOException
	{
		this.obtenerRepositorios(usuario);
		this.obtenerIssues(usuario, repositorio);
		this.obtenerCommits(usuario, repositorio);
		this.obtenerMetricas();
	}
	
	/*Creacion de instancia y return de la misma*/
	
	public static FachadaGitHub getInstance(String usuario) throws IOException
	{
		if(FachadaGitHub.usuario != usuario)
		{
			instancia = new FachadaGitHub(usuario);
		}
		return instancia;
	}
	
	public static FachadaGitHub getInstance(String usuario, String repositorio) throws IOException
	{
		if (FachadaGitHub.usuario != usuario || FachadaGitHub.repositorio != repositorio)
		{
			instancia = new FachadaGitHub(usuario, repositorio);
		}
		return instancia;
	}

	private JsonElement consulta(String consulta) throws IOException 
	{
		URLConnection conexion;
		JsonElement raiz = null;
		
		conexion = new URL(consulta).openConnection();
		conexion.connect();
	
		JsonReader lector = new JsonReader(new InputStreamReader(conexion.getInputStream()));
	
		JsonParser parseador = new JsonParser();
		raiz = parseador.parse(lector);
		
		return raiz;
	}
	
	public void obtenerRepositorios(String usuario) throws IOException
	{
		JsonElement raiz = consulta("https://api.github.com/users/" + usuario + "/repos");
		JsonArray lista = raiz.getAsJsonArray();
    	Gson json = new Gson();
    	
    	for(JsonElement elemento : lista)
    	{
    		repositorios.add(json.fromJson(elemento, RepositorioGitHub.class));  		
    	}
    	
    	nombresRepositorio = new String[repositorios.size()];
    	int contador = 0;
		for(Object x : repositorios)
		{
			nombresRepositorio[contador] = ((Repositorio) x).getName();
			contador++;
		}
	}

	public void obtenerIssues(String usuario, String repositorio) throws MalformedURLException, IOException 
	{
		JsonElement raiz = consulta("https://api.github.com/repos/" + usuario + "/" + repositorio + "/issues");
    	JsonArray lista = raiz.getAsJsonArray();
    	Gson json = new Gson();
    	
    	issues = new ArrayList<IssueGitHub>();
    	
    	for(JsonElement elemento : lista)
    	{
    		IssueGitHub issue = json.fromJson(elemento, IssueGitHub.class);
    		issues.add(issue); 		
    	}
	}
	
	public void obtenerCommits(String usuario, String repositorio) throws MalformedURLException, IOException 
	{
    	JsonElement raiz = consulta("https://api.github.com/repos/" + usuario +"/" + repositorio + "/commits");
    	JsonArray lista = raiz.getAsJsonArray();
    	Gson json = new Gson();
    	
    	ArrayList<InfoCommit> infoCommits = new ArrayList<InfoCommit>();
    	
    	for(JsonElement elemento : lista)
    	{
    		InfoCommit infCommit = json.fromJson(elemento, InfoCommit.class);
    		infoCommits.add(infCommit);
    	}
    	
    	commits = new ArrayList<CommitGitHub>();
    	
    	for(InfoCommit x : infoCommits)
    	{
    		raiz = consulta(x.getUrl());
    		json = new Gson();
    		JsonObject objeto;
    		objeto = raiz.getAsJsonObject();
    		CommitGitHub commit = json.fromJson(objeto, CommitGitHub.class);
        	commits.add(commit);
    	}
	}
		
	public void obtenerMetricas()
	{
		metricas = new MetricasGitHub<Object>(issues, commits);
	}
	
	public MetricasGitHub<?> getMetricas()
	{
		return this.metricas;
	}
	
	public String[] getNombres() 
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
