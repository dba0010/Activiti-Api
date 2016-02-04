package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Largo;

/**
 * Métrica MediaDiasCambio.
 * @author David Blanco Alonso
 */
public class MediaDiasCambio extends Metrica
{
	/**
	 * Constructor.
	 */
	public MediaDiasCambio()
	{
		descripcion = new Descripcion("Restricciones temporales", "MediaDiasCambio", "Días de media para la realización de un cambio",
				"¿Cuantos días de media pasan entre cambios?", "MDC = D (Días desde el primer al último cambio) / NTC (Número total de cambios)", "MDC >= 0 mejor valores bajos",
				"Absoluta", "D contador, NTC contador", "Repositorio GitHub de un proyecto");
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
		
		Largo valor = new Largo(mediaDias);	
		return valor;
	}
}