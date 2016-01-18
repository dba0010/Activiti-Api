package lector;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;

import charts.Graficos;
import metricas.*;
import motorMetricas.Metrica;
import motorMetricas.ResultadoMetrica;
import motorMetricas.valores.Conjunto;
import motorMetricas.valores.Entero;

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