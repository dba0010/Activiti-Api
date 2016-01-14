package metricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Double;

public class DiasPrimerUltimoCommit extends Metrica
{
	private Descripcion descripcion;
	
	public DiasPrimerUltimoCommit()
	{
		descripcion = new Descripcion("Restricciones temporales", "DiasPrimerUltimoCommit", "Dias transcurridos entre el primer y el ultimo commit",
				"¿Cuantos dias han pasado entre el primer y el ultimo commit?", "DPUC dias pasados", "DPUC >= 0 mejor valores altos",
				"Absoluta", "DPUC contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		double dias = 0;
		Double valor = new Double(0);
		
		for(int i = 0; i < lista.size(); i++)
		{
			if(i < lista.size() - 1)
			{
				dias += ((RepositoryCommit) lista.get(i)).getCommit().getAuthor().getDate().getTime() - ((RepositoryCommit) lista.get(i+1)).getCommit().getAuthor().getDate().getTime();
			}			
		}
		
		valor.setValor(dias / (1000 * 60 * 60 * 24));
			
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