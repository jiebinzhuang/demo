<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div class="pages">
			<span>显示</span>
			<select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10" <%if(request.getAttribute("numPerPage").equals(10)){out.println(" selected='selected'");} %>>10</option>
				<option value="20" <%if(request.getAttribute("numPerPage").equals(50)){out.println(" selected='selected'");} %>>20</option>
				<option value="50" <%if(request.getAttribute("numPerPage").equals(50)){out.println(" selected='selected'");} %>>50</option>
				<option value="100" <%if(request.getAttribute("numPerPage").equals(100)){out.println(" selected='selected'");} %>>100</option>
				<option value="1000" <%if(request.getAttribute("numPerPage").equals(1000)){out.println(" selected='selected'");} %>>1000</option>
			</select>
			<span>条，共${totalCount}条</span>
			<span style="margin-left:5px;"><a href="javascript:navTabPageBreak(10);void(0);"><img src="<%=ctxPath%>/dwz/themes/default/images/clear.gif"/></a></span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="${pageNumShown}" currentPage="${currentPage}"></div>
