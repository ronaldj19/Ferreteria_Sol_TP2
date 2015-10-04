
package pe.com.sol.security.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import pe.com.sol.constantes.Constantes;
import pe.com.sol.portal.security.bean.Usuario;
import pe.com.sol.portal.session.UserSessionBean;
import pe.com.sol.security.form.LoginForm;
import pe.com.sol.security.service.UsuarioService;
import pe.com.sol.utils.CommonUtils;

/**
 * @author Eduardo Zuñiga
 * 
 */
public class LoginController extends MultiActionController{
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	UserSessionBean userSessionBean;
	UsuarioService usuarioService;
			
	public ModelAndView init(HttpServletRequest request, 
			HttpServletResponse response) throws Exception
	{
		logger.debug("=================Login Init===============");
				
		/**
		List<Usuario> lstUsuario = usuarioService.getUsuario(null);
		String flagSecurity = usuarioService.getParameterSecurity(Constantes.SECURITY_CODE1,
																Constantes.SECURITY_NAME);
		logger.debug("ExisteUsuario: " + lstUsuario.size());
		logger.debug("FlagSecurity: " + flagSecurity);
		
		if (CollectionUtils.isEmpty(lstUsuario) || 
				!StringUtils.equals(flagSecurity,Constantes.SECURITY_FLAG)){
			
			return new ModelAndView(Constantes.JSP_PRE_LOGIN,"loginForm",new LoginForm());
		}else
		{
			return new ModelAndView(Constantes.JSP_LOGIN,"loginForm",new LoginForm());
		}
		**/
		return new ModelAndView(Constantes.JSP_LOGIN,"loginForm",new LoginForm());
	}
	
	public ModelAndView preLogin(HttpServletRequest request, 
			HttpServletResponse response, LoginForm loginForm) throws Exception
	{
		logger.debug("=================Login Controller===============");
		
		ModelAndView model;
		model = new ModelAndView(Constantes.JSP_PRE_LOGIN);
		
		try {
			
			model.addObject("loginForm", loginForm);
			
			List<Usuario> lstUsuario = usuarioService.getUsuario(null);
			String flagSecurity = usuarioService.getParameterSecurity(Constantes.SECURITY_CODE1,
																	Constantes.SECURITY_NAME);
			if (!CollectionUtils.isEmpty(lstUsuario) && 
					StringUtils.equals(flagSecurity,Constantes.SECURITY_FLAG))
			{
				String mensaje3 = "(!) El producto ya se encuentra registrado.";
				model = new ModelAndView(Constantes.JSP_LOGIN);
				model.addObject("loginForm", loginForm);
				model.addObject("mensaje3", mensaje3);
				return model;
			}else
			{
				if (StringUtils.equals(loginForm.getCodigoProducto(), 
						CommonUtils.getRandomPass(null)))
				{
					logger.info("----Validando el Producto---");
					
					String mensaje = "", mensaje1 = "", mensaje2 = "";
					logger.info("flagSecurity: " + flagSecurity);
					
					Usuario usuario = getUsuario(loginForm);
					logger.info("(!)Guardando Nuevo Usuario.");
					/**Se cres el usuario administrador del sistema**/
					usuarioService.updateUsuario(usuario);
					
					logger.info("(!)Guardando Codigos de Seguridad.");
					/**Se guarda el codigo de seguridad y flag de ingreso**/
					usuarioService.updateSecurity(loginForm.getCodigoProducto());
					mensaje = "(!) Se creó el usuario '"+ usuario.getUsuario() + "' con el password '" +
					usuario.getPassword() + "' ";
					mensaje1 = "para ingresar al sistema.";
					mensaje2 = "(*) Importante: Cambiar el password al ingresar al Sistema SAPN.";
										
					model = new ModelAndView(Constantes.JSP_LOGIN);
					model.addObject("loginForm", loginForm);
					model.addObject("mensaje", mensaje);
					model.addObject("mensaje1", mensaje1);
					model.addObject("mensaje2", mensaje2);
									
					return model;
									
				}else
				{
					logger.error("(X) El codigo de producto ingresado es incorrecto.");
					model.addObject(Constantes.MENSAJE_GENERAL, "(X) El código del producto ingresado es incorrecto. ");
				}
			}
						
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			model.addObject(Constantes.MENSAJE_GENERAL, "(X) Ocurrio un error, comunicarse con su proveedor. ");
		}
		
		return model;
	}
	
	public ModelAndView login(HttpServletRequest request, 
			HttpServletResponse response, LoginForm loginForm) throws Exception
	{
		logger.debug("=================Login Controller===============");
		
		Usuario usuario = new Usuario();
		ModelAndView model;
		model = new ModelAndView(Constantes.JSP_LOGIN);
		
		try {
			
			model.addObject("loginForm", loginForm);
			
		//	if (validarProducto()){
				logger.debug("User: " + loginForm.getUsuario());
				/**Valida la existencia del usuario**/
				usuario = usuarioService.getUsuario(loginForm.getUsuario(), loginForm.getPassword());
										
				if (usuario == null)
				{
					model.addObject(Constantes.MENSAJE_GENERAL, "(X) El usuario y/o password es incorrecto");
					
					
				}else{
					
					/**Guarda en session los datos del usuario**/
					userSessionBean.setUsuario(usuario);
					userSessionBean.setNombreCompleto(CommonUtils.getUpperCase(usuario.getNombreCompleto()));
					model = new ModelAndView(Constantes.JSP_PRINCIPAL);
					
				}
		/*	}else
			{
				logger.error("(X) Error al validar el código del producto.");
				model.addObject(Constantes.MENSAJE_GENERAL, "(X) Error al validar el código del producto.");
			}*/
									
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			model.addObject(Constantes.MENSAJE_GENERAL, "(X) Ocurrio un error, comunicarse con helpdesk. ");
		}
		
		return model;
	}
	
	public ModelAndView logout(HttpServletRequest request, 
			HttpServletResponse response) throws Exception
	{
		logger.debug("=================Logoff Controller===============");
				
		/**Limpiar los datos del usuario en session**/
		userSessionBean.logout();
		
		return new ModelAndView(Constantes.JSP_LOGIN,"loginForm", new LoginForm());
	}
	
	public ModelAndView changePassword(HttpServletRequest request, 
			HttpServletResponse response) throws Exception
	{
		logger.debug("=================ChangePassrord Controller===============");
		ModelAndView model = new ModelAndView(Constantes.JSP_CHANGE_PASSWORD);		
		
		LoginForm loginForm = new LoginForm();
		loginForm.setUsuario(userSessionBean.getUsuario().getUsuario());
		
		model.addObject("validar", userSessionBean.getUsuario().getPassword());
		model.addObject("loginForm", loginForm);
		
		return model;
	}
	
	public ModelAndView paginaInicio(HttpServletRequest request, 
			HttpServletResponse response, LoginForm loginForm) throws Exception{
		
		return new ModelAndView(Constantes.JSP_PRINCIPAL);
	}
	
	public ModelAndView changePasswordSave(HttpServletRequest request, 
			HttpServletResponse response, LoginForm loginForm) throws Exception
	{
		logger.debug("=================Save ChangePassrord Controller===============");
		ModelAndView model = new ModelAndView(Constantes.JSP_PRINCIPAL);		
		String mensaje = "";
		
		try {
			
			Usuario usuario = new Usuario();
			usuario.setUsuario(loginForm.getUsuario());
			usuario.setPassword(loginForm.getPassword2());//nuevo password
			
			usuarioService.updatePassword(usuario);
			mensaje = "(!) Se cambio el password correctamente.";
			
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = "(X) Ocurrio un error al cambiar el password.";
			logger.error(e.getMessage(),e);
		}
		
		model.addObject("mensaje", mensaje);
		
		return model;
	}
	
	private Usuario getUsuario(LoginForm form){
		Usuario user = new Usuario();
		String nombreCompleto = form.getNombre().toLowerCase().trim() + " " 
								+ form.getApellido().toLowerCase().trim();
		
		user.setUsuario(CommonUtils.getUsuario(form.getNombre().toLowerCase().trim(), 
										form.getApellido()).toLowerCase().trim());
		
		user.setPassword(user.getUsuario());
		user.setNombreCompleto(nombreCompleto);
		
		return user;
	}
	
	private boolean validarProducto(){
		
		String codigoProducto = usuarioService.getParameterSecurity(Constantes.SECURITY_CODE0, 
				Constantes.SECURITY_NAME);
		String codigoGuardado = getCodigoGuardado();
		
		logger.info("CodProducto: " + codigoProducto);
		logger.info("CodGuardado: " + codigoGuardado);
		
		if (!StringUtils.equals(codigoProducto, codigoGuardado))
		{
			return false;
		}
		
		return true;
	}
			
	private String getCodigoGuardado()
	{
		
		String codigoGuardado = usuarioService.getParameterSecurity(Constantes.SECURITY_CODE2, 
				Constantes.SECURITY_NAME);
		/**mmddyyyyhh**/
		codigoGuardado = codigoGuardado.substring(5, 10) + codigoGuardado.substring(0, 5);
		/**ddmmyyyyhh**/
		codigoGuardado = codigoGuardado.substring(2, 4) + "-" +
						 codigoGuardado.substring(0, 2) + "-" +
						 codigoGuardado.substring(4, 8) + "-" +
						 codigoGuardado.substring(8, 10);
				
		return CommonUtils.getRandomPass(codigoGuardado);
	}
	
	/**
	 * @param userSessionBean the userSessionBean to set
	 */
	public void setUserSessionBean(UserSessionBean userSessionBean) {
		this.userSessionBean = userSessionBean;
	}
	
	/**
	 * @param usuarioService the usuarioService to set
	 */
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

}
