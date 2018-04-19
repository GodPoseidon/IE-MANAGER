layui.use([ 'element', 'form', 'laydate' ],function() {
	var element = layui.element(), form = layui.form(), laydate = layui.laydate;
	
	// >
	$('#oneToLeft').click(function (){
		var selectValue = $('#noBinding').val();
		if (selectValue!=undefined && selectValue!="") {
			var selectText = $("#noBinding").find("option:selected").text();
			$('#binding').append("<option value='"+selectValue+"'>"+selectText+"</option>");
			$('#noBinding option[value="'+selectValue+'"]').remove();
		}
	})
	// >>
	$('#allToLeft').click(function (){
		$("#noBinding option").each(function(){ 
	        var selectValue = $(this).val(); 
			var selectText = $(this).text(); 
			$('#binding').append("<option value='"+selectValue+"'>"+selectText+"</option>");
			$('#noBinding option[value="'+selectValue+'"]').remove();
	    });
	})
	// <
	$('#oneToRight').click(function (){
		var selectValue = $('#binding').val();
		if (selectValue!=undefined && selectValue!="") {
			var selectText = $("#binding").find("option:selected").text();
			$('#noBinding').append("<option value='"+selectValue+"'>"+selectText+"</option>");
			$('#binding option[value="'+selectValue+'"]').remove();
		}
	})
	// <<
	$('#allToRight').click(function (){
		$("#binding option").each(function(){ 
	        var selectValue = $(this).val(); 
			var selectText = $(this).text(); 
			$('#noBinding').append("<option value='"+selectValue+"'>"+selectText+"</option>");
			$('#binding option[value="'+selectValue+'"]').remove();
	    });
	})

})