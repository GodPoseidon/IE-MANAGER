layui.use([ 'element', 'form' ], function() {
	var element = layui.element(), form = layui.form();
	var config = {
		url : "./system/getResourcePage.shtml",
		page : true,
		select : true,
		columns : [ {
			text : 'id',
			name : 'id',
			width : 20
		}, {
			text : '资源名称',
			name : 'resourceName',
			width : 10
		}, {
			text : '资源',
			name : 'resource',
			width : 10
		}, {
			text : '资源描述',
			name : 'resourceDesc',
			width : 10
		}, {
			text : '资源类型',
			name : 'resourceType',
			width : 10,
			formatter : function(value, row, index) {
				if (value == "1" || value == 1) {
					return "菜单";
				} else if (value == "2" || value == 2) {
					return "按钮";
				}
			}
		}, {
			text : '资源范围',
			name : 'resourceScope',
			width : 10,
			formatter : function(value, row, index) {
				if (value == "1" || value == 1) {
					return "系统资源";
				} else if (value == "2" || value == 2) {
					return "普通资源";
				}
			}
		}, {
			text : '状态',
			name : 'status',
			width : 10,
			formatter : function(value, row, index) {
				if (value == "1" || value == 1) {
					return "<div name='mds_switch1' onclick='mds_switch_click(this);' title='"+index+"' class='open1'>启用<div name='mds_switch2' class='open2'></div></div>";
				} else if (value == "2" || value == 2) {
					return "<div name='mds_switch1' onclick='mds_switch_click(this);' title='"+index+"' class='close1'>禁用<div name='mds_switch2' class='close2'></div></div>";
				}
			}
		}],
		// 事件 一定要return
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

	// 添加方法
	$('#resourceAdd').click(function() {
		layer.open({
			type : 2,
			move : false,
			title : '添加资源',
			area : [ '700px', '650px' ],
			content : './system/goResourceAdd.shtml'
		});
	})

	// 删除方法
	$('#resourceDel').click(function() {
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

		layer.confirm('是否确定删除该数据？', {
			icon : 3,
	        title : '系统提示',
			btn : [ '删除', '取消' ]
		}, function(index, layero) {
			$.post(rootPath + "/system/resourceDel.shtml", {
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
					parent.refreshResourceList();
				}
			}, "json");
			refreshResourceList();
		});
	})

	// 更新方法
	$('#resourceUpdate').click(function() {
		var row = $("#table").jfTable("getSelected");
		if (row.length != 1) {
			layer.msg("请选择一条数据进行修改操作", {
				icon : 5,
				shift : 6
			});
			return;
		}
		var id = row[0].id;
		layer.open({
			type : 2,
			move : false,
			title : '资源修改',
			area : [ '700px', '650px' ],
			content : './system/goResourceUpdate.shtml?id=' + id
		});
	})

	// 更新列表
	$('#resourceRefresh').click(function() {
		refreshResourceList();
	})

	form.on('submit(formQuery)', function(data) {
		refreshResourceList();
		return false;
	});
})

// 刷新列表，子级调用
function refreshResourceList() {
	var query_resource = $('#query_resource').val();
	var query_resource_name = $('#query_resource_name').val();
	var obj = {};
	obj.query_resource = query_resource;
	obj.query_resource_name = query_resource_name;
	$("#table").jfTable("reload",obj)
}

//开关点击事件
function mds_switch_click(obj){
	var row = $("#table").jfTable("getRow",obj.title)
	var status = row.status==1?2:1;
	
	$.post(rootPath + "/system/updateRresourceStatus.shtml", {
		id : row.id,
		status : status
	}, function(result) {
		if (!result.status) {
			layer.msg(result.message, {
				icon : 5,
				shift : 6
			});
		} else {
			layer.msg('状态修改成功', {
				icon : 1
			});
			refreshResourceList();
		}
	}, "json");
}
