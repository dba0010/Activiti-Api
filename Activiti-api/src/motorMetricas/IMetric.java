package motorMetricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Repository;

/**
 * Interface de las métricas.
 * @author David Blanco Alonso
 */
public interface IMetric 
{
	/**
	 * Metodo que calcula la métrica y la guarda en el objeto ResultadoMetrica.
	 * @param lista lista2 List<?> información necesaria para calcular la métrica.
	 * @param metricResult ResultadoMetrica objeto donde guardar el resultado.
	 * @return Valor valor obtenido en la métrica.
	 * @throws IOException
	 */
	public Valor calculate(List<?> lista, List<?> lista2, ResultadoMetrica metricResult) throws IOException;	
	
	/**
	 * Metodo que calcula la métrica y la guarda en el objeto ResultadoMetrica.
	 * @param lista List<?> información necesaria para calcular la métrica.
	 * @param metricResult ResultadoMetrica objeto donde guardar el resultado.
	 * @return Valor valor obtenido en la métrica.
	 * @throws IOException
	 */
	public Valor calculate(List<?> lista, ResultadoMetrica metricResult) throws IOException;	
	
	/**
	 * Metodo que calcula la métrica y la guarda en el objeto ResultadoMetrica.
	 * @param dato Repository información necesaria para calcular la métrica.
	 * @param metricResult ResultadoMetrica objeto donde guardar el resultado.
	 * @return Valor valor obtenido en la métrica.
	 * @throws IOException
	 */
	public Valor calculate(Repository dato, ResultadoMetrica metricResult) throws IOException;
}