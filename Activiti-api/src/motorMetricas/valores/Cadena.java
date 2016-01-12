package motorMetricas.valores;

import motorMetricas.Valor;

public class Cadena implements Valor
{
	private String valor;

    public Cadena() 
    {
        valor = "";
    }

	public Cadena(String valor)
	{
		this.valor = valor;
	}

	public String getValor() 
	{
		return valor;
	}

    public void setValor(String valor) 
    {
       this.valor = valor;
    }
}
