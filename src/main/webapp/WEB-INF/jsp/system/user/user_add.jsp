<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
<link rel="stylesheet" href="${ctx}/css/base.css">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
</head>
<body>
	<div class="body_top_marger10">
		<div class="layui-main layui-form-pane" style="width: 550px">
			<form class="layui-form" action="">
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>用户名</label>
					<div class="layui-input-inline">
						<input type="text" id="username"
							name="username" required
							lay-verify="username" placeholder="请输入用户名" autocomplete="off"
							class="layui-input">
					</div>
					<div class="mds-form-input-aux">PS：用户名只能是字母和数字</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>昵
						称</label>
					<div class="layui-input-inline">
						<input type="text" id="nickname" name="nickname"
							required lay-verify="required"
							placeholder="请输入昵称" autocomplete="off" class="layui-input">
					</div>
					<div class="mds-form-input-aux">PS：中国人的话尽可能使用中文昵称吧</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>用户描述</label>
					<div class="layui-input-block">
						<input type="text" id="userdesc" name="userdesc"
							required lay-verify="required"
							placeholder="随便写点什么吧" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">出生日期</label>
					<div class="layui-input-block">
						<input class="layui-input" id="birth_date" name="birth_date" placeholder="请选择出生日期" lay-verify="birth_date"
							onclick="layui.laydate({elem: this, festival: true})">
					</div>
				</div>
				<div class="layui-form-item" pane>
					<label class="layui-form-label"><font color="red">*</font>用户类型</label>
					<div class="layui-input-block">
						<input type="radio" name="user_type" value="0" title="普通用户" alt="指的是项目的用户，而非后台管理系统的用户"> <input
						type="radio" name="user_type" value="1" title="系统用户" alt="当前该后台管理系统的用户" checked>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">所属组织</label>
					<div class="layui-input-block">
						<select name="organization" lay-verify="required">
							<c:forEach var="organization" items="${organizationList}" varStatus="num">
								<option value="${organization.organization}" >${organization.organizationName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>手机号</label>
					<div class="layui-input-block">
						<input type="text" id="phone_number" name="phone_number"
							required lay-verify="phone"
							placeholder="请输入手机号码" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>电子邮件</label>
					<div class="layui-input-block">
						<input type="text" id="email" name="email"
							required lay-verify="email"
							placeholder="请输入email" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>住址</label>
					<div class="layui-input-block">
						<input type="text" id="address" name="address"
							required lay-verify="required"
							placeholder="请输入住址" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item" pane>
					<label class="layui-form-label">是否启用</label>
					<div class="layui-input-block">
						<input type="checkbox" id="status" checked="checked"
							name="status" lay-skin="switch" lay-text="启用|禁用">
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
	</div>
	<script src="${ctx}/js/jquery.min.js"></script>
	<script src="${ctx}/layui/layui.js"></script>
	<script type="text/javascript">
		var rootPath = '${ctx}';
	</script>
	<script src="${ctx}/js/jfTable.js"></script>
	<script src="${ctx}/js/system/user/user_add.js"></script>
</body>
</html>