package motorMetricas;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import motorMetricas.valores.Conjunto;
import motorMetricas.valores.Entero;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;

/**
 * Clase que implementa la interface IMetric.
 * @author David Blanco Alonso
 */
public abstract class Metrica implements IMetric
{
	/**
	 * Descripcion de la métrica.
	 */
	protected Descripcion descripcion;

	/**
	 * Metodo que calcula la métrica y la guarda en el objeto ResultadoMetrica.
	 * @param lista lista2 List<?> información necesaria para calcular la métrica.
	 * @param metricResult ResultadoMetrica objeto donde guardar el resultado.
	 * @return Valor valor obtenido en la métrica.
	 * @throws IOException
	 */
    public Valor calculate(List<?> lista, List<?> lista2, ResultadoMetrica metricResult) throws IOException	
    {
		Valor valor = run(lista, lista2);
		Medida measure=new Medida(this, valor);
		metricResult.addMeasure(measure);
		return valor;
	}
    
	/**
	 * Metodo que calcula la métrica y la guarda en el objeto ResultadoMetrica.
	 * @param lista List<?> información necesaria para calcular la métrica.
	 * @param metricResult ResultadoMetrica objeto donde guardar el resultado.
	 * @return Valor valor obtenido en la métrica.
	 * @throws IOException
	 */
	public Valor calculate(List<?> lista, ResultadoMetrica metricResult) throws IOException	
    {
		Valor valor = run(lista);
		Medida measure=new Medida(this, valor);
		metricResult.addMeasure(measure);
		return valor;
	}
    
    /**
	 * Metodo que calcula la métrica y la guarda en el objeto ResultadoMetrica.
	 * @param dato Repository información necesaria para calcular la métrica.
	 * @param metricResult ResultadoMetrica objeto donde guardar el resultado.
	 * @return Valor valor obtenido en la métrica.
	 * @throws IOException
	 */
    public Valor calculate(Repository dato, ResultadoMetrica metricResult) throws IOException	
    {
		Valor valor = run(dato);
		Medida measure=new Medida(this, valor);
		metricResult.addMeasure(measure);
		return valor;
	}

    /**
	 * devulve la descripción de la métrica.
	 * @return Descripcion descripción de la métrica.
	 */
	public Descripcion getDescripcion() 
	{
		return descripcion;
	}
	
	/**
	 * Obtiene el número de cambion por mes.
	 * @param lista List<?> información necesaria, en este caso List<commits>.
	 * @return Conjunto valores obtenidos.
	 */
	protected Conjunto obtenerCambiosXMesGitHub(List<?> lista)
	{
		String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		
		Conjunto valores = new Conjunto();
		Calendar fecha = Calendar.getInstance();
		
		for(String key : meses)
		{
			valores.setValor(key, new Entero(0));
		}
		
		int i = 0;
		for(Object x : lista)
		{
			fecha.setTime(((RepositoryCommit) x).getCommit().getAuthor().getDate());
			i = fecha.get(Calendar.MONTH);
			valores.setValor( meses[i], new Entero(valores.getValor(meses[i]).getValor() + 1));
		}
		
		return valores;
	}
	
	/**
	 * Obtiene el numero de issues cerradas en GitHub.
	 * @param lista List<?> información necesaria, en este caso List<issues>.
	 * @return Entero valor obtenido.
	 */
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

	/**
     * Calcula el valor de la metrica
     * @param lista List<?> información necesaria para calcular la métrica.
     * @return Valor valor obtenido en la métrica.
     * @throws IOException
     */
	public Valor run(List<?> lista) throws IOException
	{
		return null;
	}	
	
	/**
     * Calcula el valor de la metrica
     * @param lista lista2 List<?> información necesaria para calcular la métrica.
     * @return Valor valor obtenido en la métrica.
     * @throws IOException
     */
	public Valor run(List<?> lista, List<?> lista2) throws IOException
	{
		return null;
	}
	
	/**
     * Calcula el valor de la metrica
     * @param dato Repository información necesaria para calcular la métrica.
     * @return Valor valor obtenido en la métrica.
     * @throws IOException
     */
	public Valor run(Repository dato) throws IOException
	{
		return null;
	}
	
	/**
	 * Metodo toString.
	 */
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