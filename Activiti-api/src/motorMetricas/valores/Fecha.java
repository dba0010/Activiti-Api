package motorMetricas.valores;

import java.util.Date;
import motorMetricas.Valor;

/**
 * Clase para controlar los tipos Date.
 * @author David Blanco Alonso
 */
public class Fecha implements Valor
{
	/**
	 * Valor
	 */
	private Date valor;

	/**
	 * Constructor.
	 */
    public Fecha() 
    {
        valor = new Date();
    }

    /**
	 * Constructor.
	 */
	public Fecha(Date valor)
	{
		this.valor = valor;
	}

	/**
	 * Devuelve el valor.
	 * @return Date valor.
	 */
	public Date getValor() 
	{
		return valor;
	}

	/**
	 * Modifica el valor.
	 * @param valor Date valor.
	 */
    public void setValor(Date valor) 
    {
    	this.valor = valor;
    }
    
    /**
	 * Metodos toString.
	 * @return String valor.
	 */
    public String toString()
    {
		return valor.toString();
    }
}