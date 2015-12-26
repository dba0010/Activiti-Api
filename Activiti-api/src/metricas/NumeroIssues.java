package metricas;

import java.io.IOException;
import java.util.List;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Entero;

public class NumeroIssues extends Metrica
{
	private Descripcion descripcion;
	
	public NumeroIssues()
	{
		descripcion = new Descripcion("Estadistica", "Numero de issues", "Numero de issues total creadas en el repositorio",
				"¿Cuantas issues se han creado en el repositorio?", "X numero de issues", "X > 0 mejor valores bajos",
				"Absoluta", "X contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		Entero entero = new Entero(lista.size());
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