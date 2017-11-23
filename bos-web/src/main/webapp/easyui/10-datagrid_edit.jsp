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
	<!-- 方式三：使用easyui提供的api创建datagrid -->
	<table id="mytable">
	</table>
	<script type="text/javascript">
		var myIndex=-1;
		var flag=true;
		$(function() {
			//创建datagrid
			$("#mytable").datagrid({
				//定义标题行所有的列
				"columns" : [ [ {
					"title" : "编号",
					"field" : "id",
					"checkbox" : true,
				}, {
					"width" : 100,
					"title" : "姓名",
					"field" : "name",
					"editor" : {
						"type" : "validatebox",
						"options" : {}
					}
				}, {
					"width" : 100,
					"title" : "年龄",
					"field" : "age",
					"editor" : {
						"type" : "numberbox",
						"options" : {}
					}
				}, {
					"width" : 100,
					"title" : "日期",
					"field" : "date",
					"editor" : {
						"type" : "datebox",
						"options" : {}
					}
				} ] ],
				"url" : "${pageContext.request.contextPath }/json/datagrid_data.json",
				"rownumbers" : true,
				"singleSelect" : true,
				//定义工具栏
				"toolbar" : [ {
					"text" : "添加",
					"iconCls" : "icon-add",
					"handler" : function() {
						if(flag){
						$("#mytable").datagrid("insertRow", {
							"index" : 0,
							"row" : {}
						});
						myIndex=0;
						$("#mytable").datagrid("beginEdit", myIndex);
						flag=false;
						}
					}
				}, {
					"text" : "删除",
					"iconCls" : "icon-remove",
					"handler" : function() {
						var rows=$("#mytable").datagrid("getSelections");
						myIndex=$("#mytable").datagrid("getRowIndex",rows[0]);
						$("#mytable").datagrid("deleteRow", myIndex);
					}
				}, {
					"text" : "修改",
					"iconCls" : "icon-edit",
					"handler" : function() {
						if(flag){
						var rows=$("#mytable").datagrid("getSelections");
						myIndex=$("#mytable").datagrid("getRowIndex",rows[0]);
						$("#mytable").datagrid("beginEdit", myIndex);
						flag=false;
						}
					}
				}, {
					"text" : "保存",
					"iconCls" : "icon-search",
					"handler" : function() {
						$("#mytable").datagrid("endEdit", myIndex);
						flag=true;
					}
				} ],
				"pagination" : true,
				"onAfterEdit":function(rowIndex,rowData,changes){
					alert(rowData.name);
				}
			});
		});
	</script>
</body>
</html>