
package pe.com.sol.portal.web.service;

import java.util.List;

import pe.com.sol.portal.web.bean.Marca;

/**
 * @author Ronald Bautista Morales
 * 
 */
public interface MarcaProdService {
	
	public Marca getMarca(int idMarca);
	
	public List<Marca> getMarca(Marca marca, String tipoBusqueda);
	
	public void updateMarca(Marca marca);
	
	public void deleteMarca(int idMarca);

}
