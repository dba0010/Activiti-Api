package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Repository;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Conjunto;
import motorMetricas.valores.Double;

public class IssuesPorAutor extends Metrica
{
	private Descripcion descripcion;
		
	public IssuesPorAutor()
	{
		descripcion = new Descripcion("Equipo", "IssuesPorAutor", "Muestra el numero de issues realizados por cada usuario participante en el proyecto",
				"¿Cuantos issues ha realizado cada usuario?", "IPA issues por usuaurio", "IPA >= 0 mejor valores altos",
				"Absoluta", "IPA contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		Conjunto valores = new Conjunto();
				
		for(Object x : lista)
		{
			String autor = ((Issue) x).getUser().getLogin();
			Double aux = new Double(0);
			
			if(valores.getValor().containsKey(autor))
			{
				aux = new Double(valores.getValor(autor).getValor());
			}
			aux.setValor(aux.getValor() + 1);
			valores.setValor( autor, aux);
		}
		
		return valores;
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