
package pe.com.sol.security.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import pe.com.sol.constantes.Constantes;
import pe.com.sol.portal.security.bean.Usuario;
import pe.com.sol.security.dao.UsuarioDAO;
import pe.com.sol.security.dao.mapper.UsuarioRowMapper;

/**
 * @author Rosales Aponte Fausta
 * 
 */
public class UsuarioDAOImpl implements UsuarioDAO {
	
	private static Logger logger = Logger.getLogger(UsuarioDAOImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	private String query = "";
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
		
	public void updatePassword(Usuario usuario) {
		// TODO Auto-generated method stub
		
		logger.info("Usuario: " + usuario.getUsuario());
				
		/**Armando el sql para cambiar el password**/
		query = "update tb_usuario set clave = ? " +
				"where usuario = ?";
		
		logger.info("SQL: " + query);
		
		/**Ejecutando la sentencia**/
		jdbcTemplate.update(query, new Object[]{usuario.getPassword(),usuario.getUsuario()});
		
	}
	
	public void updateUsuario(Usuario usuario){
		// TODO Auto-generated method stub
		
		/**Armando el sql para cambiar el password**/
		query = "insert into tb_usuario(usuario,clave,nombreCompleto)" +
				"values(?,?,?)";
		
		logger.info("SQL: " + query);
		
		/**Ejecutando la sentencia**/
		jdbcTemplate.update(query, new Object[]{usuario.getUsuario(),usuario.getPassword(),
												usuario.getNombreCompleto()});
	}

	public Usuario getUsuario(String usuario, String password) {
		// TODO Auto-generated method stub
		
		logger.debug("===Validando Usuario===");
		Usuario user = null;
		List<Usuario> list = new ArrayList<Usuario>();
		
		/**Armando el sql para obtener al usuario logeado**/
		query = "select us.* from tb_usuario us " +
				"where usuario=? and clave=?";
		
		logger.info("SQL: " + query);
		
		/**Ejecutando la sentencia**/
		list = (List<Usuario>)jdbcTemplate.query(query, new Object[]{usuario,password}, new UsuarioRowMapper());
		
		if (!CollectionUtils.isEmpty(list))
		{
			user = list.get(0);
		}
		
		return user;
	}
	
	public List<Usuario> getUsuario(Usuario usuario){
		// TODO Auto-generated method stub
		
		/**Armando el sql para cambiar el password**/
		query = "select * from tb_usuario";
		
		/**Ejecutando la sentencia**/
		return jdbcTemplate.query(query, new UsuarioRowMapper());
	}
	
	public String getParameterSecurity(String codigo, String nombre){
		// TODO Auto-generated method stub
		
		/**Armando el sql para cambiar el password**/
		query = "select valor from tb_parametro where codigo = '" + codigo + "'" + 
				" and nombre = '" + nombre + "'";
		
		/**Ejecutando la sentencia**/
		return jdbcTemplate.queryForObject(query, String.class);
		
	}
	
	public void updateSecurity(String valor, String valGen){
		// TODO Auto-generated method stub
		
		/**Armando el sql para cambiar el password**/
		query = "update tb_parametro set valor = ? " +
				"where codigo = ? and nombre = ? ";
		
		/**Ejecutando la sentencia**/
		jdbcTemplate.update(query, new Object[]{valor, Constantes.SECURITY_CODE0, 
							Constantes.SECURITY_NAME});
		
		/**Ejecutando la sentencia**/
		jdbcTemplate.update(query, new Object[]{Constantes.SECURITY_FLAG, Constantes.SECURITY_CODE1, 
							Constantes.SECURITY_NAME});
		
		query = "insert into tb_parametro(codigo,nombre,valor)" + 
				"values(?,?,?)";
		
		/**Ejecutando la sentencia**/
		jdbcTemplate.update(query, new Object[]{Constantes.SECURITY_CODE2, Constantes.SECURITY_NAME, 
							valGen});
		
	}
}
