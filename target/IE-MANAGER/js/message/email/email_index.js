layui.use([ 'element', 'form' ], function() {
	var element = layui.element(), form = layui.form();
	var config = {
		url : "./message/getEmailPages.shtml",
		page : true,
		select : true,
		columns : [ {
			text : 'id',
			name : 'id',
			width : 20
		}, {
			text : '收件人',
			name : 'emailTouser',
			width : 10
		}, {
			text : '收件人地址',
			name : 'emailTo',
			width : 10
		}, {
			text : '抄送',
			name : 'emailCarbonCopy',
			width : 10,
			formatter : function(value, row, index) {
				if (value==undefined || value==null) {
					return "";
				}else {
					return value;
				}
			}
		}, {
			text : '主题',
			name : 'emailTitle',
			width : 10
		}, {
			text : '邮件内容',
			name : 'emailContent',
			width : 10
		}, {
			text : '发件人地址',
			name : 'emailFrom',
			width : 10
		}, {
			text : '发件人',
			name : 'emailFromuser',
			width : 10
		}, {
			text : '标识',
			name : 'systemNo',
			width : 10,
			formatter : function(value, row, index) {
				if (value == "2" || value == 2) {
					return "变通级";
				} else if (value == "1" || value == 1) {
					return "系统级";
				}
			}
		}, {
			text : '状态',
			name : 'emailStatus',
			width : 10,
			formatter : function(value, row, index) {
				if (value == "2" || value == 2) {
					return "<font color='red'>发送失败</font>";
				} else if (value == "1" || value == 1) {
					return "<font color='green'>发送成功</font>";
				}
			}
		}],
		onBeforeLoad : function(param) {
			return param;
		},
		onLoadSuccess : function(data) {
			return data;
		},
		dataFilter : function(data) {
			return data;
		}
	};
	$("#table").jfTable(config);
	
	form.on('submit(formQuery)', function(data) {
		refreshEmailList();
		return false;
	});
})

function refreshEmailList(){
	var query_emailTouser = $('#query_emailTouser').val();
	var query_emailFromuser = $('#query_emailFromuser').val();
	var obj = {};
	obj.query_emailTouser = query_emailTouser;
	obj.query_emailFromuser = query_emailFromuser;
	$("#table").jfTable("reload",obj);
}
