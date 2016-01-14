package motorMetricas;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import motorMetricas.valores.Cadena;
import motorMetricas.valores.Conjunto;
import motorMetricas.valores.Entero;
import motorMetricas.valores.Double;
import motorMetricas.valores.Fecha;


public class ResultadoMetrica
{
	private static Date fecha;
	
	private Vector<Medida> coleccionMedidas;
	
	private DecimalFormat formateador = new DecimalFormat("###0.00"); 
	
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
	
	public String getMetricas()
	{
		String cadena = "";
		
		cadena += "Metricas:";
		for(Medida x : coleccionMedidas)
		{
			switch (x.getValue().getClass().getName())
			{
				case "motorMetricas.valores.Entero": cadena += "\n  " + x.getMetrica().getDescripcion().getNombre() + ": " + ((Entero) x.getValue()).getValor();
								break;
				case "motorMetricas.valores.Double": cadena += "\n  " + x.getMetrica().getDescripcion().getNombre() + ": " + formateador.format(((Double) x.getValue()).getValor());
								break;
				case "motorMetricas.valores.Fecha": cadena += "\n  " + x.getMetrica().getDescripcion().getNombre() + ": " + ((Fecha) x.getValue()).getValor().toString();
								break;
				case "motorMetricas.valores.Cadena": cadena += "\n  " + x.getMetrica().getDescripcion().getNombre() + ": " + ((Cadena) x.getValue()).getValor();
								break;
				case "motorMetricas.valores.Conjunto": cadena += "\n  " + x.getMetrica().getDescripcion().getNombre() + ": ";
								Map<String, Double> aux = ((Conjunto) x.getValue()).getValor();
								for(String key : aux.keySet())
								{
									cadena += "\n\t" + key + ": " + aux.get(key).getValor();
								}
								break;
				default: cadena += x.getMetrica().getDescripcion().getProposito() + ": " + x.getValue();
			}
		}
		
		return cadena;
	}
}