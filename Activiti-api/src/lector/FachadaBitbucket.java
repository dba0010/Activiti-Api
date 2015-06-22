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



public class FachadaBitbucket implements FachadaLector
{
	private static FachadaBitbucket instancia;
	
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

	@SuppressWarnings("unchecked")
	public ArrayList<RepositorioBitbucket> obtenerRepositorios(String usuario) 
	{
		URLConnection conexion;
		try 
		{
			conexion = new URL("https://api.bitbucket.org/2.0/repositories/dba0010").openConnection();
	    	conexion.connect();
	    	
	    	JsonReader lector = new JsonReader(new InputStreamReader(conexion.getInputStream()));
	    	
	    	JsonParser parseador = new JsonParser();
	    	JsonElement raiz = parseador.parse(lector);
	    	
	    	Gson json = new Gson();
	    	JsonArray lista = (JsonArray) raiz.getAsJsonObject().get("values");
	    	
	    	ArrayList<RepositorioBitbucket> repos = new ArrayList<RepositorioBitbucket>();
	    	
	    	for(JsonElement elemento : lista)
	    	{
	    		repos.add(json.fromJson(elemento, RepositorioBitbucket.class));  		
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
	public ArrayList<IssueBitbucket> obtenerIssues(String usuario, String repositorio) 
	{
		URLConnection conexion;
		try
		{
			conexion = new URL("https://bitbucket.org/api/1.0/repositories/dba0010/activiti-api/issues").openConnection();
    	
	    	conexion.connect();
	    	
	    	JsonReader lector = new JsonReader(new InputStreamReader(conexion.getInputStream()));
	    	
	    	JsonParser parseador = new JsonParser();
	    	JsonElement raiz = parseador.parse(lector);
	    	
	    	
	    	Gson json = new Gson();
	    	JsonArray lista = (JsonArray) raiz.getAsJsonObject().get("issues");
	    	
	    	ArrayList<IssueBitbucket> issues = new ArrayList<IssueBitbucket>();
	    	
	    	for(JsonElement elemento : lista)
	    	{
	    		IssueBitbucket issue = json.fromJson(elemento, IssueBitbucket.class);
	    		issues.add(issue);
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
