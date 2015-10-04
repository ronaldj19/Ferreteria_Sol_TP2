
 
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
import pe.com.sol.portal.web.bean.Cliente;
import pe.com.sol.portal.web.form.ClienteForm;
import pe.com.sol.portal.web.service.ClienteService;

/**
 * @author Alonso Gutierrez
 * 
 */
public class ClienteController extends MultiActionController{
	
	private static Logger logger = Logger.getLogger(ClienteController.class);
	
	private static final String UPDATE_CLIENTE = "update_cliente";
	
	private static final String TITULO_NEW_CLIENTE = "AGREGAR UN NUEVO CLIENTE";
	
	private static final String TITULO_EDIT_CLIENTE = "ACTUALIZAR CLIENTE";
	
	UserSessionBean userSessionBean;
	ClienteService clienteService; 
		
	public ModelAndView iniciar(HttpServletRequest request, 
			HttpServletResponse response) throws Exception
	{
		logger.debug("=================Cliente Init===============");
		
		ModelAndView model = new ModelAndView(Constantes.JSP_INIT_CLIENTE);
		ClienteForm clienteForm = new ClienteForm();
		List<Cliente> lstCliente = new ArrayList<Cliente>();
		Cliente cliente = (Cliente)request.getAttribute("clienteBean");
		String tipoBusqueda = "";
		try {
			
			clienteForm.setTipoBusqueda(Constantes.BUSQUEDA_X_DOCUMENTO);
			
			if(cliente != null)
			{
				tipoBusqueda = (String)request.getAttribute("tipoBusqueda");
			}
			
			lstCliente = clienteService.getCliente(cliente, tipoBusqueda);
			
			if (CollectionUtils.isEmpty(lstCliente))
			{
				model.addObject(Constantes.MENSAJE_GENERAL, "(!) No existe registros para esta consulta. ");
			}
			
			model.addObject("lstCliente", lstCliente);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
		}
		
		model.addObject("clienteForm", clienteForm);
		
		return model;
	}
	
	public ModelAndView actualizarCliente(HttpServletRequest request, 
			HttpServletResponse response, ClienteForm clienteForm) throws Exception
	{
		logger.debug("=================Cliente UPDATE===============");
		
		ModelAndView model = new ModelAndView(Constantes.JSP_UPDATE_CLIENTE);
		String parametro = request.getParameter(Constantes.PARAMETER);
		String idCli = request.getParameter(Constantes.PARAMETER_ID);
		
		try {
			logger.debug("Parametro: " + parametro);
			logger.debug("IdProducto: " + idCli);
			
			model.addObject(UPDATE_CLIENTE, parametro);
			
			if(StringUtils.equals(parametro, Constantes.PARAMETER_NEW))
			{
				model.addObject(Constantes.TITULO, TITULO_NEW_CLIENTE);
				clienteForm = new ClienteForm();
				
			}else if(StringUtils.equals(parametro, Constantes.PARAMETER_EDIT))
			{
				model.addObject(Constantes.TITULO, TITULO_EDIT_CLIENTE);
								
				clienteForm = getClienteForm(clienteService.getCliente(Integer.parseInt(idCli)));
				
			}else if(StringUtils.equals(parametro, Constantes.PARAMETER_DELETE))
			{
				clienteService.deleteCliente(Integer.parseInt(idCli));
				logger.info("(!) Se eliminó con éxito.");
				return iniciar(request, response);
			}else
			{
				/**Busqueda de productos**/
				if (!StringUtils.isEmpty(parametro))
				{
					Cliente cliente = new Cliente();
															
					if (StringUtils.equals(clienteForm.getTipoBusqueda(), 
											Constantes.BUSQUEDA_X_DOCUMENTO))
					{
						cliente.setNumeroDocumentoId(clienteForm.getNumeroDocumentoId().trim());
					}else
					{
						cliente.setRazonSocial(clienteForm.getRazonSocial().trim().toLowerCase());
					}
					
					request.setAttribute("clienteBean", cliente);
					request.setAttribute("tipoBusqueda", clienteForm.getTipoBusqueda());
					
					return iniciar(request, response);
				}
			}		
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
		}
		
		model.addObject("clienteForm", clienteForm);
		
		return model;
	}
	
	public ModelAndView saveCliente(HttpServletRequest request, 
			HttpServletResponse response, ClienteForm clienteForm) throws Exception
	{
		logger.debug("=================Cliente SAVE===============");
		ModelAndView model = new ModelAndView(Constantes.JSP_UPDATE_PRODUCTO);
		String parametro = request.getParameter(Constantes.PARAMETER);
		
		try {
			logger.info("Parametro: " + parametro);			
			
			clienteService.updateCliente(getClienteBean(clienteForm, parametro));
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
			model.addObject(Constantes.PARAMETER, parametro);
			model.addObject("clienteForm", clienteForm);
			model.addObject("mensaje", "(X) Ocurrio un error al guardar el registro.");
			return model;
		}
						
		return iniciar(request, response);
	}
	
	private ClienteForm getClienteForm(Cliente bean)
	{
		ClienteForm form = new ClienteForm();
		
		form.setId(bean.getId()+"");
		form.setRazonSocial(bean.getRazonSocial());
		form.setFonoMovil(bean.getFonoMovil());
		form.setFonoFijo(bean.getFonoFijo());
		form.setEmail(bean.getEmail());
		form.setDescripcion(bean.getDescripcion());
		form.setDireccion(bean.getDireccion());
		form.setTipoDocumento(bean.getTipoDocumento());
		form.setNumeroDocumentoId(bean.getNumeroDocumentoId());
		
		return form;
	}
	
	private Cliente getClienteBean(ClienteForm form, String parametro)
	{
		Cliente cliente = new Cliente();
		
		if(StringUtils.equals(parametro, Constantes.PARAMETER_NEW))
		{
			cliente.setId(0);
		}else
		{
			cliente.setId(Integer.parseInt(form.getId()));
		}
		
		cliente.setRazonSocial(form.getRazonSocial());
		cliente.setFonoMovil(form.getFonoMovil());
		cliente.setFonoFijo(form.getFonoFijo());
		cliente.setEmail(form.getEmail());
		cliente.setDescripcion(form.getDescripcion());
		cliente.setDireccion(form.getDireccion());
		cliente.setTipoDocumento(form.getTipoDocumento());
		cliente.setNumeroDocumentoId(form.getNumeroDocumentoId());
					
		return cliente;
	}
	
	/**
	 * @param userSessionBean the userSessionBean to set
	 */
	public void setUserSessionBean(UserSessionBean userSessionBean) {
		this.userSessionBean = userSessionBean;
	}
	
	/**
	 * @param productoService the productoService to set
	 */
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
}
