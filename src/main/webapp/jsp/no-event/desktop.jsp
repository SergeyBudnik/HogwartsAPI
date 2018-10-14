<%@ page import="com.bdev.hogwarts_api.data.dto.events.Event" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Курсы английского Hogwarts - оставить заявку</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/speaking-club/desktop/speaking-club.css"/>"/>

    <script src="<c:url value="/resources/scripts/script.js"/>"></script>

    <style>
        .header-desktop {
            font-size: 36px;
        }
    </style>
</head>
<body class="body-container">
<div class="container">
    <div class="column">
    </div>
    <div class="content-container">
        <br>

        <div class="header header-desktop">
            <img src="<c:url value="/resources/images/logo.png"/>" class="header-logo">

            <div>Курсы английского языка Hogwarts</div>
        </div>

        <br>
        <br>

        <div class="header-secondary">
            На текущий момент мероприятие не запланировано.
        </div>

        <br>
        <br>

        <div class="header-secondary">
            Хотите узнать о нас больше?
            <br>
            <a href="https://vk.com/hogwarts_engschool">Наша группа в VK</a>
        </div>
    </div>
    <div class="column">
    </div>
</div>
</body>
</html>
