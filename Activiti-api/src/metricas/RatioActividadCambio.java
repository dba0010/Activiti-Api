package metricas;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Double;
import motorMetricas.valores.Fecha;

public class RatioActividadCambio extends Metrica
{
	private Descripcion descripcion;
	
	public RatioActividadCambio()
	{
		descripcion = new Descripcion("Restricciones temporales", "RatioActividadCambio", "Muestra el n�mero de cambios relativos al n�mero de meses.",
				"�Cu�l es el n�mero medio de cambios por mes?", "RAC = (NTC = N�mero total de cambios) / NM (N�mero de meses)", "RAC > 0 Mejor valores intermedios",
				"Ratio", "NTC contador, NM contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		Fecha inicio =new Fecha(null);
		Fecha fin =new Fecha(null);
		
		for( Object x : lista)
		{
			if(inicio.getValor() == null || inicio.getValor().after(((RepositoryCommit) x).getCommit().getAuthor().getDate()))
			{
				inicio.setValor(((RepositoryCommit) x).getCommit().getAuthor().getDate());
			}
			
			if(fin.getValor() == null || fin.getValor().before(((RepositoryCommit) x).getCommit().getAuthor().getDate()))
			{
				fin.setValor(((RepositoryCommit) x).getCommit().getAuthor().getDate());
			}
		}
		
		Calendar calencadInicio = Calendar.getInstance();
		calencadInicio.setTime(inicio.getValor());
		
		Calendar calencadFin = Calendar.getInstance();
		calencadFin.setTime(fin.getValor());
		
		int mesesInicio = (calencadInicio.get(Calendar.YEAR) * 12) + calencadInicio.get(Calendar.MONTH);
		int mesesFin = (calencadFin.get(Calendar.YEAR) * 12) + calencadFin.get(Calendar.MONTH);
		
		int diffMeses = mesesFin - mesesInicio;
		
		return new Double((double)lista.size()/diffMeses);
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