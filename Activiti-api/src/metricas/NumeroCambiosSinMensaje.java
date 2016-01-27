package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Entero;

public class NumeroCambiosSinMensaje extends Metrica
{
	public NumeroCambiosSinMensaje()
	{
		descripcion = new Descripcion("Proceso de orientación", "NumeroCambiosSinMensaje", "Número de cambios realizados que no tienen mensaje.",
				"¿Cuántos cambios se han realizado sin mensaje?", "NCSM número de cambios sin mensaje", "NCSM >= 0 mejor valores bajos",
				"Absoluta", "NCSM contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{		
		Entero entero = new Entero(0);
		
		for(Object x : lista)
		{
			if(((RepositoryCommit) x).getCommit().getMessage() == "")
			{
				entero.setValor(entero.getValor() + 1);
			}
		}

		return entero;
	}
}