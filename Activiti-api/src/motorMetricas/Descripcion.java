package motorMetricas;

/**
 * Implementa todos los campos que componen la descripción detallada de una métrica.
 * @author Irene Barbero Tera
 * @author Estrella Resa Camarero
 */

public class Descripcion 
{
	private String categoria;

	private String nombre;

	private String descripcion;

	private String proposito;

	private String formula;

	private String interpretacion;

	private String tipoEscala;

	private String tipoMedida;

	private String fuenteDeMedicion;

	public Descripcion(String categoria, String nombre, String descripcion, String proposito, String formula,
			String interpretacion, String tipoEscala, String tipoMedida, String fuenteDeMedicion)
	{
		this.categoria = categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.proposito = proposito;
		this.formula = formula;
		this.interpretacion = interpretacion;
		this.tipoEscala = tipoEscala;
		this.tipoMedida = tipoMedida;
		this.fuenteDeMedicion = fuenteDeMedicion;
	}
	
	public String getCategoria() 
	{
		return categoria;
	}

	public void setCategoria(String categoria) 
	{
		this.categoria = categoria;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}

	public String getProposito() 
	{
		return proposito;
	}

	public void setProposito(String proposito)
	{
		this.proposito = proposito;
	}

	public String getFormula()
	{
		return formula;
	}

	public void setFormula(String formula) 
	{
		this.formula = formula;
	}

	public String getInterpretacion() 
	{
		return interpretacion;
	}

	public void setInterpretacion(String interpretacion) 
	{
		this.interpretacion = interpretacion;
	}

	public String getTipoEscala() 
	{
		return tipoEscala;
	}

	public void setTipoEscala(String tipoEscala) 
	{
		this.tipoEscala = tipoEscala;
	}

	public String getTipoMedida() 
	{
		return tipoMedida;
	}

	public void setTipoMedida(String tipoMedida) 
	{
		this.tipoMedida = tipoMedida;
	}

	public String getFuenteDeMedicion()
	{
		return fuenteDeMedicion;
	}

	public void setFuenteDeMedicion(String fuenteDeMedicion) 
	{
		this.fuenteDeMedicion = fuenteDeMedicion;
	}
}
