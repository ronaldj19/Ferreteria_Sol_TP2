
package pe.com.sol.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*; 
import java.text.*; 

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pe.com.sol.portal.web.bean.Producto;

/**
 * @author Eduardo Zuñiga
 *
 */
public class CommonUtils {
	
	private static Logger logger = Logger.getLogger(CommonUtils.class);
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	/**Metodo que cambia el primer caracter a mayuscula de una palabra**/
	public static String getUpperCase(String valor)
    {
        String[] cadena = valor.split(" ");
        String rt = "";
        String retorno = "";
        
        for (int i = 0; i < cadena.length; i++) {
            if (!StringUtils.isEmpty(cadena[i]))
            {
                String cd = cadena[i];
                for (int j = 0; j < cd.length(); j++) {
                    
                    if (j == 0)
                    {
                        rt = cd.charAt(j) + "";
                        rt = rt.toUpperCase();
                    }else
                    {
                        rt = rt + cd.charAt(j);
                    }
                }
                retorno = retorno + rt + " ";
            }
        }
        return retorno.trim();
    }
	
	public static String getRandomPass(String cadena)
	{
		String[] fecha;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-hh");
		Calendar cal = Calendar.getInstance();
		
		if (StringUtils.isEmpty(cadena))
		{
			fecha = sdf.format(cal.getTime()).split("-");
		}else
		{
			fecha = cadena.split("-");
		}
		
			    
	    int pass = 0;
	    for (int i = 0; i < fecha.length; i++) {
			pass = pass + Integer.parseInt(fecha[i]);
		}
	    
	    pass = pass * pass;
	    String ret = "";
	    if (pass%2 == 0)
	    {
	    	ret = fecha[1] + pass;
	    }else
	    {
	    	ret = fecha[3] + pass;
	    }
	    
	    return StringUtils.leftPad(ret, 10, "0");
	}
		
	public static String getUsuario(String nombre, String apellido)
	{
		String[] nombres = nombre.split(" ");
		String[] apellidos = apellido.split(" ");
		String usuario = "";
		
		for (int i = 0; i < nombres.length; i++) {
			if (!StringUtils.isEmpty(nombres[i]))
			{
				usuario = usuario + nombres[i].charAt(0);
			}
		}
		
		for (int i = 0; i < apellidos.length; i++) {
			if (!StringUtils.isEmpty(apellidos[i]))
			{
				if (i==0)
				{
					usuario = usuario + apellidos[i];
				}else
				{
					usuario = usuario + apellidos[i].charAt(0);
				}
			}
		}
		
		return usuario;
	}
	
	public static Date getFormatFecha(String fecha)
	{
		Date fec = null;
		String fechaNacimiento = "11-11-1111";
		
		try {
			
			if (!StringUtils.isEmpty(fecha))
			{
				fechaNacimiento = fecha.substring(8, 10) + 
											fecha.substring(4, 8) + 
											fecha.substring(0, 4);
			}
			
			fec = sdf.parse(fechaNacimiento);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("(X) error en la conversion de fecha.");
		}
		return fec;
	}
	
	public static String getToDay()
	{
		return sdf.format(new Date());
	}
	
	public static String completarImportes(String importe, String moneda, int longitud)
	{
		String cadena = importe;
		int index = importe.indexOf(".");
		String scad = importe.substring(index+1, importe.length());
		
		if (scad.length()==1){
			cadena += "0";
		}
		
		cadena = StringUtils.leftPad(cadena, longitud, ' ');
		logger.debug("-'" + moneda + cadena + "'");
		return moneda + cadena;
	}
	
	public static int obtenerlongitudImportes(List<Producto> lstProductos, boolean flagTotal)
	{
		int min = 0;
		
		for (Iterator iterator = lstProductos.iterator(); iterator.hasNext();) {
			Producto producto = (Producto) iterator.next();
			String importe = producto.getPrecioVenta() + "";
			
			if (flagTotal)
			{
				importe = producto.getSubTotal() + "";
			}
			
			int index = importe.indexOf(".");
			String scad = importe.substring(index+1, importe.length());
			
			if (scad.length()==1){
				importe += "0";
			}
			
			if(importe.length()>min)
			{
				min=importe.length();
			}
		}
		
		return min;
	}
	
	public static String getNumeroDec(double valor_dec) 
    { 
        //se trabaja con dos decimales. pero no funca si es 10 tb devuelve 10
        String s = "###,###,###,##"; 
        DecimalFormat decimalFormat = new DecimalFormat(s); 
        String s1 = decimalFormat.format(valor_dec); 
        return s1; 
    }    

}
