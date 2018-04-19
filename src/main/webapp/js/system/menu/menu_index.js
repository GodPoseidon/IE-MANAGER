var id, options, node_, url;
layui.use([ 'tree', 'form' ], function() {
	var form = layui.form();
	var ops = eval($('#option').val());
	options = {
		elem : '#tree',
		nodes : ops,
		click : function(node) {
			url = rootPath + "/system/menuAdd.shtml";
			$('#menu_form')[0].reset();
			$('#parent_menu_name').val(node.name);
			$('#parent_menu_url').val(node.dataUrl);
			$('#parent_menu').val(node.id);
			$('#id').val(node.id);
			node_ = node;
		}
	}
	layui.tree(options);

	form.on('submit(formSubmit)', function(data) {
		$.post(url, data.field,
				function(result) {
					if (!result.status) {
						layer.msg(result.message, {
							icon : 5,
							shift : 6
						});
					} else {
						layer.msg('操作成功', {
							icon : 1
						});
					}
				}, "json");
		return false;
	});
	
});

$(function() {
	$.contextMenu({
		width : 110,// width
		itemHeight : 30,// 菜单项height
		bgColor : "#333",// 背景颜色
		color : "#fff",// 字体颜色
		fontSize : 12,// 字体大小
		target : function(ele) {// 当前元素
			console.log(ele);
		},
		selector : '.layui-tree li a',
		callback : function(key, ops) {
			if (key == "edit") {
				edit_menu(ops);
			} else if (key == "delete") {
				delete_menu(ops);
			}
		},
		items : {
			"edit" : {
				name : "<i class='iconfont'>&#xe6eb;</i> 修改"
			},
			"delete" : {
				name : "<i class='layui-icon'>&#xe640;</i> 删除"
			}
		}
	});
});

function edit_menu(ops) {
	if (node_==undefined) {
		layer.msg("请先左击一个节点再修改", {
			icon : 5,
			shift : 6
		});
		return;
	}
	$.post(rootPath + "/system/getNodeInfo.shtml", {id : node_.id},
	function(result) {
		var result = eval('(' + result + ')');
		if (!result.status) {
			layer.msg(result.message, {
				icon : 5,
				shift : 6
			});
		} else {
			$('#menu_name').val(result.content[0].menu_name);
			//menu_type
			$('#menu').val(result.content[0].menu);
			$('#parent_menu_name').val(result.content[0].p_name);
			$('#parent_menu_url').val(result.content[0].p_menu);
			$('#parent_menu').val(result.content[0].p_id);
			$('#menu_desc').val(result.content[0].menu_desc);
			$('#menu_icon').val(result.content[0].menu_icon);
			//$('#menu_name').val(result.content[0].menu_name);
			if (result.content[0].status==1 || result.content[0].status=="1") {
				$('#status').attr("checked","checked");
			}else {
				$('#status').removeAttr("checked");
			}
			url = rootPath + "/system/updateMenu.shtml";
		}
	}, "json");
}

function delete_menu(ops) {
	if (node_==undefined) {
		layer.msg("请先左击一个节点再删除", {
			icon : 5,
			shift : 6
		});
		return;
	}
	$.post(rootPath + "/system/delNodeInfo.shtml", {id : node_.id},
	function(result) {
		if (!result.status) {
			layer.msg(result.message, {
				icon : 5,
				shift : 6
			});
		} else {
			layer.msg('删除成功', {
				icon : 1
			});
		}
	}, "json");
}

function reload(){
	$.post(rootPath + "/system/loadTreeInfo.shtml",
		function(result) {
			var ops = eval($('#option').val());
			options = {
				elem : '#tree',
				nodes : ops,
				click : function(node) {
					$('#parent_menu_name').val(node.name);
					$('#parent_menu_url').val(node.dataUrl);
					$('#parent_menu').val(node.id);
					node_ = node;
				}
			}
			//layui.tree(options);
		}, "json");
}