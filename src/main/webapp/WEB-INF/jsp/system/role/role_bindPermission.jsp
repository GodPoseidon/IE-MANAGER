<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加角色</title>
<link rel="stylesheet" href="${ctx}/css/base.css">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
</head>
<body>
	<p class="layui-elem-quote">1、系统类型：作用于该后台管理系统；2、普通类型：作用于其它系统</p>
	<form class="layui-form" action="">
		<div class="layui-form-item" pane>
			<label class="layui-form-label">权限类型</label>
			<div class="layui-input-block">
				<input type="checkbox" name="like[write]" title="普通" checked>
				<input type="checkbox" name="like[read]" title="系统" checked>
			</div>
		</div>
	</form>
	<table>
		<tr>
			<td>
				<div>未选权限</div>
				<input type="text" id="user" name="user" placeholder="请输入权限编号" class="layui-input">
				<select multiple="multiple" id="noPermission"
				style="width: 300px; height: 300px;">
					<c:forEach items="${menuList}" var="menu">
						<option class="role_select_option" value="${menu.menu}">${menu.menuName} [${menu.menuScope==1?"系统权限":"普通权限"}]</option>
					</c:forEach>
			</select>
			</td>
			<td>
				<div class="body_center">
					<div class="body_top_marger10">
						<button class="layui-btn mds_bt1" id="p_oneToLeft">></button>
					</div>
					<div class="body_top_marger10">
						<button class="layui-btn mds_bt1" id="p_allToLeft">>></button>
					</div>
					<div class="body_top_marger10">
						<button class="layui-btn mds_bt1" id="p_oneToRight"><</button>
					</div>
					<div class="body_top_marger10">
						<button class="layui-btn mds_bt1" id="p_allToRight"><<</button>
					</div>
				</div>
			</td>
			<td>
				<div>已选权限</div>
				<input type="text" id="user" name="user" placeholder="请输入权限编号" class="layui-input">
				<select multiple="multiple" id="permission"
				style="width: 300px; height: 300px;">
					
			</select>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		var rootPath = '${ctx}';
	</script>
	<script src="${ctx}/js/system/role/role_bindPermission.js"></script>
</body>
</html>