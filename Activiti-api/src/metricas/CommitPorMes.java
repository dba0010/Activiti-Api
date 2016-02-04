package metricas;

import java.io.IOException;
import java.util.List;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;

/**
 * Métrica CommitPorMes.
 * @author David Blanco Alonso
 */
public class CommitPorMes extends Metrica
{
	/**
	 * Constructor.	
	 */
	public CommitPorMes()
	{
		descripcion = new Descripcion("Restricciones temporales", "CommitPorMes", "Muestra el número de commits realizados cada mes",
				"¿Cuántos commits se han realizado cada mes?", "CPM commits por mes", "CPM >= 0 mejor valores altos",
				"Absoluta", "CPM contador", "Repositorio GitHub de un proyecto");
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
		return obtenerCambiosXMesGitHub(lista);
	}
}