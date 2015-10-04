
package pe.com.sol.portal.web.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sol.portal.web.bean.Producto;

/**
 * @author Alonso Gutierrez
 *
 */
public class ProductoRowMapper implements RowMapper<Producto>{
	
	Producto producto;
	
	public Producto mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		producto = new Producto();
		
		producto.setId(rs.getInt(2));
		producto.setCodigo(rs.getString(3));
		producto.setNombre(rs.getString(4));
		producto.setDescripcion(rs.getString(5));
		producto.setPrecioVenta(rs.getDouble(6));
		producto.setPrecioCompra(rs.getDouble(7));
		producto.setStock(rs.getInt(8));
		producto.setFiles(rs.getBlob(9));
		producto.getMarca().setId(rs.getInt(10));
		producto.getMarca().setNombre(rs.getString(11));		
		return producto;
	}

}
