package metricas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Largo;

/**
 * Métrica ContadorAutor.
 * @author David Blanco Alonso
 */
public class ContadorAutor extends Metrica
{
	/**
	 * Constructor.
	 */
	public ContadorAutor()
	{
		descripcion = new Descripcion("Equipo", "ContadorAutor", "Muestra el número de autores trabajando, normalizado sobre el número de cambios realizado",
				"¿Cuál es la relación entre el número de desarrolladores y el número de cambios del proyecto?", "CA = NA (Numero autores) / NC (Numero cambios)", "CA > 0 mejor valores bajos",
				"Absoluta", "NA contador, NC contador", "Repositorio GitHub de un proyecto");
	}
	
	/**
	 * Metodo que calcula la métrica y la guarda en el objeto ResultadoMetrica.
	 * @param lista List<?> información necesaria para calcular la métrica.
	 * @param metricResult ResultadoMetrica objeto donde guardar el resultado.
	 * @return Valor valor obtenido en la métrica.
	 * @throws IOException
	 */
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
			return new Largo(0);
		}
		else
		{
			return new Largo((double)autores.size()/lista.size());
		}
	}
}