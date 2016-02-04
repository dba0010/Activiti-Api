package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Issue;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Largo;

/**
 * Métrica MediaDiasCierre.
 * @author David Blanco Alonso
 */
public class MediaDiasCierre extends Metrica
{
	/**
	 * Constructor.
	 */
	public MediaDiasCierre()
	{
		descripcion = new Descripcion("Proceso de orientacion", "MediaDiasCierre", "Muestra los días que se tarda en cerrar una issue, normalizado por el numero de issues cerradas",
				"¿Cuanto se tarda de media en cerrar una issue?", "MDC = D (suma de los días) / NIC (numero de issues cerradas)", "MDC >= 0 mejor valores bajos",
				"Ratio", "D contador, NIC contador", "Repositorio GitHub de un proyecto");
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
		int cerradas = 0;
		for(Object x : lista)
  		{
  			if(((Issue) x).getState().equals("closed"))
 			{
 				cerradas++;
 				mediaDias += (((Issue) x).getClosedAt().getTime() - ((Issue) x).getCreatedAt().getTime() )/ (1000 * 60 * 60 * 24);
 			}
 		}
		if(cerradas == 0){cerradas = 1;}
		mediaDias /= cerradas;
		
		return new Largo(mediaDias);
	}
}