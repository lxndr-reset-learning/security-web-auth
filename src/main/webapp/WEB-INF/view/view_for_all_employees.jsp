<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  This page is created by IntelliJ IDEA.
  Author: lxndr
  Date: 8/26/2023
  Time: 12:41 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Employee Information</title>
</head>
    <body>
    <h3>Information for All Employees</h3>
    <br>

<!-- Section for HR Role -->
    <security:authorize access="hasRole('HR')">
        <input type="button" value="Salary" onclick="window.location.href='hr_info'"/>
        <p>This section is only accessible for HR employees</p>
    </security:authorize>

    <br>

<!-- Section for Manager Role -->
    <security:authorize access="hasRole('MANAGER')">
        <input type="button" value="Performance" onclick="window.location.href='manager_info'"/>
        <p>This section is only accessible for managers</p>
    </security:authorize>
    </body>
</html>