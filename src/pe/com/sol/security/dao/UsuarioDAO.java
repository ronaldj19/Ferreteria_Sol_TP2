
package pe.com.sol.security.dao;

import java.util.List;

import pe.com.sol.portal.security.bean.Usuario;

/**
 * @author Rosales Aponte Fausta
 * 
 */
public interface UsuarioDAO {
	
	public Usuario getUsuario(String usuario, String password);
	
	public List<Usuario> getUsuario(Usuario usuario);
	
	public String getParameterSecurity(String codigo, String nombre);
	
	public void updateUsuario(Usuario usuario);
		
	public void updatePassword(Usuario usuario);
	
	public void updateSecurity(String valor, String valGen);

}
