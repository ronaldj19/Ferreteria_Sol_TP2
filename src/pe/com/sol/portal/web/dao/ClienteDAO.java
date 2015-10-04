
package pe.com.sol.portal.web.dao;

import java.util.List;

import pe.com.sol.portal.web.bean.Cliente;

/**
 * @author Alonso Gutierrez
 * 
 */
public interface ClienteDAO {
	
	public Cliente getCliente(int idCli);
	
	public List<Cliente> getCliente(Cliente cliente, String tipoBusqueda);
	
	public void updateCliente(Cliente cliente);
	
	public void deleteCliente(int idCli);

}
