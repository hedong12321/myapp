<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>activiti study</title>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    function submitLeave(){
    	var now = new Date();
    	var startStr = now.getFullYear() + '-'
    	           + (now.getMonth() + 1) + '-'
    	           + now.getDate() + ' '
    	           + now.getHours() + ':'
    	           + now.getMinutes() + ':'
    	           + now.getSeconds();
    	
    	var endStr = now.getFullYear() + '-'
                   + (now.getMonth() + 1) + '-'
                   + (now.getDate() + 1) + ' '
                   + now.getHours() + ':'
                   + now.getMinutes() + ':'
                   + now.getSeconds();
        
        var random = Math.floor(Math.random() * (999999999 + 1));
        
    	$('#startTime').val(startStr);
    	$('#endTime').val(endStr);
    	$('#reason').val('我就是要请假' + random + '！');
    	$('#leaveForm').submit();
    }
</script>
</head>
<body>
  <h3>
    我是：${sessionScope.loginUser.firstName } ${sessionScope.loginUser.lastName }
  </h3>
  
  <form action="submitLeave.action" method="post" id="leaveForm">
	  <table>
	    <tr>
	      <td>
	        <select name="leave.leaveType">
	          <option value="公休" selected="selected">公休</option>
	          <option value="调休">调休</option>
	        </select>
	      </td>
	      <td>
	        <input type="text" value="请假开始日期：默认系统时间" name="leave.startTime" id="startTime">
	      </td>
	      <td>
	        <input type="text" value="请假结束日期：默认系统时间  + 1天" name="leave.endTime" id="endTime">
	      </td>
	      <td>
	        <input type="text" value="请假理由：我就是要请假  + 随机数" name="leave.reason" id="reason">
	      </td>
	      <td>
	        <input type="button" value="请 假" onclick="submitLeave()">
	      </td>
	    </tr>
	  </table>
  </form>
  
  <br>
  <a href="getUnfinished.action">unfinished</a>
</body>
</html>