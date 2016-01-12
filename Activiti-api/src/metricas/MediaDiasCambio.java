package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Double;

public class MediaDiasCambio extends Metrica
{
	private Descripcion descripcion;
	
	public MediaDiasCambio()
	{
		descripcion = new Descripcion("Restricciones temporales", "MediaDiasCambio", "Dias de media para la realizacion de un cambio",
				"¿Cuantos dias de media pasan entre cambios?", "MDC = D (Dias desde el primer al ultimo cambio) / NTC (Numero total de cambios)", "MDC >= 0 mejor valores bajos",
				"Absoluta", "D contador, NTC contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		
		double mediaDias = 0;
		
		for(int i = 0; i < lista.size()-1; i++)
		{
			mediaDias += ((RepositoryCommit) lista.get(i)).getCommit().getAuthor().getDate().getTime() - ((RepositoryCommit) lista.get(i + 1)).getCommit().getAuthor().getDate().getTime();
		}
		
		mediaDias /= (1000 * 60 * 60 * 24);
		if(lista.size() == 0)
		{
			mediaDias = 0;
		}
		else
		{
			mediaDias /= lista.size();
		}
		
		Double valor = new Double(mediaDias);	
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