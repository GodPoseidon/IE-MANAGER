layui.use([ 'element', 'form' ], function() {
	var element = layui.element(), form = layui.form();
	var config = {
		url : "./message/getSmsPages.shtml",
		page : true,
		select : true,
		columns : [ {
			text : 'id',
			name : 'id',
			width : 20
		}, {
			text : '发件人号码',
			name : 'smsFrom',
			width : 10
		}, {
			text : '发件人名称',
			name : 'smsFromuser',
			width : 10
		}, {
			text : '收件人号码',
			name : 'smsAddress',
			width : 10
		}, {
			text : '收件人名称',
			name : 'smsAddressuser',
			width : 10
		}, {
			text : '短信内容',
			name : 'smsContent',
			width : 10
		}, {
			text : '短信类型',
			name : 'smsType',
			width : 10,
			formatter : function(value, row, index) {
				if (value == "2" || value == 2) {
					return "彩信";
				} else if (value == "1" || value == 1) {
					return "短信";
				}
			}
		}, {
			text : '状态',
			name : 'smsStatus',
			width : 10,
			formatter : function(value, row, index) {
				if (value == "2" || value == 2) {
					return "<span color='red'>未发送</span>";
				} else if (value == "1" || value == 1) {
					return "<span color='green'>已发送</span>";
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
		refreshSmsList();
		return false;
	});
})


function refreshSmsList(){
	var query_smsFromuser = $('#query_smsFromuser').val();
	var query_smsAddressuser = $('#query_smsAddressuser').val();
	var obj = {};
	obj.query_smsFromuser = query_smsFromuser;
	obj.query_smsAddressuser = query_smsAddressuser;
	$("#table").jfTable("reload",obj);
}
