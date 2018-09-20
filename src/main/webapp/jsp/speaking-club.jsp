<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Курсы английского Hogwarts - оставить заявку</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/speaking-club/desktop/speaking-club.css"/>"/>

    <script src="../scripts/script.js"></script>

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
            <img src="../../images/logo.png" class="header-logo">

            <div>Курсы английского Hogwarts</div>
        </div>

        <br>

        <div class="header-secondary">Приглашаем Вас к нам в гости</div>

        <br>
        <br>

        <div class="advantage-items">
            <div class="advantage-item">
                <div class="advantage-item-title">Отличный результат гарантирован</div>
                <br>
                <div class="advantage-item-text">Группы от 2 до 6 человек - преподаватель уделит ребёнку столько же времени, сколько и на индивидуальных занятиях!</div>
            </div>

            <div class="advantage-item">
                <div class="advantage-item-title">Не нужно ждать группу</div>
                <br>
                <div class="advantage-item-text">Новые группы стартуют каждую неделю! Приходи и сразу же начинай заниматься.</div>
            </div>

            <div class="advantage-item">
                <div class="advantage-item-title">Удобное расположение</div>
                <br>
                <div class="advantage-item-text">Ваш ребёнок будет экономить время и успеет посетить больше полезных кружков.</div>
            </div>
        </div>

        <br>
        <br>

        <div class="header-secondary">
            Хотите узнать о нас больше?
            <a href="https://vk.com/hogwarts_engschool">Заходите в нашу группу в VK</a>
        </div>

        <br>

        <div class="header-secondary">
            Оставьте заявку
            <br>
            Мы перезвоним Вам в течение 10 минут
        </div>

        <br>

        <input id="name" class="input" placeholder="Введите имя">

        <br>

        <input id="phone" class="input" placeholder="Введите телефон">

        <br>

        <button id="send" class="button" onclick="sendRequest()">Отправить</button>

        <br>
        <br>

        <div class="policy">Нажимая кнопку отправить,</div>
        <div class="policy">вы соглашаетесь с нашей <a href="https://vk.com/dev/uprivacy">политикой конфиденциальности</a></div>
    </div>
    <div class="column">
    </div>
</div>
</body>
</html>
