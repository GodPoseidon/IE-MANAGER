layui.use([ 'element', 'form', 'laydate' ], function() {
	var element = layui.element(), form = layui.form(),laydate = layui.laydate;

	//表单校验
	form.verify({
		username : function(value) {

		},
		nickname : function(value) {

		},
		userdesc : function(value) {

		}
	});

	form.on('submit(formSubmit)', function(data) {
		$.post(rootPath + "/system/roleUpdate.shtml", data.field, function(result) {
			if (!result.status) {
				layer.msg(result.message, {
					icon : 5,
					shift : 6
				});
			} else {
				layer.msg('更新成功', {
					icon : 1
				});
				parent.refreshRoleList();
			}
		}, "json");
		return false;
	});
	
	$("#role_bind_user").empty().load(rootPath + "/system/goRoleBindUser.shtml", function() {
		form.render();
	});
	
	$("#role_bind_resource").empty().load(rootPath + "/system/goRoleBindResource.shtml", function() {
		form.render();
	});
})