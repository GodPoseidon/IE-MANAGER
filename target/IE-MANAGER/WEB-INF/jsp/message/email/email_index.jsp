<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/js/message/email/email_index.js"></script>
<div class="body_center">

	<div class="colla-panel">
		<div class="colla-content">
			<form class="layui-form">

				<div class="layui-form-item">
					<label class="layui-form-label">发送人：</label>
					<div class="layui-input-inline">
						<input type="text" id="query_emailTouser" name="query_emailTouser" placeholder="请输入需要查询的发送人"
							autocomplete="off" class="layui-input">
					</div>
					<label class="layui-form-label">收件人：</label>
					<div class="layui-input-inline">
						<input type="text" id="query_emailFromuser" name="query_emailFromuser" placeholder="请输入需要查询的收件人"
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
	<div id="table"></div>
</div>
