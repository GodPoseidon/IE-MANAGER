<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色修改</title>
<link rel="stylesheet" href="${ctx}/css/base.css">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
</head>
<body>
	<div class="body_top_marger10">
		<div class="layui-main layui-form-pane"
			style="width: 700px; min-height: 580px;">
			<div class="layui-tab layui-tab-card">
				<ul class="layui-tab-title">
					<li class="layui-this">角色设置</li>
					<li>用户授权</li>
					<li>权限分配</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<form class="layui-form" action="">
							<div class="hidden_body">
								<input type="hidden" name="id" id="id" value="${role.id}">
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label"><font color="red">*</font>角色编号</label>
								<div class="layui-input-inline">
									<input type="text" id="role" readonly="readonly"
										value="${role.role }" name="role" required
										lay-verify="required" placeholder="请输入角色编号" autocomplete="off"
										class="layui-input">
								</div>
								<div class="mds-form-input-aux">PS：角色编号不能修改</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label"><font color="red">*</font>角色名称</label>
								<div class="layui-input-inline">
									<input type="text" id="role_name" name="role_name"
										value="${role.roleName }" required lay-verify="required"
										placeholder="请输入角色名称" autocomplete="off" class="layui-input">
								</div>
								<div class="mds-form-input-aux">PS：尽可能使用中文名称吧</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label"><font color="red">*</font>角色描述</label>
								<div class="layui-input-block">
									<input type="text" id="role_desc" name="role_desc"
										value="${role.roleDesc }" required lay-verify="required"
										placeholder="随便写点什么吧" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item" pane>
								<label class="layui-form-label"><font color="red">*</font>角色类型</label>
								<div class="layui-input-block">
									<c:if test="${role.roleType==0}">
										<input type="radio" name="role_type" value="0" title="普通角色"
											alt="指的是项目的角色，而非后台管理系统的角色" checked>
										<input type="radio" name="role_type" value="1" title="系统角色"
											alt="当前该后台管理系统的角色">
									</c:if>
									<c:if test="${role.roleType==1}">
										<input type="radio" name="role_type" value="0" title="普通角色"
											alt="指的是项目的角色，而非后台管理系统的角色">
										<input type="radio" name="role_type" value="1" title="系统角色"
											alt="当前该后台管理系统的角色" checked>
									</c:if>
								</div>
							</div>
							<div class="layui-form-item" pane>
								<label class="layui-form-label">是否启用</label>
								<div class="layui-input-block">
									<c:if test="${role.status==1 }">
										<input type="checkbox" id="status" checked="checked"
											name="status" lay-skin="switch" lay-text="启用|禁用">
									</c:if>
									<c:if test="${role.status==2}">
										<input type="checkbox" id="status" name="status"
											lay-skin="switch" lay-text="启用|禁用">
									</c:if>
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-input-block">
									<button class="layui-btn" lay-submit lay-filter="formSubmit">保存</button>
									<button type="reset" class="layui-btn layui-btn-primary">重置</button>
								</div>
							</div>
						</form>
					</div>
					<div class="layui-tab-item" id="role_bind_user"></div>
					<div class="layui-tab-item" id="role_bind_resource"></div>
				</div>
			</div>
		</div>
		<script src="${ctx}/js/jquery.min.js"></script>
		<script src="${ctx}/layui/layui.js"></script>
		<script type="text/javascript">
		var rootPath = '${ctx}';
	</script>
		<script src="${ctx}/js/jfTable.js"></script>
		<script src="${ctx}/js/system/role/role_update.js"></script>
</body>
</html>