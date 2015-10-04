
package pe.com.sol.portal.web.service;

import java.util.List;

import pe.com.sol.portal.web.bean.Cliente;

/**
 * @author Ronald Bautista Morales
 * 
 */
public interface ClienteService {
	
	public Cliente getCliente(int idCli);
	
	public List<Cliente> getCliente(Cliente cliente, String tipoBusqueda);
	
	public void updateCliente(Cliente cliente);
	
	public void deleteCliente(int idCli);

}
