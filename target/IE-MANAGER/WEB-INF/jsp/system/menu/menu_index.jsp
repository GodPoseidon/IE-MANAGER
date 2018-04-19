<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx}/css/jquery.contextMenu.css"
	media="all">
<script src="${ctx}/js/jquery.contextMenu.js"></script>
<script src="${ctx}/js/system/menu/menu_index.js"></script>

<div class="body_center">
	<div class="mds_tree_panel">
		菜单目录
		<ul id="tree"></ul>
	</div>
	<div class="hidder_body">
		<input value="${options}" type="hidden" id="option" name="option">
	</div>
	<div class="mds_tree_relation">
		<form class="layui-form" action="" id="menu_form">
			<div class="hidder_body">
				<input value="0" type="hidden" id="id" name="id">
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><font color="red">*</font>菜单名称</label>
				<div class="layui-input-block">
					<input type="text" id="menu_name" name="menu_name" required
						lay-verify="required" placeholder="输入个中文名称吧" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" pane>
				<label class="layui-form-label"><font color="red">*</font>菜单类型</label>
				<div class="layui-input-block">
					<input type="radio" name="menu_type" value="1" title="菜单" checked>
					<input type="radio" name="menu_type" value="2" title="按钮">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">菜单</label>
				<div class="layui-input-block">
					<input type="text" id="menu" name="menu" placeholder="菜单路径或js方法" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">上级菜单名称</label>
				<div class="layui-input-block">
					<input type="text" id="parent_menu_name" name="parent_menu_name" readonly="readonly"
						placeholder="上级菜单名称" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">上级菜单路径</label>
				<div class="layui-input-block">
					<input type="text" id="parent_menu_url" name="parent_menu_url" readonly="readonly"
						 autocomplete="off" class="layui-input">
				</div>
			</div>
			<input type="hidden" value="0" name="parent_menu" id="parent_menu">
			<div class="layui-form-item">
				<label class="layui-form-label"><font color="red">*</font>菜单描述</label>
				<div class="layui-input-block">
					<input type="text" id="menu_desc" name="menu_desc" required
						lay-verify="required" placeholder="随便写点什么吧" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><font color="red">*</font>菜单图标</label>
				<div class="layui-input-block">
					<input type="text" id="menu_icon" name="menu_icon" required
						lay-verify="required" placeholder="给个菜单图标样式吧" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" pane>
				<label class="layui-form-label"><font color="red">*</font>菜单范围</label>
				<div class="layui-input-block">
					<input type="radio" name="menu_scope" value="1" title="系统资源"
						checked> <input type="radio" name="menu_scope" value="2"
						title="普通资源">
				</div>
			</div>
			<div class="layui-form-item" pane>
				<label class="layui-form-label">菜单状态</label>
				<div class="layui-input-block">
					<input type="checkbox" id="status" checked="checked" name="status"
						lay-skin="switch" lay-text="启用|禁用">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn bt_greent" lay-submit
						lay-filter="formSubmit">保存</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</div>
</div>
