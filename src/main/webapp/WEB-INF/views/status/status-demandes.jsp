<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.forage.model.*" %>

<%
    List<Demande> demandes = (List<Demande>) request.getAttribute("demandes");
%>

<html>
<head>
    <title>Demandes par Status</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

<div class="sidebar">
    <h2>FORAGE</h2>

    <a href="/dashboard">Dashboard</a>
    <a href="/clients">Clients</a>
    <a href="/demandes">Demandes</a>
    <a href="/devis">Devis</a>
</div>

<div class="main">
<div class="container">

<h1>Demandes</h1>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Client</th>
            <th>Lieu</th>
            <th>District</th>
        </tr>
    </thead>

    <tbody>

    <%
    if(demandes != null && !demandes.isEmpty()){
        for(Demande d : demandes){
    %>

        <tr onclick="goToDemande(<%=d.getId()%>)" style="cursor:pointer;">
            <td><%= d.getId() %></td>
            <td><%= d.getClient().getNom() %></td>
            <td><%= d.getLieu() %></td>
            <td><%= d.getDistrict() %></td>
        </tr>

    <%
        }
    } else {
    %>
        <tr>
            <td colspan="4">Aucune demande trouvée</td>
        </tr>
    <%
    }
    %>

    </tbody>
</table>

</div>
</div>

<script>
function goToDemande(id){
    window.location = "/demandes/status?demande=" + id;
}
</script>

</body>
</html>