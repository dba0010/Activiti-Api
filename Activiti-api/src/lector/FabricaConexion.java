package lector;

import java.io.IOException;

/**
 * Interface de la fabrica abstracta.
 * @author David Blanco Alonso
 */
public interface FabricaConexion
{	
	/**
	 * Crea una fachada conexion sin autenticar.
	 * @return FachadaConexion fachada generada.
	 */
	public FachadaConexion crearFachadaConexion();
	
	/**
	 * crea una fachada conexion autenticandola mediante usuario y contraseña.
	 * @param usuario String usuario para realizar la autenticacion.
	 * @param password String contraseña para validar el usuario durante la utenticacion.
	 * @return FachadaConexion fachada generada.
	 * @throws IOException
	 */
	public FachadaConexion crearFachadaConexion(String usuario, String password) throws IOException;
}