package metricas;

import java.io.IOException;
import java.util.List;

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
		descripcion = new Descripcion("Estadistica", "Dias entre el primer y el ultimo commit", "Dias entre el primer y el ultimo commit",
				"¿Cuantos dias han pasado entre el primer y el ultimo commit?", "O dias pasados", "O >= 0 mejor valores altos",
				"Absoluta", "X contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		double dias = 0;
		
		for(int i = 0; i < lista.size(); i++)
		{
			if(i < lista.size() - 1)
			{
				dias += ((RepositoryCommit) lista.get(i)).getCommit().getAuthor().getDate().getTime() - ((RepositoryCommit) lista.get(i+1)).getCommit().getAuthor().getDate().getTime();
			}			
		}
		
		Double valor = new Double(dias / (1000 * 60 * 60 * 24));
			
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