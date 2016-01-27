package test;

import java.io.IOException;
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
			
			lector.obtenerMetricas("clopezno", "libre-gift");
			Object[] resultadoMetricas = lector.getResultados();
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
							"\n\tEnero: 0" +
							"\n\tFebrero: 0" +
							"\n\tMarzo: 0" +
							"\n\tAbril: 2" +
							"\n\tMayo: 1" +
							"\n\tJunio: 0" +
							"\n\tJulio: 0" +
							"\n\tAgosto: 0" +
							"\n\tSeptiembre: 0" +
							"\n\tOctubre: 0" +
							"\n\tNoviembre: 0" +
							"\n\tDiciembre: 0" +
							"\n  RelacionMesPico: Abril" +
							"\n  ContadorCambiosPico: 0,67" +
							"\n  RatioActividadCambio: 0,12" +
							"\n  CommitPorDia: " +
							"\n\tDomingo: 0" +
							"\n\tLunes: 0" +
							"\n\tMartes: 0" +
							"\n\tMiercoles: 0" +
							"\n\tJueves: 2" +
							"\n\tViernes: 1" +
							"\n\tSabado: 0" +
							"\n  CambioPorAutor: " +
							"\n\tCarlos López: 1" +
							"\n\tclopezno@gmail.com: 1" +
							"\n\tnobody: 1" +
							"\n  ContadorAutor: 1,00" +
							"\n  IssuesPorAutor: " +
							"\n\tGoogleCodeExporter: 12" +
							"\n  NumeroFavoritos: 0";
			
			assertEquals(cadena, resultadoMetricas[0]);
			
			//probamos el repositorio jam0101/TFGII-Quiz-Grafos
			lector.getNombresRepositorio("jam0101");	
			
			lector.obtenerMetricas("jam0101","TFGII-Quiz-Grafos");
			resultadoMetricas = lector.getResultados();
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
					"\n\tEnero: 0" +
					"\n\tFebrero: 1" +
					"\n\tMarzo: 0" +
					"\n\tAbril: 1" +
					"\n\tMayo: 4" +
					"\n\tJunio: 7" +
					"\n\tJulio: 6" +
					"\n\tAgosto: 0" +
					"\n\tSeptiembre: 0" +
					"\n\tOctubre: 0" +
					"\n\tNoviembre: 0" +
					"\n\tDiciembre: 0" +
					"\n  RelacionMesPico: Junio" +
					"\n  ContadorCambiosPico: 0,37" +
					"\n  RatioActividadCambio: 3,80" +
					"\n  CommitPorDia: " +
					"\n\tDomingo: 1" +
					"\n\tLunes: 3" +
					"\n\tMartes: 6" +
					"\n\tMiercoles: 2" +
					"\n\tJueves: 4" +
					"\n\tViernes: 0" +
					"\n\tSabado: 3" +
					"\n  CambioPorAutor: " +
					"\n\tjam0101: 19" +
					"\n  ContadorAutor: 0,05" +
					"\n  IssuesPorAutor: " +
					"\n\tclopezno: 7" +
					"\n\tjjrodriguez: 10" +
					"\n\tjam0101: 27" +
					"\n  NumeroFavoritos: 1";
			
			assertEquals(cadena, resultadoMetricas[0]);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}