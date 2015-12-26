package motorMetricas.valores;

import motorMetricas.Valor;

public class Double implements Valor{

	private double valor;

    public Double() 
    {
        valor=0;
    }

	public Double(double valor)
	{
		this.valor=valor;
	}

	public double getValor() 
	{
		return valor;
	}

    public void setValor(double valor) 
    {
       this.valor = valor;
    }
}
