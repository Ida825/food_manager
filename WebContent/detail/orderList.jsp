<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/detail/style/css/index_1.css" />
	<script type="text/javascript">
		
		
		function confirmUpdate(orderid,orderstate) {
			if(confirm("是否确认结账?")){
				if(orderstate==0){
					orderstate=1;
				}
								
				window.location="updateOrder?orderid="+orderid+"&orderstate="+orderstate;
			}		
		}
	</script>
</head>
<body>
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="style/images/title_arrow.gif" /> 餐厅订单列表
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>

	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>订单编号</td>
					<td>餐桌名</td>
					<td>下单日期</td>
					<td>总金额</td>
					<td>状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData">
				<c:forEach var="ls" items="${requestScope.pt.data }">
			 		<tr height="60" align="center">
				 		<td>${pageScope.ls.ORDERID }</td>
				 		<td>${pageScope.ls.DNAME }</td>
				 		<td>${pageScope.ls.ORDERTIME }</td>
				 		<td>${pageScope.ls.TP }</td>
		
				 				<td>${pageScope.ls.ORDERSTATE==0?'未结账':'已结账'}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				 			
				 				</td>
		
				 		<td>
							<a href="${pageContext.request.contextPath}/orderDetail?orderid=${pageScope.ls.ORDERID }" class="FunctionButton">详细</a> 
				 			
				 				<a href="javascript:confirmUpdate(${pageScope.ls.ORDERID},${pageScope.ls.ORDERSTATE})" class="FunctionButton">结账</a>
				 			
				 		</td>
			 		</tr>
			 	</c:forEach>
			</tbody>
		</table>
		<!-- 其他功能超链接 -->
		<div id="TableTail" align="center">
		</div>
	</div>
</body>
</html>
