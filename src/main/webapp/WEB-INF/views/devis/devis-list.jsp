<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.forage.model.*" %>

<%
    List<Devis> devisList = (List<Devis>) request.getAttribute("devisList");
%>

<html>
<head>
    <title>Liste Devis</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

<div class="sidebar">
        <h2>FORAGE</h2>
        <a href="/dashboard">Dashboard</a>
        <a href="/clients">Clients</a>
        <a href="/demandes">Demandes</a>
        <a href="/devis" class="active">Devis</a>
        <a href="/devis/search">Voir Devis</a>
        <a href="/status">Status</a>
        <a href="/demandes/status">Demande Status</a>
</div>

<div class="main">
<div class="container">

<h1>Liste des Devis</h1>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Demande</th>
            <th>Client</th>
            <th>Type</th>
            <th>Date</th>
        </tr>
    </thead>

    <tbody>
        <%
        for(Devis d : devisList){
        %>
        <tr onclick="window.location='/devis/<%=d.getId()%>'" style="cursor:pointer;">
            <td><%= d.getId() %></td>
            <td><%= d.getDemande().getId() %></td>
            <td><%= d.getDemande().getClient().getNom() %></td>
            <td><%= d.getType().getLibelle() %></td>
            <td><%= d.getDate() %></td>
        </tr>
        <%
        }
        %>
    </tbody>
</table>

</div>
</div>

</body>
</html>