
package pe.com.sol.portal.web.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sol.portal.web.bean.Cliente;

/**
 * @author Alonso Gutierrez
 *
 */
public class ClienteRowMapper implements RowMapper<Cliente>{
	
	Cliente cliente;
	
	public Cliente mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		cliente = new Cliente();
		
		cliente.setId(rs.getInt(2));
		cliente.setRazonSocial(rs.getString(3));
		cliente.setFonoMovil(rs.getString(4));
		cliente.setFonoFijo(rs.getString(5));
		cliente.setEmail(rs.getString(6));
		cliente.setDescripcion(rs.getString(7));
		cliente.setDireccion(rs.getString(8));
		cliente.setTipoDocumento(rs.getString(9));
		cliente.setNumeroDocumentoId(rs.getString(10));
		
		return cliente;
	}

}
