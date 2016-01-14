package test;

import java.io.IOException;
import org.eclipse.egit.github.core.RepositoryId;

import lector.*;
import junit.framework.TestCase;

public class PrincipalTest extends TestCase 
{
	public void testMetricas()
	{
		try 
		{
			FabricaConexion fabricaLector = FabricaConexionGitHub.getInstance();
		
			String usuario, password;
			usuario = "dba0010Test";
			password = "Contraseña1234";
			FachadaConexion lector = fabricaLector.crearFachadaConexion(usuario, password);
			
			//Probamos el repositorio clopezno/libre-gift
			lector.getNombresRepositorio("clopezno");			
			
			String resultadoMetricas = lector.getMetricas("clopezno", new RepositoryId("clopezno","libre-gift"));
			String cadena = "Metricas:" +
							"\n  NumeroIssues: 12" +
							"\n  ContadorTareas: 4,00" +
							"\n  NumeroIssuesCerradas: 10" +
							"\n  PorcentajeIssuesCerradas: 83,00" +
							"\n  MediaDiasCierre: 0,00" +
							"\n  NumeroCambiosSinMensaje: 0" +
							"\n  MediaDiasCambio: 256,99" +
							"\n  DiasPrimerUltimoCommit: 770,98" +
							"\n  UltimaModificacion: Fri May 22 13:47:06 CEST 2015" +
							"\n  CommitPorMes: " +
							"\n\tEnero: 0.0" +
							"\n\tFebrero: 0.0" +
							"\n\tMarzo: 0.0" +
							"\n\tAbril: 2.0" +
							"\n\tMayo: 1.0" +
							"\n\tJunio: 0.0" +
							"\n\tJulio: 0.0" +
							"\n\tAgosto: 0.0" +
							"\n\tSeptiembre: 0.0" +
							"\n\tOctubre: 0.0" +
							"\n\tNoviembre: 0.0" +
							"\n\tDiciembre: 0.0" +
							"\n  RelacionMesPico: Abril" +
							"\n  ContadorCambiosPico: 0,67" +
							"\n  RatioActividadCambio: 0,12" +
							"\n  CommitPorDia: " +
							"\n\tDomingo: 0.0" +
							"\n\tLunes: 0.0" +
							"\n\tMartes: 0.0" +
							"\n\tMiercoles: 0.0" +
							"\n\tJueves: 2.0" +
							"\n\tViernes: 1.0" +
							"\n\tSabado: 0.0" +
							"\n  CambioPorAutor: " +
							"\n\tCarlos López: 1.0" +
							"\n\tclopezno@gmail.com: 1.0" +
							"\n\tnobody: 1.0" +
							"\n  ContadorAutor: 1,00" +
							"\n  IssuesPorAutor: " +
							"\n\tGoogleCodeExporter: 12.0" +
							"\n  NumeroWatchers: 0";
			
			assertEquals(cadena, resultadoMetricas);
			
			//probamos el repositorio jam0101/TFGII-Quiz-Grafos
			lector.getNombresRepositorio("jam0101");	
			
			resultadoMetricas = lector.getMetricas("jam0101", new RepositoryId("jam0101","TFGII-Quiz-Grafos"));
			cadena = "Metricas:" +
					"\n  NumeroIssues: 44" +
					"\n  ContadorTareas: 2,32" +
					"\n  NumeroIssuesCerradas: 43" +
					"\n  PorcentajeIssuesCerradas: 97,00" +
					"\n  MediaDiasCierre: 27,91" +
					"\n  NumeroCambiosSinMensaje: 0" +
					"\n  MediaDiasCambio: 6,89" +
					"\n  DiasPrimerUltimoCommit: 131,00" +
					"\n  UltimaModificacion: Thu Jul 09 12:01:45 CEST 2015" +
					"\n  CommitPorMes: " +
					"\n\tEnero: 0.0" +
					"\n\tFebrero: 1.0" +
					"\n\tMarzo: 0.0" +
					"\n\tAbril: 1.0" +
					"\n\tMayo: 4.0" +
					"\n\tJunio: 7.0" +
					"\n\tJulio: 6.0" +
					"\n\tAgosto: 0.0" +
					"\n\tSeptiembre: 0.0" +
					"\n\tOctubre: 0.0" +
					"\n\tNoviembre: 0.0" +
					"\n\tDiciembre: 0.0" +
					"\n  RelacionMesPico: Junio" +
					"\n  ContadorCambiosPico: 0,37" +
					"\n  RatioActividadCambio: 3,80" +
					"\n  CommitPorDia: " +
					"\n\tDomingo: 1.0" +
					"\n\tLunes: 3.0" +
					"\n\tMartes: 6.0" +
					"\n\tMiercoles: 2.0" +
					"\n\tJueves: 4.0" +
					"\n\tViernes: 0.0" +
					"\n\tSabado: 3.0" +
					"\n  CambioPorAutor: " +
					"\n\tjam0101: 19.0" +
					"\n  ContadorAutor: 0,05" +
					"\n  IssuesPorAutor: " +
					"\n\tclopezno: 7.0" +
					"\n\tjjrodriguez: 10.0" +
					"\n\tjam0101: 27.0" +
					"\n  NumeroWatchers: 1";
			
			assertEquals(cadena, resultadoMetricas);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}