
package pe.com.sol.portal.web.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import pe.com.sol.constantes.Constantes;
import pe.com.sol.portal.web.bean.Marca;
import pe.com.sol.portal.web.dao.MarcaProdDAO;
import pe.com.sol.portal.web.dao.mapper.MarcaRowMapper;

/**
 * @author Alonso Gutierrez
 *
 */
public class MarcaProdDAOImpl implements MarcaProdDAO {
	
	private static Logger logger = Logger.getLogger(MarcaProdDAOImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	private String query = "";
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void deleteMarca(int idMarca) {
		// TODO Auto-generated method stub
		query = "delete from tb_marcaproducto where idProd = ?";
		
		logger.info("SQL:" + query);
		jdbcTemplate.update(query, new Object[]{idMarca});
	}

	public Marca getMarca(int idMarca) {
		// TODO Auto-generated method stub
		List<Marca> lista = null;
		query = "select '1' as RN, p.* from tb_marcaproducto p where idMarc = ?";
		
		logger.info("SQL:" + query);
		
		lista = jdbcTemplate.query(query, new Object[]{idMarca}, new MarcaRowMapper());
		
		if (!CollectionUtils.isEmpty(lista))
		{
			return lista.get(0);
		}
		
		return null;
	}

	public List<Marca> getMarca(Marca marca, String tipoBusqueda) {
		// TODO Auto-generated method stub
		query = "select PN.* from(select @rownum:=@rownum+1 AS rownum, p.* from (SELECT @rownum:=0) r, tb_marcaproducto p ";
		
		if (marca != null)
		{
			String where = "where ";
			if (StringUtils.equals(tipoBusqueda, Constantes.BUSQUEDA_X_CODIGO))
			{
				where = where + "nombre like '%" + marca.getNombre() + "%'";
			}else
			{	/**Busuqeda por nombre**/
				where = where + "procedencia like '%" + marca.getProcedencia() + "%'";
			}
			query = query + where;
		}
		
		query = query + ") PN where PN.idMarc <> 1 and PN.rownum <= 20 ";
		
		
		logger.debug("SQL: " + query);
		
		return jdbcTemplate.query(query, new MarcaRowMapper());
	}

	public void updateMarca(Marca marca) {
		// TODO Auto-generated method stub

		if (marca.getId() == 0)
		{
			query = "insert into tb_marcaproducto(nombre,procedencia,descripcion)" + 
					"values(?,?,?)";
			
			logger.info("SQL: " + query);
			
			/**Ejecutando la sentencia**/
			jdbcTemplate.update(query, new Object[]{marca.getNombre(), marca.getProcedencia(),
					marca.getDescripcion()});
			
		}else
		{
			query = "update tb_marcaproducto set nombre = ?, procedencia = ?, descripcion = ? " +
					"where idMarc = ?";
	
			logger.info("SQL: " + query);
			
			/**Ejecutando la sentencia**/
			jdbcTemplate.update(query, new Object[]{marca.getNombre(), marca.getProcedencia(),
					marca.getDescripcion(), marca.getId()});
		}
		
	}
	
	

}
