layui.use([ 'element', 'form' ], function() {
	var element = layui.element(), form = layui.form();
	var config = {
		url : "./system/getUserPage.shtml",// 获取数据的url
		page : true,
		select : true,
		columns : [ {
			text : 'id',
			name : 'id',
			width : 20
		}, {
			text : '用户名',
			name : 'username',
			width : 10
		}, {
			text : '昵称',
			name : 'nickname',
			width : 10
		}, {
			text : '用户描述',
			name : 'userdesc',
			width : 10
		}, {
			text : '出生日期',
			name : 'birthDate',
			width : 10
		}, {
			text : '组织',
			name : 'organization',
			width : 10
		}, {
			text : '用户类型',
			name : 'userType',
			width : 10,
			formatter : function(value, row, index) {
				if (value == "0" || value == 0) {
					return "普通用户";
				} else if (value == "1" || value == 1) {
					return "系统用户";
				}
			}
		}, {
			text : '手机号',
			name : 'phoneNumber',
			width : 10
		}, {
			text : '住址',
			name : 'address',
			width : 10
		}, {
			text : '电子邮件',
			name : 'email',
			width : 10
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
		} ],
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

	// 添加用户方法
	$('#userAdd').click(function() {
		layer.open({
			type : 2,
			move : false,
			title : '添加用户',
			area : [ '700px', '650px' ],
			content : './system/goAddUserPage.shtml'
		});
	})

	// 删除用户方法
	$('#userDel').click(function() {
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
			$.post(rootPath + "/system/userDel.shtml", {
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
					parent.refreshUserList();
				}
			}, "json");
			refreshUserList();
		});
	})

	// 更新用户方法
	$('#userUpdate').click(function() {
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
			title : '用户资料修改',
			area : [ '700px', '650px' ],
			content : './system/goUpdateUserPage.shtml?id=' + id
		});
	})

	// 更新用户方法
	$('#userRefresh').click(function() {
		refreshUserList();
	})
	
	form.on('submit(formQuery)', function(data) {
		console.log($('#query_username').val());
		console.log($('#query_nickname').val());
		refreshUserList();
		return false;
	});
})

// 刷新用户列表
function refreshUserList() {
	var username = $('#query_username').val();
	var nickname = $('#query_nickname').val();
	var obj = {};
	obj.username = username;
	obj.nickname = nickname;
	$("#table").jfTable("reload",obj);
}

//开关点击事件
function mds_switch_click(obj){
	var row = $("#table").jfTable("getRow",obj.title)
	var status = row.status==1?2:1;
	
	if (row.username=="admin") {
		layer.msg("admin用户不能被禁用", {
			icon : 5,
			shift : 6
		});
		return;
	}
	
	$.post(rootPath + "/system/updateStatus.shtml", {
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
			refreshUserList();
		}
	}, "json");
}
