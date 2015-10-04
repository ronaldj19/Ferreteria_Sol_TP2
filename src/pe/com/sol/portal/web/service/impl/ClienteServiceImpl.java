
package pe.com.sol.portal.web.service.impl;

import java.util.List;

import pe.com.sol.portal.web.bean.Cliente;
import pe.com.sol.portal.web.dao.ClienteDAO;
import pe.com.sol.portal.web.service.ClienteService;

/**
 * @author Rosales Aponte Fausta
 *
 */
public class ClienteServiceImpl implements ClienteService {
	
	ClienteDAO clienteDao;
		
	public void deleteCliente(int idCli) {
		// TODO Auto-generated method stub
		clienteDao.deleteCliente(idCli);
	}

	public Cliente getCliente(int idCli) {
		// TODO Auto-generated method stub
		return clienteDao.getCliente(idCli);
	}

	public List<Cliente> getCliente(Cliente cliente, String tipoBusqueda) {
		// TODO Auto-generated method stub
		return clienteDao.getCliente(cliente, tipoBusqueda);
	}

	public void updateCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteDao.updateCliente(cliente);
	}
	
	/**
	 * @param clienteDao the clienteDao to set
	 */
	public void setClienteDao(ClienteDAO clienteDao) {
		this.clienteDao = clienteDao;
	}

}
