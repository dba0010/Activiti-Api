package motorMetricas.valores;

import java.util.LinkedHashMap;
import java.util.Map;

import motorMetricas.Valor;

public class Conjunto implements Valor
{	
	private Map<String,Entero> conjunto = new LinkedHashMap<String,Entero>();

    public Conjunto() 
    {
    	conjunto = new LinkedHashMap<String,Entero>();
    }

	public Conjunto(String key, Entero valor)
	{
		conjunto.put(key,valor);
	}

	public Map<String,Entero> getValor() 
	{
		return conjunto;
	}

	public Entero getValor(String key) 
	{
		return conjunto.get(key);
	}
	
    public void setValor(String key, Entero valor) 
    {
       this.conjunto.put(key,valor);
    }
    
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
