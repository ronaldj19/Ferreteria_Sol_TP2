package pe.com.sol.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;




public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		String fecha = "01-05-2011";
		String moneda = "$ ";
	    String total1 = "7";
	    String total2 = "171.21";
	    int min = 0;
	    String[] valores = {"1132.15","2.1","15812.11","102.12"};
	    
	    for (int i = 0; i < valores.length; i++) {
			
	    	if(valores[i].length()>min)
			{
				min=valores[i].length();
			}
		}
	    try {
			System.out.println("Fecha: " + sdf.format(sdf1.parse(fecha)));
			System.out.println(fecha.substring(0, 2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //System.out.println("Numero: " + completarImportes(total1, moneda, 6));
	    //System.out.println("Numero: " + completarImportes(total2, moneda, 6));
	    //////////////////////////////////////////////////////////////////////////
	}
	
	public static String completarImportes(String importe, String moneda, int longitud){
		String cadena = importe;
		int index = importe.indexOf(".");
		System.out.println(index);
		String scad = importe.substring(index+1, importe.length());
		
		if (scad.length()==1){
			cadena += "0";
		}
		
		cadena = StringUtils.leftPad(cadena, longitud, ' ');
		
		return moneda + cadena;
	}

}

