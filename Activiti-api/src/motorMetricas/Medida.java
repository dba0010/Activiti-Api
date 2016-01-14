package motorMetricas;

import motorMetricas.Valor;

public class Medida 
{
	private Metrica metrica;
	
	private Valor value;
	
	public Medida(Metrica metrica, Valor value)
	{
		this.metrica = metrica;
		this.value = value;
	}

	public Metrica getMetrica()
	{
		return this.metrica;
	}
	
	public Valor getValue() 
	{
		return value;
	}
}