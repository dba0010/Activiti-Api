package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Issue;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Entero;

public class NumeroIssuesCerradas extends Metrica
{
	private Descripcion descripcion;
	
	public NumeroIssuesCerradas()
	{
		descripcion = new Descripcion("Estadistica", "Numero de issues cerradas", "Numero de issues cerradas total en el repositorio",
				"¿Cuantas issues se han cerrado en el repositorio?", "X numero de issues cerradas", "X >= 0 mejor valores altos",
				"Absoluta", "X contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		Entero entero = new Entero(0);
		
		for(Object x : lista)
		{
			if(((Issue) x).getState().equals("closed"))
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