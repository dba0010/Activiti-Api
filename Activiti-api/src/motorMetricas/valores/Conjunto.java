package motorMetricas.valores;

import java.util.LinkedHashMap;
import java.util.Map;

import motorMetricas.Valor;

public class Conjunto implements Valor
{	
	private Map<String,Double> conjunto = new LinkedHashMap<String,Double>();

    public Conjunto() 
    {
    	conjunto = new LinkedHashMap<String,Double>();
    }

	public Conjunto(String key, Double valor)
	{
		conjunto.put(key,valor);
	}

	public Map<String,Double> getValor() 
	{
		return conjunto;
	}

	public Double getValor(String key) 
	{
		return conjunto.get(key);
	}
	
    public void setValor(String key, Double valor) 
    {
       this.conjunto.put(key,valor);
    }
}
