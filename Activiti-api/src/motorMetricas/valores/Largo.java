package motorMetricas.valores;

import motorMetricas.Valor;

public class Largo implements Valor
{
	private double valor;

    public Largo() 
    {
        valor = 0;
    }

	public Largo(double valor)
	{
		this.valor = valor;
	}

	public double getValor() 
	{
		return valor;
	}

    public void setValor(double valor) 
    {
       this.valor = valor;
    }
    
    public String toString()
    {
    	return String.valueOf(valor);
    }
}
