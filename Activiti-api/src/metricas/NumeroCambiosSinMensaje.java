package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
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
		descripcion = new Descripcion("Proceso de orientacion", "NumeroCambiosSinMensaje", "Numero de cambios realizados que no tienen mensaje.",
				"¿Cuantos cambios se han realizado sin mensaje?", "NCSM numero de cambios sin mensaje", "NCSM >= 0 mejor valores bajos",
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
	
	public Valor run(List<?> lista, List<?> lista2) throws IOException 
	{
		return null;
	}
	
	public Valor run(Repository dato) throws IOException 
	{
		return null;
	}
	
	public Descripcion getDescripcion()
	{
		return descripcion;
	}

	public void check() 
	{		
	}
}