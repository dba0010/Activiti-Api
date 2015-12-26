package metricas;

import java.io.IOException;
import java.util.List;

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
		descripcion = new Descripcion("Estadistica", "Media de dias entre commits", "Dias de media de realizacion de un commit",
				"¿Cuantos dias de media pasan entre commits?", "C dias de media", "C >= 0 mejor valores bajos",
				"Absoluta", "C contador", "Repositorio GitHub de un proyecto");
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
	
	public Descripcion getDescripcion()
	{
		return descripcion;
	}

	public void check() 
	{		
	}
}