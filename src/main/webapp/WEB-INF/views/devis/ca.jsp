
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.math.BigDecimal" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body><p>Chiffre Affaire</p>
    <% BigDecimal chiffreAffaire = (BigDecimal) request.getAttribute("ca");
    %>
    <%= chiffreAffaire%>
</body>
</html>