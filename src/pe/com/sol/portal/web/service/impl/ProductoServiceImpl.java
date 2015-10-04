
package pe.com.sol.portal.web.service.impl;

import java.util.List;

import pe.com.sol.portal.web.bean.Parametro;
import pe.com.sol.portal.web.bean.Producto;
import pe.com.sol.portal.web.dao.ProductoDAO;
import pe.com.sol.portal.web.service.ProductoService;

/**
 * @author Rosales Aponte Fausta
 *
 */
public class ProductoServiceImpl implements ProductoService {
	
	ProductoDAO productoDao;
		
	public void deleteProducto(int idProd) {
		// TODO Auto-generated method stub
		productoDao.deleteProducto(idProd);
	}

	public Producto getProducto(int idProd) {
		// TODO Auto-generated method stub
		return productoDao.getProducto(idProd);
	}

	public List<Producto> getProducto(Producto producto, String tipoBusqueda, int registros) {
		// TODO Auto-generated method stub
		return productoDao.getProducto(producto, tipoBusqueda, registros);
	}
	
	public List<Producto> getFilterProducto(String nombre) {
		// TODO Auto-generated method stub
		return productoDao.getFilterProducto(nombre);
	}

	public void updateProducto(Producto producto) {
		// TODO Auto-generated method stub
		productoDao.updateProducto(producto);
	}
	
	/**
	 * @param productoDao the productoDao to set
	 */
	public void setProductoDao(ProductoDAO productoDao) {
		this.productoDao = productoDao;
	}

	public List<Producto> getProductoABC(String nombre, String inicio,
			String fin) {
		// TODO Auto-generated method stub
		return productoDao.getProductoABC(nombre, inicio, fin);
	}

	public List<Parametro> getPais() {
		// TODO Auto-generated method stub
		return productoDao.getPais();
	}

	public String getCodigoProducto() {
		// TODO Auto-generated method stub
		return productoDao.getCodigoProducto();
	}

}
