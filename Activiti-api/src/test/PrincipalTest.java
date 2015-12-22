package test;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

import org.eclipse.egit.github.core.RepositoryId;

import lector.*;
import junit.framework.TestCase;

public class PrincipalTest extends TestCase 
{
	public void testMetricas()
	{
		try 
		{
			FabricaLector fabricaLector = FabricaLectorGitHub.getInstance();
		
			Scanner sc = new Scanner(System.in);
			String usuario, password;
			System.out.println("Introduzca usuario: ");
			usuario = sc.nextLine();
			System.out.println("Introduzca contraseña: ");
			password = sc.nextLine();
			sc.close();
			FachadaLector lector = fabricaLector.crearFachadaLector(usuario, password);
			
			
			//Probamos el repositorio clopezno/libre-gift
			lector.obtenerRepositorios("clopezno");			
			lector.obtenerMetricas("clopezno", new RepositoryId("clopezno","libre-gift"));

			MetricasGitHub metricas = (MetricasGitHub) lector.getMetricas();
						
			DecimalFormat formateador = new DecimalFormat("###0.00"); 
			
			assertEquals(12, metricas.getNumIssues());
			assertEquals(10, metricas.getNumIssuesCerradas());
			assertEquals(0.0, metricas.getMediaDiasCierre());
			assertEquals(83.0, metricas.getPorcentajeIssuesCerradas());
			assertEquals(0, metricas.getNumCambios());
			assertEquals("256,99", formateador.format(metricas.getMediaDiasCambios()));
			assertEquals("770,98", formateador.format(metricas.getDiasPrimerUltimoCommit()));			
			assertEquals("Fri May 22 13:47:06 CEST 2015", metricas.getUltimaModificacion().toString());
			
			//probamos el repositorio jam0101/TFGII-Quiz-Grafos
			lector.obtenerRepositorios("jam0101");			
			lector.obtenerMetricas("jam0101", new RepositoryId("jam0101","TFGII-Quiz-Grafos"));

			metricas = (MetricasGitHub) lector.getMetricas();
			
			assertEquals(44, metricas.getNumIssues());
			assertEquals(43, metricas.getNumIssuesCerradas());
			assertEquals("27,91", formateador.format(metricas.getMediaDiasCierre()));
			assertEquals(97.0, metricas.getPorcentajeIssuesCerradas());
			assertEquals(0, metricas.getNumCambios());
			assertEquals("6,89", formateador.format(metricas.getMediaDiasCambios()));
			assertEquals("131,00", formateador.format(metricas.getDiasPrimerUltimoCommit()));	
			assertEquals("Tue Oct 27 18:44:46 CET 2015", metricas.getUltimaModificacion().toString());
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
	
}