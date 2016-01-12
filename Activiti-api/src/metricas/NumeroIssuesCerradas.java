package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Repository;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Entero;

public class NumeroIssuesCerradas extends Metrica
{
	private Descripcion descripcion;
	
	public NumeroIssuesCerradas()
	{
		descripcion = new Descripcion("Proceso de orientacion", "NumeroIssuesCerradas", "Numero de issues cerradas total en el repositorio",
				"¿Cuantas issues se han cerrado en el repositorio?", "NIC numero de issues cerradas", "NIC >= 0 mejor valores altos",
				"Absoluta", "NIC contador", "Repositorio GitHub de un proyecto");
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