
package pe.com.sol.portal.web.form;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alonso Gutierrez
 * 
 */
public class DocumentoForm {
	
	private String id;
	
	private String tipoDocumento;
	
	private String numeroDocumento;
	
	private String fecha;
	
	private String igv;
	
	private String subTotal;
	
	private String total;
	
	private String razonSocial;
	
	private String guiaRemision;
	
	private String ruc;
	
	private String direccion;
	
	private List<ProductoForm> lstProductoForm = new ArrayList<ProductoForm>();
	
	/**Variables para la busqueda de un cliente o proveedor**/
	private String rucBusqueda;
	
	private String nombreBusqueda;
	
	private String tipoBusqueda;
	
	/**Variables para la busqueda de un producto**/
	private String codigoProducto;
	
	private String nombreProducto;
	
	private String[] ids;
	
	private String[] codigos;
	
	private String[] cantidad;
	
	private String[] unitario;
	
	private String[] stotal;
	
	/**Variables para la GUIA DE REMISION**/
	private String partida;
	
	private String nroComprobante;
	
	private String direccionLocal;
	
	private String moneda;
	
	private String parametroIgv;
	
	private String parametroTipoCambio;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the igv
	 */
	public String getIgv() {
		return igv;
	}

	/**
	 * @param igv the igv to set
	 */
	public void setIgv(String igv) {
		this.igv = igv;
	}

	/**
	 * @return the subTotal
	 */
	public String getSubTotal() {
		return subTotal;
	}

	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @return the guiaRemision
	 */
	public String getGuiaRemision() {
		return guiaRemision;
	}

	/**
	 * @param guiaRemision the guiaRemision to set
	 */
	public void setGuiaRemision(String guiaRemision) {
		this.guiaRemision = guiaRemision;
	}

	/**
	 * @return the ruc
	 */
	public String getRuc() {
		return ruc;
	}

	/**
	 * @param ruc the ruc to set
	 */
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the lstProductoForm
	 */
	public List<ProductoForm> getLstProductoForm() {
		return lstProductoForm;
	}

	/**
	 * @param lstProductoForm the lstProductoForm to set
	 */
	public void setLstProductoForm(List<ProductoForm> lstProductoForm) {
		this.lstProductoForm = lstProductoForm;
	}

	/**
	 * @return the rucBusqueda
	 */
	public String getRucBusqueda() {
		return rucBusqueda;
	}

	/**
	 * @param rucBusqueda the rucBusqueda to set
	 */
	public void setRucBusqueda(String rucBusqueda) {
		this.rucBusqueda = rucBusqueda;
	}

	/**
	 * @return the nombreBusqueda
	 */
	public String getNombreBusqueda() {
		return nombreBusqueda;
	}

	/**
	 * @param nombreBusqueda the nombreBusqueda to set
	 */
	public void setNombreBusqueda(String nombreBusqueda) {
		this.nombreBusqueda = nombreBusqueda;
	}

	/**
	 * @return the tipoBusqueda
	 */
	public String getTipoBusqueda() {
		return tipoBusqueda;
	}

	/**
	 * @param tipoBusqueda the tipoBusqueda to set
	 */
	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the nombreProducto
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}

	/**
	 * @param nombreProducto the nombreProducto to set
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	/**
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}

	/**
	 * @return the codigos
	 */
	public String[] getCodigos() {
		return codigos;
	}

	/**
	 * @param codigos the codigos to set
	 */
	public void setCodigos(String[] codigos) {
		this.codigos = codigos;
	}

	/**
	 * @return the cantidad
	 */
	public String[] getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(String[] cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the unitario
	 */
	public String[] getUnitario() {
		return unitario;
	}

	/**
	 * @param unitario the unitario to set
	 */
	public void setUnitario(String[] unitario) {
		this.unitario = unitario;
	}

	/**
	 * @return the subTotal
	 */
	public String[] getStotal() {
		return stotal;
	}

	/**
	 * @param subTotal the subTotal to set
	 */
	public void setStotal(String[] stotal) {
		this.stotal = stotal;
	}

	/**
	 * @return the partida
	 */
	public String getPartida() {
		return partida;
	}

	/**
	 * @param partida the partida to set
	 */
	public void setPartida(String partida) {
		this.partida = partida;
	}

	/**
	 * @return the nroComprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * @param nroComprobante the nroComprobante to set
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * @return the direccionLocal
	 */
	public String getDireccionLocal() {
		return direccionLocal;
	}

	/**
	 * @param direccionLocal the direccionLocal to set
	 */
	public void setDireccionLocal(String direccionLocal) {
		this.direccionLocal = direccionLocal;
	}

	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the parametroIgv
	 */
	public String getParametroIgv() {
		return parametroIgv;
	}

	/**
	 * @param parametroIgv the parametroIgv to set
	 */
	public void setParametroIgv(String parametroIgv) {
		this.parametroIgv = parametroIgv;
	}

	/**
	 * @return the parametroTipoCambio
	 */
	public String getParametroTipoCambio() {
		return parametroTipoCambio;
	}

	/**
	 * @param parametroTipoCambio the parametroTipoCambio to set
	 */
	public void setParametroTipoCambio(String parametroTipoCambio) {
		this.parametroTipoCambio = parametroTipoCambio;
	}
	
}
