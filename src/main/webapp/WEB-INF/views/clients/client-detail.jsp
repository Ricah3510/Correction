<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.forage.model.*" %>

<%
    Client client = (Client) request.getAttribute("client");
    List<Demande> demandes = (List<Demande>) request.getAttribute("demandes");
    Map<Integer, String> statusMap = (Map<Integer, String>) request.getAttribute("statusMap");
%>

<html>
<head>
    <title>Détail Client</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

<div class="sidebar">

    <h2>FORAGE</h2>
            <a href="/dashboard">Dashboard</a>
            <a href="/clients" class="active">Clients</a>
            <a href="/demandes">Demandes</a>
            <a href="/devis">Devis</a>
            <a href="/devis/search">Voir Devis</a>
            <a href="/status">Status</a>
            <a href="/demandes/status">Demande Status</a>
</div>

<div class="main">
<div class="container">

<h1>Détail Client</h1>

<div class="card">
    <p><b>Nom :</b> <%= client.getNom() %></p>
    <p><b>Contact :</b> <%= client.getContact() %></p>
</div>

<hr>

<h2>Demandes</h2>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Lieu</th>
            <th>District</th>
            <th>Status actuel</th>
        </tr>
    </thead>

    <tbody>
        <%
        for(Demande d : demandes){
            String status = statusMap.get(d.getId());
        %>
        <tr>
            <td><%= d.getId() %></td>
            <td><%= d.getLieu() %></td>
            <td><%= d.getDistrict() %></td>
            <td><%= status != null ? status : "Aucun" %></td>
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