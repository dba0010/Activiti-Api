package motorMetricas.valores;

import motorMetricas.Valor;

/**
 * Calse para controlar los tipos double.
 * @author David Blanco Alonso
 */
public class Largo implements Valor
{
	/**
	 * Valor.
	 */
	private double valor;

	/**
	 * Constructor.
	 */
    public Largo() 
    {
        valor = 0;
    }
    /**
	 * Constructor.
	 */
	public Largo(double valor)
	{
		this.valor = valor;
	}

	/**
	 * Devuelve el valor.
	 * @return Double valor.
	 */
	public double getValor() 
	{
		return valor;
	}

	/**
	 * Modifica el valor.
	 * @param valor double valor.
	 */
    public void setValor(double valor) 
    {
       this.valor = valor;
    }
    
    /**
	 * Metodos toString.
	 * @return String valor.
	 */
    public String toString()
    {
    	return String.valueOf(valor);
    }
}
