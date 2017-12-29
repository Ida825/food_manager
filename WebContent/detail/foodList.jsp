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
	function confirmDelete(fid){
		if(confirm("是否删除该菜品？")){
			window.location="deleteFood?fid="+fid;
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
			<img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 菜品列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="${pageContext.request.contextPath}/listFood" method="get">
			<input type="hidden" name="method" value="search">
			<input type="text" name="foodname" title="请输入菜品名称" value="${param.foodname}">
			<input type="submit" value="搜索">
		</form>
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>菜编号</td>
				<td>菜名</td>
				<td>所属菜系</td>
				<td>价格</td>
				<td>简介</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
			<c:forEach var="ls" items="${requestScope.pt.data }">
				<tr class="TableDetail1" align="center">
					<td>${pageScope.ls.FOODID }&nbsp;</td>
					<td>${pageScope.ls.FOODNAME }&nbsp;</td>
					<td>${pageScope.ls.TYPENAME}&nbsp;</td>					
	                <td>${pageScope.ls.PRICE }&nbsp;</td>
	                <td>${pageScope.ls.DESCR }&nbsp;</td>
					<td>
						<a href="${pageContext.request.contextPath}/detail/updateFood.jsp?foodid=${pageScope.ls.FOODID}&foodname=${pageScope.ls.FOODNAME}&typeid=${pageScope.ls.TYPEID}&price=${pageScope.ls.PRICE }&descr=${pageScope.ls.DESCR }&img=${pageScope.ls.IMG}" class="FunctionButton">更新</a>				
						<a href="javascript:confirmDelete(${pageScope.ls.FOODID})" class="FunctionButton">删除</a>				
					</td>
				</tr>
        	</c:forEach>
			
			<tr>
				<td colspan="5">
					<a href="${pageContext.request.contextPath}/listFood?curPage=1">首页</a>
					<a href="${pageContext.request.contextPath}/listFood?curPage=${requestScope.pt.prePage}">上页</a>
					<c:forEach var="i" begin="1" end="${requestScope.pt.totalPage}">
						<a href="${pageContext.request.contextPath}/listFood?curPage=${pageScope.i}">${pageScope.i}</a>
					</c:forEach>
					<a href="${pageContext.request.contextPath}/listFood?curPage=${requestScope.pt.nextPage}">下页</a>
					<a href="${pageContext.request.contextPath}/listFood?curPage=${requestScope.pt.totalPage}">尾页</a>
				</td>
			</tr>
        
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath}/detail/saveFood.jsp">添加</a></div>
    </div> 
</div>
</body>
</html>
