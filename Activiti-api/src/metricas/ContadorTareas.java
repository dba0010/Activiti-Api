package metricas;

import java.io.IOException;
import java.util.List;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Largo;

/**
 * Métrica ContadorTareas.
 * @author David Blanco Alonso
 */
public class ContadorTareas extends Metrica
{
	/**
	 * Constructor.
	 */
	public ContadorTareas()
	{
		descripcion = new Descripcion("Proceso de orientación", "ContadorTareas", "Muestra el número de tareas, normalizada sobre el número total de cambios.",
				"¿Cuál es el volumen medio de trabajo de las tareas?", "CT = NT (Numero de tareas) / NTC (Numero total de cambios)", "CT >= 0 mejor valores intermedios",
				"Ratio", "NT contador, NTC contador", "Repositorio GitHub de un proyecto");
	}
	
	/**
	 * Metodo que calcula la métrica y la guarda en el objeto ResultadoMetrica.
	 * @param lista lista2 List<?> información necesaria para calcular la métrica.
	 * @param metricResult ResultadoMetrica objeto donde guardar el resultado.
	 * @return Valor valor obtenido en la métrica.
	 * @throws IOException
	 */
	public Valor run(List<?> lista, List<?> lista2) throws IOException
	{
		if(lista2.size() == 0)
		{
			return new Largo(0);
		}
		else
		{
			return new Largo((double)lista.size()/lista2.size());
		}
	}
}