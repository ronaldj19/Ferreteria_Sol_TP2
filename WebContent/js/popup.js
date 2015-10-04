
//SETTING UP OUR POPUP
//0 means disabled; 1 means enabled;
var popupStatus = 0;

//loading popup with jQuery magic!
function loadPopup(popup){
	//loads popup only if it is disabled
	//if(popupStatus==0){
		$("#backgroundPopup").css({
			"opacity": "0.7"
		});
		$("#backgroundPopup").fadeIn("slow");
		$("#"+popup).fadeIn("slow");
	//	popupStatus = 1;
	//}
}

//disabling popup with jQuery magic!
function disablePopup(){
	//disables popup only if it is enabled
	//if(popupStatus==1){
		$("#backgroundPopup").css("display", "none");
		$("#popupContact").css("display", "none");
		$("#popupProducto").css("display", "none");
	//	popupStatus = 0;
	//}
}

//centering popup
function centerPopup(popup,h,w){	
	//request data for centering
	//Darle el alto y ancho 
	   $("#"+popup).css('width', w + 'px'); 
	   $("#"+popup).css('height', h + 'px'); 

	   var ancho = $(this).width(); 
	   var alto = $(this).height();
		   
	   ancho = (ancho/2) - (w/2);
	   alto = (alto/2) - (h/2) - 90;
		   
	   $("#"+popup).css("left",ancho + "px"); 
	   $("#"+popup).css("top",alto + "px");   
	//only need force for IE6
	/*
	$("#backgroundPopup").css({
		"height": windowHeight
	});
	*/
}