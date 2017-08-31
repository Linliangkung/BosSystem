<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 引入easyui需要的js和css文件 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	
</script>

</head>

<body>
	<!-- 方式一:将静态html渲染成datagrid样式 -->
	<table class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>001</td>
				<td>name1</td>
				<td>12</td>
			</tr>
			<tr>
				<td>002</td>
				<td>name2</td>
				<td>32</td>
			</tr>
		</tbody>
	</table>

	<!-- 方式二：发送ajax请求获取json数据构建datagrid -->
	<table class="easyui-datagrid"
		data-options="url:'${pageContext.request.contextPath }/json/datagrid_data.json'">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>
		</thead>
	</table>

	<!-- 方式三：使用easyui提供的api创建datagrid -->
	<table id="mytable">
	</table>
	<script type="text/javascript">
		$(function() {
			//创建datagrid
			$("#mytable")
					.datagrid(
							{
								//定义标题行所有的列
								"columns" : [ [ {
									"title" : "编号",
									"field" : "id",
									"checkbox" : true
								}, {
									"title" : "姓名",
									"field" : "name"
								}, {
									"title" : "年龄",
									"field" : "age"
								} ] ],
								"url" : "${pageContext.request.contextPath }/json/datagrid_data.json",
								"rownumbers" : true,
								"singleSelect" : true,
								//定义工具栏
								"toolbar" : [ {
									"text" : "添加",
									"iconCls" : "icon-add",
									"handler" : function() {
										alert("添加");
									}
								}, {
									"text" : "删除",
									"iconCls" : "icon-remove"
								}, {
									"text" : "修改",
									"iconCls" : "icon-edit"
								}, {
									"text" : "查询",
									"iconCls" : "icon-search"
								} ],
								"pagination" : true
							});
		});
	</script>
</body>
</html>