
package pe.com.sol.portal.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import pe.com.sol.constantes.Constantes;
import pe.com.sol.portal.session.UserSessionBean;
import pe.com.sol.portal.web.bean.Parametro;
import pe.com.sol.portal.web.service.ParametroService;

/**
 * @author Alonso Gutierrez
 * 
 */
public class ParametroController extends MultiActionController{
	
	private static Logger logger = Logger.getLogger(ParametroController.class);
		
	UserSessionBean userSessionBean;
	ParametroService parametroService; 
		
	public ModelAndView iniciar(HttpServletRequest request, 
			HttpServletResponse response) throws Exception
	{
		logger.debug("=================Parametro Sistema Init===============");
		
		ModelAndView model = new ModelAndView(Constantes.JSP_INIT_PARAMETRO);
		String igv = "";
		String tipoCambio = "";
		
		try {
			
			List<Parametro> lstParametro = parametroService.getParametro(null);
			
			logger.debug("ListaParametros: " + lstParametro.size());
			
			if (!CollectionUtils.isEmpty(lstParametro))
			{
				igv = parametroService.getParametroCodigoValor(lstParametro, Constantes.PARAMETER_IGV, true);
				tipoCambio = parametroService.getParametroCodigoValor(lstParametro, Constantes.PARAMETER_TIPO_CAMBIO, true);
				
				userSessionBean.put("ListaParametro", lstParametro);
			}else
			{
				model.addObject("mensaje", "(!) No se encontro parametros en el sistema. ");
				model.addObject("mensaje1", "(!) Revisar la tabla parametros. ");
			}
			
			String msgVal = (String) request.getAttribute("commit");
			
			if(!StringUtils.isEmpty(msgVal))
			{
				model.addObject("mensaje", "(!) Se guardó correctamente los valores. ");
			}
		
			model.addObject("igv", igv);
			model.addObject("tipoCambio", tipoCambio);
			model.addObject(Constantes.TITULO, "ACTUALIZAR PARAMETROS DEL SISTEMA");
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
		}
				
		return model;
	}
	
	public ModelAndView saveParametro(HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		logger.debug("================= Save Parametro Sistema===============");
		
		String igv = request.getParameter("pigv");
		String tipoCambio = request.getParameter("ptipocambio");
		Parametro parametro = null;
		try {
			
			logger.debug("IGV: " + igv);
			logger.debug("Tipo Cambio: " + tipoCambio);
			
			List<Parametro> lstParametro = new ArrayList<Parametro>(),
			lstParametroSesion = (List<Parametro>) userSessionBean.get("ListaParametro");
			
			//parametros para el IGV
			parametro = new Parametro();
			parametro.setCodigo(parametroService.getParametroCodigoValor(lstParametroSesion, Constantes.PARAMETER_IGV, false));
			parametro.setNombre(Constantes.PARAMETER_IGV);
			parametro.setValor(igv);
			lstParametro.add(parametro);
			
			//parametros para el TIPO de Cambio
			parametro = new Parametro();
			parametro.setCodigo(parametroService.getParametroCodigoValor(lstParametroSesion, Constantes.PARAMETER_TIPO_CAMBIO, false));
			parametro.setNombre(Constantes.PARAMETER_TIPO_CAMBIO);
			parametro.setValor(tipoCambio);
			lstParametro.add(parametro);
									
			parametroService.updateParametro(lstParametro);
			userSessionBean.remove("ListaParametro");
			
			request.setAttribute("commit", "1");
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
		}
		
		return iniciar(request, response);
		
	}
	
	
	/**
	 * @param userSessionBean the userSessionBean to set
	 */
	public void setUserSessionBean(UserSessionBean userSessionBean) {
		this.userSessionBean = userSessionBean;
	}

	/**
	 * @param parametroService the parametroService to set
	 */
	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}
	
	
}
