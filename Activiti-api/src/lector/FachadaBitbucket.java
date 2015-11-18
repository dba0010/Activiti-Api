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
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import datos.*;



public class FachadaBitbucket implements FachadaLector
{
	private static FachadaBitbucket instancia;
	
	private ArrayList<RepositorioBitbucket> repositorios = new ArrayList<RepositorioBitbucket>();
	
	private ArrayList<IssueBitbucket> issues = new ArrayList<IssueBitbucket>();
	
	private String[] nombresRepositorio;
	
	private double porcentajeIssuesCerradas;
	
	private Date ultimaModificacion;
	
	private long tiempoMedioCierre;
	
	private String texto;
	
	/*Constructor privado*/
	private FachadaBitbucket(){}
	
	/*Creacion de instancia y return de la misma*/
	
	public static FachadaBitbucket getInstance()
	{
		if (instancia == null)
		{
			instancia = new FachadaBitbucket();
		}
		return instancia;
	}

	public void obtenerRepositorios(String usuario) throws IOException 
	{
		URLConnection conexion;
		conexion = new URL("https://api.bitbucket.org/2.0/repositories/" + usuario ).openConnection();
    	conexion.connect();
    	
    	JsonReader lector = new JsonReader(new InputStreamReader(conexion.getInputStream()));
    	
    	JsonParser parseador = new JsonParser();
    	JsonElement raiz = parseador.parse(lector);
    	
    	Gson json = new Gson();
    	JsonArray lista = (JsonArray) raiz.getAsJsonObject().get("values");
    	
    	for(JsonElement elemento : lista)
    	{
    		repositorios.add(json.fromJson(elemento, RepositorioBitbucket.class));  		
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
		conexion = new URL("https://bitbucket.org/api/1.0/repositories/" + usuario + "/" + repositorio + "/issues").openConnection();
	
    	conexion.connect();
    	
    	JsonReader lector = new JsonReader(new InputStreamReader(conexion.getInputStream()));
    	
    	JsonParser parseador = new JsonParser();
    	JsonElement raiz = parseador.parse(lector);
    	
    	
    	Gson json = new Gson();
    	JsonArray lista = (JsonArray) raiz.getAsJsonObject().get("issues");
    	
    	int cerradas = 0;
    	
    	for(JsonElement elemento : lista)
    	{
    		IssueBitbucket issue = json.fromJson(elemento, IssueBitbucket.class);
    		if(issue.getStatus().equals("close"))
    		{
    			cerradas++;
    			tiempoMedioCierre += issue.getUtc_last_updated().getTime() - issue.getUtc_created_on().getTime();
    		}
    		issues.add(issue);
    	}
    	
    	this.porcentajeIssuesCerradas = cerradas * 100 / issues.size();
	}
	
	public void obtenerCommits(String usuario, String repositorio) throws MalformedURLException, IOException 
	{
		URLConnection conexion = new URL("https://bitbucket.org/api/2.0/repositories/" + usuario + "/" + repositorio + "/commits").openConnection();
    	
    	conexion.connect();
    	
    	JsonReader lector = new JsonReader(new InputStreamReader(conexion.getInputStream()));
    	
    	JsonParser parseador = new JsonParser();
    	JsonElement raiz = parseador.parse(lector);
    	
    	
    	Gson json = new Gson();
    	JsonArray lista = (JsonArray) raiz.getAsJsonObject().get("values");
    	
    	ArrayList<CommitGitHub> commits = new ArrayList<CommitGitHub>();

    	    	for(JsonElement elemento : lista)
    	{
    		CommitGitHub commmit = json.fromJson(elemento, CommitGitHub.class);
    		commits.add(commmit);
    	}
    	    	
    	texto = "";
    	for(CommitGitHub x : commits)
    	{
    		texto += x.toString();
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

	
}
