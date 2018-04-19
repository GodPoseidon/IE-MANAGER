layui.use([ 'element', 'form' ], function() {
	var element = layui.element(), form = layui.form();
	var config = {
		url : "./message/getNoticePages.shtml",
		page : true,
		select : true,
		columns : [ {
			text : 'id',
			name : 'id',
			width : 20
		}, {
			text : '标题',
			name : 'noticeTitle',
			width : 10
		}, {
			text : '内容',
			name : 'noticeContent',
			width : 10
		}, {
			text : '来源',
			name : 'noticeFrom',
			width : 10
		}, {
			text : '发送至',
			name : 'noticeTo',
			width : 10
		}, {
			text : '状态',
			name : 'status',
			width : 10,
			formatter : function(value, row, index) {
				if (value == "2" || value == 2) {
					return "<font color='green'>已发布</font>";
				} else if (value == "1" || value == 1) {
					return "<font color='red'>未发布</font>";
				}
			}
		}, {
			text : '范围',
			name : 'noiceScope',
			width : 10,
			formatter : function(value, row, index) {
				if (value == "2" || value == 2) {
					return "普通级";
				} else if (value == "1" || value == 1) {
					return "系统级";
				}
			}
		}, {
			text : '创建人',
			name : 'createUser',
			width : 10
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

	//添加
	$('#noticeAdd').click(function (){
		layer.open({
			type : 2,
			move : false,
			title : '添加系统公告',
			area : [ '700px', '650px' ],
			content : './message/goNoticeAdd.shtml'
		});
	})
	
	//修改
	$('#noticeUpdate').click(function (){
		var row = $("#table").jfTable("getSelected");
		if (row.length != 1) {
			layer.msg("请选择一条数据进行修改操作", {
				icon : 5,
				shift : 6
			});
			return;
		}
		var id = row[0].id;
		var status = row[0].status;
		if (status==2) {
			layer.msg("已发布数据不能再修改", {
				icon : 5,
				shift : 6
			});
			return;
		}
		layer.open({
			type : 2,
			move : false,
			title : '修改系统公告',
			area : [ '700px', '650px' ],
			content : './message/goNoticeUpdate.shtml?id=' + id
		});
	})
	
	//删除
	$('#noticeDel').click(function (){
		var row = $("#table").jfTable("getSelected");
		if (row.length <= 0) {
			layer.msg("至少需要选择一行数据才能进行该操作", {
				icon : 5,
				shift : 6
			});
			return;
		}
		var ids = "";
		for (var i = 0; i < row.length; i++) {
			ids = ids + row[i].id;
			if (i != row.length - 1) {
				ids = ids + ",";
			}
		}
		if (row[0].status==2 || row[0].status=="已发布") {
			layer.msg("已发布公告不能删除", {
				icon : 5,
				shift : 6
			});
			return;
		}
		
		layer.confirm('是否确定删除该数据？', {
			icon : 3,
	        title : '系统提示',
			btn : [ '删除', '取消' ]
		}, function(index, layero) {
			$.post(rootPath + "/message/noticeDel.shtml", {
				ids : ids
			}, function(result) {
				if (!result.status) {
					layer.msg(result.message, {
						icon : 5,
						shift : 6
					});
				} else {
					layer.msg('删除成功', {
						icon : 1
					});
					refreshNoticeList();
				}
			}, "json");
		});
	})
	
	form.on('submit(formQuery)', function(data) {
		refreshNoticeList();
		return false;
	});
	
	//更新方法
	$('#noticeRefresh').click(function() {
		refreshNoticeList();
	})
	
	//发布方法
	$('#noticeRelease').click(function() {
		var row = $("#table").jfTable("getSelected");
		if (row.length != 1) {
			layer.msg("请选择一条数据进行修改操作", {
				icon : 5,
				shift : 6
			});
			return;
		}
		
		var status = row[0].status;
		if (status==2) {
			layer.msg("已发布数据无需重复发布", {
				icon : 5,
				shift : 6
			});
			return;
		}
		
		$.post(rootPath + "/message/releaseNotice.shtml", {
			id : row[0].id
		}, function(result) {
			if (!result.status) {
				layer.msg(result.message, {
					icon : 5,
					shift : 6
				});
			} else {
				layer.msg('发布成功', {
					icon : 1
				});
				refreshNoticeList();
			}
		}, "json");
		
	})
})

function refreshNoticeList(){
	var query_notice_title = $('#query_notice_title').val();
	var query_notice_content = $('#query_notice_content').val();
	var obj = {};
	obj.query_notice_title = query_notice_title;
	obj.query_notice_content = query_notice_content;
	$("#table").jfTable("reload",obj);
}
