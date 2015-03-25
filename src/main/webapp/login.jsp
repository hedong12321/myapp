<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>activiti study</title>
</head>
<body>
<form action="login.action" method="post">
	<table>
	    <tr>
	      <td>邮 箱：</td>
	      <td><input type="text" name="email"></td>
	    </tr>
	    <tr>
	      <td>密 码：</td>
	      <td><input type="password" name="password"></td>
	    </tr>
	    <tr>
	      <td colspan="2" align="center"><input type="submit" value="登陆"></td>
	    </tr>
	</table>
</form>
</body>
</html>