
package pe.com.sol.portal.web.service.impl;

import java.util.List;

import pe.com.sol.portal.web.bean.Marca;
import pe.com.sol.portal.web.dao.MarcaProdDAO;
import pe.com.sol.portal.web.service.MarcaProdService;

/**
 * @author Rosales Aponte Fausta
 *
 */
public class MarcaProdServiceImpl implements MarcaProdService {
	
	MarcaProdDAO marcaDao;
	
	/**
	 * @param marcaDao the marcaDao to set
	 */
	public void setMarcaDao(MarcaProdDAO marcaDao) {
		this.marcaDao = marcaDao;
	}

	public void deleteMarca(int idMarca) {
		// TODO Auto-generated method stub
		
	}

	public Marca getMarca(int idMarca) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Marca> getMarca(Marca marca, String tipoBusqueda) {
		// TODO Auto-generated method stub
		return marcaDao.getMarca(marca, tipoBusqueda);
	}

	public void updateMarca(Marca marca) {
		// TODO Auto-generated method stub
		marcaDao.updateMarca(marca);
	}
	
	
}
