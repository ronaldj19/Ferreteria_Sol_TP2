package pe.com.sol.portal.web.dao;

import java.util.List;

import pe.com.sol.portal.web.bean.Parametro;

public interface ParametroDAO {
	
	public List<Parametro> getParametro(Parametro parametro);
	
	public String getParametroValor(String nombreParametro);
	
	public void updateParametro(Parametro parametro);

}
