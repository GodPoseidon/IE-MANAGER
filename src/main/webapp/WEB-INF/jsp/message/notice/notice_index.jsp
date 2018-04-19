<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/js/message/notice/notice_index.js"></script>
<div class="body_center">

	<div class="colla-panel">
		<div class="colla-content">
			<form class="layui-form">

				<div class="layui-form-item">
					<label class="layui-form-label">公告标题：</label>
					<div class="layui-input-inline">
						<input type="text" id="query_notice_title" name="notice_title" placeholder="请输入需要查询用户名"
							autocomplete="off" class="layui-input">
					</div>
					<label class="layui-form-label">公告内容：</label>
					<div class="layui-input-inline">
						<input type="text" id="query_notice_content" name="notice_content" placeholder="请输入需要查询的昵称"
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
			<button class="layui-btn layui-btn-primary" id="noticeAdd">
				<i class="iconfont">&#xe6e2;</i> 添加
			</button>
			<button class="layui-btn layui-btn-primary" id="noticeDel">
				<i class="iconfont">&#xe6f7;</i> 删除
			</button>
			<button class="layui-btn layui-btn-primary" id="noticeUpdate">
				<i class="iconfont">&#xe6eb;</i> 修改
			</button>
			<button class="layui-btn layui-btn-primary" id="noticeRefresh">
				<i class="iconfont">&#xe71e;</i> 刷新
			</button>
			<button class="layui-btn layui-btn-primary" id="noticeRelease">
				<i class="iconfont">&#xe71e;</i> 发布
			</button>
		</div>
	</div>

	<div id="table"></div>
</div>
