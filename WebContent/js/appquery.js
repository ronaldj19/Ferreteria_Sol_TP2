
	function _js_filter_by_vendor_()
	{
		var id ;
		id = $("#filterbyvendor").val();
		
		document.location.href = "/sfPanel/web/index.php/pitems/index?v=" + $("#filterbyvendor").val();
	}

	function _js_to_export_()
	{
		
		var contents = "";
		var mpn = "";
		var x_ = 1;
		
		for ( x_=1 ; x_<100 ; x_++ )
		{
			if ( $("#row_" + x_ ).attr("checked") == true ) 
			{
				mpn = $("#mpn_" + x_ ).val();
				contents += $("#row_" + x_ ).val() + "_" + mpn  + ",";
			}
		}
		
		_js_facebox_url_( '/sfPanel/web/index.php/pitems/addtoexport/p/' + contents , 500 , 450 );
		
	}
	
	function _js_facebox_url_(_faceboxurl_,_w_,_h_)
	{
		var _URL_=  _faceboxurl_;
		jQuery.facebox('<iframe id="faceboxframe" width="'+_w_+'px" height="'+_h_+'px" scroll="no" src="'+_URL_+'"></iframe>');
		return true;
	}
	
	function _js_set_tab_( no )
	{
		//SHOW TAB
		$(".tabs").css( 'display','none' );
		$("#tab_" + no ).css( 'display','inline' );		
	}

	$(document).ready(function () {
		if(jQuery().flexigrid) {
			$('.table-form').flexigrid({ height:'auto',width:'1200' });
		}
	});

		
	if(jQuery().checkBox) {
		$(function(){
			$('input').checkBox();
			$('#toggle-all').click(function(){
		 	$('#toggle-all').toggleClass('toggle-checked');
			$('#mainform input[type=checkbox]').checkBox('toggle');
			return false;
			});
		});
	}
	
	//Tooltips 
	if(jQuery().tooltip) {
		$(function() {
			$('a.info-tooltip ').tooltip({
				track: true,
				delay: 0,
				fixPNG: true, 
				showURL: false,
				showBody: " - ",
				top: -35,
				left: 5
			});
		});
	}


	if(jQuery().fancybox) {
		$(document).ready(function() {
			$("a.fancybox").fancybox({
				'scrolling'         : 'auto',
				'type'				: 'iframe',
				'width'             : '400',
				'autoDimensions'    : 'true'
			});
		});
	}

	$(document).ready(function () {
	    $(".action-slider").click(function () {
	        $("#actions-box-slider").slideToggle("fast");
	        $(this).toggleClass("activated");
	        return false;
	    });
	});

	$(document).ready(function() {
		$(".close-yellow").click(function () {
			$("#message-yellow").fadeOut("slow");
		});
		$(".close-red").click(function () {
			$("#message-red").fadeOut("slow");
		});
		$(".close-blue").click(function () {
			$("#message-blue").fadeOut("slow");
		});
		$(".close-green").click(function () {
			$("#message-green").fadeOut("slow");
		});
	});
	
	