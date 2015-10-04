
package pe.com.sol.portal.web.dao;

import java.util.List;

import pe.com.sol.portal.web.bean.Parametro;
import pe.com.sol.portal.web.bean.Producto;

/**
 * @author Alonso Gutierrez
 * 
 */
public interface ProductoDAO {
	
	public Producto getProducto(int idProd);
	
	public List<Producto> getProducto(Producto producto, String tipoBusqueda, int registros);
	
	public List<Producto> getFilterProducto(String nombre);
	
	public void updateProducto(Producto producto);
	
	public void deleteProducto(int idProd);
	
	public List<Producto> getProductoABC(String apellido, String inicio, String fin);
	
	public List<Parametro> getPais();
	
	public String getCodigoProducto();

}
