<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title> All currency </title>
    </head>

    <body>
    <h1> All currency: </h1>
    <c:forEach items="${currencyList}" var="info">
        BaseCurrencyName: ${info.baseCurrencyName} <br>
        CurrencyName: ${info.currencyName} <br>
        Time: ${info.requestTime} <br>
        Value: ${info.value} <hr>
    </c:forEach>

    <h2><a href=${gifHttp} target="_blank"> Your gift </a></h2>

    <h3> Author: ${authorName} </h3>
    </body>

</html>