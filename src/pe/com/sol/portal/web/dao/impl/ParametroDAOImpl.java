
package pe.com.sol.portal.web.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import pe.com.sol.portal.web.bean.Parametro;
import pe.com.sol.portal.web.dao.ParametroDAO;
import pe.com.sol.portal.web.dao.mapper.ParametroRowMapper;

/**
 * @author Alonso Gutierrez
 *
 */
public class ParametroDAOImpl implements ParametroDAO {
	
	private static Logger logger = Logger.getLogger(ParametroDAOImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	private String query = "";
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Parametro> getParametro(Parametro parametro) {
		// TODO Auto-generated method stub
		query = "select * from tb_parametro";
		
		logger.debug("SQL: " + query);
		
		return jdbcTemplate.query(query, new ParametroRowMapper());
	}

	public void updateParametro(Parametro parametro) {
		// TODO Auto-generated method stub
		query = "update tb_parametro set valor = ? where codigo = ? and nombre = ?";
		
		logger.debug("SQL: " + query);
		logger.debug("Codigo: " + parametro.getCodigo());
		logger.debug("Nombre: " + parametro.getNombre());
		logger.debug("valor: " + parametro.getValor());
		
		jdbcTemplate.update(query, new Object[]{parametro.getValor(),
												parametro.getCodigo(),
												parametro.getNombre()});
		
	}

	public String getParametroValor(String nombreParametro) {
		// TODO Auto-generated method stub
		String codigo = "";
		
		query = "select valor from tb_parametro where nombre = '" + nombreParametro + "'";
		
		logger.debug("SQL: " + query);
		
		codigo = jdbcTemplate.queryForObject(query, String.class);
		
		return codigo;
	}

}
