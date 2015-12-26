package motorMetricas.valores;

import java.util.Date;

import motorMetricas.Valor;

public class Fecha implements Valor{

	private Date valor;

    public Fecha() 
    {
        valor = new Date();
    }

	public Fecha(Date valor)
	{
		this.valor=valor;
	}

	public Date getValor() 
	{
		return valor;
	}

    public void setValor(Date valor) 
    {
       this.valor = valor;
    }
}