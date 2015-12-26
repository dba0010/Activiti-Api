package motorMetricas;

import java.io.IOException;
import java.util.List;


public abstract class Metrica implements IMetric
{
	protected Valor valueMinDefault;

	protected Valor valueMaxDefault;

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
		Medida measure=new Medida(valor);
		metricResult.addMeasure(measure);
		return valor;
	}

	public abstract Valor run(List<?> lista) throws IOException;

	public abstract void check();

	public Valor getValueMinDefault() 
	{
		return valueMinDefault;
	}

	public Valor getValueMaxDefault() 
	{
		return valueMaxDefault;
	}

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