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
	private Descripcion descripcion;
	
	public NumeroCambiosSinMensaje()
	{
		descripcion = new Descripcion("Estadistica", "Numero de commits sin mensaje", "Numero de commits realizados que no tienen mensaje.",
				"¿Cuantos commits se han realizado sin mensaje?", "N numero de commits sin mensaje", "N >= 0 mejor valores bajos",
				"Absoluta", "N contador", "Repositorio GitHub de un proyecto");
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
	
	public Descripcion getDescripcion()
	{
		return descripcion;
	}

	public void check() 
	{		
	}
}