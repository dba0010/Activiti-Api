package metricas;

import java.io.IOException;
import java.util.List;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;

/**
 * Métrica NumeroIssuesCerradas.
 * @author David Blanco Alonso
 */
public class NumeroIssuesCerradas extends Metrica
{
	/**
	 * Constructor.
	 */
	public NumeroIssuesCerradas()
	{
		descripcion = new Descripcion("Proceso de orientación", "NumeroIssuesCerradas", "Número de issues cerradas total en el repositorio",
				"¿Cuántas issues se han cerrado en el repositorio?", "NIC número de issues cerradas", "NIC >= 0 mejor valores altos",
				"Absoluta", "NIC contador", "Repositorio GitHub de un proyecto");
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
		return obternerIssuesCerradasGitHub(lista);
	}
}