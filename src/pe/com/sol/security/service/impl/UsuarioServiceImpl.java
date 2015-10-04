
package pe.com.sol.security.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import pe.com.sol.portal.security.bean.Usuario;
import pe.com.sol.security.dao.UsuarioDAO;
import pe.com.sol.security.service.UsuarioService;

/**
 * @author Eduardo Zuñiga
 *
 */
public class UsuarioServiceImpl implements UsuarioService {
	
	UsuarioDAO usuarioDao;
	
	public void updatePassword(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioDao.updatePassword(usuario);
	}
	
	public void updateUsuario(Usuario usuario){
		// TODO Auto-generated method stub
		usuarioDao.updateUsuario(usuario);
	}
	
	public Usuario getUsuario(String usuario, String password) {
		// TODO Auto-generated method stub
		return usuarioDao.getUsuario(usuario, password);
	}
	
	public List<Usuario> getUsuario(Usuario usuario){
		// TODO Auto-generated method stub
		return usuarioDao.getUsuario(usuario);
	}
	
	public String getParameterSecurity(String codigo, String nombre){
		// TODO Auto-generated method stub
		return usuarioDao.getParameterSecurity(codigo, nombre);
	}
	
	public void updateSecurity(String valor){
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhh");
		Calendar cal = Calendar.getInstance();
		String str = sdf.format(cal.getTime());
		String valGen = str.substring(5, 10) + str.substring(0, 5);
		
		usuarioDao.updateSecurity(valor,valGen);
	}
			
	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}
