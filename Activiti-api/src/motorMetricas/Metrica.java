package motorMetricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Repository;


public abstract class Metrica implements IMetric
{
	protected String author;

	protected int year;

	protected Descripcion descripcion;

    protected String nombre;

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

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

	public abstract Valor run(List<?> lista) throws IOException;
	
	public abstract Valor run(List<?> lista, List<?> lista2) throws IOException;
	
	public abstract Valor run(Repository dato) throws IOException;

	public abstract void check();

	public Descripcion getDescripcion() 
	{
		return descripcion;
	}

	public String getAuthor() 
	{
		return author;
	}

	public int getYear() 
	{
		return year;
	}

    public String toString()
    {
        if(descripcion != null)
        {
            return descripcion.getNombre();
        }
        else
        {
            return getNombre();
        }
    }
}