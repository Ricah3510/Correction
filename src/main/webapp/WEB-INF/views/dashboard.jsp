<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.forage.dto.*" %>


<%
    List stats = (List) request.getAttribute("stats");

    String labels = "";
    String data = "";
    String ids = "";

    for (Object o : stats) {
        StatusStatDTO s = (StatusStatDTO) o;

        labels += "'" + s.getLibelle() + "',";
        data += s.getCount() + ",";
        ids += s.getId() + ",";
    }
%>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

<div class="sidebar">
    <h2>FORAGE</h2>

    <a href="/dashboard" class="active">Dashboard</a>
    <a href="/clients">Clients</a>
    <a href="/demandes">Demandes</a>
    <a href="/devis">Devis</a>
    <a href="/devis/search">Voir Devis</a>
    <a href="/status">Status</a>
    <a href="/demandes/status">Demande Status</a>
</div>

<div class="main">
<div class="container">

    <h1>Dashboard</h1>

    <div class="cards">

        <div class="card">
            <h3>Chiffre d'affaire</h3>
            <p><%= request.getAttribute("ca") %> Ar</p>
        </div>

        <div class="card" onclick="window.location='/clients'" style="cursor:pointer;">
            <h3>Nombre de clients</h3>
            <p><%= request.getAttribute("nbClients") %></p>
            <%-- <a href="/clients">Details</a> --%>
        </div>

        <div class="card" onclick="window.location='/devis/list'" style="cursor:pointer;">
            <h3>Nombre de devis</h3>
            <p><%= request.getAttribute("nbDevis") %></p>
            <%-- <a href="/devis/list">Details</a> --%>
        </div>

    </div>
    <hr>

<h2>Statistiques par Status</h2>

<div class="chart-container">
    <canvas id="myChart"></canvas>
</div>

<%-- <script src="https://cdn.jsdelivr.net/npm/chart.js"></script> --%>
    <script src="/js/chart.js"></script>

<script>
const ctx = document.getElementById('myChart');

const labelsArr = [<%=labels%>];
const idsArr = [<%=ids%>];

new Chart(ctx, {
    type: 'pie',
    data: {
        labels: labelsArr,
        datasets: [{
            data: [<%=data%>]
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        onClick: (e, elements) => {
            if(elements.length > 0){
                const index = elements[0].index;
                const statusId = idsArr[index];

                window.location = "/dashboard/status/" + statusId;
            }
        }
    }
});
</script>

</div>
</div>

</body>
</html>