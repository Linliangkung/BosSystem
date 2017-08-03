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
<!-- 引入ztree需要的js和css文件 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>


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
			<div data-options="iconCls:'icon-save'" title="面板二">
				<!-- 展示ztree效果：使用标准json数据构造ztree -->
				<ul id="ztree1" class="ztree">
				</ul>
				<script type="text/javascript">
				$(function(){
					 var setting={};
					 //构造节点数据
					 var zNodes=[{name:"节点一",
						 		children:[{name:"孩子一"},
						 		          {name:"孩子二"}]},//每个json对象代表一个节点
					             {name:"节点二"},
					             {name:"节点三"}];
					 //调用ztree Api初始化ztree
					 $.fn.zTree.init($("#ztree1"),setting,zNodes);
				});
				</script>
			</div>
			<div data-options="iconCls:'icon-cut'" title="面板三 ">
				<!-- 展示ztree效果：使用简单json数据构造ztree -->
				<ul id="ztree2" class="ztree">
				</ul>
				<script type="text/javascript">
				$(function(){
					 var setting2={	data: {
							simpleData: {
								enable: true//使用简单json构造ztree节点
							}
						}
					 };
					 //构造节点数据
					 var zNodes2=[{id:1,pId:0,name:"节点一"},
					             {id:2,pId:1,name:"节点二"},
					             {id:3,pId:2,name:"节点三"}];
					 //调用ztree Api初始化ztree
					 $.fn.zTree.init($("#ztree2"),setting2,zNodes2);
				});
				</script>
			</div>
			<div  data-options="iconCls:'icon-cut'" title="面板四">
				<!-- 展示ztree效果：使用ajax获取json数据构造ztree -->
				<ul id="ztree3" class="ztree">
				</ul>
				<script type="text/javascript">
				$(function(){
					 var setting3={	data: {
							simpleData: {
								enable: true//使用简单json构造ztree节点
							}
						},
						callback: {
							onClick: function(event,treeId,treeNode){
								if(treeNode.page==undefined){
									return;
								}
								//判断选项卡是否已经存在
								if($("#mytabs").tabs("exists",treeNode.name)){
									//如果存在,则选择该选项卡
									$("#mytabs").tabs("select",treeNode.name)
									return;
								}
								//调用tabs 动态添加选项卡
								$("#mytabs").tabs("add",{
									title:treeNode.name,
									iconCls:"icon-cut",
									closable:true,
									content:'<iframe frameborder="0" height="100%" width="100%" src="${pageContext.request.contextPath}/'+treeNode.page+'"/>'
								});
							}
						}
					 };
					 //发送ajax请求，获取json数据
					 //jQuery提供六个ajax方法：ajax,post,get,getScript,getJSON,load
					 var url="${pageContext.request.contextPath}/json/menu.json";
					 $.post(url,
							 {},
							 function(data){
								 //调用ztree Api初始化ztree
								 $.fn.zTree.init($("#ztree3"),setting3,data);
							 },
							 'json');
				});
				</script>
			</div>
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