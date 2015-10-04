
package pe.com.sol.portal.web.service;

import java.util.List;

import pe.com.sol.portal.web.bean.Parametro;
import pe.com.sol.portal.web.bean.Producto;

/**
 * @author Ronald Bautista Morales
 * 
 */
public interface ProductoService {
	
	public Producto getProducto(int idProd);
	
	public List<Producto> getProducto(Producto producto, String tipoBusqueda, int registros);
	
	public List<Producto> getFilterProducto(String nombre);
	
	public void updateProducto(Producto producto);
	
	public void deleteProducto(int idProd);
	
	public List<Producto> getProductoABC(String nombre, String inicio, String fin);
	
	public List<Parametro> getPais();
	
	public String getCodigoProducto();

}
