
package pe.com.sol.portal.web.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sol.portal.web.bean.Parametro;

/**
 * @author Alonso Gutierrez
 *
 */
public class ParametroRowMapper implements RowMapper<Parametro>{
	
	Parametro parametro;
	
	public Parametro mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		parametro = new Parametro();
		parametro.setCodigo(rs.getString(1));
		parametro.setNombre(rs.getString(2));
		parametro.setValor(rs.getString(3));
		return parametro;
	}

}
