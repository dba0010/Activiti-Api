package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Issue;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Double;

public class MediaDiasCierre extends Metrica
{
	private Descripcion descripcion;
	
	public MediaDiasCierre()
	{
		descripcion = new Descripcion("Estadistica", "Media de dias de cieere de issues", "Media de los dias que se tarda en cerrar una issue",
				"¿Cuanto se tarda de media en cerrar una issue?", "D dias de media", "D > 0 mejor valores bajos",
				"Absoluta", "D contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{		
		double mediaDias = 0;
		int cerradas = 0;
		
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
		
		Double valor = new Double(mediaDias);
		return valor;
	}
	
	public Descripcion getDescripcion()
	{
		return descripcion;
	}

	public void check() 
	{		
	}
}