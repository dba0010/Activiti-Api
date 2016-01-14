package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Fecha;

public class UltimaModificacion extends Metrica
{	
	public UltimaModificacion()
	{
		descripcion = new Descripcion("Restricciones temporales", "UltimaModificacion", "Fecha en la que se realizo el ultimo cambio en el repositorio",
				"¿Cuando se realizo el ultimo cambio en el repositorio?", "UM fecha", "",
				"Absoluta", "UM fecha", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		Fecha valor = new Fecha(null);
		
		for(Object x : lista)
		{
			if(valor.getValor() == null || ((RepositoryCommit) x).getCommit().getAuthor().getDate().after(valor.getValor()))
			{
				valor.setValor(((RepositoryCommit) x).getCommit().getAuthor().getDate());
			}
		}
		
		return valor;
	}
}