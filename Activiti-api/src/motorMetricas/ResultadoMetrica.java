package motorMetricas;

import java.util.Date;
import java.util.Vector;

public class ResultadoMetrica
{
	
	private static Date fecha;
	
	private Vector<Medida> coleccionMedidas;

	
	public ResultadoMetrica()
	{
		fecha = new Date();
		coleccionMedidas = new Vector<Medida>();
	}
	
	public void addMeasure(Medida medida)
	{
		coleccionMedidas.add(medida);
	}
	
	public static Date getFecha() 
	{
		return fecha;
	}
	
	public Medida getMedida(int i) 
	{
		return coleccionMedidas.get(i);
	}
	
	public int size()
	{
		return coleccionMedidas.size();
	}
}