
package pe.com.sol.portal.session;

import java.util.HashMap;
import java.util.Map;

import pe.com.sol.portal.security.bean.Usuario;

/**
 * 
 */
public class UserSessionBean {
	
	private Map<Object, Object> session=new HashMap<Object, Object>();
			
	private Usuario usuario;
	
	private String nombreCompleto;
	
	private String ministerio;
						
	public UserSessionBean() {
		super();
	}

	public void logout() {
		
		this.nombreCompleto=null;
		this.ministerio=null;
		this.usuario=null;
		if (this.session!=null){
			this.session.clear();
		}
	}
	
	
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
		
	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	/**
	 * @return the ministerio
	 */
	public String getMinisterio() {
		return ministerio;
	}

	/**
	 * @param ministerio the ministerio to set
	 */
	public void setMinisterio(String ministerio) {
		this.ministerio = ministerio;
	}
					
	public void put(Object key, Object value){
		session.put(key, value);
	}
	
	public Object get(Object key){
		return session.get(key);
	}
	
	public void remove(Object key)
	{
		session.remove(key);
	}
	
	public boolean containsKey(Object key){
		return session.containsKey(key);
	}
	
	public void clear(){
		session.clear();
	}

}
