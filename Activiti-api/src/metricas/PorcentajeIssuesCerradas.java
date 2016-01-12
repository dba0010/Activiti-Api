package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Repository;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Double;

public class PorcentajeIssuesCerradas extends Metrica
{
	private Descripcion descripcion;
	
	public PorcentajeIssuesCerradas()
	{
		descripcion = new Descripcion("Proceso de orientacion", "PorcentajeIssuesCerradas", "Porcentaje de las issues cerradas en el repositorio",
				"¿Proporcion de issues cerradas en el repositorio en funcion de las creadas?", "PIC = NIC (Numero de issues cerradas) * 100 / NI (Numero de issues)", "0 <= N <= 100 mejor valores altos",
				"Ratio", "NIC contador, NI contador", "Repositorio GitHub de un proyecto");
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