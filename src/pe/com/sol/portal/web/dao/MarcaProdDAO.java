
package pe.com.sol.portal.web.dao;

import java.util.List;

import pe.com.sol.portal.web.bean.Marca;

/**
 * @author Alonso Gutierrez
 * 
 */
public interface MarcaProdDAO {
	
	public Marca getMarca(int idMarca);
	
	public List<Marca> getMarca(Marca marca, String tipoBusqueda);
	
	public void updateMarca(Marca marca);
	
	public void deleteMarca(int idMarca);

}
