package lector;

import java.io.IOException;
import java.util.List;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;

import metricas.*;
import motorMetricas.Metrica;
import motorMetricas.ResultadoMetrica;

public class FachadaMetricasGitHub implements FachadaMetricas
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
		
		this.numWatchers = new NumeroWatchers();
		this.numWatchers.calculate(repositorio, metricas);
	}
	
	public ResultadoMetrica getResultado()
	{
		return this.metricas;
	}
}