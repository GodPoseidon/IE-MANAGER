layui.use([ 'element', 'form', 'laydate' ], function() {
	var element = layui.element(), form = layui.form(),laydate = layui.laydate;

	form.verify({
		username : function(value) {

		},
		nickname : function(value) {

		},
		userdesc : function(value) {

		}
	});

	form.on('submit(formSubmit)', function(data) {
		$.post(rootPath + "/message/noticeUpdate.shtml", data.field, function(result) {
			if (!result.status) {
				layer.msg(result.message, {
					icon : 5,
					shift : 6
				});
			} else {
				layer.msg('更新成功', {
					icon : 1
				});
				parent.refreshNoticeList();
			}
		}, "json");
		return false;
	});
})