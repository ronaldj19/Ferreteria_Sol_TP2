
package pe.com.sol.portal.web.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sol.portal.web.bean.Marca;

/**
 * @author Alonso Gutierrez
 *
 */
public class MarcaRowMapper implements RowMapper<Marca>{
	
	Marca marca;
	
	public Marca mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		marca = new Marca();
		
		marca.setId(rs.getInt(2));
		marca.setNombre(rs.getString(3));
		marca.setProcedencia(rs.getString(4));
		marca.setDescripcion(rs.getString(5));
				
		return marca;
	}

}
