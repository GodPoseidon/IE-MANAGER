<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx}/css/jquery.contextMenu.css" media="all">
<script src="${ctx}/js/jquery.contextMenu.js"></script>
<script src="${ctx}/js/system/organization/organization_index.js"></script>

<div class="body_center">
	<div class="mds_tree_panel">
				组织目录
				<ul id="tree"></ul>
	</div>
	<div class="hidder_body">
		<input value="${options}" type="hidden" id="option" name="option">
	</div>
	<div class="mds_tree_relation">
		<form class="layui-form" action="" id="organization_form">
			<div class="hidden_body">
				<input value="0" type="hidden" id="organization_parent" name="organization_parent">
				<input value="0" type="hidden" id="id" name="id">
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><font color="red">*</font>组织编号</label>
				<div class="layui-input-block">
					<input type="text" id="organization" name="organization" required
						lay-verify="required" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><font color="red">*</font>组织名称</label>
				<div class="layui-input-block">
					<input type="text" id="organization_name" name="organization_name" required
						lay-verify="required" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">上级组织</label>
				<div class="layui-input-block">
					<input type="text" id="organization_parent_name" name="organization_parent_name" readonly="readonly"
						autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">组织描述</label>
				<div class="layui-input-block">
					<input type="text" id="organization_desc" name="organization_desc" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" pane>
				<label class="layui-form-label"><font color="red">*</font>组织范围</label>
				<div class="layui-input-block">
					<input type="radio" name="organization_scope" value="2" title="普通范围" > <input
					type="radio" name="organization_scope" value="1" title="系统范围" checked>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">内部电话</label>
				<div class="layui-input-block">
					<input type="text" id="organization_innerPhone" name="organization_innerPhone" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">外部电话</label>
				<div class="layui-input-block">
					<input type="text" id="organization_outerPhone" name="organization_outerPhone" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><font color="red">*</font>地址</label>
				<div class="layui-input-block">
					<input type="text" id="organization_address" name="organization_address" required
						lay-verify="required" autocomplete="off"
						class="layui-input">
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
