layui.use([ 'element', 'form', 'laydate' ], function() {
	var element = layui.element(), form = layui.form(),laydate = layui.laydate;

	//表单校验
	form.verify({
		username : function(value) {

		},birth_date : function(value){
			if (value!="" && value!=null) {
				var a = /^(\d{4})-(\d{2})-(\d{2})$/
				if (!a.test(value)) {
					return "日期格式必须为：yyyy-MM-dd格式";
				}
			}
		},
		userdesc : function(value) {

		}
	});

	form.on('submit(formSubmit)', function(data) {
		$.post(rootPath + "/system/userUpdate.shtml", data.field, function(result) {
			if (!result.status) {
				layer.msg(result.message, {
					icon : 5,
					shift : 6
				});
			} else {
				layer.msg('更新成功', {
					icon : 1
				});
				parent.refreshUserList();
			}
		}, "json");
		return false;
	});
})