package metricas;

import java.io.IOException;
import java.util.List;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Entero;

/**
 * Métrica NumeroIssues.
 * @author David Blanco Alonso
 */
public class NumeroIssues extends Metrica
{
	/**
	 * Constructor.
	 */
	public NumeroIssues()
	{
		descripcion = new Descripcion("Proceso de orientación", "NumeroIssues", "Número de issues total creadas en el repositorio",
				"¿Cuántas issues se han creado en el repositorio?", "NI número de issues", "NI >= 0 mejor valores bajos",
				"Absoluta", "NI contador", "Repositorio GitHub de un proyecto");
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
		Entero entero = new Entero(lista.size());
		return entero;
	}
}