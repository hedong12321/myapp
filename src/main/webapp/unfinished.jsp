<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>activiti study</title>

<style type="text/css">
    .tabCls {
        padding: 0;
        margin: 0;
        border: none;
        background-color: #999;
    }
    .tabCls td {
        padding: 2px;
        margin: 0;
        border: none;
        background-color: white;
    }
</style>
</head>
<body>
  <h3>
    我是：${sessionScope.loginUser.firstName } ${sessionScope.loginUser.lastName }
  </h3>
  <table class="tabCls">
    <tr><td colspan="8">我发布的任务（未完成）</td></tr>
    <tr>
        <td>ID</td>
        <td>申请人</td>
        <td>开始时间</td>
        <td>结束时间</td>
        <td>类型</td>
        <td>请假原因</td>
        <td>当前节点</td>
        <td>处理人</td>
    </tr>
    <s:if test="voLst != null">
        <s:iterator value="voLst" var="leave">
            <tr>
		        <td>${leave.id }</td>
		        <td>${leave.userName }</td>
		        <td>${leave.startTime }</td>
		        <td>${leave.endTime }</td>
		        <td>${leave.leaveType }</td>
		        <td>${leave.reason }</td>
		        <td>${leave.currNode }</td>
		        <td>${leave.disposeUser }</td>
		    </tr>
        </s:iterator>
    </s:if>
  </table>
  
  <br>
  <table class="tabCls">
    <tr><td colspan="6">我发布的任务（已完成）</td></tr>
    <tr>
        <td>ID</td>
        <td>申请人</td>
        <td>开始时间</td>
        <td>结束时间</td>
        <td>类型</td>
        <td>请假原因</td>
    </tr>
  </table>
  
  <br>
  <table class="tabCls">
    <tr><td colspan="9">需要我处理的任务</td></tr>
    <tr>
        <td>ID</td>
        <td>申请人</td>
        <td>开始时间</td>
        <td>结束时间</td>
        <td>类型</td>
        <td>请假原因</td>
        <td>当前节点</td>
        <td>处理人</td>
        <td>操作</td>
    </tr>
    <s:if test="dealWithLst != null">
        <s:iterator value="dealWithLst" var="leave">
            <tr>
                <td>${leave.id }</td>
                <td>${leave.userName }</td>
                <td>${leave.startTime }</td>
                <td>${leave.endTime }</td>
                <td>${leave.leaveType }</td>
                <td>${leave.reason }</td>
                <td>${leave.currNode }</td>
                <td>${leave.disposeUser }</td>
                <td>
                    <a href="sign.action?params.taskId=${leave.taskId }">签收</a>
                    <a href="dealWith.action?params.taskId=${leave.taskId }">经理同意</a>
                    <a href="dealWith.action?params.taskId=${leave.taskId }">人事同意</a>
                    <a href="dealWith.action?params.taskId=${leave.taskId }">销假</a>
                </td>
            </tr>
        </s:iterator>
    </s:if>
  </table>
</body>
</html>