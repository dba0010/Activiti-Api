package lector;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;

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
	
	private ArrayList<RepositorioGitHub> repositorios = new ArrayList<RepositorioGitHub>();
	
	private ArrayList<IssueGitHub> issues = new ArrayList<IssueGitHub>();
	
	private ArrayList<CommitGitHub> commits = new ArrayList<CommitGitHub>();
	
	private String[] nombresRepositorio;
	
	private double porcentajeIssuesCerradas = 0;
	
	private Date ultimaModificacion = null;
	
	private long tiempoMedioCierre = 0;
	
	private String texto;
	
	/*Constructor privado*/
	private FachadaGitHub(){}
	
	/*Creacion de instancia y return de la misma*/
	
	public static FachadaGitHub getInstance()
	{
		if (instancia == null)
		{
			instancia = new FachadaGitHub();
		}
		return instancia;
	}

	public void obtenerRepositorios(String usuario) throws IOException 
	{
		URLConnection conexion;
		conexion = new URL("https://api.github.com/users/" + usuario + "/repos").openConnection();
		conexion.connect();
		
		JsonReader lector = new JsonReader(new InputStreamReader(conexion.getInputStream()));
    	
    	JsonParser parseador = new JsonParser();
    	JsonElement raiz = parseador.parse(lector);
    	
    	Gson json = new Gson();
    	JsonArray lista = raiz.getAsJsonArray();
    	
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
		URLConnection conexion;
		conexion = new URL("https://api.github.com/repos/" + usuario + "/" + repositorio + "/issues").openConnection();
		conexion.connect();
		
		JsonReader lector = new JsonReader(new InputStreamReader(conexion.getInputStream()));
    	
    	JsonParser parseador = new JsonParser();
    	JsonElement raiz = parseador.parse(lector);
    	
    	Gson json = new Gson();
    	JsonArray lista = raiz.getAsJsonArray();
    	
    	int cerradas = 0;
    	
    	for(JsonElement elemento : lista)
    	{
    		IssueGitHub issue = json.fromJson(elemento, IssueGitHub.class);
    		if(issue.getState().equals("close"))
    		{
    			cerradas++;
    			tiempoMedioCierre += issue.getClosedAt().getTime() - issue.getCreatedAt().getTime();
    		}
    		texto += issue.toString() + "\n";
    		
    		if(ultimaModificacion == null || issue.getUpdatedAt().after(ultimaModificacion))
    		{
    			ultimaModificacion = issue.getUpdatedAt();
    		}
    		issues.add(issue); 		
    	}
    	
    	if(cerradas > 0)
    	{
    		tiempoMedioCierre /= cerradas;
    		this.porcentajeIssuesCerradas = cerradas * 100 / issues.size();
    	}
    	
	}
	
	public void obtenerCommits(String usuario, String repositorio) throws MalformedURLException, IOException 
	{
		URLConnection conexion = new URL("https://api.github.com/repos/" + usuario +"/" + repositorio + "/commits").openConnection();
    	
    	conexion.connect();
    	
    	JsonReader lector = new JsonReader(new InputStreamReader(conexion.getInputStream()));
    	
    	JsonParser parseador = new JsonParser();
    	JsonElement raiz = parseador.parse(lector);
    	
    	
    	Gson json = new Gson();
    	JsonArray lista = raiz.getAsJsonArray();
    	
    	ArrayList<InfoCommit> info_commits = new ArrayList<InfoCommit>();
    	
    	for(JsonElement elemento : lista)
    	{
    		InfoCommit inf_commmit = json.fromJson(elemento, InfoCommit.class);
    		info_commits.add(inf_commmit);
    	}
    	
    	texto = "";
    	for(InfoCommit x : info_commits)
    	{
    		conexion = new URL(x.getUrl()).openConnection();
    		conexion.connect();
    		lector = new JsonReader(new InputStreamReader(conexion.getInputStream()));
    		parseador = new JsonParser();
    		raiz = parseador.parse(lector);
    		json = new Gson();
    		JsonObject objeto;
    		objeto = raiz.getAsJsonObject();
    		CommitGitHub commit = json.fromJson(objeto, CommitGitHub.class);
    		texto += commit.toString();
        	commits.add(commit);
    	}
	}
	
	public String getTexto()
	{
		return texto;
	}

	public String[] getNombres()
	{
		return this.nombresRepositorio;
	}
	
	public double getPorcentajeIssuesCerradas()
	{
		return this.porcentajeIssuesCerradas;
	}
	
	public Date getUltimaModificacion()
	{
		return this.ultimaModificacion;
	}
	
	public long getTiempoMedioCierre()
	{
		return this.tiempoMedioCierre;
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
