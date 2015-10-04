
package pe.com.sol.security.service;

import java.util.List;

import pe.com.sol.portal.security.bean.Usuario;

/**
 * @author Eduardo Zuñiga
 * 
 */
public interface UsuarioService {
	
	public Usuario getUsuario(String usuario, String password);
	
	public List<Usuario> getUsuario(Usuario usuario);
	
	public String getParameterSecurity(String codigo, String nombre);
	
	public void updateUsuario(Usuario usuario);
	
	public void updatePassword(Usuario usuario);
	
	public void updateSecurity(String valor);

}
