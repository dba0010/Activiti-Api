package metricas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Double;

public class ContadorAutor extends Metrica
{
	public ContadorAutor()
	{
		descripcion = new Descripcion("Equipo", "ContadorAutor", "Muestra el numero de autores trabajando, normalizado sobre el numero de cambios realizado",
				"¿Cuál es la relacián entre el número de desarrolladores y el número de cambios del proyecto?", "CA = NA (Numero autores) / NC (Numero cambios)", "CA > 0 mejor valores bajos",
				"Absoluta", "NA contador, NC contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		List<String> autores = new ArrayList<String>();
		String autor = "";
				
		for(Object x : lista)
		{
			autor = ((RepositoryCommit) x).getCommit().getAuthor().getName();
			
			if(!autores.contains(autor))
			{
				autores.add(autor);
			}
		}
		
		if(lista.size() == 0)
		{
			return new Double(0);
		}
		else
		{
			return new Double((double)autores.size()/lista.size());
		}
	}
}