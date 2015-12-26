package motorMetricas;

import java.util.Date;

import motorMetricas.Valor;

public class Medida {

	private Date fecha;

	private Valor value;
	
	public Medida(Valor value)
	{
		fecha=new Date();
		this.value=value;
	}

	public Date getFecha() 
	{
		return fecha;
	}

	public Valor getValue() 
	{
		return value;
	}
}