package motorMetricas.valores;

import java.util.LinkedHashMap;
import java.util.Map;

import motorMetricas.Valor;

/**
 * Clase para controlar los tipos Map.
 * @author David Blanco Alonso
 */
public class Conjunto implements Valor
{	
	/**
	 * Conjunto.
	 */
	private Map<String,Entero> conjunto = new LinkedHashMap<String,Entero>();

	/**
	 * Constructor.
	 */
    public Conjunto() 
    {
    	conjunto = new LinkedHashMap<String,Entero>();
    }

    /**
	 * Constructor.
	 */
	public Conjunto(String key, Entero valor)
	{
		conjunto.put(key,valor);
	}

	/**
	 * Devuelve el valor.
	 * @return Map<String,Entero> valor.
	 */
	public Map<String,Entero> getValor() 
	{
		return conjunto;
	}

	/**
	 * Obtiene un valor para un identificador.
	 * @param key String identificador.
	 * @return Entero valor.
	 */
	public Entero getValor(String key) 
	{
		return conjunto.get(key);
	}
	
	/**
	 * Modifica el valor.
	 * @param key String identifcador.
	 * @param valor Entero valor.
	 */
    public void setValor(String key, Entero valor) 
    {
       this.conjunto.put(key,valor);
    }
    
    /**
	 * Metodos toString.
	 * @return String valor.
	 */
    public String toString()
    {
    	String resultado = conjunto.size() + "\n";
    	for(String key : conjunto.keySet())
    	{
    		resultado += " " + key + ": " + conjunto.get(key).toString() + "\n";
    	}
    	resultado = resultado.substring(0, resultado.length() - 1);
    	return resultado;
    }
}
