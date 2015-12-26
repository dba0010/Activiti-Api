package motorMetricas.valores;

import motorMetricas.Valor;

public class Entero implements Valor{

	private int valor;

    public Entero() 
    {
        valor=0;
    }

	public Entero(int valor)
	{
		this.valor=valor;
	}


	public int getValor() 
	{
		return valor;
	}

    public void setValor(int valor) 
    {
       this.valor = valor;
    }
}
