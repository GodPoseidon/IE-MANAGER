<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改系统公告</title>
<link rel="stylesheet" href="${ctx}/css/base.css">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">
</head>
<body>
	<div class="body_top_marger10">
		<div class="layui-main layui-form-pane" style="width: 550px">
			<form class="layui-form" action="">
				<div class="hidden_body">
					<input type="hidden" value="${notice.id }" name="id" id="id">
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>公告标题</label>
					<div class="layui-input-block">
						<input type="text" id="noticeTitle" value="${notice.noticeTitle }"
							name="noticeTitle" required
							lay-verify="required" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>公告内容</label>
					<div class="layui-input-block">
						<input type="text" id="noticeContent" name="noticeContent" value="${notice.noticeContent }"
							required lay-verify="required"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>来源</label>
					<div class="layui-input-block">
						<input type="text" id="noticeFrom" name="noticeFrom" value="${notice.noticeFrom }"
							required lay-verify="required"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"><font color="red">*</font>发送至</label>
					<div class="layui-input-block">
						<input type="text" id="noticeTo" name="noticeTo" value="${notice.noticeTo }"
							required lay-verify="required"
							 autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item" pane>
					<label class="layui-form-label"><font color="red">*</font>公告范围</label>
					<div class="layui-input-block">
						<c:if test="${notice.noiceScope==1 }">
							<input type="radio" name="noiceScope" value="2" title="普通级"> <input
							type="radio" name="noiceScope" value="1" title="系统级" checked>
						</c:if>
						<c:if test="${notice.noiceScope==2 }">
							<input type="radio" name="noiceScope" value="2" title="普通级" checked> <input
							type="radio" name="noiceScope" value="1" title="系统级" >
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
	</div>
	<script src="${ctx}/js/jquery.min.js"></script>
	<script src="${ctx}/layui/layui.js"></script>
	<script type="text/javascript">
		var rootPath = '${ctx}';
	</script>
	<script src="${ctx}/js/jfTable.js"></script>
	<script src="${ctx}/js/message/notice/notice_update.js"></script>
</body>
</html>