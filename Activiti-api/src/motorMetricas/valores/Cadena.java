package motorMetricas.valores;

import motorMetricas.Valor;

/**
 * Calse para manejar los tipos de valores String.
 * @author David Blanco Alonso
 */
public class Cadena implements Valor
{
	/**
	 * valor.
	 */
	private String valor;

	/**
	 * Constructor.
	 */
    public Cadena() 
    {
        valor = "";
    }

    /**
	 * Constructor.
	 */
	public Cadena(String valor)
	{
		this.valor = valor;
	}

	/**
	 * Devuelve el valor.
	 * @return String valor.
	 */
	public String getValor() 
	{
		return valor;
	}

	/**
	 * Modifica el valor.
	 * @param valor String valor.
	 */
    public void setValor(String valor) 
    {
       this.valor = valor;
    }
    
    /**
	 * Metodos toString.
	 * @return String valor.
	 */
    public String toString()
    {
		return valor;
    }
}
