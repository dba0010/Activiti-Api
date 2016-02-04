package lector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import metricas.CambioPorAutor;
import metricas.CommitPorDia;
import metricas.CommitPorMes;
import metricas.ContadorAutor;
import metricas.ContadorCambiosPico;
import metricas.ContadorTareas;
import metricas.DiasPrimerUltimoCommit;
import metricas.IssuesPorAutor;
import metricas.MediaDiasCambio;
import metricas.MediaDiasCierre;
import metricas.NumeroCambiosSinMensaje;
import metricas.NumeroFavoritos;
import metricas.NumeroIssues;
import metricas.NumeroIssuesCerradas;
import metricas.PorcentajeIssuesCerradas;
import metricas.RatioActividadCambio;
import metricas.RelacionMesPico;
import metricas.UltimaModificacion;
import motorMetricas.Medida;
import motorMetricas.Metrica;
import motorMetricas.ResultadoMetrica;
import motorMetricas.valores.Cadena;
import motorMetricas.valores.Conjunto;
import motorMetricas.valores.Entero;
import motorMetricas.valores.Fecha;
import motorMetricas.valores.Largo;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;

import charts.Graficos;

/**
 * Fachada encargada de calcular las metricas asignadas a GitHub.
 * @author David Blanco Alonso
 */
public class FachadaMetricasGitHub implements FachadaMetricas
{	
	/**
	 * Medidas resultantes del cálculo de las metricas.
	 */
	ResultadoMetrica metricas;
	
	/**
	 * Metricas asignadas.
	 */
	private Metrica commitXAutor;
	private Metrica commitXDia;
	private Metrica commitXMes;
	private Metrica contadorAutor;
	private Metrica contadorCambiosPico;
	private Metrica contadorTareas;
	private Metrica diasPrimerUltimoCommit;
	private Metrica issueXAutor;
	private Metrica mediaDiasCambios;
	private Metrica mediaDiasCierre;
	private Metrica numCambiosSinMensaje;
	private Metrica numFavoritos;
	private Metrica numIssues;
	private Metrica numIssuesCerradas;
	private Metrica porcentajeIssuesCerradas;
	private Metrica ratioActividadCambio;
	private Metrica relacionMesPico;
	private Metrica ultimaModificacion; 
	
	/**
	 * Formato para las salidas de tiopos Largo o double.
	 */
	private DecimalFormat formateador = new DecimalFormat("###0.00");
	
	/**
	 * constructor desde archivo.
	 * @param archivo BufferedReader archivo leido.
	 */
	public FachadaMetricasGitHub(BufferedReader archivo)
	{
		metricas = new ResultadoMetrica();
		
		String linea = "";
		int nLinea = 0;
		Medida medida;
		try
		{
			linea = archivo.readLine();
		
			while(linea != null)
			{
				if (!linea.startsWith("//"))
				{
					switch (nLinea)
					{
						case 0: 
							this.numIssues = new NumeroIssues();
							medida = new Medida(this.numIssues, new Entero(Integer.parseInt(linea.substring(linea.indexOf(":") + 2))));
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 1:
							this.contadorTareas = new ContadorTareas();
							medida = new Medida(this.contadorTareas, new Largo(Double.parseDouble(linea.substring(linea.indexOf(":") + 2))));
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 2:
							this.numIssuesCerradas = new NumeroIssuesCerradas();
							medida = new Medida(this.numIssuesCerradas, new Entero(Integer.parseInt(linea.substring(linea.indexOf(":") + 2))));
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 3:
							this.porcentajeIssuesCerradas = new PorcentajeIssuesCerradas();
							medida = new Medida(this.porcentajeIssuesCerradas, new Largo(Double.parseDouble(linea.substring(linea.indexOf(":") + 2))));
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 4:
							this.mediaDiasCierre = new MediaDiasCierre();
							medida = new Medida(this.mediaDiasCierre, new Largo(Double.parseDouble(linea.substring(linea.indexOf(":") + 2))));
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 5:
							this.numCambiosSinMensaje = new NumeroCambiosSinMensaje();
							medida = new Medida(this.numCambiosSinMensaje, new Entero(Integer.parseInt(linea.substring(linea.indexOf(":") + 2))));
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 6:
							this.mediaDiasCambios = new MediaDiasCambio();
							medida = new Medida(this.mediaDiasCambios, new Largo(Double.parseDouble(linea.substring(linea.indexOf(":") + 2))));
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 7:
							this.diasPrimerUltimoCommit = new DiasPrimerUltimoCommit();
							medida = new Medida(this.diasPrimerUltimoCommit, new Largo(Double.parseDouble(linea.substring(linea.indexOf(":") + 2))));
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 8:
							this.ultimaModificacion = new UltimaModificacion();
							try 
							{
						    	SimpleDateFormat formatoFecha = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
								medida = new Medida(this.ultimaModificacion, new Fecha(formatoFecha.parse(linea.substring(linea.indexOf(":") + 2))));
								metricas.addMeasure(medida);
							} 
							catch (ParseException e) 
							{
								e.printStackTrace();
							}
							nLinea++; 
							break;
						case 9:
							this.commitXMes = new CommitPorMes();
							Conjunto valores = new Conjunto();
							int tamaño = Integer.parseInt(linea.substring(linea.indexOf(":") + 2));
							for (int i = 0; i < tamaño; i++)
							{
								linea = archivo.readLine();
								if(linea != null)
								{
									valores.setValor(linea.substring(0, linea.indexOf(":")), new Entero(Integer.parseInt(linea.substring(linea.indexOf(":") + 2))));
								}
							}
							medida = new Medida(this.commitXMes, valores);
							metricas.addMeasure(medida);
							nLinea++; 
							break;	
						case 10:
							this.relacionMesPico = new RelacionMesPico();
							medida = new Medida(this.relacionMesPico, new Cadena(linea.substring(linea.indexOf(":") + 2)));
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 11:
							this.contadorCambiosPico = new ContadorCambiosPico();
							medida = new Medida(this.contadorCambiosPico, new Largo(Double.parseDouble(linea.substring(linea.indexOf(":") + 2))));
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 12:
							this.ratioActividadCambio = new RatioActividadCambio();
							medida = new Medida(this.ratioActividadCambio, new Largo(Double.parseDouble(linea.substring(linea.indexOf(":") + 2))));
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 13:
							this.commitXDia = new CommitPorDia();
							valores = new Conjunto();
							tamaño = Integer.parseInt(linea.substring(linea.indexOf(":") + 2));
							for (int i = 0; i < tamaño; i++)
							{
								linea = archivo.readLine();
								if(linea != null)
								{
									valores.setValor(linea.substring(0, linea.indexOf(":")), new Entero(Integer.parseInt(linea.substring(linea.indexOf(":") + 2))));
								}
							}
							medida = new Medida(this.commitXDia, valores);
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 14:
							this.commitXAutor = new CambioPorAutor();
							valores = new Conjunto();
							tamaño = Integer.parseInt(linea.substring(linea.indexOf(":") + 2));
							for (int i = 0; i < tamaño; i++)
							{
								linea = archivo.readLine();
								if(linea != null)
								{
									valores.setValor(linea.substring(0, linea.indexOf(":")), new Entero(Integer.parseInt(linea.substring(linea.indexOf(":") + 2))));
								}
							}
							medida = new Medida(this.commitXAutor, valores);
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 15:
							this.contadorAutor = new ContadorAutor();
							medida = new Medida(this.contadorAutor, new Largo(Double.parseDouble(linea.substring(linea.indexOf(":") + 2))));
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 16:
							this.issueXAutor = new IssuesPorAutor();
							valores = new Conjunto();
							tamaño = Integer.parseInt(linea.substring(linea.indexOf(":") + 2));
							for (int i = 0; i < tamaño; i++)
							{
								linea = archivo.readLine();
								if(linea != null)
								{
									valores.setValor(linea.substring(0, linea.indexOf(":")), new Entero(Integer.parseInt(linea.substring(linea.indexOf(":") + 2))));
								}
							}
							medida = new Medida(this.issueXAutor, valores);
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						case 17: 
							this.numFavoritos = new NumeroFavoritos();
							medida = new Medida(this.numFavoritos, new Entero(Integer.parseInt(linea.substring(linea.indexOf(":") + 2))));
							metricas.addMeasure(medida);
							nLinea++; 
							break;
						default:;
					}
				}
				linea = archivo.readLine();
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor desde información obtenida de GitHub.
	 * @param repositorio Repository información del repositorio
	 * @param issues List<issues> información de las issues.
	 * @param commits List<RepositoryCommit> información de los commits.
	 * @throws IOException
	 */
	public FachadaMetricasGitHub(Repository repositorio, List<Issue> issues, List<RepositoryCommit> commits) throws IOException
	{
		metricas = new ResultadoMetrica();
				
		this.numIssues = new NumeroIssues();
		this.numIssues.calculate(issues, metricas);

		this.contadorTareas = new ContadorTareas();
		this.contadorTareas.calculate(issues, commits, metricas);
		
		this.numIssuesCerradas = new NumeroIssuesCerradas();
		this.numIssuesCerradas.calculate(issues, metricas);
		
		this.porcentajeIssuesCerradas = new PorcentajeIssuesCerradas();
		this.porcentajeIssuesCerradas.calculate(issues, metricas);
		
		this.mediaDiasCierre = new MediaDiasCierre();
		this.mediaDiasCierre.calculate(issues, metricas);
		
		this.numCambiosSinMensaje = new NumeroCambiosSinMensaje();
		this.numCambiosSinMensaje.calculate(commits, metricas);
		
		this.mediaDiasCambios = new MediaDiasCambio();
		this.mediaDiasCambios.calculate(commits, metricas);
		
		this.diasPrimerUltimoCommit = new DiasPrimerUltimoCommit();
		this.diasPrimerUltimoCommit.calculate(commits, metricas);
		
		this.ultimaModificacion = new UltimaModificacion();
		this.ultimaModificacion.calculate(commits, metricas);	
		
		this.commitXMes = new CommitPorMes();
		this.commitXMes.calculate(commits, metricas);
		
		this.relacionMesPico = new RelacionMesPico();
		this.relacionMesPico.calculate(commits, metricas);
		
		this.contadorCambiosPico = new ContadorCambiosPico();
		this.contadorCambiosPico.calculate(commits, metricas);
		
		this.ratioActividadCambio = new RatioActividadCambio();
		this.ratioActividadCambio.calculate(commits, metricas);
		
		this.commitXDia = new CommitPorDia();
		this.commitXDia.calculate(commits, metricas);
		
		this.commitXAutor = new CambioPorAutor();
		this.commitXAutor.calculate(commits, metricas);
		
		this.contadorAutor = new ContadorAutor();
		this.contadorAutor.calculate(commits, metricas);
		
		this.issueXAutor = new IssuesPorAutor();
		this.issueXAutor.calculate(issues, metricas);
		
		this.numFavoritos = new NumeroFavoritos();
		this.numFavoritos.calculate(repositorio, metricas);
	}
	
	/**
	 * Realiza la comparacion con otra conexion.
	 * @param comparacion FachadaConexion conexion con la que comparar.
	 * @return String texto resultante del a comparacion.
	 */
	public String comparar(FachadaConexion comparacion)
	{
		String resultadoComparacion = "";
		for(int i = 0; i < metricas.size(); i++)
		{
			switch(metricas.getMedida(i).getMetrica().getDescripcion().getNombre())
			{
				case "NumeroIssues":
				case "NumeroCambiosSinMensaje":
					resultadoComparacion += "<tr>";
					resultadoComparacion += "<td>" + metricas.getMedida(i).getMetrica().getDescripcion().getNombre() + "</td>";
					if(((Entero)metricas.getMedida(i).getValue()).getValor() < ((Entero)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor())
					{
						resultadoComparacion += "<td class=\"verde\">" + ((Entero)metricas.getMedida(i).getValue()).getValor() + "</td>";
						resultadoComparacion += "<td class=\"rojo\">" + ((Entero)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor() + "</td>";
					}
					else if(((Entero)metricas.getMedida(i).getValue()).getValor() > ((Entero)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor())
					{
						resultadoComparacion += "<td class=\"rojo\">" + ((Entero)metricas.getMedida(i).getValue()).getValor() + "</td>";
						resultadoComparacion += "<td class=\"verde\">" + ((Entero)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor() + "</td>";
					}
					else
					{
						resultadoComparacion += "<td>" + ((Entero)metricas.getMedida(i).getValue()).getValor() + "</td>";
						resultadoComparacion += "<td>" + ((Entero)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor() + "</td>";
					}
					resultadoComparacion += "</tr>";
					break;
				case "NumeroIssuesCerradas":
				case "NumeroFavoritos":
					resultadoComparacion += "<tr>";
					resultadoComparacion += "<td>" + metricas.getMedida(i).getMetrica().getDescripcion().getNombre() + "</td>";
					if(((Entero)metricas.getMedida(i).getValue()).getValor() > ((Entero)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor())
					{
						resultadoComparacion += "<td class=\"verde\">" + ((Entero)metricas.getMedida(i).getValue()).getValor() + "</td>";
						resultadoComparacion += "<td class=\"rojo\">" + ((Entero)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor() + "</td>";
					}
					else if(((Entero)metricas.getMedida(i).getValue()).getValor() < ((Entero)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor())
					{
						resultadoComparacion += "<td class=\"rojo\">" + ((Entero)metricas.getMedida(i).getValue()).getValor() + "</td>";
						resultadoComparacion += "<td class=\"verde\">" + ((Entero)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor() + "</td>";
					}
					else
					{
						resultadoComparacion += "<td>" + ((Entero)metricas.getMedida(i).getValue()).getValor() + "</td>";
						resultadoComparacion += "<td>" + ((Entero)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor() + "</td>";
					}
					resultadoComparacion += "</tr>";
					break;
				case "ContadorTareas":
				case "PorcentajeIssuesCerradas":
				case "DiasPrimerUltimoCommit":
				case "ContadorCambiosPico":
				case "RatioActividadCambio":
					resultadoComparacion += "<tr>";
					resultadoComparacion += "<td>" + metricas.getMedida(i).getMetrica().getDescripcion().getNombre() + "</td>";
					if(((Largo)metricas.getMedida(i).getValue()).getValor() > ((Largo)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor())
					{
						resultadoComparacion += "<td class=\"verde\">" + formateador.format(((Largo)metricas.getMedida(i).getValue()).getValor()) + "</td>";
						resultadoComparacion += "<td class=\"rojo\">" + formateador.format(((Largo)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor()) + "</td>";
					}
					else if(((Largo)metricas.getMedida(i).getValue()).getValor() < ((Largo)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor())
					{
						resultadoComparacion += "<td class=\"rojo\">" + formateador.format(((Largo)metricas.getMedida(i).getValue()).getValor()) + "</td>";
						resultadoComparacion += "<td class=\"verde\">" + formateador.format(((Largo)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor()) + "</td>";
					}
					else
					{
						resultadoComparacion += "<td>" + formateador.format(((Largo)metricas.getMedida(i).getValue()).getValor()) + "</td>";
						resultadoComparacion += "<td>" + formateador.format(((Largo)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor()) + "</td>";
					}
					resultadoComparacion += "</tr>";
					break;
				case "MediaDiasCierre":
				case "MediaDiasCambio":
				case "ContadorAutor":
					resultadoComparacion += "<tr>";
					resultadoComparacion += "<td>" + metricas.getMedida(i).getMetrica().getDescripcion().getNombre() + "</td>";
					if(((Largo)metricas.getMedida(i).getValue()).getValor() < ((Largo)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor())
					{
						resultadoComparacion += "<td class=\"verde\">" + formateador.format(((Largo)metricas.getMedida(i).getValue()).getValor()) + "</td>";
						resultadoComparacion += "<td class=\"rojo\">" + formateador.format(((Largo)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor()) + "</td>";
					}
					else if(((Largo)metricas.getMedida(i).getValue()).getValor() > ((Largo)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor())
					{
						resultadoComparacion += "<td class=\"rojo\">" + formateador.format(((Largo)metricas.getMedida(i).getValue()).getValor()) + "</td>";
						resultadoComparacion += "<td class=\"verde\">" + formateador.format(((Largo)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor()) + "</td>";
					}
					else
					{
						resultadoComparacion += "<td>" + formateador.format(((Largo)metricas.getMedida(i).getValue()).getValor()) + "</td>";
						resultadoComparacion += "<td>" + formateador.format(((Largo)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor()) + "</td>";
					}
					resultadoComparacion += "</tr>";
					break;
				case "UltimaModificacion":
					resultadoComparacion += "<tr>";
					resultadoComparacion += "<td>" + metricas.getMedida(i).getMetrica().getDescripcion().getNombre() + "</td>";
					if(((Fecha)metricas.getMedida(i).getValue()).getValor().getTime() > ((Fecha)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor().getTime())
					{
						resultadoComparacion += "<td class=\"verde\">" + ((Fecha)metricas.getMedida(i).getValue()).getValor() + "</td>";
						resultadoComparacion += "<td class=\"rojo\">" + ((Fecha)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor() + "</td>";
					}
					else if(((Fecha)metricas.getMedida(i).getValue()).getValor().getTime() < ((Fecha)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor().getTime())
					{
						resultadoComparacion += "<td class=\"rojo\">" + ((Fecha)metricas.getMedida(i).getValue()).getValor() + "</td>";
						resultadoComparacion += "<td class=\"verde\">" + ((Fecha)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor() + "</td>";
					}
					else
					{
						resultadoComparacion += "<td>" + ((Fecha)metricas.getMedida(i).getValue()).getValor() + "</td>";
						resultadoComparacion += "<td>" + ((Fecha)comparacion.getMetricas().getResultadoMetrica().getMedida(i).getValue()).getValor() + "</td>";
					}
					resultadoComparacion += "</tr>";
					break;
				default:;
			}
		}
		return resultadoComparacion;
	}
	
	/**
	 * Genera un texto con los resultados obtenidos en las metricas.
	 * @return String texto del informe.
	 */
	public String generarArchivo()
	{
		String texto = "";
		for(int i = 0; i < metricas.size(); i++)
		{
			texto += metricas.getMedida(i).getMetrica().getDescripcion().getNombre() + ": " + metricas.getMedida(i).getValue().toString() + "\n";
		}
		return texto;
	}
	
	/**
	 * Devuelve los resutlados de l as metricas.
	 * @return ResultadoMetrica objeto con todas las medidas resultantes del cálculo de métricas.
	 */
	public ResultadoMetrica getResultadoMetrica()
	{
		return metricas;
	}
	
	/**
	 * Metodo que devuelve los resultados de las metricas en formato texto y gráfico.
	 * @return Object[] resultados en formato texto y ChartPanel.
	 */
	public Object[] getResultados()
	{
		Object[] resultados = new Object[6];
		
		//String con toda la lista de métricas y sus resultados.
		resultados[0] = metricas.getMetricas();  
		
		int[] valoresPieChart = new int[2];
		//Recorremos las metricas para recoger los valores que necesitaremos para los graficos.
		for(int i = 0; i < metricas.size(); i++)
		{
			switch (metricas.getMedida(i).getMetrica().getDescripcion().getNombre())
			{
				case "NumeroIssues":
					valoresPieChart[0] = ((Entero) metricas.getMedida(i).getValue()).getValor();
					break;
				case "NumeroIssuesCerradas":
					valoresPieChart[0] -= ((Entero) metricas.getMedida(i).getValue()).getValor();
					valoresPieChart[1] = ((Entero) metricas.getMedida(i).getValue()).getValor();
					//Grafico de tarta para mostrar el porcentaje de cierre de issues.
					resultados[1] = Graficos.crearGraficoTarta(new String[]{"Abiertas", "Cerradas"}, valoresPieChart, "Issues abiertas - cerradas");
					break;
				case "CommitPorMes":
					resultados[2] = Graficos.crearGraficoBarra3d((Conjunto)metricas.getMedida(i).getValue(), "Commits por mes", "Meses", "Nº Commits");
					break;
				case "CommitPorDia":
					resultados[3] = Graficos.crearGraficoBarra3d((Conjunto)metricas.getMedida(i).getValue(), "Commits por Día", "Días", "Nº Commits");
					break;
				case "CambioPorAutor":
					resultados[4] = Graficos.crearGraficoBarra3d((Conjunto)metricas.getMedida(i).getValue(), "Cambio por autor", "Autor", "Nº Commits");
					break;
				case "IssuesPorAutor":
					resultados[5] = Graficos.crearGraficoBarra3d((Conjunto)metricas.getMedida(i).getValue(), "Issues por autor", "Autor", "Nº Issues");
					break;
			}
		}
		
		return resultados;
	}
}