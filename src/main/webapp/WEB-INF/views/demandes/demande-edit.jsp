<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.forage.model.Demande" %>
<%@ page import="com.example.forage.model.Client" %>

<%
Demande d = (Demande) request.getAttribute("demande");
List<Client> clients = (List<Client>) request.getAttribute("clients");
%>

<html>
<head>
    <title>Modifier Demande</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

<div class="container">

<h1>Modifier Demande</h1>

<form method="post" action="/demandes/update">

    <input type="hidden" name="id" value="<%=d.getId()%>">

    <div class="form-group">
        <label>Client</label>
        <select name="client">
        <% for(Client c : clients){ %>
            <option value="<%=c.getId()%>"
                <%= c.getId().equals(d.getClient().getId()) ? "selected" : "" %>>
                <%=c.getNom()%>
            </option>
        <% } %>
        </select>
    </div>

    <div class="form-group">
        <label>Lieu</label>
        <input type="text" name="lieu" value="<%=d.getLieu()%>">
    </div>

    <div class="form-group">
        <label>District</label>
        <input type="text" name="district" value="<%=d.getDistrict()%>">
    </div>

    <button class="btn btn-primary">Mettre à jour</button>

</form>

</div>

</body>
</html>