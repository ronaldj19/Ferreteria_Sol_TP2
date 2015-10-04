
package pe.com.sol.portal.web.service;

import java.util.List;

import pe.com.sol.portal.web.bean.Parametro;

/**
 * @author Ronald Bautista Morales
 * 
 */
public interface ParametroService {
	
	public List<Parametro> getParametro(Parametro parametro);
	
	public String getParametroCodigoValor(List<Parametro> lstParametro, String nombreParametro, boolean flagValor);
	
	public String getParametroValor(String nombreParametro);
	
	public void updateParametro(List<Parametro> lstParametro);

}
