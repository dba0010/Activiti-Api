package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Conjunto;
import motorMetricas.valores.Entero;

/**
 * Métrica CambioPorAutor.
 * @author David Blanco Alonso
 */
public class CambioPorAutor extends Metrica
{
	/**
	 * constructor
	 */
	public CambioPorAutor()
	{
		descripcion = new Descripcion("Equipo", "CambioPorAutor", "Muestra el número de commits realizados por cada usuario participante en el proyecto",
				"¿Cuántos commits ha realizado cada usuario?", "CA cambio por autor", "CA > 0 mejor valores altos",
				"Absoluta", "CA contador", "Repositorio GitHub de un proyecto");
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
		Conjunto valores = new Conjunto();
				
		for(Object x : lista)
		{
			String autor = ((RepositoryCommit) x).getCommit().getAuthor().getName();
			Entero aux = new Entero(0);
			
			if(valores.getValor().containsKey(autor))
			{
				aux = new Entero(valores.getValor(autor).getValor());
			}
			aux.setValor(aux.getValor() + 1);
			valores.setValor( autor, aux);
		}
		
		return valores;
	}
}