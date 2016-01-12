package lector;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;

import metricas.*;
import motorMetricas.Metrica;
import motorMetricas.ResultadoMetrica;
import motorMetricas.valores.*;
import motorMetricas.valores.Double;

public class MetricasGitHub implements FachadaMetricas
{	
	ResultadoMetrica metricas;
	
	private Metrica numIssues;
	private Metrica numIssuesCerradas;
	private Metrica porcentajeIssuesCerradas;
	private Metrica mediaDiasCierre;
	private Metrica numCambiosSinMensaje;
	private Metrica mediaDiasCambios;
	private Metrica diasPrimerUltimoCommit;
	private Metrica ultimaModificacion;
	private Metrica commitXMes;
	private Metrica commitXDia;
	private Metrica commitXAutor;
	private Metrica issueXAutor;
	private Metrica numWatchers;
	private Metrica contadorAutor;
	private Metrica contadorTareas;
	private Metrica relacionMesPico;
	private Metrica contadorCambiosPico;
	private Metrica ratioActividadCambio;
	
	private DecimalFormat formateador = new DecimalFormat("###0.00"); 
	
	public MetricasGitHub(Repository repositorio, List<Issue> issues, List<RepositoryCommit> commits) throws IOException
	{
		metricas = new ResultadoMetrica();
				
		this.numIssues = new NumeroIssues();
		this.numIssues.calculate(issues, metricas);
		
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
		
		this.commitXDia = new CommitPorDia();
		this.commitXDia.calculate(commits, metricas);
		
		this.commitXAutor = new CambioPorAutor();
		this.commitXAutor.calculate(commits, metricas);
		
		this.issueXAutor = new IssuesPorAutor();
		this.issueXAutor.calculate(issues, metricas);
		
		this.numWatchers = new NumeroWatchers();
		this.numWatchers.calculate(repositorio, metricas);
		
		this.contadorAutor = new ContadorAutor();
		this.contadorAutor.calculate(commits, metricas);
		
		this.contadorTareas = new ContadorTareas();
		this.contadorTareas.calculate(issues, commits, metricas);
		
		this.relacionMesPico = new RelacionMesPico();
		this.relacionMesPico.calculate(commits, metricas);
		
		this.contadorCambiosPico = new ContadorCambiosPico();
		this.contadorCambiosPico.calculate(commits, metricas);
		
		this.ratioActividadCambio = new RatioActividadCambio();
		this.ratioActividadCambio.calculate(commits, metricas);
	}
	
	public int getNumIssues() 
	{
		return ((Entero)this.metricas.getMedida(0).getValue()).getValor();
	}
	
	public int getNumIssuesCerradas() 
	{
		return ((Entero)this.metricas.getMedida(1).getValue()).getValor();
	}
	
	public double getPorcentajeIssuesCerradas() 
	{
		return ((Double)this.metricas.getMedida(2).getValue()).getValor();
	}
	
	public double getMediaDiasCierre() 
	{
		return ((Double)this.metricas.getMedida(3).getValue()).getValor();
	}
	
	public int getNumCambiosSinMensaje()
	{
		return ((Entero)this.metricas.getMedida(4).getValue()).getValor();
	}
	
	public double getMediaDiasCambios() 
	{
		return ((Double)this.metricas.getMedida(5).getValue()).getValor();
	}

	public double getDiasPrimerUltimoCommit() 
	{
		return ((Double)this.metricas.getMedida(6).getValue()).getValor();
	}
	
	public Date getUltimaModificacion() 
	{
		return ((Fecha)this.metricas.getMedida(7).getValue()).getValor();
	}
	
	public Map<String, Double> getCommitXMes()
	{
		return ((Conjunto)this.metricas.getMedida(8).getValue()).getValor();
	}
	
	public Map<String, Double> getCommitXDia()
	{
		return ((Conjunto)this.metricas.getMedida(9).getValue()).getValor();
	}
	
	public Map<String, Double> getCommitXAutor()
	{
		return ((Conjunto)this.metricas.getMedida(10).getValue()).getValor();
	}
	
	public Map<String, Double> getIssueXAutor()
	{
		return ((Conjunto)this.metricas.getMedida(11).getValue()).getValor();
	}
	
	public int getNumWatchers()
	{
		return ((Entero)this.metricas.getMedida(12).getValue()).getValor();
	}
	
	public String getContadorAutor() 
	{
		return formateador.format(((Double)this.metricas.getMedida(13).getValue()).getValor());
	}
	
	public String getContadorTareas() 
	{
		return formateador.format(((Double)this.metricas.getMedida(14).getValue()).getValor());
	}
	
	public String getRelacionMesPico() 
	{
		return ((Cadena)this.metricas.getMedida(15).getValue()).getValor();
	}
	
	public String getContadorCambiosPico() 
	{
		return formateador.format(((Double)this.metricas.getMedida(16).getValue()).getValor());
	}
	
	public String getRatioActividadCambio() 
	{
		return formateador.format(((Double)this.metricas.getMedida(17).getValue()).getValor());
	}
	
	public String toString()
	{
		String x = "Estadisticas:" + 
				"\n Número de issues: " + ((Entero)this.metricas.getMedida(0).getValue()).getValor() + 
				"\n Número de issues cerradas: " + ((Entero)this.metricas.getMedida(1).getValue()).getValor() + 
				"\n Porcentaje de issues cerradas: " + ((Double)this.metricas.getMedida(2).getValue()).getValor() + "%" +
				"\n Media de dias para cerrar issue: " + formateador.format(((Double)this.metricas.getMedida(3).getValue()).getValor()) + 
				"\n Numero de commits sin mensaje: " + ((Entero)this.metricas.getMedida(4).getValue()).getValor() + 
				"\n Media de dias por commit: " + formateador.format(((Double)this.metricas.getMedida(5).getValue()).getValor()) + 
				"\n Dias entre el primer y el ultimo commit: " + formateador.format(((Double)this.metricas.getMedida(6).getValue()).getValor()) +
				"\n Ultima modificacion: " + ((Fecha)this.metricas.getMedida(7).getValue()).getValor().toString() +
				"\n Commits por mes:";
				Map<String, Double> aux = getCommitXMes();
				for(String key : aux.keySet())
				{
					x += "\n\t" + key + ": " + aux.get(key).getValor();
				}
				x += "\n Relacion mes pico: " + getRelacionMesPico();
				x += "\n Contador cambios pico: " + getContadorCambiosPico();
				x += "\n Ratio actividad cambio: " + getRatioActividadCambio();
				x += "\n Commits por dia:";
				aux = getCommitXDia();
				for(String key : aux.keySet())
				{
					x += "\n\t" + key + ": " + aux.get(key).getValor();
				}
				x += "\n Commits por autor:";
				aux = getCommitXAutor();
				for(String key : aux.keySet())
				{
					x += "\n\t" + key + ": " + aux.get(key).getValor();
				}
				x += "\n Issues por autor:";
				aux = getIssueXAutor();
				for(String key : aux.keySet())
				{
					x += "\n\t" + key + ": " + aux.get(key).getValor();
				}
				x += "\n Numero de watchers: " + getNumWatchers();
				x += "\n Contador autores: " + getContadorAutor();
				x += "\n Contador tareas: " + getContadorTareas();
		return x;
	}
}