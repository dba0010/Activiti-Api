package motorMetricas;

/**
 * Implementa todos los campos que componen la descripción detallada de una métrica.
 * @author David Blanco Alonso
 */
public class Descripcion 
{
	/**
	 * Categoria de la métrica.
	 */
	private String categoria;

	/**
	 * Descripcion de la métrica.
	 */
	private String descripcion;

	/**
	 * Formula de la métrica.
	 */
	private String formula;

	/**
	 * Fuente de medición de la métrica.
	 */
	private String fuenteDeMedicion;

	/**
	 * Interpretación de la métrica.
	 */
	private String interpretacion;

	/**
	 * Nombre de la métrica.
	 */
	private String nombre;

	/**
	 * Proposito de la métrica.
	 */
	private String proposito;

	/**
	 * Tipo de escala de la métrica.
	 */
	private String tipoEscala;

	/**
	 * Tipo de medida de la métrica..
	 */
	private String tipoMedida;

	/**
	 * Constructor.
	 * @param categoria
	 * @param nombre
	 * @param descripcion
	 * @param proposito
	 * @param formula
	 * @param interpretacion
	 * @param tipoEscala
	 * @param tipoMedida
	 * @param fuenteDeMedicion
	 */
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
	
	/**
	 * Devuelve la categoria de la métrica..
	 * @return String categoria.
	 */
	public String getCategoria() 
	{
		return categoria;
	}

	/**
	 * Devuelve la descripción de la métrica..
	 * @return String descripción.
	 */
	public String getDescripcion() 
	{
		return descripcion;
	}

	/**
	 * Devuelve la formula de la métrica..
	 * @return String formula.
	 */
	public String getFormula()
	{
		return formula;
	}
	
	/**
	 * Devuelve la fuente de medición de la métrica..
	 * @return String fuente de medición.
	 */
	public String getFuenteDeMedicion()
	{
		return fuenteDeMedicion;
	}
	
	/**
	 * Devuelve la interpretación de la métrica..
	 * @return String interpretación.
	 */
	public String getInterpretacion() 
	{
		return interpretacion;
	}

	/**
	 * Devuelve el nombre de la métrica..
	 * @return String nombre.
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * Devuelve el proposito de la métrica..
	 * @return String proposito.
	 */
	public String getProposito() 
	{
		return proposito;
	}

	/**
	 * Devuelve el tipo de escala de la métrica..
	 * @return String tipo de escala.
	 */
	public String getTipoEscala() 
	{
		return tipoEscala;
	}
	
	/**
	 * Devuelve el tipo de medida de la métrica..
	 * @return String tipo de medida.
	 */
	public String getTipoMedida() 
	{
		return tipoMedida;
	}
}
