package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Largo;

/**
 * Métrica DiasPrimerUltimoCommit.
 * @author David Blanco Alonso
 */
public class DiasPrimerUltimoCommit extends Metrica
{
	/**
	 * Constructor.
	 */
	public DiasPrimerUltimoCommit()
	{
		descripcion = new Descripcion("Restricciones temporales", "DiasPrimerUltimoCommit", "Días transcurridos entre el primer y el ultimo commit",
				"¿Cuantos días han pasado entre el primer y el último commit?", "DPUC días pasados", "DPUC >= 0 mejor valores altos",
				"Absoluta", "DPUC contador", "Repositorio GitHub de un proyecto");
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
		double dias = 0;
		Largo valor = new Largo(0);
		
		for(int i = 0; i < lista.size(); i++)
		{
			if(i < lista.size() - 1)
			{
				dias += ((RepositoryCommit) lista.get(i)).getCommit().getAuthor().getDate().getTime() - ((RepositoryCommit) lista.get(i+1)).getCommit().getAuthor().getDate().getTime();
			}			
		}
		
		valor.setValor(dias / (1000 * 60 * 60 * 24));
			
		return valor;
	}
}