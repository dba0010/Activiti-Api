package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Repository;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Double;

public class MediaDiasCierre extends Metrica
{
	private Descripcion descripcion;
	
	public MediaDiasCierre()
	{
		descripcion = new Descripcion("Proceso de orientacion", "MediaDiasCierre", "Muestra los dias que se tarda en cerrar una issue, normalizado por el numero de issues cerradas",
				"¿Cuanto se tarda de media en cerrar una issue?", "MDC = D (suma de los dias) / NIC (numero de issues cerradas)", "MDC >= 0 mejor valores bajos",
				"Ratio", "D contador, NIC contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{		
		double mediaDias = 0;
		int cerradas = 0;
		
		Double valor = new Double(0);
		
		for(Object x : lista)
		{
			if(((Issue) x).getState().equals("closed"))
			{
				cerradas++;
				mediaDias += (((Issue) x).getClosedAt().getTime() - ((Issue) x).getCreatedAt().getTime() )/ (1000 * 60 * 60 * 24);
			}
		}
		if(cerradas == 0){cerradas = 1;}
		mediaDias /= cerradas;
		
		valor.setValor(mediaDias);
		return valor;
	}
	
	public Valor run(List<?> lista, List<?> lista2) throws IOException 
	{
		return null;
	}
	
	public Valor run(Repository dato) throws IOException 
	{
		return null;
	}
	
	public Descripcion getDescripcion()
	{
		return descripcion;
	}

	public void check() 
	{		
	}
}