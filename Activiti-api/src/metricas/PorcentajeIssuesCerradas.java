package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Issue;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Double;

public class PorcentajeIssuesCerradas extends Metrica
{
	private Descripcion descripcion;
	
	public PorcentajeIssuesCerradas()
	{
		descripcion = new Descripcion("Estadistica", "Porcentaje de issues cerradas", "Porcentaje de las issues cerrdas en el repositorio",
				"¿Proporceion de issues cerradas en el repositorio en funcion de las creadas?", "X porcentaje", "0 <= N <= 100 mejor valores altos",
				"Ratio", "NI contador, NC contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		int cerradas = 0;
		for(Object x : lista)
		{
			if(((Issue) x).getState().equals("closed"))
			{
				cerradas++;
			}
		}
		
		Double valor = new Double(cerradas * 100 / lista.size());
		
		return valor;
	}
	
	public Descripcion getDescripcion()
	{
		return descripcion;
	}

	public void check() 
	{		
	}
}