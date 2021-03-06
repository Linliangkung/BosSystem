<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 引入easyui需要的js和css文件 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>


</head>
<body class="easyui-layout">
	<!-- 使用div元素描述每个区域 -->
	<div title="XXX管理系统" data-options="region:'north'" style="height:100px" >北部区域</div>
	<div title="系统菜单" data-options="region:'west'" style="width:200px">
		<!-- 制作accordion折叠面板 
		data-options="fit:true":表示自适应,填充父窗体
		-->
		<div  class="easyui-accordion" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div data-options="iconCls:'icon-edit'" title="面板一">
				<a id="btn1" class="easyui-linkbutton">添加一个选项卡</a>
			</div>
			
			<script type="text/javascript">
				$(function(){
					//页面加载完全后，为上面按钮绑定事件
					$("#btn1").click(function(){
						//判断选项卡是否已经存在
						if($("#mytabs").tabs("exists","面板四")){
							//如果存在,则选择该选项卡
							$("#mytabs").tabs("select","面板四")
							return;
						}
						//调用tabs 动态添加选项卡
						$("#mytabs").tabs("add",{
							title:"面板四",
							iconCls:"icon-cut",
							closable:true,
							content:'<iframe frameborder="0" height="100%" width="100%" src="https://www.baidu.com"/>'
						});
 					});
				});
			</script>
			<div data-options="iconCls:'icon-save'" title="面板二">222</div>
			<div data-options="iconCls:'icon-cut'" title="面板三 ">333</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<!-- 制作一个tabs选项面板 -->
		<div id="mytabs" class="easyui-tabs" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div data-options="iconCls:'icon-edit'" title="面板一">111</div>
			<div data-options="iconCls:'icon-save',closable:true" title="面板二">222</div>
			<div data-options="iconCls:'icon-cut'" title="面板三 ">333</div>
		</div>
	</div>
	<div data-options="region:'east'" style="width:100px">东部区域</div>
	<div data-options="region:'south'" style="height:50px">南部区域
	</div>

</body>
</html>