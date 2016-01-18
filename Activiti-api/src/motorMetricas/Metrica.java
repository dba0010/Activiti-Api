package motorMetricas;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import motorMetricas.valores.Conjunto;
import motorMetricas.valores.Double;
import motorMetricas.valores.Entero;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;


public abstract class Metrica implements IMetric
{
	protected Descripcion descripcion;

	public Valor calculate(List<?> lista, ResultadoMetrica metricResult) throws IOException	
    {
		check();
		Valor valor = run(lista);
		Medida measure=new Medida(this, valor);
		metricResult.addMeasure(measure);
		return valor;
	}
    
    public Valor calculate(List<?> lista, List<?> lista2, ResultadoMetrica metricResult) throws IOException	
    {
		check();
		Valor valor = run(lista, lista2);
		Medida measure=new Medida(this, valor);
		metricResult.addMeasure(measure);
		return valor;
	}
    
    public Valor calculate(Repository dato, ResultadoMetrica metricResult) throws IOException	
    {
		check();
		Valor valor = run(dato);
		Medida measure=new Medida(this, valor);
		metricResult.addMeasure(measure);
		return valor;
	}

	public Valor run(List<?> lista) throws IOException
	{
		return null;
	}
	
	public Valor run(List<?> lista, List<?> lista2) throws IOException
	{
		return null;
	}
	
	public Valor run(Repository dato) throws IOException
	{
		return null;
	}

	public void check()
	{
	}

	public Descripcion getDescripcion() 
	{
		return descripcion;
	}	
	
	protected Entero obternerIssuesCerradasGitHub(List<?> lista)
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
	
	protected Conjunto obtenerCambiosXMesGitHub(List<?> lista)
	{
		String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		
		Conjunto valores = new Conjunto();
		Calendar fecha = Calendar.getInstance();
		
		for(String key : meses)
		{
			valores.setValor(key, new Double(0));
		}
		
		int i = 0;
		for(Object x : lista)
		{
			fecha.setTime(((RepositoryCommit) x).getCommit().getAuthor().getDate());
			i = fecha.get(Calendar.MONTH);
			valores.setValor( meses[i], new Double(valores.getValor(meses[i]).getValor() + 1));
		}
		
		return valores;
	}
	
    public String toString()
    {
        if(descripcion != null)
        {
            return descripcion.getNombre();
        }
        else
        {
            return "";
        }
    }
}