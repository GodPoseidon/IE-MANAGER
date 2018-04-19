<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/js/system/user/user_index.js"></script>
<div class="body_center">

	<div class="colla-panel">
		<div class="colla-content">
			<form class="layui-form">

				<div class="layui-form-item">
					<label class="layui-form-label">用户名：</label>
					<div class="layui-input-inline">
						<input type="text" id="query_username" name="username" placeholder="请输入需要查询用户名"
							autocomplete="off" class="layui-input">
					</div>
					<label class="layui-form-label">昵称：</label>
					<div class="layui-input-inline">
						<input type="text" id="query_nickname" name="nickname" placeholder="请输入需要查询的昵称"
							autocomplete="off" class="layui-input">
					</div>
					<button class="layui-btn layui-btn-primary" lay-submit lay-filter="formQuery">
						<i class="iconfont">&#xe741;</i>查询
					</button>
					<button type="reset" class="layui-btn layui-btn-primary">
						<i class="iconfont">&#xe6f8;</i>重置
					</button>
				</div>

			</form>
		</div>
	</div>
	<div class="empty-line"></div>
	<div class="div_centent_right">
		<div class="layui-btn-group">
			<button class="layui-btn layui-btn-primary" id="userAdd">
				<i class="iconfont">&#xe6e2;</i> 添加
			</button>
			<button class="layui-btn layui-btn-primary" id="userDel">
				<i class="iconfont">&#xe6f7;</i> 删除
			</button>
			<button class="layui-btn layui-btn-primary" id="userUpdate">
				<i class="iconfont">&#xe6eb;</i> 修改
			</button>
			<button class="layui-btn layui-btn-primary" id="userRefresh">
				<i class="iconfont">&#xe71e;</i> 刷新
			</button>
		</div>
	</div>

	<div id="table"></div>
</div>
