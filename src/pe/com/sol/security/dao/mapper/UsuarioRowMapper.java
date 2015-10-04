
package pe.com.sol.security.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sol.portal.security.bean.Usuario;

/**
 * @author Eduardo Zuñiga
 *
 */
public class UsuarioRowMapper implements RowMapper<Usuario>{
	
	Usuario usuario;
	
	public Usuario mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		usuario = new Usuario();
		usuario.setId(rs.getInt(1));
		usuario.setUsuario(rs.getString(2));
		usuario.setPassword(rs.getString(3));
		usuario.setNombreCompleto(rs.getString(4));
						
		return usuario;
	}

}
