/*******************************************************************/
/** Synopsis 
 *  Validacion JavaScript *  
 *  @date   14/10/2009
 */
/*******************************************************************/


document.onkeypress=function(e){
	var esIE=(document.all);
	var esNS=(document.layers);
	tecla=(esIE) ? event.keyCode : e.which;
	if(tecla==13){
		return false;
	  }
	return true;
	}


/**  
 * Valida campos alfanumericos , y permite campos vacios
 *   
 * @param obj : nombre del componente
 * @param msj   : mensaje   
 * */
function validarAlfaNumVacios(obj,msj){
	
	var f = document.forms[0];
	var cad = obj.value;
	var re = /^[a-zA-Z0-9Ò—\s]+$/;
	
	if (!re.test(cad) && obj.value!='') { 
	    alert(msj +" : Campo con caracteres no validos!");  
	    //obj.value = '';
	    obj.focus();
	} 
	return;
}

/**  
 * Valida campos alfanumericos , no permite campos vacios
 *   
 * @param obj : nombre del componente
 * @param msj   : mensaje
 * */
function validarAlfaNum(obj,msj){
	
	var f = document.forms[0];
	var num = obj.value;
	var re = /^[a-zA-Z0-9Ò—\s]+$/;
	
	if (!re.test(num)) { 
	    alert(msj +" : Campo vacio o con caracteres no validos!"); 
	    obj.value = '';
	    obj.focus();
	} 
	return;
}
/**  
 * Valida y Formatea un numero real con "n" decimales , y permite campos vacios
 *   
 * @param obj : nombre del componente
 * @param d   : cantidad de decimales   
 * @param msj   : mensaje 
 * */
function valNumFormatVacios(obj,d,msj){
	//alert(obj.value+":"+validarNumero(obj));
	if(validarNumero(obj) || obj.value == ''){		
		if(obj.value==''){			
			obj.value=0;
		} 		
		var num = obj.value;
		
		obj.value=(parseFloat(num)).toFixed(d);
	}else{
		alert("(!) " + msj +" ingresado no es numÈrico.");
		obj.value = "";
		obj.focus();
	}
}
/**  
 * Valida y Formatea un numero real con "n" decimales y solo 2 enteros, y permite campos vacios
 *   
 * @param obj : nombre del componente
 * @param d   : cantidad de decimales   
 * @param msj   : mensaje 
 * */
function valNumFormatVacios2(obj,d,msj){
	//alert(obj.value+":"+validarNumero(obj));
	if(validarNumero(obj) || obj.value == '' ){		
		if(obj.value==''){			
			obj.value=0;
		}
		if((parseFloat(obj.value)) >= 100.0){
			alert("(!) " + msj +" ingresado debe tener solo 2 enteros.");
			obj.value = "";
			obj.focus();
			return;
		}
		var num = obj.value;
		
		obj.value=(parseFloat(num)).toFixed(d);
	}else{
		alert("(!) " + msj +" ingresado no es numÈrico.");
		obj.value = "";
		obj.focus();
	}
}

/**  
 * Formatea un numero real con "n" decimales
 *   
 * @param obj : nombre del componente
 * @param d   : cantidad de decimales    
 * */
function formatNumero(obj,d){ 
	
	if(obj.value == "")return;
	
	var dec = Math.pow(10,d); 
	var num = obj.value * dec;
	num=Math.floor(num);
	num=num/(dec);
    
	obj.value = num;
} 
/**  
 * Verifica si es un numero  
 *   
 * @param obj : nombre del componente
 * */
function validarNumero(obj){
	
	var num = obj.value;
	var re = /^\d+(\.\d+)?$/;
	
	if (!re.test(num)) { 
	    return false;	    
	} 
	return true;
}

/*****************************************************************************/


/**  
 * Valida campos alfanumericos + espacios en blanco, permite campo vacio
 *   
 * @param obj : nombre del componente  
 * */
function alfaNumVacios(obj){
	
	var f = document.forms[0];
	var cad = obj.value;
	var re = /^[a-zA-Z0-9Ò—\s]+$/;
	
	if (!re.test(cad) && obj.value!='') { 
	    return false;	    
	} 
	return true;
}
/**  
 * (campo obligatorio)
 * Valida campos alfanumericos, no permite campo vacio
 *   
 * @param obj : nombre del componente  
 * */
function alfaNum(obj){
	
	var f = document.forms[0];
	var cad = obj.value;
	var re = /^[a-zA-Z0-9Ò—]+$/;
	
	if (!re.test(cad)) { 
	    return false;	    
	} 
	return true;
}

/*[MGL HPM]*/
function trimSpaces(sString){
	while (sString.substring(0,1) == ' '){
		sString = sString.substring(1, sString.length);
	}
	while (sString.substring(sString.length-1, sString.length) == ' '){
		sString = sString.substring(0,sString.length-1);
	}
	return sString;
}

 



