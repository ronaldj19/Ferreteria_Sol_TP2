
package pe.com.sol.portal.web.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import pe.com.sol.constantes.Constantes;
import pe.com.sol.portal.web.bean.Parametro;
import pe.com.sol.portal.web.bean.Producto;
import pe.com.sol.portal.web.dao.ProductoDAO;
import pe.com.sol.portal.web.dao.mapper.ParametroRowMapper;
import pe.com.sol.portal.web.dao.mapper.ProductoRowMapper;

/**
 * @author Alonso Gutierrez
 *
 */
public class ProductoDAOImpl implements ProductoDAO {
	
	private static Logger logger = Logger.getLogger(ProductoDAOImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	private String query = "";
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void deleteProducto(int idProd) {
		// TODO Auto-generated method stub
		query = "delete from tb_producto where idProd = ?";
		
		logger.info("SQL:" + query);
		jdbcTemplate.update(query, new Object[]{idProd});
	}

	public Producto getProducto(int idProd) {
		// TODO Auto-generated method stub
		List<Producto> lista = null;
		query = "select '1' as RN, p.*, '2' as Marca from tb_producto p where idProd = ?";
		
		logger.info("SQL:" + query);
		
		lista = jdbcTemplate.query(query, new Object[]{idProd}, new ProductoRowMapper());
		
		if (!CollectionUtils.isEmpty(lista))
		{
			return lista.get(0);
		}
		
		return null;
	}

	public List<Producto> getProducto(Producto producto, String tipoBusqueda, int registros) {
		// TODO Auto-generated method stub
		query = "select PN.* from(select @rownum:=@rownum+1 AS rownum, p.*, m.nombre as nombremarca from (SELECT @rownum:=0) r, tb_producto p, tb_marcaproducto m ";
		String where = "where p.idMarc = m.idMarc ";
		
		if (producto != null)
		{
			
			if (StringUtils.equals(tipoBusqueda, Constantes.BUSQUEDA_X_CODIGO))
			{
				where = where + " and p.codigo = '" + producto.getCodigo().toUpperCase() + "'";
			}else
			{	/**Busuqeda por nombre**/
				where = where + " and p.nombre like '%" + producto.getNombre().toLowerCase() + "%'";
			}
		}
		
		query = query + where;
		query = query + ") PN ";
		
		if (registros != 0)
			query = query + "where PN.rownum <= " + registros;
		
		
		logger.debug("SQL: " + query);
		
		return jdbcTemplate.query(query, new ProductoRowMapper());
	}
	
	public List<Producto> getFilterProducto(String nombre) {
		// TODO Auto-generated method stub
		query = "select PN.* from(select @rownum:=@rownum+1 AS rownum, p.*, m.nombre as nombremarca from (SELECT @rownum:=0) r, tb_producto p, tb_marcaproducto m ";
		String where = "where p.idMarc = m.idMarc ";
		
		where = where + " and p.nombre like '%" + nombre.toLowerCase() + "%'";
				
		query = query + where;
		query = query + ") PN ";
		
		query = query + "where PN.rownum <= " + 10;
		
		
		logger.debug("SQL: " + query);
		
		return jdbcTemplate.query(query, new ProductoRowMapper());
	}

	public void updateProducto(Producto producto) {
		// TODO Auto-generated method stub
		
		if (producto.getId() == 0)
		{
			query = "insert into tb_producto(codigo,nombre,descripcion,precioVenta,precioCompra,stock,foto,idMarc)" + 
					"values(?,?,?,?,?,?,?,?)";
			
			logger.info("SQL: " + query);
			
			/**Ejecutando la sentencia**/
			jdbcTemplate.update(query, new Object[]{producto.getCodigo().toUpperCase(),
					producto.getNombre().toLowerCase(),
					producto.getDescripcion().toLowerCase(),
					producto.getPrecioVenta(),
					producto.getPrecioCompra(),
					producto.getStock(),
					producto.getFile(),
					producto.getMarca().getId()==0?1:producto.getMarca().getId()});
			
		}else
		{
			query = "update tb_producto set nombre = ?, descripcion = ?, precioVenta = ?, " +
					"precioCompra = ?, stock = ?, foto = ?, idMarc = ? " +
					"where idProd = ?";
	
			logger.info("SQL: " + query);
			
			/**Ejecutando la sentencia**/
			jdbcTemplate.update(query, new Object[]{producto.getNombre().toLowerCase(),
					producto.getDescripcion().toLowerCase(),
					producto.getPrecioVenta(),
					producto.getPrecioCompra(),
					producto.getStock(),
					producto.getFile(), 
					producto.getMarca().getId()==0?1:producto.getMarca().getId(),
					producto.getId()});
		}
		
	}

	public List<Producto> getProductoABC(String nombre, String inicio,
			String fin) {
		// TODO Auto-generated method stub
		query = "SELECT PN.*, '1' as Marca FROM(" +
		"SELECT @rownum:=@rownum+1 AS rownum, p.* " +
		"FROM (SELECT @rownum:=0) r, tb_producto p where nombre like '%" + nombre + "%') PN ";
		String where = "";		
		
		if (!StringUtils.isEmpty(inicio))
		{
			where = "where PN.rownum BETWEEN " + inicio + " and " + fin;
		}
		
		query = query + where;
		
		logger.info("SQL:" + query);
		
		return jdbcTemplate.query(query, new ProductoRowMapper());
	}

	public List<Parametro> getPais() {
		// TODO Auto-generated method stub
		query = "select idPais + '', codigo, nombre from tb_pais";
		
		return jdbcTemplate.query(query, new ParametroRowMapper());
	}

	public String getCodigoProducto() {
		// TODO Auto-generated method stub
		query = "select max(idProd) from tb_producto";
		int codProducto = jdbcTemplate.queryForInt(query);
		
		return "" + (codProducto+1);
	}
	
	

}
