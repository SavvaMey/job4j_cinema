<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <%@page contentType="text/html; charset=UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<script>
    $(document).ready(function() {
        $.ajax({
            type: 'GET',
            crossdomain: true,
            dataType: 'json',
            url: 'http://localhost:8080/cinema/index',
        }).done(function(data) {
            let addTr = '';
            $.each(data, function(i, item) {
                if (item.idPlace === 1 || item.idPlace === 4 || item.idPlace === 7)
                {
                    // addTr += '<tr>' + '<th>' + i + '</th>';
                    if (item.idPlace === 1) {
                        addTr += '<tr>' + '<th>1</th>';
                    } else if (item.idPlace === 4) {
                        addTr += '<tr>' + '<th>2</th>';
                    } else {
                        addTr += '<tr>' + '<th>3</th>';
                    }
                }
                var occupied = item.status;
                if (occupied === false) {
                    addTr +=
                        '<td BGCOLOR="GREEN">' + '<input type="checkbox" name="place" '
                    + 'value="' + item.idPlace + '">'
                    + ' Ряд '+ item.row + ', Место ' + item.column + '</td>';
                } else {
                    addTr +=
                        '<td BGCOLOR="RED">' + '<input type="checkbox" disabled name="place" '
                        + 'value="' + item.row + item.column + '">'
                        + ' Место занято'+ '</td>';
                }
                if (item.idPlace === 3 || item.idPlace === 6 || item.idPlace === 9)
                {
                    addTr += '</tr>';
                }
            });
            $('#Hall tbody').after(addTr);
        }).fail(function(err){
            alert(err);
        });
     });
</script>
<%--<script>--%>
<%--    $(document).ready(function(){--%>
<%--        $("#button").click(function(){--%>
<%--                var selected = [];--%>
<%--                $.each($("input[name='place']:checked"), function(){--%>
<%--                    selected.push($(this).val());--%>
<%--                });--%>
<%--                selected = JSON.stringify(selected);--%>
<%--                console.log(selected);--%>
<%--                if (selected.length !== 0 ) {--%>
<%--                    $.ajax({--%>
<%--                        type: 'POST',--%>
<%--                        url: 'http://localhost:8080/cinema/index',--%>
<%--                        data: selected,--%>
<%--                        contentType: 'application/json; charset=utf-8',--%>
<%--                        dataType: 'json',--%>
<%--                        // success: function() {--%>
<%--                        //     window.location.href = '/cinema' + "/payment.jsp";--%>
<%--                        // }--%>
<%--                    });--%>
<%--                } else {--%>
<%--                    alert('выберите места')--%>
<%--                }--%>

<%--         });--%>
<%--    });--%>
<%--</script>--%>
<script>
    function validate() {
        let result = true;
        let selected = [];
        $.each($("input[name='place']:checked"), function(){
            selected.push($(this).val());
        });
        if (selected.length === 0 ) {
            alert("не выбраны места");
            result = false;
        }
        return result;
    }
</script>
<script>
    $(document).ready(function () {
        setTimeout(function () {
            location.reload(true);
        }, 20000);
    });
</script>
</head>
<body>
    <form action="<%=request.getContextPath()%>/payment" method="get" >
        <div class="container">
            <div class="row pt-3">
                    <h4>
                        Бронирование мест на сеанс
                    </h4>
                <table class="table table-bordered" id="Hall">
                    <thead>
                    <tr>
                        <th style="width: 120px;">Ряд / Место</th>
                        <th>1</th>
                        <th>2</th>
                        <th>3</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="row float-right">
                <button type="submit" class="btn btn-primary" onclick="return validate()">отправить</button>
            </div>
        </div>
    </form>
</body>
</html>