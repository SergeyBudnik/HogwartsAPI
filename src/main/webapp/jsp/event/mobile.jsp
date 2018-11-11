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
            font-size: 24px;
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

        <fmt:setTimeZone value="GMT+3" scope="session"/>

        <jsp:useBean id="dateValue" class="java.util.Date"/>

        <jsp:setProperty name="dateValue" property="time" value="${event.date}"/>

        <%
            Event event = (Event) request.getAttribute("event");

            String eventStartTime = event.getStartTime().toString().replace("T_", "").replace("_", ":");
            String eventFinishTime = event.getFinishTime().toString().replace("T_", "").replace("_", ":");
        %>

        <c:set var='eventStartTime' value='<%=eventStartTime%>'/>
        <c:set var='eventFinishTime' value='<%=eventFinishTime%>'/>

        <div class="header-secondary">
            Приглашаем Вас к нам в гости на
            <span class="header-secondary-item-highlighted">
                ${event.name}
            </span>
            , который состоится
            <span class="header-secondary-item-highlighted">
                <fmt:formatDate value="${dateValue}" pattern="dd.MM.yyyy"/>
            </span>
            с
            <span class="header-secondary-item-highlighted">
                ${eventStartTime}
            </span>
            до
            <span class="header-secondary-item-highlighted">
                ${eventFinishTime}
            </span>
        </div>

        <br>

        <div class="header-secondary">
            Оставить заявку
        </div>

        <br>

        <input id="name" class="input" placeholder="Имя и фамилия">

        <br>

        <input id="phone" class="input" placeholder="Телефон">

        <br>

        <button id="send" class="button" onclick="sendRequest(${event.id})">Отправить</button>

        <br>

        <div class="policy">Нажимая кнопку отправить,</div>
        <div class="policy">вы соглашаетесь с нашей <a href="https://vk.com/dev/uprivacy">политикой конфиденциальности</a></div>

        <br>
    </div>
    <div class="column">
    </div>
</div>
</body>
</html>
