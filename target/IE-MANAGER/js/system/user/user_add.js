layui.use([ 'element', 'form', 'laydate' ], function() {
	var element = layui.element(), form = layui.form(),laydate = layui.laydate;

	form.verify({
		username : function(value) {
			var msg = "";
			$.ajax({
				type : "post",
				url : rootPath + "/system/roleIsExist.shtml?username="+value,
				cache : false,
				async : false,
				dataType : "json",
				success : function(obj) {
					if (!obj.status) {
						console.log(!obj.status+"===="+obj.message);
						msg = obj.message;
						return obj.message;
					}
				}
			});
			if (msg!="") {
				return msg;
			}
		},birth_date : function(value){
			if (value!="" && value!=null) {
				var a = /^(\d{4})-(\d{2})-(\d{2})$/
				if (!a.test(value)) {
					return "日期格式必须为：yyyy-MM-dd格式";
				}
			}
		}
	});

	form.on('submit(formSubmit)', function(data) {
		$.post(rootPath + "/system/userAdd.shtml", data.field, function(result) {
			if (!result.status) {
				layer.msg(result.message, {
					icon : 5,
					shift : 6
				});
			} else {
				layer.msg('添加成功', {
					icon : 1
				});
				parent.refreshUserList();
			}
		}, "json");
		return false;
	});
})