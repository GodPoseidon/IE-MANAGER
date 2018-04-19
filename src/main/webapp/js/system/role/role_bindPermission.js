layui.use([ 'element', 'form', 'laydate' ],function() {
	var element = layui.element(), form = layui.form(), laydate = layui.laydate;
	
	// >
	$('#p_oneToLeft').click(function (){
		var selectValue = $('#noPermission').val();
		if (selectValue!=undefined && selectValue!="") {
			var selectText = $("#noPermission").find("option:selected").text();
			$('#permission').append("<option value='"+selectValue+"'>"+selectText+"</option>");
			$('#noPermission option[value="'+selectValue+'"]').remove();
		}
	})
	// >>
	$('#p_allToLeft').click(function (){
		$("#noPermission option").each(function(){ 
	        var selectValue = $(this).val(); 
			var selectText = $(this).text(); 
			$('#permission').append("<option value='"+selectValue+"'>"+selectText+"</option>");
			$('#noPermission option[value="'+selectValue+'"]').remove();
	    });
	})
	// <
	$('#p_oneToRight').click(function (){
		var selectValue = $('#permission').val();
		if (selectValue!=undefined && selectValue!="") {
			var selectText = $("#permission").find("option:selected").text();
			$('#noPermission').append("<option value='"+selectValue+"'>"+selectText+"</option>");
			$('#permission option[value="'+selectValue+'"]').remove();
		}
	})
	// <<
	$('#p_allToRight').click(function (){
		$("#permission option").each(function(){ 
	        var selectValue = $(this).val(); 
			var selectText = $(this).text(); 
			$('#noPermission').append("<option value='"+selectValue+"'>"+selectText+"</option>");
			$('#permission option[value="'+selectValue+'"]').remove();
	    });
	})
	
	//未选列表双击事件
	$("#noPermission").dblclick(function(){
		var selectValue = $('#noPermission').val();
		var selectText =  $("#noPermission").find("option:selected").text();
		$('#permission').append("<option value='"+selectValue+"'>"+selectText+"</option>");
		$('#noPermission option[value="'+selectValue+'"]').remove();
	})
	
	//已选列表双击事件
	$("#permission").dblclick(function(){
		var selectValue = $('#permission').val();
		var selectText =  $("#permission").find("option:selected").text();
		$('#noPermission').append("<option value='"+selectValue+"'>"+selectText+"</option>");
		$('#permission option[value="'+selectValue+'"]').remove();
	})
})