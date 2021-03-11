<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title>Hello, world!</title>
    <script>
        function validate() {
            let result = true;
            let fio = $('#username').val();
            let phone = $('#phone').val();
            let regexp = /^[\+\d]\d{10,20}/;
                phone.match(regexp)
            if (phone.match(regexp) === null || fio === '') {
                alert("введите корректные данные")
                result = false;
            }
            return result;
        }
    </script>
</head>
<body>
<%
    int sum = 0;
%>
<div class="container">
    <div class="row pt-3">
        <c:forEach items="${hallPlaces}" var="hallPlace">
        <h3>
            Вы выбрали ряд <c:out value="${hallPlace.row}"/> место <c:out value="${hallPlace.col}"/>, цена : 500 рублей.
            <%
                sum += 500;
            %>

        </h3>
        </c:forEach>
    </div>
    <div class="row pt-3">
        <h4>
        Сумма: <%=sum%> рублей
        </h4>
    </div>
    <div class="row">
        <form action="<%=request.getContextPath()%>/payment" method="post">
            <div class="form-group">
                <label for="username">ФИО</label>
                <input type="text" class="form-control" name="username" id="username" placeholder="Иванов Иван Иванович">
            </div>
            <div class="form-group">
                <label for="phone">Номер телефона</label>
                <input type="text" class="form-control" name="phone" id="phone" placeholder="+79120000000">
            </div>
            <button type="submit" class="btn btn-success" onclick="return validate();">Оплатить</button>
        </form>
    </div>
</div>
</body>
</html>