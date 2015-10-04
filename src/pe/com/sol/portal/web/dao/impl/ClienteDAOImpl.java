
package pe.com.sol.portal.web.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import pe.com.sol.constantes.Constantes;
import pe.com.sol.portal.web.bean.Cliente;
import pe.com.sol.portal.web.dao.ClienteDAO;
import pe.com.sol.portal.web.dao.mapper.ClienteRowMapper;

/**
 * @author Alonso Gutierrez
 *
 */
public class ClienteDAOImpl implements ClienteDAO {
	
	private static Logger logger = Logger.getLogger(ClienteDAOImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	private String query = "";
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void deleteCliente(int idCli) {
		// TODO Auto-generated method stub
		query = "delete from tb_cliente where idClie = ?";
		
		logger.info("SQL:" + query);
		jdbcTemplate.update(query, new Object[]{idCli});
	}

	public Cliente getCliente(int idCli) {
		// TODO Auto-generated method stub
		List<Cliente> lista = null;
		query = "select '1' as RN, p.* from tb_cliente p where idClie = ?";
		
		logger.info("SQL:" + query);
		
		lista = jdbcTemplate.query(query, new Object[]{idCli}, new ClienteRowMapper());
		
		if (!CollectionUtils.isEmpty(lista))
		{
			return lista.get(0);
		}
		
		return null;
	}

	public List<Cliente> getCliente(Cliente cliente, String tipoBusqueda) {
		// TODO Auto-generated method stub
		query = "select PN.* from(select @rownum:=@rownum+1 AS rownum, p.* from (SELECT @rownum:=0) r, tb_cliente p ";
		
		if (cliente != null)
		{
			String where = "where ";
			if (StringUtils.equals(tipoBusqueda, Constantes.BUSQUEDA_X_RAZONSOCIAL))
			{
				/**Busuqeda por nombre**/
				where = where + "razonSocial like '%" + cliente.getRazonSocial() + "%'";
				
			}else
			{	
				where = where + "numeroDocumento = " + cliente.getNumeroDocumentoId();
			}
			query = query + where;
		}
		
		query = query + ") PN where PN.rownum <= 200 order by PN.razonSocial asc ";
		
		
		logger.debug("SQL: " + query);
		
		return jdbcTemplate.query(query, new ClienteRowMapper());
	}

	public void updateCliente(Cliente cliente) {
		// TODO Auto-generated method stub

		if (cliente.getId() == 0)
		{
			query = "insert into tb_cliente(razonSocial,fonoMovil,fonoFijo,correo,descripcion,direccion,tipoDocumento,numeroDocumento)" + 
					"values(?,?,?,?,?,?,?,?)";
			
			logger.info("SQL: " + query);
			
			/**Ejecutando la sentencia**/
			jdbcTemplate.update(query, new Object[]{cliente.getRazonSocial(),
								cliente.getFonoMovil(), cliente.getFonoFijo(), 
								cliente.getEmail(), cliente.getDescripcion(),
								cliente.getDireccion(),cliente.getTipoDocumento(),
								cliente.getNumeroDocumentoId()});
			
		}else
		{
			query = "update tb_cliente set razonSocial = ?, fonoMovil = ?, fonoFijo = ?, correo = ?, descripcion = ?, direccion = ?, " +
					"tipoDocumento = ?, numeroDocumento = ? " +
					"where idClie = ?";
	
			logger.info("SQL: " + query);
			
			/**Ejecutando la sentencia**/
			jdbcTemplate.update(query, new Object[]{cliente.getRazonSocial(),
										cliente.getFonoMovil(), cliente.getFonoFijo(), 
										cliente.getEmail(), cliente.getDescripcion(),
										cliente.getDireccion(), cliente.getTipoDocumento(),
										cliente.getNumeroDocumentoId(),	cliente.getId()});
		}
		
	}
	
	

}
