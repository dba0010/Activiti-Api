package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Repository;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Entero;

public class NumeroIssues extends Metrica
{
	private Descripcion descripcion;
	
	public NumeroIssues()
	{
		descripcion = new Descripcion("Proceso de orientacion", "NumeroIssues", "Numero de issues total creadas en el repositorio",
				"¿Cuantas issues se han creado en el repositorio?", "NI numero de issues", "NI >= 0 mejor valores bajos",
				"Absoluta", "NI contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		Entero entero = new Entero(lista.size());
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