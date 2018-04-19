layui.use([ 'element', 'form', 'laydate' ], function() {
	var element = layui.element(), form = layui.form(),laydate = layui.laydate;

	form.verify({
		resource : function(value) {
			var msg = "";
			$.ajax({
				type : "post",
				url : rootPath + "/system/resourceIsExist.shtml?resource="+value,
				cache : false,
				async : false,
				dataType : "json",
				success : function(obj) {
					if (!obj.status) {
						msg = obj.message;
						return obj.message;
					}
				}
			});
			if (msg!="") {
				return msg;
			}
		}
	});

	form.on('submit(formSubmit)', function(data) {
		$.post(rootPath + "/system/resourceAdd.shtml", data.field, function(result) {
			if (!result.status) {
				layer.msg(result.message, {
					icon : 5,
					shift : 6
				});
			} else {
				layer.msg('添加成功', {
					icon : 1
				});
				parent.refreshResourceList();
			}
		}, "json");
		return false;
	});
})