<%@ page import="com.bdev.hogwarts_api.data.dto.events.Event" %>
<%@ page import="java.util.*" %>
<%@ page import="com.bdev.hogwarts_api.data.dto.common.DayOfWeek" %>
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

    <script>
        function onTestClicked(id) {
            window.location.href = '/HogwartsAPI/pages/events/specific/' + id + '/DESKTOP';
        }
    </script>

    <style>
        .header-desktop {
            font-size: 36px;
        }

        .picker-items-row {
            margin-top: 5px;
        }

        .picker-item-day {
            width: 100%;
            margin-right: 10px;
            padding-left: 0;
            padding-right: 0;
            text-align: center;
            float: left;
        }

        .picker-item-variant {
            margin-top: 2px;
            margin-right: 2px;
            padding-left: 5px;
            padding-right: 5px;
            float: left;
            text-align: center;
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

        <fmt:setTimeZone value="GMT+3" scope="session"/>

        <jsp:useBean id="dateValue" class="java.util.Date"/>

        <div class="header-secondary">
            Приглашаем Вас на бесплатное тестирование к нам на курсы!
        </div>

        <br>

        <div class="header-secondary">
            Выберите дату и время тестирования:
        </div>

        <br>

        <%
            Map<DayOfWeek, List<Event>> eventsByDayOfWeek =
                    (Map<DayOfWeek, List<Event>>) request.getAttribute("events");

            String dayOfWeekString = "";

            for (DayOfWeek dayOfWeek : eventsByDayOfWeek.keySet()) {
                switch (dayOfWeek) {
                    case SUNDAY:
                        dayOfWeekString = "Вс";
                        break;
                    case MONDAY:
                        dayOfWeekString = "Пн";
                        break;
                    case TUESDAY:
                        dayOfWeekString = "Вт";
                        break;
                    case WEDNESDAY:
                        dayOfWeekString = "Ср";
                        break;
                    case THURSDAY:
                        dayOfWeekString = "Чт";
                        break;
                    case FRIDAY:
                        dayOfWeekString = "Пт";
                        break;
                    case SATURDAY:
                        dayOfWeekString = "Сб";
                        break;
                }

                List<Event> events = eventsByDayOfWeek.get(dayOfWeek);

        %>
        <c:set var='firstEvent' value="<%=events.get(0)%>"/>
        <c:set var='eventDayOfWeek' value='<%=dayOfWeekString%>'/>

        <jsp:setProperty name="dateValue" property="time" value="${firstEvent.date}"/>

        <div class="picker-items-row">
            <span class="row-label picker-item-day">
                <b>${eventDayOfWeek}</b>,
                <fmt:formatDate value="${dateValue}" pattern="dd.MM.yyyy"/>
            </span>
        </div>

        <div class="picker-items-row">
            <%

                for (Event event : events) {
                    String eventStartTime = event.getStartTime().toString().replace("T_", "").replace("_", ":");
                    String eventFinishTime = event.getFinishTime().toString().replace("T_", "").replace("_", ":");

            %>
            <c:set var='eventStartTime' value='<%=eventStartTime%>'/>
            <c:set var='eventFinishTime' value='<%=eventFinishTime%>'/>

            <c:set var='event' value='<%=event%>'/>

            <span class="button-secondary picker-item-variant" onclick="onTestClicked(${event.id})">
                            ${eventStartTime} - ${eventFinishTime}
                        </span>
            <%
                }
            %>
        </div>
        <%
            }
        %>

        <br>
    </div>
    <div class="column">
    </div>
</div>
</body>
</html>
