package motorMetricas.valores;

import motorMetricas.Valor;

/**
 * Clase para controlar los tipos enteros.
 * @author David Blanco Alonso
 */
public class Entero implements Valor
{
	/**
	 * Valor.
	 */
	private int valor;

	/**
	 * Constructor.
	 */
    public Entero() 
    {
        valor = 0;
    }

    /**
	 * Constructor.
	 */
	public Entero(int valor)
	{
		this.valor = valor;
	}

	/**
	 * Devuelve el valor.
	 * @return int valor.
	 */
	public int getValor() 
	{
		return valor;
	}

	/**
	 * Modifica el valor.
	 * @param valor int valor.
	 */
    public void setValor(int valor) 
    {
       this.valor = valor;
    }
    
    /**
	 * Metodos toString.
	 * @return String valor.
	 */
    public String toString()
    {
    	return Integer.toString(valor);
    }
}
