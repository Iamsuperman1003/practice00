<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>員工資料修改</title>
</head>
<body>
<h3>員工資料修改</h3>
<c:if test="${not empty errorMsgs}">
    <font color='red'>請修正以下錯誤:
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li>${message}</li>
            </c:forEach>
        </ul>
    </font>
</c:if>
<form method="POST" action="${pageContext.request.contextPath}/emp/emp.do" name="form1">
    <table>
        <tr>
            <td>員工編號:<font color=red><b>*</b></font></td>
            <td>${empDO.empno}</td>
        </tr>
        <tr>
            <td>員工姓名:</td>
            <td>
                <input type="TEXT" name="ename" size="45" value="${empDO.ename}" />
            </td>
        </tr>
        <tr>
            <td>職位:</td>
            <td>
                <input type="TEXT" name="job" size="45"	value="${empDO.job}" />
            </td>
        </tr>
        <tr>
            <td>雇用日期:</td>
            <td>
                <input type="date" name="hiredate" value="${empDO.hiredate}" />
            </td>
        </tr>
        <tr>
            <td>薪水:</td>
            <td>
                <input type="TEXT" name="sal" size="45"	value="${empDO.sal}" />
            </td>
        </tr>
        <tr>
            <td>獎金:</td>
            <td>
                <input type="TEXT" name="comm" size="45" value="${empDO.comm}" />
            </td>
        </tr>
        <tr>
            <td>部門:<font color=red><b>*</b></font></td>
            <td>
                <select size="1" name="deptno">
                    <c:forEach var="deptDO" items="${deptDOs}">
                        <option value="${deptDO.deptno}" ${(empDO.deptDO.deptno==deptDO.deptno) ? 'selected' : ''} >${deptDO.dname}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <br />
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="empno" value="${empDO.empno}">
    <a href="${pageContext.request.contextPath}/">回首頁</a>
    <input type="submit" value="送出修改">
</form>
</body>
</html>
