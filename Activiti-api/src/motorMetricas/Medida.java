package motorMetricas;

import motorMetricas.Valor;

/**
 * Objeto que relaciona una métrica con su valor.
 * @author David Blanco Alonso
 */
public class Medida 
{
	/**
	 * Métrica.
	 */
	private Metrica metrica;
	
	/**
	 * Valor.
	 */
	private Valor value;
	
	/**
	 * Constructor.
	 * @param metrica Metrica asociada.
	 * @param value Valor asociado.
	 */
	public Medida(Metrica metrica, Valor value)
	{
		this.metrica = metrica;
		this.value = value;
	}

	/**
	 * Devuelve la métrica asociada.
	 * @return Metrica asociada.
	 */
	public Metrica getMetrica()
	{
		return this.metrica;
	}
	
	/**
	 * Devuelve el valor asociado.
	 * @return Valor asociado.
	 */
	public Valor getValue() 
	{
		return value;
	}
}