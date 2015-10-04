
package pe.com.sol.portal.web.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import pe.com.sol.portal.web.bean.Parametro;
import pe.com.sol.portal.web.dao.ParametroDAO;
import pe.com.sol.portal.web.service.ParametroService;

/**
 * @author Rosales Aponte Fausta
 *
 */
public class ParametroServiceImpl implements ParametroService {

	ParametroDAO parametroDao;
	
	public List<Parametro> getParametro(Parametro parametro) {
		// TODO Auto-generated method stub
		return parametroDao.getParametro(parametro);
	}

	public void updateParametro(List<Parametro> lstParametro) {
		// TODO Auto-generated method stub
		
		for (Iterator iterator = lstParametro.iterator(); iterator.hasNext();) {
			Parametro parametro = (Parametro) iterator.next();
			parametroDao.updateParametro(parametro);
		}
	}
	
	public String getParametroValor(String nombreParametro) {
		// TODO Auto-generated method stub
		return parametroDao.getParametroValor(nombreParametro);
	}	
	
	public String getParametroCodigoValor(List<Parametro> lstParametro, String nombreParametro, boolean flagValor) {
		// TODO Auto-generated method stub
		
		for (Iterator iterator = lstParametro.iterator(); iterator.hasNext();) {
			Parametro parametro = (Parametro) iterator.next();
						
			if (StringUtils.equals(nombreParametro, parametro.getNombre()))
			{
				if (flagValor)
				{
					return parametro.getValor();
				}else
				{
					return parametro.getCodigo();
				}
			}			
		}
		
		return null;
	}

	/**
	 * @param parametroDao the parametroDao to set
	 */
	public void setParametroDao(ParametroDAO parametroDao) {
		this.parametroDao = parametroDao;
	}
}
