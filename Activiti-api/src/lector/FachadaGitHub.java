package lector;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import datos.*;



public class FachadaGitHub implements FachadaLector
{
	private static FachadaGitHub instancia;
	
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

	@SuppressWarnings("unchecked")
	public ArrayList<RepositorioGitHub> obtenerRepositorios(String usuario) 
	{
		URLConnection conexion;
		try 
		{
			conexion = new URL("https://api.github.com/users/" + usuario + "/repos").openConnection();
			conexion.connect();
			
			JsonReader lector = new JsonReader(new InputStreamReader(conexion.getInputStream()));
	    	
	    	JsonParser parseador = new JsonParser();
	    	JsonElement raiz = parseador.parse(lector);
	    	
	    	Gson json = new Gson();
	    	JsonArray lista = raiz.getAsJsonArray();
	    	
	    	ArrayList<RepositorioGitHub> repos = new ArrayList<RepositorioGitHub>();
	    	
	    	for(JsonElement elemento : lista)
	    	{
	    		repos.add(json.fromJson(elemento, RepositorioGitHub.class));  		
	    	}
	    	
	    	return repos;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<IssueGitHub> obtenerIssues(String usuario, String repositorio) 
	{
		URLConnection conexion;
		try 
		{
			conexion = new URL("https://api.github.com/repos/" + usuario + "/" + repositorio + "/issues").openConnection();
			conexion.connect();
			
			JsonReader lector = new JsonReader(new InputStreamReader(conexion.getInputStream()));
	    	
	    	JsonParser parseador = new JsonParser();
	    	JsonElement raiz = parseador.parse(lector);
	    	
	    	Gson json = new Gson();
	    	JsonArray lista = raiz.getAsJsonArray();
	    	
	    	ArrayList<IssueGitHub> issues = new ArrayList<IssueGitHub>();
	    	
	    	for(JsonElement elemento : lista)
	    	{
	    		issues.add(json.fromJson(elemento, IssueGitHub.class));  		
	    	}
	    	
	    	return issues;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	
}
