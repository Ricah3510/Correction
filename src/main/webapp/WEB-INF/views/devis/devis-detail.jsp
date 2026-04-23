<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.example.forage.model.*" %>

<%
    Devis devis = (Devis) request.getAttribute("devis");
    List<DetailsDevis> details = (List<DetailsDevis>) request.getAttribute("details");

    BigDecimal total = BigDecimal.ZERO;
%>

<html>
<head>
    <title>Détail Devis</title>
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

<h1>Détail Devis</h1>

<div class="card">
    <p><b>ID :</b> <%= devis.getId() %></p>
    <p><b>Demande :</b> <%= devis.getDemande().getId() %></p>
    <p><b>Client :</b> <%= devis.getDemande().getClient().getNom() %></p>
    <p><b>Type :</b> <%= devis.getType().getLibelle() %></p>
    <p><b>Date :</b> <%= devis.getDate() %></p>
</div>

<hr>

<h2>Détails</h2>

<table>
    <thead>
        <tr>
            <th>Motif</th>
            <th>Qte</th>
            <th>PU</th>
            <th>Montant</th>
        </tr>
    </thead>

    <tbody>
        <%
        for(DetailsDevis d : details){

            BigDecimal montant = d.getQte().multiply(d.getPu());
            total = total.add(montant);
        %>
        <tr>
            <td><%= d.getLibelle() %></td>
            <td><%= d.getQte() %></td>
            <td><%= d.getPu() %></td>
            <td><%= montant %></td>
        </tr>
        <%
        }
        %>
    </tbody>
</table>

<h3>Total : <%= total %></h3>

</div>
</div>

</body>
</html>