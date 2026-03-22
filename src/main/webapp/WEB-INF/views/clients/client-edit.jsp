<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.forage.model.Client" %>

<%
Client c = (Client) request.getAttribute("client");
%>

<html>
<head>
    <title>Modifier Client</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

<div class="container">

<h1>Modifier Client</h1>

<form method="post" action="/clients/update">

    <input type="hidden" name="id" value="<%=c.getId()%>">

    <div class="form-group">
        <label>Nom</label>
        <input type="text" name="nom" value="<%=c.getNom()%>">
    </div>

    <div class="form-group">
        <label>Contact</label>
        <input type="text" name="contact" value="<%=c.getContact()%>">
    </div>

    <button class="btn btn-primary">Mettre à jour</button>

</form>

</div>

</body>
</html>